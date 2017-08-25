package org.manuel.teambuilting.experience.comments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

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
    public Set<PlayerComment> getCommentsFor(final String playerId) {
        Assert.notNull(playerId);
        return repository.findByPlayerId(playerId);
    }
}
