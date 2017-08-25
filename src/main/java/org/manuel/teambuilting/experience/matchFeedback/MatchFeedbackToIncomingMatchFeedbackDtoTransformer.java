package org.manuel.teambuilting.experience.matchFeedback;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@Component
@AllArgsConstructor
public class MatchFeedbackToIncomingMatchFeedbackDtoTransformer {

    public IncomingMatchFeedbackDto apply(final MatchFeedback input) {
        return IncomingMatchFeedbackDto.builder().id(input.getId()).matchId(input.getMatchId())
                .anonymous(input.getAnonymous()).ratings(input.getRatings()).rewards(input.getRewards()).build();
    }

}
