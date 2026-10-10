package com.example.airport.ex13_max_gate_revenue_dp;

import java.time.LocalDateTime;
import java.util.List;

public class MaxGateRevenue {

    /**
     * See exercises/ex13-max-gate-revenue-dp/README.md.
     * TODO: implement.
     */
    public static double maxRevenue(List<GateBooking> bookings) {
        return 0;
    }

    public record GateBooking(String id, LocalDateTime start, LocalDateTime end, double revenue) {
    }
}
