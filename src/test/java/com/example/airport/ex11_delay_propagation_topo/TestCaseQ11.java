package com.example.airport.ex11_delay_propagation_topo;

import com.example.airport.ex11_delay_propagation_topo.DelayPropagation.Dependency;
import com.example.airport.ex11_delay_propagation_topo.DelayPropagation.FlightLeg;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ11 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void legWithNoDependencies_departsOnSchedule() {
        FlightLeg leg = new FlightLeg("L1", D, 60, List.of());

        Map<String, LocalDateTime> result = DelayPropagation.computeActualDepartures(List.of(leg));

        assertEquals(D, result.get("L1"));
    }

    @Test
    public void delayedPredecessor_propagatesToSuccessor() {
        // L1 scheduled at 8:00, duration 180min (arrives 11:00) -- 3h "delay" baked into duration vs schedule window
        FlightLeg l1 = new FlightLeg("L1", D, 180, List.of());
        // L2 scheduled 9:00 but depends on L1 arriving + 30min turnaround => max(9:00, 11:00+30) = 11:30
        FlightLeg l2 = new FlightLeg("L2", D.plusHours(1), 60, List.of(new Dependency("L1", 30)));

        Map<String, LocalDateTime> result = DelayPropagation.computeActualDepartures(List.of(l1, l2));

        assertEquals(D, result.get("L1"));
        assertEquals(D.plusHours(3).plusMinutes(30), result.get("L2"));
    }

    @Test
    public void onTimePredecessor_successorDepartsOnOwnSchedule() {
        FlightLeg l1 = new FlightLeg("L1", D, 30, List.of());
        FlightLeg l2 = new FlightLeg("L2", D.plusHours(2), 60, List.of(new Dependency("L1", 10)));

        Map<String, LocalDateTime> result = DelayPropagation.computeActualDepartures(List.of(l1, l2));

        assertEquals(D.plusHours(2), result.get("L2"));
    }

    @Test
    public void multipleDependencies_latestOneWins() {
        FlightLeg l1 = new FlightLeg("L1", D, 60, List.of());               // arrives 9:00
        FlightLeg l2 = new FlightLeg("L2", D, 180, List.of());              // arrives 11:00
        FlightLeg l3 = new FlightLeg("L3", D, 30, List.of(
                new Dependency("L1", 10),  // 9:10
                new Dependency("L2", 10))); // 11:10 -> this one dominates

        Map<String, LocalDateTime> result = DelayPropagation.computeActualDepartures(List.of(l1, l2, l3));

        assertEquals(D.plusHours(3).plusMinutes(10), result.get("L3"));
    }

    @Test
    public void cycle_throws() {
        FlightLeg l1 = new FlightLeg("L1", D, 60, List.of(new Dependency("L2", 10)));
        FlightLeg l2 = new FlightLeg("L2", D, 60, List.of(new Dependency("L1", 10)));

        assertThrows(IllegalStateException.class, () -> DelayPropagation.computeActualDepartures(List.of(l1, l2)));
    }
}
