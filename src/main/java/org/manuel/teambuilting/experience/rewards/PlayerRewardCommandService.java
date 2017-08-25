package org.manuel.teambuilting.experience.rewards;

import com.auth0.Auth0User;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.experience.utils.ExperienceUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
@AllArgsConstructor
public class PlayerRewardCommandService {

    private final PlayerRewardRepository repository;
    private final Util util;

    /**
     * Save a comment received
     * @param playerReward
     * @return The comments received by the player
     */
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public PlayerReward savePlayerReward(final PlayerReward playerReward) {
        Assert.notNull(playerReward, "PlayerReward cannot be null");
        Assert.isTrue(noOverlapping(playerReward), "A previous reward with an overlapping timeframe was found");
        final Auth0User user = util.getUserProfile().get();
        playerReward.setUserId(user.getUserId());
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
        return ExperienceUtil.getOverlappingEntries(playerReward, votesForOnePlayer).isEmpty();
    }

}
