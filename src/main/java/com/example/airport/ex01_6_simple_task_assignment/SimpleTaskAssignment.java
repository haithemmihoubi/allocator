package com.example.airport.ex01_6_simple_task_assignment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SimpleTaskAssignment {

    /**
     * See exercises/ex01.6-simple-task-assignment/README.md.
     * TODO: implement.
     */
    public static Optional<Worker> findAssignable(List<Worker> workers, Task task) {
        return null;
    }

    public record Task(LocalDateTime start, LocalDateTime end, Set<String> requiredSkills) {
    }

    public static class Worker {
        private final int id;
        private final Set<String> skills;
        private final List<Task> tasks;

        public Worker(int id, Set<String> skills, List<Task> tasks) {
            this.id = id;
            this.skills = skills;
            this.tasks = tasks;
        }

        public int getId() {
            return id;
        }

        public Set<String> getSkills() {
            return skills;
        }

        public List<Task> getTasks() {
            return tasks;
        }
    }
}
