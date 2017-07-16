package org.manuel.teambuilting.experience.rewards;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
@AllArgsConstructor
public class PlayerRewardQueryService {

    private final PlayerRewardRepository repository;
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
