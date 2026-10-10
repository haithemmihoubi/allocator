package com.example.airport.ex01_5_carousel_log_append;

import java.util.ArrayList;
import java.util.List;

public class CarouselLogAppend {

    /**
     * See exercises/ex01.5-carousel-log-append/README.md.
     * TODO: implement.
     */
    public static Carousel logBag(List<Carousel> carousels, String flightId) {
        return null;
    }

    public static class Carousel {
        private final int id;
        private final List<String> log;

        public Carousel(int id, List<String> log) {
            this.id = id;
            this.log = new ArrayList<>(log);
        }

        public int getId() {
            return id;
        }

        /** Mutable on purpose: logging a bag means adding to this directly. */
        public List<String> getLog() {
            return log;
        }
    }
}
