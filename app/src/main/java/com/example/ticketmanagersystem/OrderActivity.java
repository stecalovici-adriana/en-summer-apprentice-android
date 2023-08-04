package com.example.ticketmanagersystem;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<TicketCategory> selectedTicketCategories = new ArrayList<>();
    private EditText numberOfTicketsEditText;
    private BigDecimal totalPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);

        // Asigurăm afișarea butonului de back (săgeată) în Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        // Get the event data passed from MainActivity
        Event event = getIntent().getParcelableExtra("event");


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Butonul de navigare înapoi (back button) a fost apăsat
            onBackPressed(); // Închide activitatea curentă și revine la activitatea anterioară
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTicketCategories.clear();
        Event event = getIntent().getParcelableExtra("event");
        TicketCategory selectedTicketCategory = event.getTicketCategories().get(position);
        selectedTicketCategories.add(selectedTicketCategory);
        updateTotalPrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedTicketCategories.clear();
    }

    private void updateTotalPrice() {
        int numberOfTickets = 0;
        try {
            numberOfTickets = Integer.parseInt(numberOfTicketsEditText.getText().toString());
        } catch (NumberFormatException e) {
            // Handle the case when the input is not a valid number
        }

        if (numberOfTickets > 0 && selectedTicketCategories.size() > 0) {
            BigDecimal ticketPrice = selectedTicketCategories.get(0).getPrice();
            totalPrice = ticketPrice.multiply(BigDecimal.valueOf(numberOfTickets));
        } else {
            totalPrice = BigDecimal.ZERO;
        }
    }

    private void handleCheckout() {

        EditText usernameEditText = (EditText) findViewById(R.id.numberOfTicketsEditText);
        if (!usernameEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter a number of ticker", Toast.LENGTH_SHORT).show();
        }
    }
}