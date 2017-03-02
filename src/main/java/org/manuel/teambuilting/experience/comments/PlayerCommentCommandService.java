package org.manuel.teambuilting.experience.comments;

import com.auth0.spring.security.api.Auth0JWTToken;
import org.manuel.teambuilting.core.config.Auth0Client;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
public class PlayerCommentCommandService {

    private final Auth0Client auth0Client;
    private final PlayerCommentRepository repository;

    @Inject
    public PlayerCommentCommandService(final Auth0Client auth0Client, final PlayerCommentRepository repository) {
        this.auth0Client = auth0Client;
        this.repository = repository;
    }

    /**
     * Save a comment received
     * @param playerComment
     * @return The comments received by the player
     */
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public PlayerComment savePlayerComment(final PlayerComment playerComment) {
        Assert.notNull(playerComment);
        Assert.isNull(repository.findByUserIdAndPlayerId(playerComment.getUserId(), playerComment.getPlayerId()), "A previous comment was found");
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        playerComment.setUserId(auth0Client.getUser((Auth0JWTToken) auth).getId());
        return repository.save(playerComment);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerComment(final String id) {
        Assert.hasLength(id);
        repository.delete(id);
    }

}
