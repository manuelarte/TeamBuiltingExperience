package org.manuel.teambuilting.experience.rewards;

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
@RequestMapping("/rewards")
public class RewardController {


    @RequestMapping(method = RequestMethod.GET)
    public Set<Reward> getCommentsFor() {
        return Arrays.stream(Reward.values()).collect(Collectors.toSet());
    }

}
