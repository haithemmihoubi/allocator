package com.example.airport.ex12_max_runway_slot_usage;

import com.example.airport.ex12_max_runway_slot_usage.MaxRunwaySlotUsage.SlotRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ12 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    private static void assertNoOverlap(List<SlotRequest> all, List<String> chosenIds) {
        List<SlotRequest> chosen = all.stream().filter(r -> chosenIds.contains(r.flightId())).toList();
        for (int i = 0; i < chosen.size(); i++) {
            for (int j = i + 1; j < chosen.size(); j++) {
                SlotRequest a = chosen.get(i);
                SlotRequest b = chosen.get(j);
                boolean overlap = a.start().isBefore(b.end()) && b.start().isBefore(a.end());
                assertFalse(overlap, "Overlap between " + a.flightId() + " and " + b.flightId());
            }
        }
    }

    @Test
    public void classicGreedyCase() {
        List<SlotRequest> requests = List.of(
                new SlotRequest("F1", D.plusHours(1), D.plusHours(3)),
                new SlotRequest("F2", D.plusHours(2), D.plusHours(5)),
                new SlotRequest("F3", D.plusHours(4), D.plusHours(6)),
                new SlotRequest("F4", D.plusHours(6), D.plusHours(8)));

        List<String> chosen = MaxRunwaySlotUsage.maxNonOverlappingSlots(requests);

        assertEquals(3, chosen.size()); // F1, F3, F4 (or any other optimal triple)
        assertNoOverlap(requests, chosen);
    }

    @Test
    public void allOverlapping_onlyOneCanBeChosen() {
        List<SlotRequest> requests = List.of(
                new SlotRequest("F1", D, D.plusHours(5)),
                new SlotRequest("F2", D.plusHours(1), D.plusHours(6)),
                new SlotRequest("F3", D.plusHours(2), D.plusHours(7)));

        List<String> chosen = MaxRunwaySlotUsage.maxNonOverlappingSlots(requests);

        assertEquals(1, chosen.size());
    }

    @Test
    public void noneOverlapping_allAreChosen() {
        List<SlotRequest> requests = List.of(
                new SlotRequest("F1", D, D.plusHours(1)),
                new SlotRequest("F2", D.plusHours(1), D.plusHours(2)),
                new SlotRequest("F3", D.plusHours(2), D.plusHours(3)));

        List<String> chosen = MaxRunwaySlotUsage.maxNonOverlappingSlots(requests);

        assertEquals(3, chosen.size());
        assertEquals(Set.copyOf(chosen), Set.of("F1", "F2", "F3"));
    }

    @Test
    public void emptyInput_returnsEmptyList() {
        assertTrue(MaxRunwaySlotUsage.maxNonOverlappingSlots(List.of()).isEmpty());
    }

    @Test
    public void invalidRequest_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> MaxRunwaySlotUsage.maxNonOverlappingSlots(List.of(new SlotRequest("F1", D, D))));
    }
}
