package org.manuel.teambuilting.experience.utils;

import org.manuel.teambuilting.experience.model.TimeSlice;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author manuel.doncel.martos
 * @since 11-3-2017
 */
public class ExperienceUtil {

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
