package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.manuel.teambuilting.experience.model.Player;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Data
@AllArgsConstructor
@Builder
public class PlayerDeletedMessage {

    @NotNull
    private final Player player;

    @NotNull
    private final String userId;

    @NotNull
    private final Date date;

}
