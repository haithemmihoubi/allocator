package com.example.airport.ex14_crew_pairing_backtracking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CrewPairing {

    /**
     * See exercises/ex14-crew-pairing-backtracking/README.md.
     * TODO: implement.
     */
    public static Optional<Map<String, String>> pairCrewToFlights(List<CrewMember> crew, List<FlightDuty> flights) {
        return null;
    }

    public record CrewMember(String id, Set<String> qualifications, List<FlightDuty> existingDuties) {
    }

    public record FlightDuty(String id, LocalDateTime start, LocalDateTime end, Set<String> requiredQualifications) {
    }
}
