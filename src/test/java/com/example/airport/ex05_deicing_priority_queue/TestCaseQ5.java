package com.example.airport.ex05_deicing_priority_queue;

import com.example.airport.ex05_deicing_priority_queue.DeicingQueue.DeicingRequest;
import com.example.airport.ex05_deicing_priority_queue.DeicingQueue.Priority;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ5 {

    @Test
    public void higherPriorityGoesFirstRegardlessOfArrival() {
        DeicingRequest low = new DeicingRequest("LOW1", Priority.LOW, 0);
        DeicingRequest emergency = new DeicingRequest("EM1", Priority.EMERGENCY, 1);

        List<String> order = DeicingQueue.processingOrder(List.of(low, emergency));

        assertEquals(List.of("EM1", "LOW1"), order);
    }

    @Test
    public void samePriority_earlierArrivalGoesFirst() {
        DeicingRequest second = new DeicingRequest("A", Priority.HIGH, 2);
        DeicingRequest first = new DeicingRequest("B", Priority.HIGH, 1);

        List<String> order = DeicingQueue.processingOrder(List.of(second, first));

        assertEquals(List.of("B", "A"), order);
    }

    @Test
    public void allFourPriorityLevels_sortedCorrectly() {
        DeicingRequest low = new DeicingRequest("LOW", Priority.LOW, 0);
        DeicingRequest medium = new DeicingRequest("MED", Priority.MEDIUM, 0);
        DeicingRequest high = new DeicingRequest("HIGH", Priority.HIGH, 0);
        DeicingRequest emergency = new DeicingRequest("EM", Priority.EMERGENCY, 0);

        List<String> order = DeicingQueue.processingOrder(List.of(low, high, emergency, medium));

        assertEquals(List.of("EM", "HIGH", "MED", "LOW"), order);
    }

    @Test
    public void emptyInput_returnsEmptyList() {
        assertTrue(DeicingQueue.processingOrder(List.of()).isEmpty());
    }

    @Test
    public void singleRequest_returnsItself() {
        DeicingRequest only = new DeicingRequest("ONLY", Priority.MEDIUM, 0);
        assertEquals(List.of("ONLY"), DeicingQueue.processingOrder(List.of(only)));
    }

    @Test
    public void invalidRequest_negativeSequence_throws() {
        DeicingRequest invalid = new DeicingRequest("X", Priority.LOW, -1);
        assertThrows(IllegalArgumentException.class, () -> DeicingQueue.processingOrder(List.of(invalid)));
    }
}
