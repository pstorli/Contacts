package com.sd.contacts.events;

/**
 * Created by pstorli on 9/25/2017.
 *
 * Event sent to show a menmu item.
 */

public class ShowMenuEvent implements ContactsEvent
{
    private int id;

    public ShowMenuEvent(int id) {
        this.id = id;
    }

    public int getId ()
    {
        return id;
    }
}
