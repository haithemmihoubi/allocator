package com.example.airport.ex01_1_ground_crew_dispatch;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GroundCrewDispatch {

    /**
     * See exercises/ex01.1-ground-crew-dispatch/README.md.
     * TODO: implement.
     */
    public static Optional<Team> dispatch(List<Team> teams, Set<String> requiredCertifications) {


        // works stream filter is sufficune t for theis problem
        return teams.stream().filter(team -> team.qualifications.containsAll(requiredCertifications))
                .min(Comparator.comparingInt(Team::id));
    }

    public record Team(int id, Set<String> qualifications) {
    }
}
