package org.manuel.teambuilting.experience.rewards;

import java.util.Date;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("/teams/rewards")
public class PlayerRewardController {

    private final PlayerRewardQueryService queryService;
    private final PlayerRewardCommandService commandService;

    @Inject
    public PlayerRewardController(final PlayerRewardQueryService queryService, final PlayerRewardCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @RequestMapping(path = "/{teamId}", method = RequestMethod.GET)
    public Set<PlayerReward> getRewardsFor(@PathVariable("teamId") final String teamId, @RequestParam(value="date") final Date date) {
        return queryService.getRewardsFor(teamId, date);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PlayerReward savePlayerReward(@Valid @RequestBody final PlayerReward playerReward) {
        return commandService.savePlayerReward(playerReward);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PlayerReward> deletePlayerReward(@PathVariable("id") final String id) {
        commandService.deletePlayerReward(id);
        return new ResponseEntity<PlayerReward>(HttpStatus.NO_CONTENT);
    }

}
