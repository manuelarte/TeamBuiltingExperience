package org.manuel.teambuilting.experience.repositories;

import org.manuel.teambuilting.experience.model.documents.MatchFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Repository
public interface MatchFeedbackRepository extends MongoRepository<MatchFeedback, String> {

    /**
     * Find all the feedback for one match
     * @param matchId
     * @return
     */
    Set<MatchFeedback> findByMatchId(String matchId);

    /**
     * Return the feedback of the user for the desired match
     * @param userId
     * @param matchId
     * @return
     */
    MatchFeedback findByUserIdAndMatchId(String userId, String matchId);

}
