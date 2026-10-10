package com.example.airport.ex10_fastest_ground_transfer_dijkstra;

import com.example.airport.ex10_fastest_ground_transfer_dijkstra.FastestGroundTransfer.Walkway;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ10 {

    @Test
    public void directPathIsCheaperThanMultihop() {
        List<Walkway> walkways = List.of(
                new Walkway("A", "B", 5),
                new Walkway("A", "X", 1),
                new Walkway("X", "B", 10));

        assertEquals(Optional.of(5), FastestGroundTransfer.fastestTransferMinutes(walkways, "A", "B"));
    }

    @Test
    public void multihopIsCheaperThanDirectPath() {
        List<Walkway> walkways = List.of(
                new Walkway("A", "B", 20),
                new Walkway("A", "X", 2),
                new Walkway("X", "B", 3));

        assertEquals(Optional.of(5), FastestGroundTransfer.fastestTransferMinutes(walkways, "A", "B"));
    }

    @Test
    public void unreachableGate_returnsEmpty() {
        List<Walkway> walkways = List.of(new Walkway("A", "B", 5), new Walkway("C", "D", 5));

        assertTrue(FastestGroundTransfer.fastestTransferMinutes(walkways, "A", "D").isEmpty());
    }

    @Test
    public void sameGate_returnsZero() {
        List<Walkway> walkways = List.of(new Walkway("A", "B", 5));

        assertEquals(Optional.of(0), FastestGroundTransfer.fastestTransferMinutes(walkways, "A", "A"));
    }

    @Test
    public void negativeWeight_throws() {
        List<Walkway> walkways = List.of(new Walkway("A", "B", -1));

        assertThrows(IllegalArgumentException.class,
                () -> FastestGroundTransfer.fastestTransferMinutes(walkways, "A", "B"));
    }
}
