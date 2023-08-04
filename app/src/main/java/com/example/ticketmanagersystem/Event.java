package com.example.ticketmanagersystem;

import java.util.Date;
import java.util.List;

public class Event {
    private int eventID;
    private int venueID;
    private int eventTypeID;
    private String eventDescription;
    private String eventName;
    private Date startDate;
    private Date endDate;
    private int imageResource; // Câmpul pentru a stoca resursa imaginii asociate evenimentului

    private List<TicketCategory> ticketCategories; // Adăugăm lista de categorii de bilete
    public Event(int eventID, int venueID, int eventTypeID, String eventDescription, String eventName,
                 Date startDate, Date endDate, int imageResource, List<TicketCategory> ticketCategories) {
        this.eventID = eventID;
        this.venueID = venueID;
        this.eventTypeID = eventTypeID;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageResource = imageResource;
        this.ticketCategories = ticketCategories; // Inițializăm lista de categorii de bilete
    }


    // Getters pentru a obține valorile câmpurilor
    public int getEventID() {
        return eventID;
    }

    public int getVenueID() {
        return venueID;
    }

    public int getEventTypeID() {
        return eventTypeID;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getImageResource() {
        return imageResource;
    }
    public List<TicketCategory> getTicketCategories() {
        return ticketCategories;
    }
}