package org.manuel.teambuilting.experience.comments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

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

    @GetMapping(path = "/{playerId}")
    public Collection<PlayerComment> getCommentsFor(@PathVariable("playerId") final BigInteger playerId) {
        return queryService.findByPlayerId(playerId);
    }

    @PostMapping
    public PlayerComment savePlayerComment(@Valid @RequestBody final PlayerComment playerComment) {
        Assert.isNull(playerComment.getId());
        return commandService.savePlayerComment(playerComment);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PlayerComment> deletePlayerComment(@PathVariable("id") final String id) {
        commandService.deletePlayerComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
