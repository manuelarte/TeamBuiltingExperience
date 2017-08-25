package org.manuel.teambuilting.experience.rest;

import org.manuel.teambuilting.experience.model.enums.CommentReason;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/experience/comments/reasons")
public class CommentReasonController {


    @GetMapping
    public Set<CommentReason> getCommentsFor() {
        return Arrays.stream(CommentReason.values()).collect(Collectors.toSet());
    }

}
