package org.manuel.teambuilting.experience.utils;

import com.auth0.Auth0Client;
import com.auth0.Auth0User;
import com.auth0.Tokens;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.experience.model.TimeSlice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author manuel.doncel.martos
 * @since 11-3-2017
 */
@Component
@AllArgsConstructor
public class Util {

	private final Auth0Client auth0Client;

	public Optional<Auth0User> getUserProfile() {
		Optional<Auth0User> toReturn = Optional.empty();
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AuthenticationJsonWebToken) {
			final String token = ((AuthenticationJsonWebToken) auth).getToken();
			toReturn = Optional.of(auth0Client.getUserProfile(new Tokens(token, null, "JWT", null)));
		}
		return toReturn;
	}

	public static <T extends TimeSlice>  Set<T> getOverlappingEntries(final T newEntry, final Collection<T> collection) {
		final Set<T> overlappingEntries = new HashSet<>();
		collection.forEach(entry -> {
			if (isOverlapping(entry, newEntry)) {
				overlappingEntries.add(entry);
			}
		});
		return overlappingEntries;
	}

	public static <T extends TimeSlice> boolean isOverlapping(final T previousEntry, final T newEntry) {
		// previous entry is not inside
		final boolean previousEntryIsInsideNewOne = newEntry.getFromDate().compareTo(previousEntry.getFromDate()) == 1 &&  newEntry.getToDate().compareTo(previousEntry.getToDate()) == -1;
		final boolean fromDateBetweenPreviousDates = (newEntry.getFromDate().compareTo(previousEntry.getFromDate()) == 0 || newEntry.getFromDate().after(previousEntry.getFromDate())) && newEntry.getFromDate().before(previousEntry.getToDate());
		final boolean toDateBetweenPreviousDates = newEntry.getToDate().after(previousEntry.getFromDate()) && newEntry.getToDate().before(previousEntry.getToDate());
		return previousEntryIsInsideNewOne || fromDateBetweenPreviousDates || toDateBetweenPreviousDates;
	}

}
