package com.greganderson.forteostabberhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;

public class MainActivity extends AppCompatActivity {

    private String[] placements = {
            "Upper left abdomen",
            "Upper right abdomen",
            "Lower right abdomen",
            "Lower left abdomen",
            "Upper left thigh",
            "Upper right thigh",
            "Lower right thigh",
            "Lower left thigh",
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView day = findViewById(R.id.day);
        TextView placement = findViewById(R.id.placement);
        TextView alert = findViewById(R.id.alert);

        // Make sure alert is clear
        alert.setText("");

        MutableDateTime epoch = new MutableDateTime();
        epoch.setDate(0);
        DateTime now = new DateTime();
        int days = Days.daysBetween(epoch, now).getDays() - 4;  // -4 is the right offset for the current setup
        int lifeOfPen = 28 - (days % 28);
        int position = (days + 1) % placements.length;  // +1 is the right offset to line things up

        if (lifeOfPen == 1)
            alert.setText("LAST DAY WITH THIS PEN");
        else if (lifeOfPen == 28)
            alert.setText("NEW PEN TODAY");
        day.setText("Days left before new pen: " + lifeOfPen);
        placement.setText("Position: " + placements[position]);
    }
}
