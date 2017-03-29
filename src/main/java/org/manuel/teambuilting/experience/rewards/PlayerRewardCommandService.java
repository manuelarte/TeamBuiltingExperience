package org.manuel.teambuilting.experience.rewards;

import com.auth0.spring.security.api.Auth0JWTToken;

import java.util.Set;

import javax.inject.Inject;

import org.manuel.teambuilting.experience.config.Auth0Client;
import org.manuel.teambuilting.experience.utils.Util;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
public class PlayerRewardCommandService {

    private final Auth0Client auth0Client;
    private final PlayerRewardRepository repository;

    @Inject
    public PlayerRewardCommandService(final Auth0Client auth0Client, final PlayerRewardRepository repository) {
        this.auth0Client = auth0Client;
        this.repository = repository;
    }

    /**
     * Save a comment received
     * @param playerReward
     * @return The comments received by the player
     */
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public PlayerReward savePlayerReward(final PlayerReward playerReward) {
        Assert.notNull(playerReward);
        Assert.isTrue(noOverlapping(playerReward), "A previous reward with an overlapping timeframe was found");
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        playerReward.setUserId(auth0Client.getUser((Auth0JWTToken) auth).getId());
        return repository.save(playerReward);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public void deletePlayerReward(final String id) {
        Assert.hasLength(id);
        repository.delete(id);
    }

    private boolean noOverlapping(final PlayerReward playerReward) {
        final Set<PlayerReward> votesForOnePlayer = repository
            .findByUserIdAndTeamIdAndReward(playerReward.getUserId(), playerReward.getTeamId(), playerReward.getReward());
        return Util.getOverlappingEntries(playerReward, votesForOnePlayer).isEmpty();
    }

}
