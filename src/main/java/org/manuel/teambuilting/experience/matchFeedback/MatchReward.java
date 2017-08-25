package org.manuel.teambuilting.experience.matchFeedback;

import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 * @since 16/07/2017.
 */
@Getter
public enum MatchReward {

    MAN_OF_THE_MATCH("best player"), NOT_HIS_BEST_DAY("could have done better");

    private final String title;

    MatchReward(final String title) {
        this.title = title;
    }
}
