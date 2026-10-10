package com.example.airport.ex16_air_traffic_separation_sweep;

import com.example.airport.ex16_air_traffic_separation_sweep.AirTrafficSeparation.Landing;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ16 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void noViolations_whenWellSeparated() {
        List<Landing> landings = List.of(
                new Landing("F1", D),
                new Landing("F2", D.plusMinutes(10)));

        assertTrue(AirTrafficSeparation.violations(landings, 5).isEmpty());
        assertEquals(0, AirTrafficSeparation.minimumDelayMinutesToFix(landings, 5));
    }

    @Test
    public void exactlyAtSeparation_isNotAViolation() {
        List<Landing> landings = List.of(
                new Landing("F1", D),
                new Landing("F2", D.plusMinutes(5)));

        assertTrue(AirTrafficSeparation.violations(landings, 5).isEmpty());
    }

    @Test
    public void oneViolation_isFlagged() {
        List<Landing> landings = List.of(
                new Landing("F1", D),
                new Landing("F2", D.plusMinutes(2)));

        assertEquals(List.of("F2"), AirTrafficSeparation.violations(landings, 5));
        assertEquals(3, AirTrafficSeparation.minimumDelayMinutesToFix(landings, 5));
    }

    @Test
    public void cascadingViolations_compoundCorrectly() {
        // F1 @ 0, F2 @ 1, F3 @ 2, min separation 5.
        // Enforced: F1=0, F2=5 (delay 4), F3=10 (delay 8). Total delay = 12.
        List<Landing> landings = List.of(
                new Landing("F1", D),
                new Landing("F2", D.plusMinutes(1)),
                new Landing("F3", D.plusMinutes(2)));

        assertEquals(List.of("F2", "F3"), AirTrafficSeparation.violations(landings, 5));
        assertEquals(12, AirTrafficSeparation.minimumDelayMinutesToFix(landings, 5));
    }

    @Test
    public void negativeSeparation_throws() {
        List<Landing> landings = List.of(new Landing("F1", D));
        assertThrows(IllegalArgumentException.class, () -> AirTrafficSeparation.violations(landings, -1));
    }
}
