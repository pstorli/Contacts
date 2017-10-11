package com.sd.contacts.fragment;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sd.contacts.R;
import com.sd.contacts.db.UserHelper;
import com.sd.contacts.db.UserManager;
import com.sd.contacts.events.AddEvent;
import com.sd.contacts.events.HideMenuEvent;
import com.sd.contacts.events.ShowMenuEvent;
import com.sd.contacts.events.TitleEvent;
import com.sd.contacts.events.UserEvent;
import com.sd.contacts.util.Helper;

import de.greenrobot.event.EventBus;

/**
 * Created by pstorli on 9/19/2017.
 *
 * Fragment used to show contacts list.
 * It is also the first fragment shown and the main one.
 */
public class Contacts extends Fragment
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    private UserManager         dbManager;
    private SimpleCursorAdapter adapter;
    final String[]              from = new String[] { UserHelper._ID, UserHelper.IMAGE, UserHelper.NAME };
    final int[]                 to   = new int[]    { R.id.userId,    R.id.userImagePath,   R.id.userName };

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////////////////////////

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
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Set the title to contacts.
        EventBus.getDefault().post(new TitleEvent(R.string.contacts));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contacts_view, container, false);

        // Set up the user list.
        setUpUserList (view);

        // Subscribe to EventBus events
        EventBus.getDefault().register(this);

        // Hide the save and home buttons.
        EventBus.getDefault().post(new HideMenuEvent(R.id.action_home));
        EventBus.getDefault().post(new HideMenuEvent(R.id.action_save));

        // Show the user and search buttons.
        EventBus.getDefault().post(new ShowMenuEvent(R.id.action_user));
        EventBus.getDefault().post(new ShowMenuEvent(R.id.action_search));

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

    /**
     * Note that we are doing a version check to see if we need to call a depreciated method or not, so suppress the error.
     * @param view - the contacts view that we just created.
     */
    @SuppressWarnings("deprecation")
    public void setUpUserList (View view)
    {
        // Set up user db.
        dbManager = new UserManager();
        dbManager.open(getActivity());
        Cursor cursor = dbManager.fetch();

        adapter = new SimpleCursorAdapter(getActivity(), R.layout.contacts_user_record, cursor, from, to, 0);

        // Images need special handling.
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder()
        {
            /** Binds the Cursor column defined by the specified index to the specified view */
            public boolean setViewValue (View view, Cursor cursor, int columnIndex){
                if(view.getId() == R.id.userImagePath) {
                    Drawable drawable = null;

                    String imagePath = cursor.getString (columnIndex);
                    if (null != imagePath && !imagePath.isEmpty()) {
                        try {
                            drawable = Drawable.createFromPath(imagePath);
                        }
                        catch (Exception ex) {
                            // Oops!
                            drawable = null;
                        }
                    }

                    // Use default drawable?
                    if (null == drawable) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //>= API 21
                            drawable = getResources().getDrawable(R.drawable.ic_user, getActivity().getTheme());
                        } else {
                            drawable = getResources().getDrawable(R.drawable.ic_user);
                        }
                    }

                    // Do we have a custom image or should we just use the stock one?
                    if (null != drawable) {
                        // Get imageview as the view passed in is the text view.
                        ViewParent parent = view.getParent();
                        if (null != parent && parent instanceof ViewGroup) {
                            ImageView imageView = (ImageView) ((ViewGroup)parent).findViewById (R.id.userImage);
                            if (null != imageView) {
                                imageView.setImageDrawable(drawable);
                            }
                        }
                    }
                }

                // Note always return false so that this column gets mapped to the R.id.userImagePath TextView
                return false;
            }
        });

        // retrieve the list view and set the adapter.
        ListView listView = (ListView) view.findViewById(R.id.user_list_view);
        if (null != listView) {
            listView.setEmptyView(view.findViewById(R.id.empty));
            listView.setAdapter(adapter);

            // OnCLickListiner For List Items
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                    TextView idTextView = (TextView) view.findViewById(R.id.userId);
                    TextView pathTextView = (TextView) view.findViewById(R.id.userImagePath);
                    TextView nameTextView = (TextView) view.findViewById(R.id.userName);

                    String id = idTextView.getText().toString();
                    String path = pathTextView.getText().toString();
                    String name = nameTextView.getText().toString();

                }
            });
        }

        adapter.notifyDataSetChanged();
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods listening to ContactsEvent eventts.
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method will be called when the add button is pressed.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (AddEvent event)
    {
        // When they click this, go to the user screen.
        showUserScreen ();
    }

    /**
     * This method will be called when the user button is pressed.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (UserEvent event)
    {
        // When they click this, go to the user screen.
        showUserScreen ();
    }

    /**
     * Show the user screen.
     */
    protected void showUserScreen ()
    {
        // Are we the current fragment?
        if (this == getFragmentManager().findFragmentById(Helper.MAIN_FRAG_ID)) {

            // When they click this switch out the fragment.

            // Add the add/edit user frag.
            getFragmentManager().beginTransaction().replace(Helper.MAIN_FRAG_ID, new User(), Helper.USER_FRAGMENT).commit();

        }
    }
}
