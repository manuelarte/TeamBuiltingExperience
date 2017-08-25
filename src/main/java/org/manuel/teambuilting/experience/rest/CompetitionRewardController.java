package org.manuel.teambuilting.experience.rest;

import org.manuel.teambuilting.experience.model.enums.CompetitionReward;
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
public class CompetitionRewardController {


    @RequestMapping(method = RequestMethod.GET)
    public Set<CompetitionReward> getAllRewards() {
        return Arrays.stream(CompetitionReward.values()).collect(Collectors.toSet());
    }

}
