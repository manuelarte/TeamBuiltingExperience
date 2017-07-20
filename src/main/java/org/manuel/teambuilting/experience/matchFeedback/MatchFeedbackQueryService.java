package org.manuel.teambuilting.experience.matchFeedback;

import org.manuel.teambuilting.core.services.query.AbstractQueryService;
import org.manuel.teambuilting.experience.utils.Util;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
public class MatchFeedbackQueryService extends AbstractQueryService<MatchFeedback, String, MatchFeedbackRepository>{

    private final Util utils;

    public MatchFeedbackQueryService(final MatchFeedbackRepository repository, final Util utils){
        super(repository);
        this.utils = utils;
    }

    /**
     * Get all the user feedback for one match
     * @param matchId
     * @return
     */
    public Set<MatchFeedback> getMatchFeedbackForMatch(final String matchId) {
        Assert.notNull(matchId, "The match id cannot be null");
        return repository.findByMatchId(matchId);
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public Optional<MatchFeedback> getMatchFeedbackForUserAndMatch(final String matchId) {
        Assert.notNull(matchId, "The match id cannot be null");
        final String userId = utils.getUserProfile().get().getUserId();
        return Optional.ofNullable(repository.findByUserIdAndMatchId(userId, matchId));
    }
}
