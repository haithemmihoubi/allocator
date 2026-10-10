package com.example.airport.ex07_runway_two_pointer_conflict;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RunwayTwoPointerConflict {

    /**
     * See exercises/ex07-runway-two-pointer-conflict/README.md.
     * TODO: implement.
     */
    public static Optional<Conflict> firstConflict(List<Operation> arrivals, List<Operation> departures) {
        return null;
    }

    public record Operation(String flightId, LocalDateTime start, LocalDateTime end) {
    }

    public record Conflict(String arrivalFlightId, String departureFlightId) {
    }
}
