package com.example.airport.ex17_gate_reuse_lru;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ17 {

    @Test
    public void repeatedUseOfSameStand_neverEvicts() {
        GateReuseCache cache = new GateReuseCache(2);
        cache.recordUse(1);
        Optional<Integer> evicted = cache.recordUse(1);

        assertTrue(evicted.isEmpty());
        assertEquals(List.of(1), cache.mostToLeastRecentlyUsed());
    }

    @Test
    public void insertingBeyondCapacity_evictsTrueLru() {
        GateReuseCache cache = new GateReuseCache(2);
        cache.recordUse(1);
        cache.recordUse(2);
        Optional<Integer> evicted = cache.recordUse(3);

        assertEquals(Optional.of(1), evicted);
        assertEquals(List.of(3, 2), cache.mostToLeastRecentlyUsed());
    }

    @Test
    public void reusingExistingStand_refreshesItsRecencyAndProtectsFromEviction() {
        GateReuseCache cache = new GateReuseCache(2);
        cache.recordUse(1);
        cache.recordUse(2);
        cache.recordUse(1); // 1 is now most-recently-used again
        Optional<Integer> evicted = cache.recordUse(3);

        assertEquals(Optional.of(2), evicted);
        assertEquals(List.of(3, 1), cache.mostToLeastRecentlyUsed());
    }

    @Test
    public void capacityOne_alwaysEvictsThePrevious() {
        GateReuseCache cache = new GateReuseCache(1);
        cache.recordUse(1);
        Optional<Integer> evicted = cache.recordUse(2);

        assertEquals(Optional.of(1), evicted);
        assertEquals(List.of(2), cache.mostToLeastRecentlyUsed());
    }

    @Test
    public void invalidCapacity_throws() {
        assertThrows(IllegalArgumentException.class, () -> new GateReuseCache(0));
    }
}
