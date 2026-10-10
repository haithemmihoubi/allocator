package com.example.airport.ex07_runway_two_pointer_conflict;

import com.example.airport.ex07_runway_two_pointer_conflict.RunwayTwoPointerConflict.Conflict;
import com.example.airport.ex07_runway_two_pointer_conflict.RunwayTwoPointerConflict.Operation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ7 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    @Test
    public void noConflicts_returnsEmpty() {
        List<Operation> arrivals = List.of(new Operation("A1", D, D.plusMinutes(10)));
        List<Operation> departures = List.of(new Operation("D1", D.plusMinutes(10), D.plusMinutes(20)));

        assertTrue(RunwayTwoPointerConflict.firstConflict(arrivals, departures).isEmpty());
    }

    @Test
    public void oneClearConflict_isFound() {
        List<Operation> arrivals = List.of(new Operation("A1", D, D.plusMinutes(10)));
        List<Operation> departures = List.of(new Operation("D1", D.plusMinutes(5), D.plusMinutes(15)));

        Optional<Conflict> result = RunwayTwoPointerConflict.firstConflict(arrivals, departures);

        assertTrue(result.isPresent());
        assertEquals(new Conflict("A1", "D1"), result.get());
    }

    @Test
    public void earliestConflictIsReturned_whenMultipleExist() {
        List<Operation> arrivals = List.of(
                new Operation("A1", D, D.plusMinutes(10)),
                new Operation("A2", D.plusMinutes(30), D.plusMinutes(40)));
        List<Operation> departures = List.of(
                new Operation("D1", D.plusMinutes(5), D.plusMinutes(15)),
                new Operation("D2", D.plusMinutes(35), D.plusMinutes(45)));

        Optional<Conflict> result = RunwayTwoPointerConflict.firstConflict(arrivals, departures);

        assertTrue(result.isPresent());
        assertEquals(new Conflict("A1", "D1"), result.get());
    }

    @Test
    public void touchingBoundary_isNotAConflict() {
        List<Operation> arrivals = List.of(new Operation("A1", D, D.plusMinutes(10)));
        List<Operation> departures = List.of(new Operation("D1", D.plusMinutes(10), D.plusMinutes(20)));

        assertTrue(RunwayTwoPointerConflict.firstConflict(arrivals, departures).isEmpty());
    }

    @Test
    public void emptyLists_returnEmpty() {
        assertTrue(RunwayTwoPointerConflict.firstConflict(List.of(), List.of()).isEmpty());
    }

    @Test
    public void invalidOperation_throws() {
        List<Operation> arrivals = List.of(new Operation("A1", D, D));
        assertThrows(IllegalArgumentException.class, () -> RunwayTwoPointerConflict.firstConflict(arrivals, List.of()));
    }
}
