package com.sd.contacts.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sd.contacts.R;
import com.sd.contacts.events.AddEvent;
import com.sd.contacts.util.Helper;

import de.greenrobot.event.EventBus;

/**
 * Created by pstorli on 9/21/2017.
 *
 * The user address tab fragment.
 */
public class UserAddressTab extends Fragment
{
    /**
     * Overriden method onCreateView
     *
     * @param inflater the layout inflater to use
     * @param container the view parent
     * @param savedInstanceState the saved instance
     *
     * @return the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Return the layout view for this tab after inflating
        return inflater.inflate(R.layout.user_address_tab, container, false);
    }

    /**
     * Unregister fragment from event bus when it stops.
     */
    @Override
    public void onStop () {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods listening to ContactsEvent eventts.
    // /////////////////////////////////////////////////////////////////////////////////////////////

}
