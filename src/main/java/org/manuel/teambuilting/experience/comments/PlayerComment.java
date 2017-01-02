package org.manuel.teambuilting.experience.comments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Manuel on 31/12/2016.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Immutable
@Document
@Data
@Builder
@NoArgsConstructor
public class PlayerComment {

    @Id
    private String id;

    /**
     * The user who made the comment
     */
    @NotNull
    @Indexed
    private String user_id;

    /**
     * The id of the player who received the comment
     */
    @NotNull
    @Indexed
    private String playerId;

    /**
     * The reason that the user knows the player
     */
    @NotNull
    private CommentReason reason;

    /* I can add here more information related to the reason like:
     * - Played with him, when we where playing in Devo, and the period
     * - Played against him, when he was playing at ... and at....
     * - Know him, because we used to go to the same school, etc.
     */

    /**
     * The holder of the comment
     */
    @NotNull
    @Size(min = 5)
    private String comment;

    @PersistenceConstructor
    public PlayerComment(final String user_id, final String playerId, final CommentReason reason, final String comment) {
        this.user_id = user_id;
        this.playerId = playerId;
        this.reason = reason;
        this.comment = comment;
    }

}