package org.manuel.teambuilting.experience.services.query;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.services.query.PlayerDependentQueryService;
import org.manuel.teambuilting.experience.model.documents.PlayerReward;
import org.manuel.teambuilting.experience.repositories.PlayerRewardRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
@AllArgsConstructor
public class PlayerCompetitionRewardQueryService implements PlayerDependentQueryService<PlayerReward> {

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

    @Override
    public Collection<PlayerReward> findByPlayerId(final BigInteger playerId) {
        Assert.notNull(playerId, "The id of the player cannot be null");
        return repository.findByPlayerId(playerId);
    }
}
