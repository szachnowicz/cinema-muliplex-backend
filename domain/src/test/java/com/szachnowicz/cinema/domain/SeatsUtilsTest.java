package com.szachnowicz.cinema.domain;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class SeatsUtilsTest {

    @Test
    void areSeatsCodesAdjacent_method_testOfAdjacentSeats() {
        List<String> list = Arrays.asList("A1", "A5");
        assertFalse(SeatsUtils.areSeatsCodesAdjacent(list));
    }

    @Test
    void areSeatsCodesAdjacent_method_testOfAdjacentSeatsMultiply() {
        List<String> list = Arrays.asList("A1", "A2", "A3");
        assertTrue(SeatsUtils.areSeatsCodesAdjacent(list));
    }

    @Test
    void areSeatsCodesAdjacent_method_testOfNotAdjacentSeats() {
        List<String> list = Arrays.asList("A1", "A2", "A3", "B12");
        assertFalse(SeatsUtils.areSeatsCodesAdjacent(list));
    }

    @Test
    void  areSeatsCodesAdjacent_method_testOfSingle() {
        List<String> list = Collections.singletonList("A1");
        assertTrue(SeatsUtils.areSeatsCodesAdjacent(list));
    }

    @Test
    void  areSeatsCodesAdjacent_method_emptyListTest() {
        List<String> list = Collections.emptyList();
        assertFalse(SeatsUtils.areSeatsCodesAdjacent(list));
    }
}