package com.youcash.calendardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CalendarSelector
        .ICalendarSelectorCallBack {

    private TextView tv;
    private CalendarSelector mCalendarSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        mCalendarSelector = new CalendarSelector(this, 0, this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendarSelector.show(tv);
            }
        });
    }

    @Override
    public void transmitPeriod(HashMap<String, String> result) {
        tv.setText(result.get("year") + "--" + result.get("month") + "--" + result.get("day"));
    }
}
