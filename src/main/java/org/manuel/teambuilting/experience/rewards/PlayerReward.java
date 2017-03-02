package org.manuel.teambuilting.experience.rewards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;
import lombok.Data;
import org.manuel.teambuilting.experience.model.TimeSlice;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Immutable
@Document
@Data
public class PlayerReward implements TimeSlice {

    @Id
    private String id;

    /**
     * The user who voted the reward
     */
    @Indexed
    private String userId;

    /**
     * The teamId of the team that the player was playing when receiving the reward vote
     */
    @NotNull
    @Indexed
    private String teamId;

    /**
     * The id of the player who received the comment
     */
    @NotNull
    @Indexed
    private String playerId;

    /**
     * The reward that the user voted for the player
     */
    @NotNull
    private Reward reward;

    /* I can add here more information related to the reason like:
     * - Played with him, when we where playing in Devo, and the period
     * - Played against him, when he was playing at ... and at....
     * - Know him, because we used to go to the same school, etc.
     */

    /**
     * The holder of the comment
     */
    @Size(max = 200)
    private String comment;

    @NotNull
    @Past
    private Date fromDate;

    /**
     * Until when the reward is valid
     */
    @NotNull
    private Date toDate;

    @PersistenceConstructor
    public PlayerReward(final String userId, final String teamId, final String playerId, final Reward reward, final String comment,
        final Date fromDate, final Date toDate) {
        this.userId = userId;
        this.teamId = teamId;
        this.playerId = playerId;
        this.reward = reward;
        this.comment = comment;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public PlayerReward() {

    }

    @AssertTrue
    private boolean startDateBeforeEndDate() {
        return toDate.getTime() > fromDate.getTime();
    }

}