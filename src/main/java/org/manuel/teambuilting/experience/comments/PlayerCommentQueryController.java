package org.manuel.teambuilting.experience.comments;

import java.math.BigInteger;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/experience/comments/players")
@AllArgsConstructor
public class PlayerCommentQueryController {

    private final PlayerCommentQueryService queryService;

    @GetMapping(path = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PlayerComment> getCommentsFor(@PathVariable("playerId") final BigInteger playerId, @PageableDefault final Pageable pageable) {
        return queryService.getCommentsFor(playerId, pageable);
    }

}
