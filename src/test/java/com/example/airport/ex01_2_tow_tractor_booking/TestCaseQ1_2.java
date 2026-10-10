package com.example.airport.ex01_2_tow_tractor_booking;

import com.example.airport.ex01_2_tow_tractor_booking.TowTractorBooking.TimeSlot;
import com.example.airport.ex01_2_tow_tractor_booking.TowTractorBooking.Tractor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_2 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void tractorWithNoBookings_isFree() {
        Tractor tractor = new Tractor(1, List.of());

        Optional<Tractor> result = TowTractorBooking.book(List.of(tractor), new TimeSlot(D, D.plusHours(1)));

        assertTrue(result.isPresent());
    }

    @Test
    public void overlappingBooking_excludesTractor() {
        Tractor busy = new Tractor(1, List.of(new TimeSlot(D, D.plusHours(2))));
        Tractor free = new Tractor(2, List.of());

        Optional<Tractor> result = TowTractorBooking.book(List.of(busy, free), new TimeSlot(D.plusMinutes(30), D.plusHours(1)));

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getId());
    }

    @Test
    public void touchingBoundary_isStillFree() {
        Tractor tractor = new Tractor(1, List.of(new TimeSlot(D, D.plusHours(1))));

        Optional<Tractor> result = TowTractorBooking.book(List.of(tractor), new TimeSlot(D.plusHours(1), D.plusHours(2)));

        assertTrue(result.isPresent());
    }

    @Test
    public void amongFreeTractors_smallestIdWins() {
        Tractor five = new Tractor(5, List.of());
        Tractor three = new Tractor(3, List.of());

        Optional<Tractor> result = TowTractorBooking.book(List.of(five, three), new TimeSlot(D, D.plusHours(1)));

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getId());
    }

    @Test
    public void noFreeTractor_returnsEmpty() {
        Tractor busy = new Tractor(1, List.of(new TimeSlot(D, D.plusHours(2))));

        Optional<Tractor> result = TowTractorBooking.book(List.of(busy), new TimeSlot(D.plusMinutes(30), D.plusHours(1)));

        assertTrue(result.isEmpty());
    }
}
