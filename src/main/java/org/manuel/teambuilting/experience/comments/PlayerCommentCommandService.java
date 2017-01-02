package org.manuel.teambuilting.experience.comments;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
public class PlayerCommentCommandService {

    private final PlayerCommentRepository repository;

    @Inject
    public PlayerCommentCommandService(final PlayerCommentRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a comment received
     * @param playerComment
     * @return The comments received by the player
     */
    public PlayerComment savePlayerComment(final PlayerComment playerComment) {
        Assert.notNull(playerComment);
        Assert.isNull(repository.findByUserIdAndPlayerId(playerComment.getUserId(), playerComment.getPlayerId()), "A previous comment was found");
        return repository.save(playerComment);
    }
}
