package org.manuel.teambuilting.experience.listeners;

import org.manuel.teambuilting.experience.comments.PlayerCommentRepository;
import org.manuel.teambuilting.experience.rewards.PlayerRewardRepository;
import org.manuel.teambuilting.messages.PlayerDeletedMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
@RabbitListener(id = PlayerListener.LISTENER_ID, bindings = @QueueBinding(
        value = @Queue(durable = "true", value = "${messaging.amqp.player.queue.name}"),
        exchange = @Exchange(durable = "true", value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC),
        key = "player.deleted"))
@Component
public class PlayerListener {

    public static final String LISTENER_ID = "PlayerListenerId";

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