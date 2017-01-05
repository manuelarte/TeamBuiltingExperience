package org.manuel.teambuilting.experience.rewards;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author manuel.doncel.martos
 * @since 5-1-2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerRewardCommandServiceTest {

	@Inject
	private PlayerRewardCommandService commandService;

	@Inject
	private PlayerRewardRepository repository;

	@BeforeClass
	public static void beforeClass() {
		setSecurityContext();
	}

	@After
	public void after() {
		repository.deleteAll();
	}

	@Test
	public void testCanSavePlayerAwardsAfterAPreviousRecordNoOverlapping() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * --|-----|-------- t
		 * FD_P  TD_P        previousOne
		 *
		 * ---------|-----|-- t
		 *       FD_N  TD_N  newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";

		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date fromDateN = changeDate(toDateP, +1, Calendar.DAY_OF_MONTH);
		final Date toDateN = changeDate(fromDateN, +1, Calendar.YEAR);

		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));
		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}


	@Test
	public void testCanSavePlayerAwardsAfterAPreviousRecordNoOverlappingRightAfter() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * --|-----|-------- t
		 * FD_P  TD_P        previousOne
		 *
		 * --------|-----|-- t
		 *       FD_N  TD_N  newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";

		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date fromDateN = toDateP;
		final Date toDateN = changeDate(fromDateN, +1, Calendar.YEAR);

		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));
		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}

	@Test
	public void testCanSavePlayerAwardsBeforeAPreviousRecordNoOverlapping() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * --------|-----|-- t
		 *       FD_P  TD_P  previousOne
		 *
		 * --|-----|-------- t
		 * FD_N  TD_N        newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";

		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date toDateN = fromDateP;
		final Date fromDateN = changeDate(toDateN, -1, Calendar.YEAR);


		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));
		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePlayerAwardFromDateAfterFromDatePreviousOneAndToDateBeforeToDatePreviousOneSameUserSamePlayerSameRewardCannotBeDone() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * -----|------------|---- t
		 *    FD_P         TD_P    previousOne
		 *
		 * --------|-------|------ t
		 *       FD_N    TD_N      newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";
		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date fromDateN = changeDate(fromDateP, +1, Calendar.MONTH);
		final Date toDateN = changeDate(toDateP, -1, Calendar.MONTH);

		repository.save(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));

		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePlayerAwardFromDateBeforeFromDatePreviousOneAndToDateBeforeToDatePreviousOneSameUserSamePlayerSameRewardCannotBeDone() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * -----|------------|----  t
		 *    FD_P          TD_P    previousOne
		 *
		 * --|-------------|------- t
		 * FD_N          TD_N       newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";

		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date fromDateN = changeDate(fromDateP, -1, Calendar.MONTH);
		final Date toDateN = changeDate(toDateP, -1, Calendar.MONTH);

		repository.save(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));

		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePlayerAwardFromDateAfterFromDatePreviousOneAndToDateAfterToDatePreviousOneSameUserSamePlayerSameRewardCannotBeDone() {
		/*
		 * Same User votes to the same player with the same reward in a different time frame
		 *
		 * -----|-------------|---- t
		 *    FD_P          TD_P    previousOne
		 *
		 * --------|------------|-- t
		 *       FD_N         TD_N  newOne
		 */
		final String userId = "userId";
		final String teamId = "teamId";
		final String playerId = "playerId";
		final Reward reward = Reward.BEST_GOAL;
		final String comment = "";

		final Date toDateP = new Date();
		final Date fromDateP = changeDate(toDateP, -1, Calendar.YEAR);

		final Date fromDateN = changeDate(fromDateP, +1, Calendar.MONTH);
		final Date toDateN = changeDate(toDateP, +1, Calendar.MONTH);

		repository.save(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateP, toDateP));

		commandService.savePlayerReward(new PlayerReward(userId, teamId, playerId, reward, comment, fromDateN, toDateN));
	}

	private Date changeDate(final Date date, final int number, final int calendarField ) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, number);
		return cal.getTime();
	}

	private static void setSecurityContext() {
		final SecurityContext securityContext = new SecurityContext() {
			@Override
			public Authentication getAuthentication() {
				return new Authentication() {
					@Override
					public Collection<? extends GrantedAuthority> getAuthorities() {
						return Arrays.asList(new SimpleGrantedAuthority("user"), new SimpleGrantedAuthority("admin"));
					}

					@Override
					public Object getCredentials() {
						return null;
					}

					@Override
					public Object getDetails() {
						return null;
					}

					@Override
					public Object getPrincipal() {
						return null;
					}

					@Override
					public boolean isAuthenticated() {
						return true;
					}

					@Override
					public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {

					}

					@Override
					public String getName() {
						return null;
					}
				};
			}

			@Override
			public void setAuthentication(final Authentication authentication) {
			}
		};
		SecurityContextHolder.setContext(securityContext);
	}
}
