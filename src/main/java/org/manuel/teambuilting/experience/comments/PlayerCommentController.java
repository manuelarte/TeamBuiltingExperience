package org.manuel.teambuilting.experience.comments;

import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/comments/players")
public class PlayerCommentController {

    private final PlayerCommentQueryService queryService;
    private final PlayerCommentCommandService commandService;

    @Inject
    public PlayerCommentController(final PlayerCommentQueryService queryService, final PlayerCommentCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @RequestMapping(path = "/{playerId}", method = RequestMethod.GET)
    public Set<PlayerComment> getCommentsFor(@PathVariable("playerId") final String playerId) {
        return queryService.getCommentsFor(playerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PlayerComment savePlayerComment(@Valid @RequestBody final PlayerComment playerComment) {
        return commandService.savePlayerComment(playerComment);
    }

}
