package com.greganderson.forteostabberhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.joda.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dayTextView = findViewById(R.id.day);
        TextView placementTextView = findViewById(R.id.placement);
        TextView alertTextView = findViewById(R.id.alert);

        // Make sure alert is clear
        alertTextView.setText("");

        Placement placement = new Placement(LocalDate.now());

        int lifeOfPen = placement.getPenTimeLeft();
        if (lifeOfPen == 1)
            alertTextView.setText(R.string.pens_last_day);
        else if (lifeOfPen == 28)
            alertTextView.setText(R.string.new_pen);
        dayTextView.setText("Days left before new pen: " + lifeOfPen);
        placementTextView.setText("Position: " + placement.getStabbingPlacement());
    }
}
