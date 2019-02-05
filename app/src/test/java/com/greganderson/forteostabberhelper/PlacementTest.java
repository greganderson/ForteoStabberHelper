package com.greganderson.forteostabberhelper;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlacementTest {

    @Test
    public void placement_isCorrectNormal() {
        String dateString = "2019-1-28";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        String expected = placement.getPlacementString(Placement.Place.UPPER_RIGHT_THIGH);
        String actual = placement.getStabbingPlacement();
        assertEquals(expected, actual);
    }

    @Test
    public void placement_isCorrectEndOfMonthJan() {
        String dateString = "2019-1-31";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        String expected = placement.getPlacementString(Placement.Place.UPPER_LEFT_ABDOMEN);
        String actual = placement.getStabbingPlacement();
        assertEquals(expected, actual);
    }

    @Test
    public void placement_isCorrectEndOfMonthFeb() {
        String dateString = "2019-2-28";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        String expected = placement.getPlacementString(Placement.Place.UPPER_LEFT_THIGH);
        String actual = placement.getStabbingPlacement();
        assertEquals(expected, actual);
    }

    @Test
    public void placement_isCorrectBeginningOfMonthFeb() {
        String dateString = "2019-2-1";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        String expected = placement.getPlacementString(Placement.Place.UPPER_RIGHT_ABDOMEN);
        String actual = placement.getStabbingPlacement();
        assertEquals(expected, actual);
    }

    @Test
    public void placement_isCorrectBeginningOfMonthMarch() {
        String dateString = "2019-3-1";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        String expected = placement.getPlacementString(Placement.Place.UPPER_RIGHT_THIGH);
        String actual = placement.getStabbingPlacement();
        assertEquals(expected, actual);
    }

    @Test
    public void placement_isCorrectAllPlacements() {
        // Upper right abdomen is correct for Feb 1, 2019
        int placeOffset = Placement.Place.UPPER_RIGHT_ABDOMEN.ordinal() - 1;
        for (int dayOfMonth = 1; dayOfMonth < Placement.Place.values().length; dayOfMonth++) {
            LocalDate date = LocalDate.parse("2019-2-" + dayOfMonth);
            Placement placement = new Placement(date);
            int placeValue = (dayOfMonth + placeOffset) % Placement.Place.values().length;
            Placement.Place expectedPlace = Placement.Place.values()[placeValue];
            String expected = placement.getPlacementString(expectedPlace);
            String actual = placement.getStabbingPlacement();
            assertEquals(expected, actual);
        }
    }


    // LIFE OF PEN


    @Test
    public void lifetime_isCorrectNormal() {
        String dateString = "2019-1-28";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        int expected = 28 - 1;
        int actual = placement.getPenTimeLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void lifetime_isCorrectEndOfMonthJan() {
        String dateString = "2019-1-31";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        int expected = 28 - 4;
        int actual = placement.getPenTimeLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void lifetime_isCorrectLastDay() {
        String dateString = "2019-2-23";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        int expected = 28 - 27;
        int actual = placement.getPenTimeLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void lifetime_isCorrectFirstDay() {
        String dateString = "2019-2-24";
        LocalDate date = LocalDate.parse(dateString);
        Placement placement = new Placement(date);
        int expected = 28;
        int actual = placement.getPenTimeLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void lifetime_isCorrectAllPlacements() {
        LocalDate date = LocalDate.parse("2019-2-1");
        int expected = 28 - 5;  // Correct for Feb 1
        for (int i = 0; i < 60; i++) {
            Placement placement = new Placement(date);
            int actual = placement.getPenTimeLeft();
            assertEquals(expected, actual);
            date = date.plusDays(1);
            expected--;
            if (expected == 0)
                expected = 28;
        }
    }
}