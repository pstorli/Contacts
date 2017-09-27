package com.sd.contacts.events;

/**
 * Created by pstorli on 9/25/2017.
 *
 * Event sent when the search button is pressed.
 */

public class SearchEvent implements ContactsEvent
{
    private String searchFor = "";

    public SearchEvent(String searchText) {
        this.searchFor = searchText;
    }

    /**
     *
     * @return - the text to search for.
     */
    public String getSearchText ()
    {
        return searchFor;
    }
}
