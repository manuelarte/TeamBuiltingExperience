package org.manuel.teambuilting.experience.matchFeedback;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final MatchFeedbackToIncomingMatchFeedbackDtoTransformer transformerBack;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public IncomingMatchFeedbackDto saveMatchFeedbackForMatch(@Valid @RequestBody final IncomingMatchFeedbackDto matchFeedback) {
        return transformerBack.apply(commandService.save(transformer.apply(matchFeedback)));
    }

}
