package com.sd.contacts.events;

/**
 * Created by pstorli on 9/25/2017.
 *
 * Event sent to update the toolbar title.
 */

public class TitleEvent implements ContactsEvent
{
    private int id;

    public TitleEvent(int id) {
        this.id = id;
    }

    /**
     *
     * @return the string resource to change the text to.
     */
    public int getTitleId ()
    {
        return id;
    }
}
