package com.sd.contacts.events;

/**
 * Created by pstorli on 9/25/2017.
 *
 * Event sent to hide a menmu item.
 */

public class HideMenuEvent implements ContactsEvent
{
    private int id;

    public HideMenuEvent(int id) {
        this.id = id;
    }

    public int getId ()
    {
        return id;
    }
}
