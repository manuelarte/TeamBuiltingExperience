package org.manuel.teambuilting.experience.matchFeedbacks;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("/experience/matchFeedbacks")
@AllArgsConstructor
public class MatchFeedbackQueryController {

    private final MatchFeedbackQueryService queryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<MatchFeedback> getMatchFeedbacksForMatch(@RequestParam final String matchId) {
        return queryService.getMatchFeedbackForMatch(matchId);
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public MatchFeedback getMatchFeedbackForUserAndMatch(@RequestParam final String matchId) {
        return queryService.getMatchFeedbackForUserAndMatch(matchId);
    }

}
