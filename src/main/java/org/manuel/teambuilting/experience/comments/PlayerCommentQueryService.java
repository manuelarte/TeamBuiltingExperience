package org.manuel.teambuilting.experience.comments;

import org.manuel.teambuilting.core.services.query.PlayerDependentQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Collection;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
public class PlayerCommentQueryService implements PlayerDependentQueryService<PlayerComment> {

    private final PlayerCommentRepository repository;

    @Inject
    public PlayerCommentQueryService(final PlayerCommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<PlayerComment> findByPlayerId(final BigInteger playerId) {
        Assert.notNull(playerId, "The id of the player cannot be null");
        return repository.findByPlayerId(playerId);
    }
}
