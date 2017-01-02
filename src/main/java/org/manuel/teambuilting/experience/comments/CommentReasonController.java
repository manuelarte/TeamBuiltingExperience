package org.manuel.teambuilting.experience.comments;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RestController
@RequestMapping("/comments/reasons")
public class CommentReasonController {


    @RequestMapping(method = RequestMethod.GET)
    public Set<CommentReason> getCommentsFor() {
        return Arrays.stream(CommentReason.values()).collect(Collectors.toSet());
    }

}
