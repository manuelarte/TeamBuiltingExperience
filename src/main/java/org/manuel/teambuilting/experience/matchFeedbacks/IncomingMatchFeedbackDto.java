package org.manuel.teambuilting.experience.matchFeedbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = IncomingMatchFeedbackDto.IncomingMatchFeedbackDtoBuilder.class)
@Immutable
@Document
@Data
@Builder
@AllArgsConstructor
public class IncomingMatchFeedbackDto {

    private final String id;

    /**
     * The teamId of the team that the player was playing when receiving the reward vote
     */
    @NotNull
    @Indexed
    private final String matchId;

    /**
     * If the feedback is anonymous
     */
    private final Boolean anonymous;

    /**
     * Players and their ratings
     */
    private Map<String, Double> ratings;

    private Map<String, MatchReward> rewards;

    @AssertFalse
    public boolean ratingsBetweenRange() {
        final Optional<Map.Entry<String, Double>> first = ratings.entrySet().stream().filter(entry -> entry.getValue() < 0 || entry.getValue() > 5).findFirst();
        return first.isPresent();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class IncomingMatchFeedbackDtoBuilder {

    }

}