package com.example.airport.ex06_crew_duty_time_window;

import com.example.airport.ex06_crew_duty_time_window.CrewDutyTimeWindow.DutyPeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ6 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 0, 0);

    @Test
    public void wellUnderCap_isAllowed() {
        DutyPeriod existing = new DutyPeriod(D, D.plusHours(2));
        DutyPeriod newDuty = new DutyPeriod(D.plusHours(4), D.plusHours(6));

        assertTrue(CrewDutyTimeWindow.canAssignDuty(List.of(existing), newDuty, 600, 24));
    }

    @Test
    public void addingDutyPushesWindowOverCap_isRejected() {
        DutyPeriod existing = new DutyPeriod(D, D.plusHours(8));
        DutyPeriod newDuty = new DutyPeriod(D.plusHours(9), D.plusHours(12));

        // existing = 480 min, new = 180 min, both end within 24h of the new duty's end => 660 > 600
        assertFalse(CrewDutyTimeWindow.canAssignDuty(List.of(existing), newDuty, 600, 24));
    }

    @Test
    public void exactlyAtCap_isAllowed() {
        DutyPeriod existing = new DutyPeriod(D, D.plusHours(8));
        DutyPeriod newDuty = new DutyPeriod(D.plusHours(9), D.plusHours(11));

        // 480 + 120 = 600 == cap, not a violation
        assertTrue(CrewDutyTimeWindow.canAssignDuty(List.of(existing), newDuty, 600, 24));
    }

    @Test
    public void oldDutyOutsideRollingWindow_isIgnored() {
        DutyPeriod oldDuty = new DutyPeriod(D, D.plusHours(8));
        DutyPeriod newDuty = new DutyPeriod(D.plusHours(32), D.plusHours(40));

        // oldDuty ends 24h+ before newDuty ends -> outside the rolling window entirely
        assertTrue(CrewDutyTimeWindow.canAssignDuty(List.of(oldDuty), newDuty, 500, 24));
    }

    @Test
    public void invalidNewDuty_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> CrewDutyTimeWindow.canAssignDuty(List.of(), new DutyPeriod(D, D), 600, 24));
    }
}
