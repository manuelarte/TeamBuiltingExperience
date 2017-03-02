package org.manuel.teambuilting.experience.rewards;

import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
public class PlayerRewardCommandService {

    private final PlayerRewardRepository repository;
    private final Auth0Client auth0Client;

    @Inject
    public PlayerRewardCommandService(final Auth0Client auth0Client, final PlayerRewardRepository repository) {
        this.auth0Client = auth0Client;
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
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        playerReward.setUserId(auth0Client.getUser((Auth0JWTToken) auth).getId());
        return repository.save(playerReward);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerReward(final String id) {
        Assert.hasLength(id);
        repository.delete(id);
    }

    private boolean noOverlapping(final PlayerReward playerReward) {
        final Set<PlayerReward> overlappingEntries = new HashSet<>();
        final Set<PlayerReward> votesForOnePlayer = repository
            .findByUserIdAndTeamIdAndReward(playerReward.getUserId(), playerReward.getTeamId(), playerReward.getReward());

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

}
