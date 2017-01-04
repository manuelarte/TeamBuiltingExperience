package org.manuel.teambuilting.experience.rewards;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
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
        Assert.isNull(repository.findByUserIdAndTeamIdAndPlayerIdAndFromDateLowerAndToDateGreaterOrEqualTo(playerReward.getUserId(), playerReward.getTeamId(), playerReward.getPlayerId(), playerReward.getFromDate(), playerReward.getToDate()), "A previous comment was found");
        return repository.save(playerReward);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerReward(final String id) {
        Assert.hasLength(id);
        repository.delete(id);
    }

}
