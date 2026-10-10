package com.example.airport.ex01_2_tow_tractor_booking;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TowTractorBooking {

    /**
     * See exercises/ex01.2-tow-tractor-booking/README.md.
     * TODO: implement.
     */
    public static Optional<Tractor> book(List<Tractor> tractors, TimeSlot requested) {
        return tractors.stream()
                .filter(tractor -> isFree(tractor, requested))
                .min(Comparator.comparingInt(Tractor::getId));
    }

    // is free
    public static boolean isFree(Tractor tractor, TimeSlot requested) {
        return tractor.bookings.stream()
                .noneMatch(existing -> overlaps(existing, requested));
    }

    // overlaps
    private static boolean overlaps(TimeSlot a, TimeSlot b) {
        return a.start().isBefore(b.end()) && b.start().isBefore(a.end());
    }

    public record TimeSlot(LocalDateTime start, LocalDateTime end) {
    }

    public static class Tractor {
        private final int id;
        private final List<TimeSlot> bookings;

        public Tractor(int id, List<TimeSlot> bookings) {
            this.id = id;
            this.bookings = bookings;
        }

        public int getId() {
            return id;
        }

        public List<TimeSlot> getBookings() {
            return bookings;
        }
    }
}
