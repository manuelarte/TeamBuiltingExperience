package org.manuel.teambuilting.experience.rewards;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
public class PlayerRewardCommandService {

    private final PlayerRewardRepository repository;

    @Inject
    public PlayerRewardCommandService(final PlayerRewardRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a comment received
     * @param playerReward
     * @return The comments received by the player
     */
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public PlayerReward savePlayerReward(final PlayerReward playerReward) {
        Assert.notNull(playerReward);
        Assert.isTrue(noOverlapping(playerReward), "A previous reward with an overlapping timeframe was found");
        return repository.save(playerReward);
    }

    private boolean noOverlapping(final PlayerReward playerReward) {
        final Set<PlayerReward> overlappingEntries = new HashSet<>();
        final Set<PlayerReward> votesForOnePlayer = repository
            .findByUserIdAndTeamIdAndPlayerIdAndReward(playerReward.getUserId(), playerReward.getTeamId(), playerReward.getPlayerId(), playerReward.getReward());

        votesForOnePlayer.forEach(entry -> {
            if (isOverlapping(entry, playerReward)) {
                overlappingEntries.add(entry);
            }
        });
        return overlappingEntries.isEmpty();
    }

    private boolean isOverlapping(final PlayerReward previousEntry, final PlayerReward newEntry) {
        // previous entry is not inside
        final boolean previousEntryIsInsideNewOne = newEntry.getFromDate().compareTo(previousEntry.getFromDate()) == 1 &&  newEntry.getToDate().compareTo(previousEntry.getToDate()) == -1;
        final boolean fromDateBetweenPreviousDates = (newEntry.getFromDate().compareTo(previousEntry.getFromDate()) == 0 || newEntry.getFromDate().after(previousEntry.getFromDate())) && newEntry.getFromDate().before(previousEntry.getToDate());
        final boolean toDateBetweenPreviousDates = newEntry.getToDate().after(previousEntry.getFromDate()) && newEntry.getToDate().before(previousEntry.getToDate());
        return previousEntryIsInsideNewOne || fromDateBetweenPreviousDates || toDateBetweenPreviousDates;
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerReward(final String id) {
        Assert.hasLength(id);
        repository.delete(id);
    }

}
