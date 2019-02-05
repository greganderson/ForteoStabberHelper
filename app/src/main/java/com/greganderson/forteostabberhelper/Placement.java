package com.greganderson.forteostabberhelper;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class Placement {

    enum Place {
        UPPER_LEFT_ABDOMEN,
        UPPER_RIGHT_ABDOMEN,
        LOWER_RIGHT_ABDOMEN,
        LOWER_LEFT_ABDOMEN,
        UPPER_LEFT_THIGH,
        UPPER_RIGHT_THIGH,
        LOWER_RIGHT_THIGH,
        LOWER_LEFT_THIGH,
    }

    private final Map<Place, String> placements = new HashMap<Place, String>() {
    {
            put(Place.UPPER_LEFT_ABDOMEN, "Upper left abdomen");
            put(Place.UPPER_RIGHT_ABDOMEN, "Upper right abdomen");
            put(Place.LOWER_RIGHT_ABDOMEN, "Lower right abdomen");
            put(Place.LOWER_LEFT_ABDOMEN, "Lower left abdomen");
            put(Place.UPPER_LEFT_THIGH, "Upper left thigh");
            put(Place.UPPER_RIGHT_THIGH, "Upper right thigh");
            put(Place.LOWER_RIGHT_THIGH, "Lower right thigh");
            put(Place.LOWER_LEFT_THIGH, "Lower left thigh");
    }};

    private final int daysLeftBeforePenDies;
    private final String placement;

    Placement(LocalDate today) {
        // 2019-01-27 is the first day
        // Upper left abdomen was the first placement
        LocalDate start = LocalDate.parse("2018-12-30");
        int days = Days.daysBetween(start, today).getDays();

        daysLeftBeforePenDies = 28 - (days % 28);

        int position = days % placements.size();
        placement = getPlacementString(Place.values()[position]);
    }

    String getPlacementString(Place placeEnum) {
        return placements.get(placeEnum);
    }

    int getPenTimeLeft() {
        return daysLeftBeforePenDies;
    }

    String getStabbingPlacement() {
        return placement;
    }
}
