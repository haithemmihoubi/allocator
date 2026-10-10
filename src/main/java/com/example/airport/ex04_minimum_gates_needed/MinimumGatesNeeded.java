package com.example.airport.ex04_minimum_gates_needed;

import java.time.LocalDateTime;
import java.util.List;

public class MinimumGatesNeeded {

    /**
     * See exercises/ex04-minimum-gates-needed/README.md.
     * TODO: implement.
     */
    public static int minimumGatesNeeded(List<FlightWindow> flights) {
        return 0;
    }

    public record FlightWindow(LocalDateTime start, LocalDateTime end) {
    }
}
