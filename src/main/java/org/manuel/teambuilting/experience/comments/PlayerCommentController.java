package org.manuel.teambuilting.experience.comments;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/experience/comments/players")
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

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PlayerComment> deletePlayerComment(@PathVariable("id") final String id) {
        commandService.deletePlayerComment(id);
        return new ResponseEntity<PlayerComment>(HttpStatus.NO_CONTENT);
    }

}
