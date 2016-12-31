package org.manuel.teambuilting.experience.comments;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Repository
public interface PlayerCommentRepository extends MongoRepository<PlayerComment, String> {

    /**
     * Find all the comments made by one user
     * @param user_id
     * @return
     */
    Set<PlayerComment> findByUserId(String user_id);

    /**
     * Find all the comments received for one player
     * @param playerId
     * @return
     */
    Set<PlayerComment> findByPlayerId(String playerId);

    /**
     * Find the comment done by one user to one player
     * @param user_id
     * @param playerId
     * @return
     */
    PlayerComment findByUserIdAndPlayerId(String user_id, String playerId);
}
