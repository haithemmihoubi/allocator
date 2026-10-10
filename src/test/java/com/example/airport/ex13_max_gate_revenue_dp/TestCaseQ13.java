package com.example.airport.ex13_max_gate_revenue_dp;

import com.example.airport.ex13_max_gate_revenue_dp.MaxGateRevenue.GateBooking;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ13 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    @Test
    public void emptyInput_returnsZero() {
        assertEquals(0, MaxGateRevenue.maxRevenue(List.of()));
    }

    @Test
    public void singleBooking_returnsItsRevenue() {
        GateBooking b = new GateBooking("B1", D, D.plusHours(1), 100);
        assertEquals(100, MaxGateRevenue.maxRevenue(List.of(b)));
    }

    @Test
    public void nonOverlapping_sumsAllRevenue() {
        List<GateBooking> bookings = List.of(
                new GateBooking("B1", D, D.plusHours(1), 50),
                new GateBooking("B2", D.plusHours(1), D.plusHours(2), 75));

        assertEquals(125, MaxGateRevenue.maxRevenue(bookings));
    }

    @Test
    public void highValueSingleBooking_beatsTwoLowerOverlappingOnes() {
        List<GateBooking> bookings = List.of(
                new GateBooking("Big", D, D.plusHours(4), 1000),
                new GateBooking("Small1", D, D.plusHours(2), 100),
                new GateBooking("Small2", D.plusHours(2), D.plusHours(4), 100));

        assertEquals(1000, MaxGateRevenue.maxRevenue(bookings));
    }

    @Test
    public void twoSmallerOnes_beatOneBigOverlapperWhenTheySumHigher() {
        List<GateBooking> bookings = List.of(
                new GateBooking("Big", D, D.plusHours(4), 150),
                new GateBooking("Small1", D, D.plusHours(2), 100),
                new GateBooking("Small2", D.plusHours(2), D.plusHours(4), 100));

        assertEquals(200, MaxGateRevenue.maxRevenue(bookings));
    }

    @Test
    public void invalidBooking_negativeRevenue_throws() {
        GateBooking invalid = new GateBooking("B1", D, D.plusHours(1), -5);
        assertThrows(IllegalArgumentException.class, () -> MaxGateRevenue.maxRevenue(List.of(invalid)));
    }
}
