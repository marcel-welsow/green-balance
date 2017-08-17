package com.mojodictive.greenbalance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mojodictive.greenbalance.crud.ICRUDOperationsApplication;
import com.mojodictive.greenbalance.model.IUser;
import com.mojodictive.greenbalance.model.User;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView calenderImageView;

    private TextView dateTextView;

    private EditText nameEditText;
    private EditText emailEditText;

    private FloatingActionButton saveUserFloatingActionButton;

    private Calendar calendar = Calendar.getInstance();

    private ICRUDOperationsApplication crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        crudOperations = ((ICRUDOperationsApplication) getApplication()).getCrudOperations();

        crudOperations.readUser(new ICRUDOperationsApplication.CallbackFunction<IUser>() {
            @Override
            public void process(IUser user) {

                if (user == null) {
                    initViewElements();
                    initOnClickListener();
                } else {
                    goToDashboardActivity();
                }
            }
        });
    }

    private void initViewElements() {

        calenderImageView = (ImageView) findViewById(R.id.button_login_calender);

        dateTextView = (TextView) findViewById(R.id.textview_login_date);

        nameEditText = (EditText) findViewById(R.id.edittext_name);
        emailEditText = (EditText) findViewById(R.id.edittext_email);

        saveUserFloatingActionButton = (FloatingActionButton) findViewById(R.id.button_save_user);
    }

    private void initOnClickListener() {

        calenderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalenderDialog();
            }
        });
        saveUserFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
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

    private void saveUserData() {

        if (validateUserData()) {

            IUser user = new User();
            user.setName(nameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setDate(calendar.getTimeInMillis() / 1000);

            crudOperations.createUser(user, new ICRUDOperationsApplication.CallbackFunction<IUser>() {
                @Override
                public void process(IUser result) {
                    goToDashboardActivity();
                }
            });
        }
    }

    private boolean validateUserData() {

        String name = nameEditText.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(LoginActivity.this, R.string.toast_no_name, Toast.LENGTH_SHORT).show();
            return false;
        }

        String email = emailEditText.getText().toString();

        if (isEmailValid(email)) {
            Toast.makeText(LoginActivity.this, R.string.toast_no_email, Toast.LENGTH_SHORT).show();
            return false;
        }

        String dateSinceVegan = dateTextView.getText().toString();

        if (dateSinceVegan.length() == 0) {
            Toast.makeText(LoginActivity.this, R.string.toast_no_date_since_vegan, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() > 0;
    }

    private void goToDashboardActivity() {

        Intent dashboardIntent = new Intent(LoginActivity.this, DashboardActivity.class);

        startActivity(dashboardIntent);
    }
}
