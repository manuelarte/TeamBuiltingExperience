package org.manuel.teambuilting.experience.rewards;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("experience/rewards")
public class RewardController {


    @RequestMapping(method = RequestMethod.GET)
    public Set<Reward> getAllRewards() {
        return Arrays.stream(Reward.values()).collect(Collectors.toSet());
    }

}
