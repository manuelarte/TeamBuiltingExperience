package org.manuel.teambuilting.experience.services.query.impl;

import org.manuel.teambuilting.core.services.query.AbstractQueryService;
import org.manuel.teambuilting.core.utils.Util;
import org.manuel.teambuilting.experience.repositories.MatchFeedbackRepository;
import org.manuel.teambuilting.experience.model.documents.MatchFeedback;
import org.manuel.teambuilting.experience.services.query.MatchFeedbackQueryService;
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
class MatchFeedbackQueryServiceImpl extends AbstractQueryService<MatchFeedback, String, MatchFeedbackRepository>
        implements MatchFeedbackQueryService {

    private final Util utils;

    public MatchFeedbackQueryServiceImpl(final MatchFeedbackRepository repository, final Util utils){
        super(repository);
        this.utils = utils;
    }

    /**
     * Get all the user feedback for one match
     * @param matchId
     * @return
     */
    @Override
    public Set<MatchFeedback> getMatchFeedbackForMatch(final String matchId) {
        Assert.notNull(matchId, "The match id cannot be null");
        return repository.findByMatchId(matchId);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public Optional<MatchFeedback> getMatchFeedbackForUserAndMatch(final String matchId) {
        Assert.notNull(matchId, "The match id cannot be null");
        final String userId = utils.getUserProfile().get().getUserId();
        return Optional.ofNullable(repository.findByUserIdAndMatchId(userId, matchId));
    }
}
