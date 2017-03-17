package com.example.nisandkb.dateperious;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity  {
    private TextView tvDisplayDate,tv;

    private Button btnChangeDate;

    private int myear;
    private int mmonth;
    private int mday;

    static final int DATE_DIALOG_ID = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayDate = (TextView) findViewById(R.id.textView2);
        tv = (TextView) findViewById(R.id.textView3);
        final Calendar c = Calendar.getInstance();
        myear = c.get(Calendar.YEAR);
        mmonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mday).append("-").append(mmonth + 1).append("-")
                .append(myear).append(" "));

        btnChangeDate = (Button) findViewById(R.id.button);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
    }
   // public void addListenerOnButton() {



   // }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                DatePickerDialog _date =   new DatePickerDialog(this, datePickerListener, myear,mmonth,
                        mday){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < myear)
                            view.updateDate(myear, mmonth, mday);

                        if (monthOfYear < mmonth && year == myear)
                            view.updateDate(myear, mmonth, mday);

                        if (dayOfMonth < mday && year == myear && monthOfYear == mmonth)
                            view.updateDate(myear, mmonth, mday);

                    }
                };
                return _date;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            int myear1 = selectedYear;
            int mmonth1 = selectedMonth;
            int mday1 = selectedDay;

            if((myear <= myear1 ) && (mmonth<=mmonth1) && mday<=mday1 )
            {
                tv.setText(new StringBuilder().append(mday1)
                                .append("-").append(mmonth1 + 1).append("-").append(myear1)
                                .append(" "));
            }else {
                        Toast.makeText(MainActivity.this,"please give upcoming dates",Toast.LENGTH_LONG).show();
                  }

        }
    };

}




