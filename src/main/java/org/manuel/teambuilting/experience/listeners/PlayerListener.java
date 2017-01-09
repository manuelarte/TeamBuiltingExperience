package org.manuel.teambuilting.experience.listeners;

import org.manuel.teambuilting.experience.comments.PlayerCommentRepository;
import org.manuel.teambuilting.experience.messages.PlayerDeletedMessage;
import org.manuel.teambuilting.experience.rewards.PlayerRewardRepository;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(durable = "true", value = "${messaging.event.amqp.queue}"),
        exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
        key = "player.#"))
@Configuration
public class PlayerListener {

    private final PlayerCommentRepository playerCommentRepository;
    private final PlayerRewardRepository playerRewardRepository;

    @Inject
    public PlayerListener(final PlayerCommentRepository playerCommentRepository, final PlayerRewardRepository playerRewardRepository) {
        this.playerCommentRepository = playerCommentRepository;
        this.playerRewardRepository = playerRewardRepository;
    }

    @RabbitHandler
    public void handle(final PlayerDeletedMessage message) {
        playerCommentRepository.delete(playerCommentRepository.findByPlayerId(message.getPlayer().getId()));
        playerRewardRepository.delete(playerRewardRepository.findByPlayerId(message.getPlayer().getId()));
    }

}
