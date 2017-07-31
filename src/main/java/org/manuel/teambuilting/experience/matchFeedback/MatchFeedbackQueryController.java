package org.manuel.teambuilting.experience.matchFeedback;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.exceptions.ErrorCode;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("/experience/matchFeedback")
@AllArgsConstructor
public class MatchFeedbackQueryController {

    private final MatchFeedbackQueryService queryService;
    private final MatchFeedbackToIncomingMatchFeedbackDtoTransformer transformer;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MatchFeedback>> getMatchFeedbackForMatch(@RequestParam final String matchId) {
        return ResponseEntity.ok(queryService.getMatchFeedbackForMatch(matchId));
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomingMatchFeedbackDto> getMatchFeedbackForUserAndMatch(@RequestParam final String matchId) {
        final Optional<MatchFeedback> matchFeedbackForUserAndMatch = queryService.getMatchFeedbackForUserAndMatch(matchId);
        if (matchFeedbackForUserAndMatch.isPresent()) {
            return ResponseEntity.ok(transformer.apply(matchFeedbackForUserAndMatch.get()));
        }
        throw new ValidationRuntimeException(ErrorCode.ENTITY_NOT_FOUND, MatchFeedback.class.getSimpleName());
    }

    @GetMapping(path = "/rewards")
    public ResponseEntity<Set<MatchReward>> getMatchRewardsForSport(@RequestParam final String sport) {
        return ResponseEntity.ok(Arrays.stream(MatchReward.values()).collect(Collectors.toSet()));
    }

}
