package com.example.ticketmanagersystem;

import java.math.BigDecimal;

public class TicketCategory {
    private int ticketCategoryID;
    private String description;
    private BigDecimal price;

    public TicketCategory(int ticketCategoryID, String description, BigDecimal price) {
        this.ticketCategoryID = ticketCategoryID;
        this.description = description;
        this.price = price;
    }

    public int getTicketCategoryID() {
        return ticketCategoryID;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}