package com.example.ticketmanagersystem;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
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

        // Inițializăm și adăugăm un listener pentru butonul de checkout
        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckout(); // Apelăm metoda care va gestiona comenzile de checkout
            }
        });

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
        TicketCategory selectedTicketCategory = (TicketCategory) parent.getItemAtPosition(position);
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

        if (numberOfTickets > 0 && !selectedTicketCategories.isEmpty()) {
            BigDecimal ticketPrice = selectedTicketCategories.get(0).getPrice();
            totalPrice = ticketPrice.multiply(BigDecimal.valueOf(numberOfTickets));
        } else {
            totalPrice = BigDecimal.ZERO;
        }
    }

    private void handleCheckout() {
        String numberOfTicketsStr = numberOfTicketsEditText.getText().toString().trim();

        if (numberOfTicketsStr.isEmpty()) {
            Toast.makeText(this, "Please enter the number of tickets", Toast.LENGTH_SHORT).show();
            return;
        }

        int numberOfTickets = Integer.parseInt(numberOfTicketsStr);

        if (numberOfTickets <= 0) {
            Toast.makeText(this, "Number of tickets must be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedTicketCategories.isEmpty()) {
            Toast.makeText(this, "Please select a ticket category", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculează prețul total
        BigDecimal ticketPrice = selectedTicketCategories.get(0).getPrice();
        totalPrice = ticketPrice.multiply(BigDecimal.valueOf(numberOfTickets));

        // Afișează dialogul de confirmare
        showConfirmationDialog(totalPrice, numberOfTickets);
    }

    private void showConfirmationDialog(BigDecimal totalPrice, int numberOfTickets) {
        // Construiți conținutul dialogului de confirmare cu detaliile comenzii
        String confirmationMessage = "Total Price: $" + totalPrice + "\n"
                + "Number of Tickets: " + numberOfTickets;

        // Creați și afișați dialogul
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Confirmation")
                .setMessage(confirmationMessage)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    // Dacă utilizatorul confirmă, puteți implementa aici acțiunea de plasare a comenzii
                    // De exemplu, puteți trimite comanda la server sau efectua o altă acțiune
                    // După plasarea comenzii, puteți naviga către o altă activitate, dacă este necesar.
                    // Exemplu: startActivity(new Intent(OrderActivity.this, SomeOtherActivity.class));
                    Toast.makeText(OrderActivity.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dacă utilizatorul anulează, pur și simplu închideți dialogul
                    dialog.dismiss();
                })
                .create()
                .show();
    }
}
