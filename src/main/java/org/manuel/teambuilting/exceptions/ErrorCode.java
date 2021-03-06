package org.manuel.teambuilting.exceptions;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */

public enum ErrorCode {

	ID_NOT_FOUND("0001", "Entity {0} with id {1} not found"),
	ENTITY_NOT_FOUND("0021", "Entity {0} not found"),
	PLAYER_DETAIL_FOR_SPORT_NOT_FOUND("0002", "The player with id {0} does not have details for sport {1}"),
	ENTRY_OVERLAPS("0003", "The entry {0} overlaps with previous entries"),
	SPORT_NOT_FOUND("0010", "Sport {0} not available"),
	SPORT_DETAIL_DUPLICATED("0011", "The Sport {0} already have an entry"),
	SPORT_DETAILS_FOR_PLAYER_ALREADY_REGISTERED("0011", "The player with id {0} already have details for sport {1}"),
	MATCH_FEEDBACK_ALREADY_GIVEN("0201", "The match with id {0} already have feedback from you");

	@Getter
	final String errorCode;
	final String message;

	ErrorCode(final String errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage(final Object... args) {
		return MessageFormat.format(message, args);
	}
}
