package com.example.airport.ex15_carousel_load_balancer_heap;

import java.util.List;
import java.util.Map;

public class CarouselLoadBalancer {

    /**
     * See exercises/ex15-carousel-load-balancer-heap/README.md.
     * TODO: implement.
     */
    public static Map<Integer, List<String>> balance(List<FlightBagLoad> flights, int carouselCount) {
        return null;
    }

    public record FlightBagLoad(String flightId, int bagMinutes) {
    }
}
