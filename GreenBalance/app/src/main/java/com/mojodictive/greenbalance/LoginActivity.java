package com.mojodictive.greenbalance;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView calenderImageView;

    private TextView dateTextView;

    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initViewElements();
        initOnClickListener();
    }

    private void initViewElements() {

        calenderImageView = (ImageView) findViewById(R.id.button_login_calender);
        dateTextView = (TextView) findViewById(R.id.textview_login_date);
    }

    private void initOnClickListener() {

        calenderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalenderDialog();
            }
        });
    }

    private void showCalenderDialog() {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                LoginActivity.this, LoginActivity.this, year, month, day
        );

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(year, month, dayOfMonth);

        String dayString = Integer.toString(dayOfMonth);
        if (dayOfMonth < 10) dayString = "0" + dayString;

        dateTextView.setText(dayString + "." + getFullMonth(month) + " " + year);
    }

    private String getFullMonth(int month) {

        switch (month) {
            case 0: return "Januar";
            case 1: return "Februar";
            case 2: return "März";
            case 3: return "April";
            case 4: return "Mai";
            case 5: return "Juni";
            case 6: return "Juli";
            case 7: return "August";
            case 8: return "September";
            case 9: return "Oktober";
            case 10: return "November";
            case 11: return "Dezember";
        }

        return "Januar";
    }
}
