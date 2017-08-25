package org.manuel.teambuilting.experience.comments;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.services.query.PlayerDependentQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.Collection;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@Service
@AllArgsConstructor
public class PlayerCommentQueryService implements PlayerDependentQueryService<PlayerComment> {

    private final PlayerCommentRepository repository;

    @Override
    public Collection<PlayerComment> findByPlayerId(final BigInteger playerId) {
        Assert.notNull(playerId, "The id of the player cannot be null");
        return repository.findByPlayerId(playerId);
    }
}
