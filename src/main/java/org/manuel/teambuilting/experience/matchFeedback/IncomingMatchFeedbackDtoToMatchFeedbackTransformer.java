package org.manuel.teambuilting.experience.matchFeedback;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.experience.utils.Util;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@Component
@AllArgsConstructor
public class IncomingMatchFeedbackDtoToMatchFeedbackTransformer {

    private final Util utils;

    /**
     * Transform the Incoming match feedback to MatchFeedback, by using the userId
     * @param input
     * @return
     */
    public MatchFeedback apply(final IncomingMatchFeedbackDto input) {
        final String userId = utils.getUserProfile().get().getUserId();
        final boolean anonymous = input.getAnonymous() != null ? input.getAnonymous() : false;
        return MatchFeedback.builder().id(input.getId()).userId(userId).matchId(input.getMatchId())
                .anonymous(anonymous).ratings(input.getRatings()).rewards(input.getRewards()).build();
    }

}
