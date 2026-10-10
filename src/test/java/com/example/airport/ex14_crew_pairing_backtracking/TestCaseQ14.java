package com.example.airport.ex14_crew_pairing_backtracking;

import com.example.airport.ex14_crew_pairing_backtracking.CrewPairing.CrewMember;
import com.example.airport.ex14_crew_pairing_backtracking.CrewPairing.FlightDuty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ14 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void trivialSingleFlightSingleQualifiedCrew() {
        CrewMember crew = new CrewMember("C1", Set.of("PILOT"), List.of());
        FlightDuty flight = new FlightDuty("F1", D, D.plusHours(1), Set.of("PILOT"));

        Optional<Map<String, String>> result = CrewPairing.pairCrewToFlights(List.of(crew), List.of(flight));

        assertEquals(Optional.of(Map.of("F1", "C1")), result);
    }

    @Test
    public void naiveGreedyWouldFail_butValidAssignmentExists() {
        // C1 is the ONLY one qualified for F2. C2 is qualified for both F1 and F2.
        // A greedy left-to-right pass that gives F1 to C2 first would strand F2.
        CrewMember c1 = new CrewMember("C1", Set.of("MEDIC"), List.of());
        CrewMember c2 = new CrewMember("C2", Set.of("PILOT", "MEDIC"), List.of());

        FlightDuty f1 = new FlightDuty("F1", D, D.plusHours(1), Set.of("PILOT"));
        FlightDuty f2 = new FlightDuty("F2", D.plusHours(2), D.plusHours(3), Set.of("MEDIC"));

        Optional<Map<String, String>> result = CrewPairing.pairCrewToFlights(List.of(c1, c2), List.of(f1, f2));

        assertTrue(result.isPresent());
        assertEquals("C2", result.get().get("F1"));
        assertEquals("C1", result.get().get("F2"));
    }

    @Test
    public void insufficientQualifiedCrew_returnsEmpty() {
        CrewMember crew = new CrewMember("C1", Set.of("MEDIC"), List.of());
        FlightDuty flight = new FlightDuty("F1", D, D.plusHours(1), Set.of("PILOT"));

        assertTrue(CrewPairing.pairCrewToFlights(List.of(crew), List.of(flight)).isEmpty());
    }

    @Test
    public void preExistingDutyBlocksOverlappingAssignment() {
        FlightDuty existing = new FlightDuty("EXIST", D, D.plusHours(2), Set.of());
        CrewMember crew = new CrewMember("C1", Set.of("PILOT"), List.of(existing));
        FlightDuty flight = new FlightDuty("F1", D.plusMinutes(30), D.plusHours(1), Set.of("PILOT"));

        assertTrue(CrewPairing.pairCrewToFlights(List.of(crew), List.of(flight)).isEmpty());
    }

    @Test
    public void invalidFlightDuty_throws() {
        CrewMember crew = new CrewMember("C1", Set.of(), List.of());
        FlightDuty invalid = new FlightDuty("F1", D, D, Set.of());

        assertThrows(IllegalArgumentException.class,
                () -> CrewPairing.pairCrewToFlights(List.of(crew), List.of(invalid)));
    }
}
