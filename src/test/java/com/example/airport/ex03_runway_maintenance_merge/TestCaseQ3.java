package com.example.airport.ex03_runway_maintenance_merge;

import com.example.airport.ex03_runway_maintenance_merge.RunwayMaintenanceMerge.Window;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ3 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    @Test
    public void overlappingWindowsAreMergedIntoOne() {
        Window a = new Window(D.plusHours(1), D.plusHours(4));
        Window b = new Window(D.plusHours(3), D.plusHours(6));

        List<Window> merged = RunwayMaintenanceMerge.mergeWindows(List.of(a, b));

        assertEquals(1, merged.size());
        assertEquals(D.plusHours(1), merged.get(0).start());
        assertEquals(D.plusHours(6), merged.get(0).end());
    }

    @Test
    public void touchingWindowsAreMerged() {
        Window a = new Window(D.plusHours(1), D.plusHours(4));
        Window b = new Window(D.plusHours(4), D.plusHours(5));

        List<Window> merged = RunwayMaintenanceMerge.mergeWindows(List.of(a, b));

        assertEquals(1, merged.size());
        assertEquals(D.plusHours(5), merged.get(0).end());
    }

    @Test
    public void disjointWindowsStaySeparate() {
        Window a = new Window(D.plusHours(1), D.plusHours(2));
        Window b = new Window(D.plusHours(5), D.plusHours(6));

        List<Window> merged = RunwayMaintenanceMerge.mergeWindows(List.of(a, b));

        assertEquals(2, merged.size());
    }

    @Test
    public void outOfOrderInputIsSortedAfterMerging() {
        Window a = new Window(D.plusHours(5), D.plusHours(6));
        Window b = new Window(D.plusHours(1), D.plusHours(2));

        List<Window> merged = RunwayMaintenanceMerge.mergeWindows(List.of(a, b));

        assertEquals(D.plusHours(1), merged.get(0).start());
        assertEquals(D.plusHours(5), merged.get(1).start());
    }

    @Test
    public void flightFittingInGapCanBeScheduled() {
        List<Window> merged = List.of(new Window(D.plusHours(1), D.plusHours(2)), new Window(D.plusHours(5), D.plusHours(6)));

        boolean result = RunwayMaintenanceMerge.canScheduleFlight(merged, new Window(D.plusHours(2), D.plusHours(5)));

        assertTrue(result);
    }

    @Test
    public void flightOverlappingBlockedWindowCannotBeScheduled() {
        List<Window> merged = List.of(new Window(D.plusHours(1), D.plusHours(4)));

        boolean result = RunwayMaintenanceMerge.canScheduleFlight(merged, new Window(D.plusHours(3), D.plusHours(5)));

        assertFalse(result);
    }

    @Test
    public void invalidWindow_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> RunwayMaintenanceMerge.mergeWindows(List.of(new Window(D, D))));
    }
}
