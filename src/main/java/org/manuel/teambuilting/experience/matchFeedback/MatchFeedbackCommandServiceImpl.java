package org.manuel.teambuilting.experience.matchFeedback;

import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.experience.utils.Util;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.manuel.teambuilting.core.exceptions.ErrorCode.MATCH_FEEDBACK_ALREADY_GIVEN;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@Service
class MatchFeedbackCommandServiceImpl extends AbstractCommandService<MatchFeedback, String, MatchFeedbackRepository>
        implements MatchFeedbackCommandService{

    private final Util util;

    public MatchFeedbackCommandServiceImpl(final MatchFeedbackRepository repository, final Util util) {
        super(repository);
        this.util = util;
    }

    @Override
    protected void beforeSave(final MatchFeedback matchFeedback) {
        // check no previous entry for that match
        final String userId = matchFeedback.getUserId();
        final Optional<MatchFeedback> feedbackForTheMatch = Optional.ofNullable(repository.findByUserIdAndMatchId(userId, matchFeedback.getMatchId()));
        if (feedbackForTheMatch.isPresent() && !feedbackForTheMatch.get().getId().equals(matchFeedback.getId())) {
            throw new ValidationRuntimeException(MATCH_FEEDBACK_ALREADY_GIVEN, matchFeedback.getMatchId());
        }
    }

}
