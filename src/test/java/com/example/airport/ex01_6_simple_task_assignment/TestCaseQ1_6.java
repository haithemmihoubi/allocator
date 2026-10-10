package com.example.airport.ex01_6_simple_task_assignment;

import com.example.airport.ex01_6_simple_task_assignment.SimpleTaskAssignment.Task;
import com.example.airport.ex01_6_simple_task_assignment.SimpleTaskAssignment.Worker;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_6 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void qualifiedAndFreeWorker_isFound() {
        Worker worker = new Worker(1, Set.of("WELD"), List.of());
        Task task = new Task(D, D.plusHours(1), Set.of("WELD"));

        assertTrue(SimpleTaskAssignment.findAssignable(List.of(worker), task).isPresent());
    }

    @Test
    public void unqualifiedWorker_isExcludedEvenIfFree() {
        Worker worker = new Worker(1, Set.of("PAINT"), List.of());
        Task task = new Task(D, D.plusHours(1), Set.of("WELD"));

        assertTrue(SimpleTaskAssignment.findAssignable(List.of(worker), task).isEmpty());
    }

    @Test
    public void busyWorker_isExcludedEvenIfQualified() {
        Task existing = new Task(D, D.plusHours(2), Set.of());
        Worker worker = new Worker(1, Set.of("WELD"), List.of(existing));
        Task task = new Task(D.plusMinutes(30), D.plusHours(1), Set.of("WELD"));

        assertTrue(SimpleTaskAssignment.findAssignable(List.of(worker), task).isEmpty());
    }

    @Test
    public void noRequiredSkills_everyWorkerQualifies() {
        Worker worker = new Worker(1, Set.of(), List.of());
        Task task = new Task(D, D.plusHours(1), Set.of());

        assertTrue(SimpleTaskAssignment.findAssignable(List.of(worker), task).isPresent());
    }

    @Test
    public void noQualifiedAndFreeWorker_returnsEmpty() {
        Task existing = new Task(D, D.plusHours(2), Set.of());
        Worker worker = new Worker(1, Set.of("WELD"), List.of(existing));
        Task task = new Task(D.plusMinutes(30), D.plusHours(1), Set.of("WELD"));

        Optional<Worker> result = SimpleTaskAssignment.findAssignable(List.of(worker), task);

        assertTrue(result.isEmpty());
    }
}
