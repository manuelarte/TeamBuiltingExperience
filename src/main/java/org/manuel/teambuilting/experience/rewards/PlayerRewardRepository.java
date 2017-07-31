package org.manuel.teambuilting.experience.rewards;

import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Repository
public interface PlayerRewardRepository extends MongoRepository<PlayerReward, String>, PlayerDependentRepository {

    /**
     * Find all the rewards for one team between two dates
     * @param teamId
     * @param fromDate
     * @param toDate
     * @return
     */
    Set<PlayerReward> findByTeamIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(String teamId, Date fromDate, Date toDate);

    /**
     * Get all the timeframes for the vote of one user for one reward
     */
    Set<PlayerReward> findByUserIdAndTeamIdAndReward(String userId, String teamId, Reward reward);

}
