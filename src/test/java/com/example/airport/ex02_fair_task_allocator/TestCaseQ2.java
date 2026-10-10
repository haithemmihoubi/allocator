package com.example.airport.ex02_fair_task_allocator;

import com.example.airport.ex02_fair_task_allocator.FairTaskAllocator.Task;
import com.example.airport.ex02_fair_task_allocator.FairTaskAllocator.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Self-check tests for exercise 02, written from the README's rule list.
 * Not the official company grader — see the README for context.
 */
public class TestCaseQ2 {

    private static final LocalDateTime NOW = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void singleQualifiedAndFreeTeam_isAssigned() {
        Team team = new Team(1, Set.of("DEICE"), new ArrayList<>());
        Task task = new Task("T1", NOW, NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(team), task);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void unqualifiedTeamIsSkipped_qualifiedOneIsChosen() {
        Team unqualified = new Team(1, Set.of("FUEL"), new ArrayList<>());
        Team qualified = new Team(2, Set.of("DEICE", "FUEL"), new ArrayList<>());
        Task task = new Task("T1", NOW, NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(unqualified, qualified), task);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getId());
    }

    @Test
    public void busyTeamIsSkipped_freeOneIsChosen() {
        Task existing = new Task("EXIST", NOW, NOW.plusHours(2), Set.of());
        Team busy = new Team(1, Set.of("DEICE"), List.of(existing));
        Team free = new Team(2, Set.of("DEICE"), new ArrayList<>());
        Task task = new Task("T1", NOW.plusMinutes(30), NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(busy, free), task);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getId());
    }

    @Test
    public void amongQualifiedAndFree_lowestWorkloadWins() {
        Task heavy = new Task("H", NOW, NOW.plusHours(5), Set.of());
        Task light = new Task("L", NOW, NOW.plusMinutes(30), Set.of());
        Team heavyTeam = new Team(1, Set.of("DEICE"), List.of(heavy));
        Team lightTeam = new Team(2, Set.of("DEICE"), List.of(light));
        Task task = new Task("T1", NOW.plusHours(6), NOW.plusHours(7), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(heavyTeam, lightTeam), task);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getId());
    }

    @Test
    public void equalWorkload_smallestIdWins() {
        Team teamA = new Team(5, Set.of("DEICE"), new ArrayList<>());
        Team teamB = new Team(3, Set.of("DEICE"), new ArrayList<>());
        Task task = new Task("T1", NOW, NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(teamA, teamB), task);

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getId());
    }

    @Test
    public void taskWithNoRequiredQualifications_everyTeamQualifies() {
        Team team = new Team(1, Set.of(), new ArrayList<>());
        Task task = new Task("T1", NOW, NOW.plusHours(1), Set.of());

        Optional<Team> result = FairTaskAllocator.assign(List.of(team), task);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void chosenTeamGetsTheTaskAddedToItsList() {
        Team team = new Team(1, Set.of("DEICE"), new ArrayList<>());
        Task task = new Task("T1", NOW, NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(team), task);

        assertTrue(result.isPresent());
        assertTrue(result.get().getTasks().contains(task));
    }

    @Test
    public void noTeamQualifiedAndFree_returnsEmpty() {
        Task existing = new Task("EXIST", NOW, NOW.plusHours(2), Set.of());
        Team busy = new Team(1, Set.of("DEICE"), List.of(existing));
        Task task = new Task("T1", NOW.plusMinutes(30), NOW.plusHours(1), Set.of("DEICE"));

        Optional<Team> result = FairTaskAllocator.assign(List.of(busy), task);

        assertTrue(result.isEmpty());
    }

    @Test
    public void invalidTask_startNotBeforeEnd_throws() {
        Team team = new Team(1, Set.of(), new ArrayList<>());
        Task invalid = new Task("T1", NOW, NOW, Set.of());

        assertThrows(IllegalArgumentException.class, () -> FairTaskAllocator.assign(List.of(team), invalid));
    }

    @Test
    public void invalidTask_nullStart_throws() {
        Team team = new Team(1, Set.of(), new ArrayList<>());
        Task invalid = new Task("T1", null, NOW.plusHours(1), Set.of());

        assertThrows(IllegalArgumentException.class, () -> FairTaskAllocator.assign(List.of(team), invalid));
    }
}
