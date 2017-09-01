package org.manuel.teambuilting.experience.comments;

import com.auth0.Auth0User;

import java.util.Date;
import java.util.Optional;

import org.manuel.teambuilting.experience.utils.Util;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;

/**
 * @author manuel.doncel.martos
 * @since 1-9-2017.
 */
@Component
@AllArgsConstructor
public class IncomingPlayerCommentToPlayerCommentTransformer {

	private final Util utils;

	public PlayerComment apply(final IncomingPlayerCommentDto incomingPlayerCommentDto, final Date when) {
		Assert.notNull(incomingPlayerCommentDto, "The player comment cannot be null");
		Assert.notNull(when, "The date cannot be null");
		final Auth0User userProfile = utils.getUserProfile().get();
		return PlayerComment.builder().id(incomingPlayerCommentDto.getId()).playerId(incomingPlayerCommentDto.getPlayerId())
			.userId(userProfile.getUserId()).reason(incomingPlayerCommentDto.getReason()).comment(incomingPlayerCommentDto.getComment())
			.when(when).build();
	}
}
