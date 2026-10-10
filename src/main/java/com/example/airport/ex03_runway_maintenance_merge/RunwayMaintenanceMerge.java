package com.example.airport.ex03_runway_maintenance_merge;

import java.time.LocalDateTime;
import java.util.List;

public class RunwayMaintenanceMerge {

    /**
     * See exercises/ex03-runway-maintenance-merge/README.md.
     * TODO: implement.
     */
    public static List<Window> mergeWindows(List<Window> windows) {
        return null;
    }

    /**
     * TODO: implement.
     */
    public static boolean canScheduleFlight(List<Window> mergedWindows, Window requestedSlot) {
        return false;
    }

    public record Window(LocalDateTime start, LocalDateTime end) {
    }
}
