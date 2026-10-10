package com.example.airport.ex04_minimum_gates_needed;

import com.example.airport.ex04_minimum_gates_needed.MinimumGatesNeeded.FlightWindow;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ4 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    @Test
    public void emptySchedule_needsZeroGates() {
        assertEquals(0, MinimumGatesNeeded.minimumGatesNeeded(List.of()));
    }

    @Test
    public void nonOverlappingFlights_needOneGate() {
        List<FlightWindow> flights = List.of(
                new FlightWindow(D.plusHours(1), D.plusHours(2)),
                new FlightWindow(D.plusHours(2), D.plusHours(3)));

        assertEquals(1, MinimumGatesNeeded.minimumGatesNeeded(flights));
    }

    @Test
    public void allOverlapping_needOneGatePerFlight() {
        List<FlightWindow> flights = List.of(
                new FlightWindow(D.plusHours(1), D.plusHours(5)),
                new FlightWindow(D.plusHours(2), D.plusHours(6)),
                new FlightWindow(D.plusHours(3), D.plusHours(7)));

        assertEquals(3, MinimumGatesNeeded.minimumGatesNeeded(flights));
    }

    @Test
    public void classicMixedSchedule() {
        List<FlightWindow> flights = List.of(
                new FlightWindow(D.plusHours(0), D.plusHours(2)),
                new FlightWindow(D.plusHours(1), D.plusHours(3)),
                new FlightWindow(D.plusHours(2), D.plusHours(4)),
                new FlightWindow(D.plusHours(5), D.plusHours(6)));

        assertEquals(2, MinimumGatesNeeded.minimumGatesNeeded(flights));
    }

    @Test
    public void unsortedInput_isHandledCorrectly() {
        List<FlightWindow> flights = List.of(
                new FlightWindow(D.plusHours(5), D.plusHours(6)),
                new FlightWindow(D.plusHours(0), D.plusHours(2)),
                new FlightWindow(D.plusHours(1), D.plusHours(3)));

        assertEquals(2, MinimumGatesNeeded.minimumGatesNeeded(flights));
    }

    @Test
    public void invalidWindow_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> MinimumGatesNeeded.minimumGatesNeeded(List.of(new FlightWindow(D, D))));
    }
}
