package com.sd.contacts.util;

import android.app.Activity;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sd.contacts.R;

/**
 * Created by pstorli on 9/21/2017.
 *
 * Helper class to store universally used constants and methods.
 */
public class Helper
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    public static final int     MAIN_FRAG_ID = R.id.main_fragment;

    public static final String  CONTACTS_FRAGMENT   = "contacts";
    public static final String  USER_FRAGMENT       = "user";

    public static final int     ADDRESS_TAB_POS     = 0;
    public static final int     EMAIL_TAB_POS       = 1;
    public static final int     PHONE_TAB_POS       = 2;

    public static final int     USER_TAB_COUNT      = 3;

    /**
     * Hide the keyboard.
     *
     * @param activity - The activity associated with the keyboard
     */
    public static void hideSoftKeyboard (Activity activity)
    {
        if (null != activity) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (null != inputMethodManager) {
                View curView = activity.getCurrentFocus();
                if (null != curView) {
                    IBinder wt = curView.getWindowToken();
                    if (null != wt) {
                        inputMethodManager.hideSoftInputFromWindow(wt, 0);
                    }
                }
            }
        }
    }
}
