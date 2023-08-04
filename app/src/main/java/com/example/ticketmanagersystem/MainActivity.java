package com.example.ticketmanagersystem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inițializați RecyclerView și setați LinearLayoutManager cu orientare verticală
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Inițializați lista de evenimente și adaptorul RecyclerView
        eventList = new ArrayList<>();
        List<TicketCategory> untoldTicketCategories = new ArrayList<>();
        untoldTicketCategories.add(new TicketCategory(1, "  VIP", BigDecimal.valueOf(100)));
        untoldTicketCategories.add(new TicketCategory(2, "  Standard", BigDecimal.valueOf(50)));
        eventList.add(new Event(
                1,
                1,
                1,
                "   Experience the magic of Untold.",
                "   Untold Festival",
                new Date(2023, 7, 15),
                new Date(2023, 7, 18),
                R.drawable.untold,
                untoldTicketCategories // Adăugăm lista de categorii de bilete pentru Untold Festival
        ));

        // Similar pentru Electric Castle Festival
        List<TicketCategory> electricCastleTicketCategories = new ArrayList<>();
        electricCastleTicketCategories.add(new TicketCategory(1, "  VIP", BigDecimal.valueOf(150)));
        electricCastleTicketCategories.add(new TicketCategory(2, "  Standard", BigDecimal.valueOf(75)));
        eventList.add(new Event(
                2,
                2,
                2,
                "   Experience the magic of Electric Castle.",
                "   Electric Castle Festival",
                new Date(2023, 6, 1),
                new Date(2023, 6, 4),
                R.drawable.electric_castle,
                electricCastleTicketCategories // Adăugăm lista de categorii de bilete pentru Electric Castle Festival
        ));

        // Similar pentru Football Game
        List<TicketCategory> footballGameTicketCategories = new ArrayList<>();
        footballGameTicketCategories.add(new TicketCategory(1, "    VIP", BigDecimal.valueOf(200)));
        footballGameTicketCategories.add(new TicketCategory(2, "    Standard", BigDecimal.valueOf(100)));
        eventList.add(new Event(
                2,
                2,
                2,
                "   Football",
                "   Football Game",
                new Date(2023, 6, 28),
                new Date(2023, 6, 30),
                R.drawable.footbal,
                footballGameTicketCategories // Adăugăm lista de categorii de bilete pentru Football Game
        ));

        // Similar pentru Football Game
        List<TicketCategory> wineFestivalTicketCategories = new ArrayList<>();
        wineFestivalTicketCategories.add(new TicketCategory(1, "    VIP", BigDecimal.valueOf(200)));
        wineFestivalTicketCategories.add(new TicketCategory(2, "    Standard", BigDecimal.valueOf(100)));
        eventList.add(new Event(
                2,
                2,
                2,
                "   Wine",
                "   Wine Festival",
                new Date(2023, 6, 17),
                new Date(2023, 6, 18),
                R.drawable.wine,
                footballGameTicketCategories // Adăugăm lista de categorii de bilete pentru Wine Festival
        ));
        eventAdapter = new EventAdapter(eventList);

        // Setăm adaptorul RecyclerView
        recyclerView.setAdapter(eventAdapter);
        Button shoppingCartButton = findViewById(R.id.shopping_cart); // Asigură-te că id-ul este corect
        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

    }
    private void openOrderActivity() {
        // Deschidem OrderActivity folosind un Intent
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


}