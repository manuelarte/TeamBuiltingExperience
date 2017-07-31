package org.manuel.teambuilting.experience.listeners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.experience.comments.CommentReason;
import org.manuel.teambuilting.experience.comments.PlayerComment;
import org.manuel.teambuilting.experience.comments.PlayerCommentRepository;
import org.manuel.teambuilting.experience.model.Player;
import org.manuel.teambuilting.experience.rewards.PlayerReward;
import org.manuel.teambuilting.experience.rewards.PlayerRewardRepository;
import org.manuel.teambuilting.experience.rewards.Reward;
import org.manuel.teambuilting.messages.PlayerDeletedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness.InvocationData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author manuel.doncel.martos
 * @since 11-3-2017
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@RabbitListenerTest(capture = true)
public class PlayerListenerTest {

	// TODO, FIX BECAUSE THEY TAKE THE PRODUCTION VALUES, NOT THE TEST ONES
	@Value("${messaging.amqp.player.exchange.name}")
	private String playerExchange;

	@Inject
	private PlayerCommentRepository playerCommentRepository;

	@Inject
	private PlayerRewardRepository playerRewardRepository;

	@Inject
	private RabbitTemplate rabbitTemplate;

	@Inject
	RabbitListenerTestHarness harness;

	@Test
	public void deletePlayerTest() throws InterruptedException {
		final Player player = Player.builder().id(BigInteger.ONE).build();
		savePlayerComment(player);
		savePlayerReward(player);

		final PlayerDeletedEvent event = PlayerDeletedEvent.builder().playerId(player.getId()).date(new Date()).userId("userId").build();

		rabbitTemplate.convertAndSend(playerExchange, PlayerDeletedEvent.ROUTING_KEY, event);

		InvocationData data = harness.getNextInvocationDataFor(PlayerListener.LISTENER_ID, 5, TimeUnit.SECONDS);
		assertNotNull(data);
		assertEquals(1, data.getArguments().length);
		assertEquals(event, (data.getArguments()[0]));
		assertEquals(0, playerCommentRepository.findAll().size());
		assertEquals(0, playerRewardRepository.findAll().size());
	}

	private void savePlayerReward(final Player player) {
		final PlayerReward playerReward = PlayerReward.builder().playerId(player.getId()).reward(Reward.BEST_PLAYER).comment("Test").userId("userId")
			.teamId("teamId").fromDate(new Date()).toDate(new Date()).build();
		playerRewardRepository.save(playerReward);
	}

	private void savePlayerComment(final Player player) {
		final PlayerComment playerComment = PlayerComment.builder().playerId(player.getId()).reason(CommentReason.KNOW).comment("Test").userId("userId").when(new Date()).build();
		playerCommentRepository.save(playerComment);
	}

}
