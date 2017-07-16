package org.manuel.teambuilting.experience.matchFeedbacks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MatchFeedbackQueryServiceTest {

    @Inject
    private MatchFeedbackQueryService matchFeedbackQueryService;

    @Inject
    private MatchFeedbackRepository matchFeedbackRepository;

    @Test
    public void testRetrieveAllMatchFeedbacksFromOneExistingMatch() {
        final String matchId = UUID.randomUUID().toString();
        final Map<String, Double> ratings = new HashMap<>();
        ratings.put("player1", 3D);
        ratings.put("player2", 5D);
        ratings.put("player3", 0D);
        final MatchFeedback matchFeedback = MatchFeedback.builder().anonymous(true).matchId(matchId).ratings(ratings).userId("userId").build();
        matchFeedbackRepository.save(matchFeedback);

        final Set<MatchFeedback> matchFeedbackForMatch = matchFeedbackQueryService.getMatchFeedbackForMatch(matchId);
        assertTrue(!Optional.ofNullable(matchFeedbackForMatch.iterator().next().getUserId()).isPresent());

    }

}
