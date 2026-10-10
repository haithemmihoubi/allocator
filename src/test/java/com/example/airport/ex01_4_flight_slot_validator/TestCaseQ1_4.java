package com.example.airport.ex01_4_flight_slot_validator;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_4 {

    private static final LocalDateTime D = LocalDateTime.of(2026, 1, 1, 8, 0);

    @Test
    public void validSlot_doesNotThrow() {
        assertDoesNotThrow(() -> FlightSlotValidator.validate(D, D.plusHours(1)));
    }

    @Test
    public void equalStartAndEnd_throws() {
        assertThrows(IllegalArgumentException.class, () -> FlightSlotValidator.validate(D, D));
    }

    @Test
    public void startAfterEnd_throws() {
        assertThrows(IllegalArgumentException.class, () -> FlightSlotValidator.validate(D.plusHours(1), D));
    }

    @Test
    public void nullStart_throws() {
        assertThrows(IllegalArgumentException.class, () -> FlightSlotValidator.validate(null, D));
    }

    @Test
    public void nullEnd_throws() {
        assertThrows(IllegalArgumentException.class, () -> FlightSlotValidator.validate(D, null));
    }
}
