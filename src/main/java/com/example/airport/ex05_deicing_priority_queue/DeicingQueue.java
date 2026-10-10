package com.example.airport.ex05_deicing_priority_queue;

import java.util.List;

public class DeicingQueue {

    public enum Priority {
        LOW, MEDIUM, HIGH, EMERGENCY
    }

    /**
     * See exercises/ex05-deicing-priority-queue/README.md.
     * TODO: implement.
     */
    public static List<String> processingOrder(List<DeicingRequest> requests) {
        return null;
    }

    public record DeicingRequest(String flightId, Priority priority, int arrivalSequence) {
    }
}
