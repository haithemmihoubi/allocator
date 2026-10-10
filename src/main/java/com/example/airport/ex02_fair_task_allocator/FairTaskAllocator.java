package com.example.airport.ex02_fair_task_allocator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FairTaskAllocator {

    /**
     * See exercises/ex02-fair-task-allocator/README.md for the full rule set.
     * TODO: implement.
     */
    public static Optional<Team> assign(List<Team> teams, Task task) {

        // validation
        if (task.start == null || task.end == null || task.requiredQualifications.isEmpty() || teams.isEmpty()) {
            throw new IllegalArgumentException();
        }




        // A team is free if the new task
        // does not overlap any task the team already has.


        return Optional.empty();
    }

    public static class Team {
        private final int id;
        private final Set<String> qualifications;
        private final List<Task> tasks;

        public Team(int id, Set<String> qualifications, List<Task> tasks) {
            this.id = id;
            this.qualifications = qualifications;
            this.tasks = new ArrayList<>(tasks);
        }

        public int getId() {
            return id;
        }

        public Set<String> getQualifications() {
            return qualifications;
        }

        /**
         * Mutable on purpose: assigning a task means adding it here.
         */
        public List<Task> getTasks() {
            return tasks;
        }
    }

    public static class Task {
        private final String id;
        private final LocalDateTime start;
        private final LocalDateTime end;
        private final Set<String> requiredQualifications;

        public Task(String id, LocalDateTime start, LocalDateTime end, Set<String> requiredQualifications) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.requiredQualifications = requiredQualifications;
        }

        public String getId() {
            return id;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public Set<String> getRequiredQualifications() {
            return requiredQualifications;
        }

        public long durationMinutes() {
            return Duration.between(start, end).toMinutes();
        }
    }
}
