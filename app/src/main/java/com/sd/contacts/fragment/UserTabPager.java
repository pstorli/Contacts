package com.sd.contacts.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sd.contacts.R;
import com.sd.contacts.util.Helper;

/**
 * Created by pstorli on 9/21/2017.
 *
 * Custom tab pager that extends FragmentStatePagerAdapter
 */
class UserTabPager extends FragmentStatePagerAdapter
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    private Context context = null;

    /**
     * Constructor.
     *
     * @param context the context to use
     * @param fm the frag mgr to use
     */
    UserTabPager (Context context, FragmentManager fm)
    {
        super(fm);

        this.context = context;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Create the desired tab.
     *
     * @param position - the position of the fragment to retrieve.
     *
     * @return the new desire3d frag.
     */
    @Override
    public Fragment getItem (int position)
    {
        Fragment result;
        switch (position) {
            case Helper.ADDRESS_TAB_POS:
                result = new UserAddressTab();
                break;
            case Helper.EMAIL_TAB_POS:
                result = new UserAddressTab();
                break;
            case Helper.PHONE_TAB_POS:
                result = new UserAddressTab();
                break;
            default:
                // Got tab? NOT!
                result = null;
        }

        return result;
    }

    /**
     * Get the desired tabs title.
     *
     * @param position what tab?
     *
     * @return the tabs title
     */
    public CharSequence getPageTitle(int position)
    {
        String result;
        switch (position) {
            case Helper.ADDRESS_TAB_POS:
                result = context.getString (R.string.address);
                break;
            case Helper.EMAIL_TAB_POS:
                result = context.getString (R.string.email);
                break;
            case Helper.PHONE_TAB_POS:
                result = context.getString (R.string.phone);
                break;
            default:
                // Got tab? NOT!
                result = null;
        }

        return result;
    }

    /**
     * How many tabs have ye?
     * Drinks are on the house!
     *
     * @return how many tabs there are.
     */
    @Override
    public int getCount()
    {
        return Helper.USER_TAB_COUNT;
    }
}
