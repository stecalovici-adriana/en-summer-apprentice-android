package com.example.ticketmanagersystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        // Initialize views
        Spinner ticketCategorySpinner = findViewById(R.id.ticketCategorySpinner);
        numberOfTicketsEditText = findViewById(R.id.numberOfTicketsEditText);
        Button checkoutButton = findViewById(R.id.checkoutButton);

        // Get the event data passed from MainActivity
        Event event = getIntent().getParcelableExtra("event");

        // Set up the Ticket Category spinner
        List<String> ticketCategoryNames = new ArrayList<>();
        for (TicketCategory ticketCategory : event.getTicketCategories()) {
            ticketCategoryNames.add(ticketCategory.getDescription());
        }
        ArrayAdapter<String> ticketCategoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ticketCategoryNames);
        ticketCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ticketCategorySpinner.setAdapter(ticketCategoryAdapter);
        ticketCategorySpinner.setOnItemSelectedListener(this);

        // Set up the Checkout button click listener
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckout();
            }
        });
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
        if (totalPrice.compareTo(BigDecimal.ZERO) > 0) {
            String message = "Total price: " + totalPrice.toString();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select ticket category and enter the number of tickets.", Toast.LENGTH_SHORT).show();
        }
    }
}
