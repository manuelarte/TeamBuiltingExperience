package org.manuel.teambuilting.experience.rewards;

import java.util.Date;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
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
     * @param date
     * @return The rewards of the team in that date
     */
    public Set<PlayerReward> getRewardsForTeam(final String teamId, final Date date) {
        Assert.notNull(teamId);
        return repository.findByTeamIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(teamId, date, date);
    }

    public Set<PlayerReward> getRewardsForPlayer(final String playerId) {
        Assert.notNull(playerId);
        return repository.findByPlayerId(playerId);
    }
}
