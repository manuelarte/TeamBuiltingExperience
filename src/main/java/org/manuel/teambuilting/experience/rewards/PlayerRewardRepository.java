package org.manuel.teambuilting.experience.rewards;

import java.util.Date;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Repository
public interface PlayerRewardRepository extends MongoRepository<PlayerReward, String> {

    /**
     * Find all the rewards for one team between two dates
     * @param teamId
     * @param fromDate
     * @param toDate
     * @return
     */
    Set<PlayerReward> findByTeamIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(String teamId, Date fromDate, Date toDate);

    Set<PlayerReward> findByPlayerId(String playerId);

    /**
     * Get all the timeframes for the vote of one user for one reward
     */
    Set<PlayerReward> findByUserIdAndTeamIdAndReward(String userId, String teamId, Reward reward);

}
