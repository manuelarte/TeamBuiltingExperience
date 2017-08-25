package org.manuel.teambuilting.experience.rewards;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author Manuel Doncel Martos
 * @since 04/01/2016.
 */
@RestController
@RequestMapping("/experience/rewards")
@AllArgsConstructor
public class PlayerRewardController {

    private final PlayerRewardQueryService queryService;
    private final PlayerRewardCommandService commandService;

    @GetMapping(path = "/teams/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<PlayerReward> getRewardsForTeam(@PathVariable("teamId") final String teamId, @RequestParam(value="date") final Date date) {
        return queryService.getRewardsForTeam(teamId, date);
    }

    @GetMapping(path = "/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PlayerReward> getRewardsForPlayer(@PathVariable("playerId") final BigInteger playerId) {
        return queryService.findByPlayerId(playerId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerReward savePlayerReward(@Valid @RequestBody final PlayerReward playerReward) {
        Assert.isNull(playerReward.getUserId());
        return commandService.savePlayerReward(playerReward);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PlayerReward> deletePlayerReward(@PathVariable("id") final String id) {
        commandService.deletePlayerReward(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
