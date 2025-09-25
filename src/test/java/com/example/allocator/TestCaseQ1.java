package com.example.allocator;


import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1 {
    /**
     * Test Case 1: Single park position available
     * Scenario: One park position exists and is available for the requested time slot
     * Expected: Should return the available park position
     */
    @Test
    public void testCase1() {
        InterviewQuestion.ParkPosition parkPosition = getParkPosition(0, LocalDateTime.now());

        InterviewQuestion.ParkPositionRequest request = new InterviewQuestion.ParkPositionRequest(5, LocalDateTime.now().plusHours(12), LocalDateTime.now().plusHours(14));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPosition), request);

        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPosition.getLocation(), result.getLocation());
    }

    /**
     * Test Case 2: Single park position not available
     * Scenario: One park position exists but is not available due to time conflict
     * Expected: Should return empty Optional
     */
    @Test
    public void testCase2() {
        InterviewQuestion.ParkPosition parkPosition = getParkPosition(0, LocalDateTime.now());
        InterviewQuestion.ParkPositionRequest request = new InterviewQuestion.ParkPositionRequest(5, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(20));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPosition), request);

        assertTrue(resultOpt.isEmpty());

    }

    /**
     * Test Case 3: Two park positions, requested one unavailable
     * Scenario: Two park positions exist, requested location is unavailable, should return the other one
     * Expected: Should return the available park position (not the requested one)
     */
    @Test
    public void testCase3() {
        InterviewQuestion.ParkPosition parkPositionTodayFull = getParkPosition(0, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionTomorrowFull = getParkPosition(2, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(0, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(20));
        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionTodayFull, parkPositionTomorrowFull), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPositionTomorrowFull.getLocation(), result.getLocation());
    }

    /**
     * Test Case 4: Three park positions, find closest available
     * Scenario: Three park positions exist, requested location is unavailable, should return the closest available one
     * Expected: Should return the closest available park position based on distance
     */
    @Test
    public void testCase4() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(0, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(1, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(2, LocalDateTime.now().plusDays(2));


        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(0, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(11));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPositionPlus1Day.getLocation(), result.getLocation());
    }

    /**
     * Test Case 5: Five park positions, requested one is available
     * Scenario: Five park positions exist, requested location is available
     * Expected: Should return the requested park position (exact match)
     */
    @Test
    public void testCase5() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(0, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(1, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(2, LocalDateTime.now().plusDays(2));
        InterviewQuestion.ParkPosition parkPositionPlus3Day = getParkPosition(3, LocalDateTime.now().plusDays(3));
        InterviewQuestion.ParkPosition parkPositionPlus4Day = getParkPosition(4, LocalDateTime.now().plusDays(4));

        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(0, LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(2).plusHours(11));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day, parkPositionPlus3Day, parkPositionPlus4Day), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPositionToday.getLocation(), result.getLocation());
    }

    /**
     * Test Case 6: Five park positions, only last one is available
     * Scenario: Five park positions exist, only the last one is available due to time conflicts
     * Expected: Should return the last available park position
     */
    @Test
    public void testCase6() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(0, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(1, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(2, LocalDateTime.now().plusDays(2));
        InterviewQuestion.ParkPosition parkPositionPlus3Day = getParkPosition(3, LocalDateTime.now().plusDays(3));
        InterviewQuestion.ParkPosition parkPositionPlus4Day = getParkPosition(4, LocalDateTime.now().plusDays(4));

        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(0, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusDays(3).plusHours(11));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day, parkPositionPlus3Day, parkPositionPlus4Day), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPositionPlus4Day.getLocation(), result.getLocation());
    }

    /**
     * Test Case 7: Five park positions, all available
     * Scenario: Five park positions exist, all are available, should return the closest one
     * Expected: Should return the closest park position based on distance
     */
    @Test
    public void testCase7() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(10, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(20, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(30, LocalDateTime.now().plusDays(2));
        InterviewQuestion.ParkPosition parkPositionPlus3Day = getParkPosition(40, LocalDateTime.now().plusDays(3));
        InterviewQuestion.ParkPosition parkPositionPlus4Day = getParkPosition(50, LocalDateTime.now().plusDays(4));

        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(34, LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(20));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day, parkPositionPlus3Day, parkPositionPlus4Day), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        assertEquals(parkPositionPlus2Day.getLocation(), result.getLocation());
    }

    /**
     * Test Case 8: Five park positions, none available
     * Scenario: Five park positions exist, but none are available due to time conflicts
     * Expected: Should return empty Optional
     */
    @Test
    public void testCase8() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(10, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(20, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(30, LocalDateTime.now().plusDays(2));
        InterviewQuestion.ParkPosition parkPositionPlus3Day = getParkPosition(40, LocalDateTime.now().plusDays(3));
        InterviewQuestion.ParkPosition parkPositionPlus4Day = getParkPosition(50, LocalDateTime.now().plusDays(4));

        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(34, LocalDateTime.now(), LocalDateTime.now().plusDays(5));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day, parkPositionPlus3Day, parkPositionPlus4Day), requestForToday);
        assertTrue(resultOpt.isEmpty());
    }

    /**
     * Test Case 9: Five park positions, all available, two have same distance
     * Scenario: Five park positions exist, all are available, two have the same distance from request
     * Expected: Should return one of the park positions with the same distance (implementation choice)
     */
    @Test
    public void testCase9() {
        InterviewQuestion.ParkPosition parkPositionToday = getParkPosition(10, LocalDateTime.now());
        InterviewQuestion.ParkPosition parkPositionPlus1Day = getParkPosition(20, LocalDateTime.now().plusDays(1));
        InterviewQuestion.ParkPosition parkPositionPlus2Day = getParkPosition(30, LocalDateTime.now().plusDays(2));
        InterviewQuestion.ParkPosition parkPositionPlus3Day = getParkPosition(40, LocalDateTime.now().plusDays(3));
        InterviewQuestion.ParkPosition parkPositionPlus4Day = getParkPosition(50, LocalDateTime.now().plusDays(4));

        InterviewQuestion.ParkPositionRequest requestForToday = new InterviewQuestion.ParkPositionRequest(35, LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(20));

        Optional<InterviewQuestion.ParkPosition> resultOpt = InterviewQuestion.question1(List.of(parkPositionToday, parkPositionPlus1Day, parkPositionPlus2Day, parkPositionPlus3Day, parkPositionPlus4Day), requestForToday);
        assertTrue(resultOpt.isPresent());
        InterviewQuestion.ParkPosition result = resultOpt.get();
        int distance = Math.abs(result.getLocation() - requestForToday.getLocation());
        assertEquals(5, distance);
    }

    /**
     * Helper method to create a park position with predefined time slots
     * @param location The location of the park position
     * @param refDate The reference date for creating time slots
     * @return ParkPosition with predefined time slots (mostly unavailable between +2 and +11 hours)
     */
    public static InterviewQuestion.ParkPosition getParkPosition(int location, LocalDateTime refDate) {
        InterviewQuestion.ParkPositionRequest ppr1 = new InterviewQuestion.ParkPositionRequest(location, refDate, refDate.plusHours(2));
        InterviewQuestion.ParkPositionRequest ppr2 = new InterviewQuestion.ParkPositionRequest(location, refDate.plusHours(3), refDate.plusHours(5));
        InterviewQuestion.ParkPositionRequest ppr3 = new InterviewQuestion.ParkPositionRequest(location, refDate.plusHours(6), refDate.plusHours(8));
        InterviewQuestion.ParkPositionRequest ppr4 = new InterviewQuestion.ParkPositionRequest(location, refDate.plusHours(9), refDate.plusHours(11));
        return new InterviewQuestion.ParkPosition(location, Set.of(ppr1, ppr2, ppr3, ppr4));
    }

}
