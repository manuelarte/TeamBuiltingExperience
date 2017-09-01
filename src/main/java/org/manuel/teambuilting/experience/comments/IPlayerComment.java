package org.manuel.teambuilting.experience.comments;

import java.util.Date;

import org.manuel.teambuilting.core.model.PlayerDependentEntity;

/**
 * @author Manuel Doncel Martos
 * @since 2017-09-01.
 */
public interface IPlayerComment extends PlayerDependentEntity {

	/**
	 * The reason that the user knows the player
	 */
	CommentReason getReason();

	/**
	 * The actual comment
	 */
	String getComment();

}
