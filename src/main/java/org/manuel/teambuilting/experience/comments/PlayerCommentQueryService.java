package org.manuel.teambuilting.experience.comments;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
@AllArgsConstructor
public class PlayerCommentQueryService {

    private final PlayerCommentRepository repository;

    /**
     * Get all the comments received by one player
     * @param playerId
     * @return The comments received by the player
     */
    public Page<PlayerComment> getCommentsFor(final BigInteger playerId, final Pageable pageable) {
        Assert.notNull(playerId, "The player id cannot be null");
        return repository.getByPlayerId(playerId, pageable);
    }
}
