package org.manuel.teambuilting.experience.comments;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Repository
public interface PlayerCommentRepository extends MongoRepository<PlayerComment, String> {

    /**
     * Find all the comments made by one user
     * @param userId
     * @return
     */
    Set<PlayerComment> findByUserId(String userId);

    /**
     * Find all the comments received for one player
     * @param playerId
     * @return
     */
    Set<PlayerComment> findByPlayerId(String playerId);

    /**
     * Find the comment done by one user to one player
     * @param userId
     * @param playerId
     * @return
     */
    PlayerComment findByUserIdAndPlayerId(String userId, String playerId);
}
