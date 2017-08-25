package org.manuel.teambuilting.experience.services.query;

import org.manuel.teambuilting.core.services.query.BaseQueryService;
import org.manuel.teambuilting.experience.model.documents.MatchFeedback;

import java.util.Optional;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
public interface MatchFeedbackQueryService extends BaseQueryService<MatchFeedback, String> {

    /**
     * Get all the user feedback for one match
     * @param matchId
     * @return
     */
    Set<MatchFeedback> getMatchFeedbackForMatch(String matchId);

    Optional<MatchFeedback> getMatchFeedbackForUserAndMatch(String matchId);
}
