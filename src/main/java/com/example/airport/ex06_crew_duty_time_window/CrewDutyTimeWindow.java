package com.example.airport.ex06_crew_duty_time_window;

import java.time.LocalDateTime;
import java.util.List;

public class CrewDutyTimeWindow {

    /**
     * See exercises/ex06-crew-duty-time-window/README.md.
     * TODO: implement.
     */
    public static boolean canAssignDuty(
            List<DutyPeriod> existingDuties,
            DutyPeriod newDuty,
            int maxMinutesPerRollingWindow,
            int rollingWindowHours) {
        return false;
    }

    public record DutyPeriod(LocalDateTime start, LocalDateTime end) {
    }
}
