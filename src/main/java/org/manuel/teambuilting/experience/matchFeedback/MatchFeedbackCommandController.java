package org.manuel.teambuilting.experience.matchFeedback;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("experience/matchFeedback")
@AllArgsConstructor
public class MatchFeedbackCommandController {

    private final MatchFeedbackCommandService commandService;
    private final IncomingMatchFeedbackDtoToMatchFeedbackTransformer transformer;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MatchFeedback saveMatchFeedbackForMatch(@Valid @RequestBody final IncomingMatchFeedbackDto matchFeedback) {
        return commandService.save(transformer.apply(matchFeedback));
    }

}