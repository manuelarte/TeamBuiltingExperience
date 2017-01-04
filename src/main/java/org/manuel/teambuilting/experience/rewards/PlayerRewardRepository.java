package org.manuel.teambuilting.experience.rewards;

import java.util.Date;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
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
    Set<PlayerReward> findByUserIdAndFromDateGreaterOrEqualToAndToDateLowerOrEqualTo(String teamId, Date fromDate, Date toDate);

    // test this
    PlayerReward findByUserIdAndTeamIdAndPlayerIdAndFromDateLowerAndToDateGreaterOrEqualTo(String userId, String teamId, String playerId, Date fromDate, Date toDate);

}
