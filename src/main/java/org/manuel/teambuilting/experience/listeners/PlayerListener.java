package org.manuel.teambuilting.experience.listeners;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.experience.repositories.PlayerCommentRepository;
import org.manuel.teambuilting.experience.repositories.PlayerRewardRepository;
import org.manuel.teambuilting.messages.PlayerDeletedEvent;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
@RabbitListener(id = PlayerListener.LISTENER_ID, bindings = @QueueBinding(
        value = @Queue(value = "${messaging.amqp.player.queue.name}",
            durable = "${messaging.amqp.player.queue.durable}", autoDelete = "${messaging.amqp.player.queue.autodelete}"),
        exchange = @Exchange(value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC,
            durable = "${messaging.amqp.player.exchange.durable}", autoDelete = "${messaging.amqp.player.exchange.autodelete}"),
        key = "${messaging.amqp.player.queue.binding}"))
@Component
@AllArgsConstructor
public class PlayerListener {

    public static final String LISTENER_ID = "PlayerListenerId";

    private final PlayerCommentRepository playerCommentRepository;
    private final PlayerRewardRepository playerRewardRepository;

    @RabbitHandler
    public void handle(final PlayerDeletedEvent event) {
        playerCommentRepository.delete(playerCommentRepository.findByPlayerId(event.getPlayerId()));
        playerRewardRepository.delete(playerRewardRepository.findByPlayerId(event.getPlayerId()));
    }

}