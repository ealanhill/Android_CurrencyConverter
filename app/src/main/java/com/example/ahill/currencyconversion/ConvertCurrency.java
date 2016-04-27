package com.example.ahill.currencyconversion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertCurrency extends AppCompatActivity {

    public void convertCurrency(View view) {
        try {
            // grab the amount the user desires to convert from the input field, convert it to a
            // double from a String
            Double amountToConvert = new Double(
                                            ((EditText) findViewById(R.id.amountToConvert))
                                                        .getText()
                                                        .toString()
                                    );

            // convert the amount and round it to the nearest 2 decimal place
            double convertedAmount = round(amountToConvert * 0.6857, 2);

            // pop-up the amount to the user using the unicode string of the GBP symbol
            Toast.makeText(getApplicationContext(), "\u00a3" + convertedAmount, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("Error", "The user entered an incorrect value", e);
        }
    }

    private double round(double value, int places) {
        if(places < 0) {
            throw new IllegalArgumentException();
        }

        Log.i("Info", "The value to round: " + value + ", to " + places + " places");

        // BigDecimal has a great rounding function
        BigDecimal bd = new BigDecimal(value);

        // set the rounding scale so that we round up to the next value on a 5 or greater, but down
        // for anything less than 5
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_convert_currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
