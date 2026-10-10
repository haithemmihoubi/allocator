package com.example.airport.ex01_5_carousel_log_append;

import com.example.airport.ex01_5_carousel_log_append.CarouselLogAppend.Carousel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_5 {

    @Test
    public void appendsToEmptyLog() {
        Carousel carousel = new Carousel(1, List.of());

        Carousel result = CarouselLogAppend.logBag(List.of(carousel), "F1");

        assertEquals(List.of("F1"), result.getLog());
    }

    @Test
    public void appendsAfterExistingEntries() {
        Carousel carousel = new Carousel(1, List.of("OLD1", "OLD2"));

        Carousel result = CarouselLogAppend.logBag(List.of(carousel), "NEW");

        assertEquals(List.of("OLD1", "OLD2", "NEW"), result.getLog());
    }

    @Test
    public void smallestIdCarouselIsTheOneMutated() {
        Carousel five = new Carousel(5, List.of());
        Carousel three = new Carousel(3, List.of());

        Carousel result = CarouselLogAppend.logBag(List.of(five, three), "F1");

        assertEquals(3, result.getId());
        assertEquals(List.of("F1"), three.getLog());
        assertTrue(five.getLog().isEmpty());
    }

    @Test
    public void returnedObjectIsTheSameMutatedInstance() {
        Carousel carousel = new Carousel(1, List.of());

        Carousel result = CarouselLogAppend.logBag(List.of(carousel), "F1");

        assertSame(carousel, result);
    }
}
