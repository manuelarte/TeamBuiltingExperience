package org.manuel.teambuilting.experience.rest;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.experience.model.documents.PlayerComment;
import org.manuel.teambuilting.experience.services.command.PlayerCommentCommandService;
import org.manuel.teambuilting.experience.services.query.PlayerCommentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/experience/comments/players")
@AllArgsConstructor
public class PlayerCommentController {

    private final PlayerCommentQueryService queryService;
    private final PlayerCommentCommandService commandService;

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
