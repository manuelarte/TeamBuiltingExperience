package org.manuel.teambuilting.experience.repositories;

import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.manuel.teambuilting.experience.model.documents.PlayerComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Repository
public interface PlayerCommentRepository extends MongoRepository<PlayerComment, String>, PlayerDependentRepository {

    /**
     * Find all the comments made by one user
     * @param userId
     * @return
     */
    Set<PlayerComment> findByUserId(String userId);

    /**
     * Find the comment done by one user to one player
     * @param userId
     * @param playerId
     * @return
     */
    PlayerComment findByUserIdAndPlayerId(String userId, BigInteger playerId);
}
