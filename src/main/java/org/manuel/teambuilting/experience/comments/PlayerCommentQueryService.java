package org.manuel.teambuilting.experience.comments;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
public class PlayerCommentQueryService {

    private final PlayerCommentRepository repository;

    @Inject
    public PlayerCommentQueryService(final PlayerCommentRepository repository) {
        this.repository = repository;
    }

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
