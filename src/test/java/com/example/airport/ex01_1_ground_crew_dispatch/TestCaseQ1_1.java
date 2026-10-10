package com.example.airport.ex01_1_ground_crew_dispatch;

import com.example.airport.ex01_1_ground_crew_dispatch.GroundCrewDispatch.Team;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_1 {

    @Test
    public void qualifiedTeamIsDispatched() {
        Team team = new Team(1, Set.of("FUEL", "DEICE"));

        Optional<Team> result = GroundCrewDispatch.dispatch(List.of(team), Set.of("FUEL"));

        assertTrue(result.isPresent());
        assertEquals(1, result.get().id());
    }

    @Test
    public void unqualifiedTeamIsSkipped() {
        Team unqualified = new Team(1, Set.of("FUEL"));
        Team qualified = new Team(2, Set.of("FUEL", "DEICE"));

        Optional<Team> result = GroundCrewDispatch.dispatch(List.of(unqualified, qualified), Set.of("DEICE"));

        assertTrue(result.isPresent());
        assertEquals(2, result.get().id());
    }

    @Test
    public void amongQualified_smallestIdWins() {
        Team teamFive = new Team(5, Set.of("FUEL"));
        Team teamThree = new Team(3, Set.of("FUEL"));

        Optional<Team> result = GroundCrewDispatch.dispatch(List.of(teamFive, teamThree), Set.of("FUEL"));

        assertTrue(result.isPresent());
        assertEquals(3, result.get().id());
    }

    @Test
    public void noRequirement_anyTeamQualifies() {
        Team team = new Team(1, Set.of());

        Optional<Team> result = GroundCrewDispatch.dispatch(List.of(team), Set.of());

        assertTrue(result.isPresent());
    }

    @Test
    public void noQualifiedTeam_returnsEmpty() {
        Team team = new Team(1, Set.of("FUEL"));

        Optional<Team> result = GroundCrewDispatch.dispatch(List.of(team), Set.of("DEICE"));

        assertTrue(result.isEmpty());
    }
}
