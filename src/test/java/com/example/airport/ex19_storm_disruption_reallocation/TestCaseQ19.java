package com.example.airport.ex19_storm_disruption_reallocation;

import com.example.airport.ex19_storm_disruption_reallocation.StormDisruptionReallocation.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ19 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void simpleCase_allFlightsFit() {
        Stand stand = new Stand(1, Set.of("WIDEBODY"), List.of());
        FlightRequest flight = new FlightRequest("F1", Set.of("WIDEBODY"), D, D.plusHours(4), 60);

        Optional<Map<String, StandAssignment>> result =
                StormDisruptionReallocation.reallocate(List.of(stand), List.of(flight));

        assertTrue(result.isPresent());
        assertEquals(1, result.get().get("F1").standId());
        assertEquals(D, result.get().get("F1").start());
    }

    @Test
    public void capabilityMismatch_excludesStandEvenIfFree() {
        Stand stand = new Stand(1, Set.of("NARROWBODY"), List.of());
        FlightRequest flight = new FlightRequest("F1", Set.of("WIDEBODY"), D, D.plusHours(4), 60);

        Optional<Map<String, StandAssignment>> result =
                StormDisruptionReallocation.reallocate(List.of(stand), List.of(flight));

        assertTrue(result.isEmpty());
    }

    @Test
    public void flightThatCannotFitAnywhere_failsTheWholeRun() {
        Stand stand = new Stand(1, Set.of("WIDEBODY"),
                List.of(new StormDisruptionReallocation.TimeWindow(D, D.plusHours(4))));
        FlightRequest flight = new FlightRequest("F1", Set.of("WIDEBODY"), D, D.plusHours(4), 60);

        Optional<Map<String, StandAssignment>> result =
                StormDisruptionReallocation.reallocate(List.of(stand), List.of(flight));

        assertTrue(result.isEmpty());
    }

    @Test
    public void secondCompetingFlight_getsPushedToNextFreeSlotOnOnlyCapableStand() {
        Stand stand = new Stand(1, Set.of("WIDEBODY"), List.of());
        FlightRequest first = new FlightRequest("F1", Set.of("WIDEBODY"), D, D.plusHours(4), 60);
        FlightRequest second = new FlightRequest("F2", Set.of("WIDEBODY"), D, D.plusHours(4), 60);

        Optional<Map<String, StandAssignment>> result =
                StormDisruptionReallocation.reallocate(List.of(stand), List.of(first, second));

        assertTrue(result.isPresent());
        assertEquals(D, result.get().get("F1").start());
        assertEquals(D.plusHours(1), result.get().get("F2").start());
    }

    @Test
    public void invalidFlightRequest_throws() {
        Stand stand = new Stand(1, Set.of(), List.of());
        FlightRequest invalid = new FlightRequest("F1", Set.of(), D, D, 60);

        assertThrows(IllegalArgumentException.class,
                () -> StormDisruptionReallocation.reallocate(List.of(stand), List.of(invalid)));
    }
}
