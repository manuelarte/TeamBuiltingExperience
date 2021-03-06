package org.manuel.teambuilting.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@Data
public class ExceptionMessage {

	private final HttpStatus status;

	private final String errorCode;

	private final String message;

	private final String detailedMessage;

}
