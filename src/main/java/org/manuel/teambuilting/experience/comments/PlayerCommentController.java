package org.manuel.teambuilting.experience.comments;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/experience/comments/players")
@AllArgsConstructor
public class PlayerCommentController {

    private final PlayerCommentCommandService commandService;
    private final IncomingPlayerCommentToPlayerCommentTransformer transformer;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerComment savePlayerComment(@Valid @RequestBody final IncomingPlayerCommentDto playerComment) {
        return commandService.savePlayerComment(transformer.apply(playerComment, new Date()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PlayerComment> deletePlayerComment(@PathVariable("id") final String id) {
        commandService.deletePlayerComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
