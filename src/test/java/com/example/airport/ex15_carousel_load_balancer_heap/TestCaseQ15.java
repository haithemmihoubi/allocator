package com.example.airport.ex15_carousel_load_balancer_heap;

import com.example.airport.ex15_carousel_load_balancer_heap.CarouselLoadBalancer.FlightBagLoad;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ15 {

    private static int maxCarouselLoad(Map<Integer, List<String>> assignment, List<FlightBagLoad> flights) {
        Map<String, Integer> loadById = new java.util.HashMap<>();
        for (FlightBagLoad f : flights) loadById.put(f.flightId(), f.bagMinutes());

        int max = 0;
        for (List<String> flightIds : assignment.values()) {
            int total = flightIds.stream().mapToInt(loadById::get).sum();
            max = Math.max(max, total);
        }
        return max;
    }

    @Test
    public void singleCarousel_getsEverything() {
        List<FlightBagLoad> flights = List.of(new FlightBagLoad("F1", 10), new FlightBagLoad("F2", 20));

        Map<Integer, List<String>> result = CarouselLoadBalancer.balance(flights, 1);

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).size());
    }

    @Test
    public void moreCarouselsThanFlights_maxLoadIsHeaviestSingleFlight() {
        List<FlightBagLoad> flights = List.of(new FlightBagLoad("F1", 10), new FlightBagLoad("F2", 50));

        Map<Integer, List<String>> result = CarouselLoadBalancer.balance(flights, 5);

        assertEquals(50, maxCarouselLoad(result, flights));
    }

    @Test
    public void balancesLoadAcrossTwoCarousels() {
        List<FlightBagLoad> flights = List.of(
                new FlightBagLoad("F1", 10), new FlightBagLoad("F2", 10),
                new FlightBagLoad("F3", 10), new FlightBagLoad("F4", 10));

        Map<Integer, List<String>> result = CarouselLoadBalancer.balance(flights, 2);

        assertEquals(20, maxCarouselLoad(result, flights));
    }

    @Test
    public void everyFlightIsAssignedExactlyOnce() {
        List<FlightBagLoad> flights = List.of(
                new FlightBagLoad("F1", 5), new FlightBagLoad("F2", 7), new FlightBagLoad("F3", 3));

        Map<Integer, List<String>> result = CarouselLoadBalancer.balance(flights, 2);

        List<String> allAssigned = result.values().stream().flatMap(List::stream).toList();
        assertEquals(3, allAssigned.size());
        assertTrue(allAssigned.containsAll(List.of("F1", "F2", "F3")));
    }

    @Test
    public void invalidCarouselCount_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> CarouselLoadBalancer.balance(List.of(new FlightBagLoad("F1", 5)), 0));
    }

    @Test
    public void negativeBagMinutes_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> CarouselLoadBalancer.balance(List.of(new FlightBagLoad("F1", -5)), 1));
    }
}
