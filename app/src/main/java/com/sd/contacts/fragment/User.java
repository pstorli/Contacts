package com.sd.contacts.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sd.contacts.MainActivity;
import com.sd.contacts.R;
import com.sd.contacts.events.AddEvent;
import com.sd.contacts.events.HideMenuEvent;
import com.sd.contacts.events.HomeEvent;
import com.sd.contacts.events.SaveEvent;
import com.sd.contacts.events.ShowMenuEvent;
import com.sd.contacts.events.TitleEvent;
import com.sd.contacts.util.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * Created by pstorli on 9/20/2017.
 *
 * Fragment used to add or modify user info.
 */
public class User extends Fragment implements TabLayout.OnTabSelectedListener
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    protected Calendar  myCalendar  = Calendar.getInstance();
    protected EditText  edittext    = null;

    //This is our tablayout
    protected TabLayout tabLayout   = null;

    //This is our viewPager
    protected ViewPager viewPager   = null;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Set the title to users.
        EventBus.getDefault().post(new TitleEvent(R.string.users));

        // /////////////////////////////////////////////////////////////////////////////////////////////
        // Inflate the layout for this fragment
        // /////////////////////////////////////////////////////////////////////////////////////////////
        View view = inflater.inflate(R.layout.user_view, container, false);

        // /////////////////////////////////////////////////////////////////////////////////////////////
        // Birthday picker
        // /////////////////////////////////////////////////////////////////////////////////////////////
        edittext = (EditText) view.findViewById(R.id.userDOB);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // /////////////////////////////////////////////////////////////////////////////////////////////
        // Tabs
        // /////////////////////////////////////////////////////////////////////////////////////////////
        tabLayout = (TabLayout) view.findViewById(R.id.user_tab_layout);

        UserTabPager adapter = new UserTabPager (getContext(), getFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.userTabPager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.addOnTabSelectedListener(this);

        // Hide the save, search and user buttons.
        EventBus.getDefault().post(new HideMenuEvent(R.id.action_search));
        EventBus.getDefault().post(new HideMenuEvent(R.id.action_user));
        EventBus.getDefault().post(new HideMenuEvent(R.id.action_save));

        // Show the home button.
        EventBus.getDefault().post(new ShowMenuEvent(R.id.action_home));

        EventBus.getDefault().register(this);

        Helper.hideSoftKeyboard(getActivity()); // Hide the soft keyboard.

        return view;
    }

    /**
     * Unregister fragment from event bus when it stops.
     */
    @Override
    public void onStop () {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    /**
     * On fragemnt is being destroyed.
     */
    public void onDestroy () {
        super.onDestroy();
    }

    private void updateLabel()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods Implementing the TabLayout.OnTabSelectedListener interface.
    // /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onTabSelected (TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabUnselected (TabLayout.Tab tab)
    {
        Helper.hideSoftKeyboard(getActivity()); // Hide the soft keyboard.
    }

    @Override
    public void onTabReselected (TabLayout.Tab tab)
    {
        Helper.hideSoftKeyboard(getActivity()); // Hide the soft keyboard.
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods listening to ContactsEvent eventts.
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method will be called when the save button is pressed
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (SaveEvent event)
    {
        // Are we the current fragment?
        if (this == getFragmentManager().findFragmentById(Helper.MAIN_FRAG_ID)) {
            Helper.hideSoftKeyboard(getActivity()); // Hide the soft keyboard.

            // Hide the save button.
            EventBus.getDefault().post(new HideMenuEvent(R.id.action_save));

            // Save changes.
        }
    }

    /**
     * This method will be called when the home button is pressed.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (HomeEvent event)
    {
        // Are we the current fragment?
        if (this == getFragmentManager().findFragmentById(Helper.MAIN_FRAG_ID)) {
            Helper.hideSoftKeyboard(getActivity()); // Hide the soft keyboard.

            // Go back to main screen.
            getFragmentManager().beginTransaction().replace(Helper.MAIN_FRAG_ID, new Contacts(), Helper.CONTACTS_FRAGMENT).commit();
        }
    }
}
