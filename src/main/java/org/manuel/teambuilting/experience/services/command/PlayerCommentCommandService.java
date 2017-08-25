package org.manuel.teambuilting.experience.services.command;

import com.auth0.Auth0User;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.experience.model.documents.PlayerComment;
import org.manuel.teambuilting.experience.repositories.PlayerCommentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
@AllArgsConstructor
public class PlayerCommentCommandService {

    private final PlayerCommentRepository repository;
    private final Util util;

    /**
     * Save a comment received
     * @param playerComment
     * @return The comments received by the player
     */
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public PlayerComment savePlayerComment(final PlayerComment playerComment) {
        Assert.notNull(playerComment, "The player comment cannot be null");
        Assert.isNull(repository.findByUserIdAndPlayerId(playerComment.getUserId(), playerComment.getPlayerId()), "A previous comment was found");
        final Auth0User user = util.getUserProfile().get();
        playerComment.setUserId(user.getUserId());
        return repository.save(playerComment);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerComment(final String id) {
        Assert.hasLength(id, "The id cannot be null");
        repository.delete(id);
    }

}
