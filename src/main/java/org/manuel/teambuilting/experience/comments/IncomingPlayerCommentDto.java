package org.manuel.teambuilting.experience.comments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manuel Doncel Martos
 * @author 2017-09-01.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Data
@AllArgsConstructor
@Builder
public class IncomingPlayerCommentDto implements IPlayerComment {

    private final String id;

    /**
     * The id of the player who received the comment
     */
    @NotNull
    private final BigInteger playerId;

    /**
     * The reason that the user knows the player
     */
    @NotNull
    private final CommentReason reason;

    /**
     * The holder of the comment
     */
    @NotNull
    @Size(min = 5, max = 200)
    private final String comment;

	@JsonPOJOBuilder(withPrefix = "")
	public static final class IncomingPlayerCommentDtoBuilder {
	}

}