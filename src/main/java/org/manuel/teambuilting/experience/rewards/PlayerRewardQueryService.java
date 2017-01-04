package org.manuel.teambuilting.experience.rewards;

import java.util.Date;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
public class PlayerRewardQueryService {

    private final PlayerRewardRepository repository;

    @Inject
    public PlayerRewardQueryService(final PlayerRewardRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all the rewards given to the members of the team
     * @param teamId
     * @return The comments received by the player
     */
    public Set<PlayerReward> getRewardsFor(final String teamId, final Date fromDate, final Date toDate) {
        Assert.notNull(teamId);
        return repository.findByUserIdAndFromDateGreaterOrEqualToAndToDateLowerOrEqualTo(teamId, fromDate, toDate);
    }
}
