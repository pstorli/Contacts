/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sd.contacts;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.sd.contacts.events.AddEvent;
import com.sd.contacts.events.HideMenuEvent;
import com.sd.contacts.events.HomeEvent;
import com.sd.contacts.events.SaveEvent;
import com.sd.contacts.events.SearchEvent;
import com.sd.contacts.events.ShowMenuEvent;
import com.sd.contacts.events.TitleEvent;
import com.sd.contacts.events.UserEvent;
import com.sd.contacts.fragment.Contacts;
import com.sd.contacts.util.Helper;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////
    protected Menu      mainMenu        = null;
    protected Toolbar   toolbar         = null;

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to Main screen
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Called only once when the main menu is created.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        mainMenu = menu;

        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Retrieve the SearchView and plug it into SearchManager
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        if (null != searchMenuItem) {
            SearchView searchView = (SearchView) searchMenuItem.getActionView();
            if (null != searchView) {
                SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

                searchView.setOnQueryTextListener(this);
            }
        }

        // Fire off event when the save button is selected.
        MenuItem saveMenuItem = mainMenu.findItem(R.id.action_save);
        if (null != saveMenuItem) {
            saveMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    EventBus.getDefault().post(new SaveEvent());
                    return false;
                }
            });
        }

        // Fire off event when the home button is selected.
        MenuItem homeMenuItem = mainMenu.findItem(R.id.action_home);
        if (null != homeMenuItem) {
            homeMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    EventBus.getDefault().post(new HomeEvent());
                    return false;
                }
            });
        }

        // Fire off event when the user button is selected.
        MenuItem userMenuItem = mainMenu.findItem(R.id.action_user);
        if (null != userMenuItem) {
            userMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    EventBus.getDefault().post(new UserEvent());
                    return false;
                }
            });
        }

        // Add the contacts frag.
        getSupportFragmentManager().beginTransaction().add(Helper.MAIN_FRAG_ID, new Contacts(), Helper.CONTACTS_FRAGMENT).commit();

        // Subscribe to EventBus events
        EventBus.getDefault().register(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Unregister from event bus when it stops.
     */
    @Override
    public void onStop () {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    /**
     * They pressed the add button.
     * Notify the current fragment.
     *
     * @param view - The button that was pressed.
     */
    public void addButtonPressed (View view)
    {
        EventBus.getDefault().post(new AddEvent());
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        MenuItem searchMenuItem = mainMenu.findItem(R.id.action_search);
        if (null != searchMenuItem) {
            searchMenuItem.collapseActionView();
        }

        // Notify everyone that the search button was pressed.
        EventBus.getDefault().post(new SearchEvent(query));

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods listening to ContactsEvent eventts.
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method will be called to hide the indicated menu item.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (ShowMenuEvent event)
    {
        if (null != mainMenu) {
            int hideId = event.getId();

            MenuItem menuItem = mainMenu.findItem(hideId);
            if (null != menuItem) {
                menuItem.setVisible(true);
            }
        }
    }

    /**
     * This method will be called to hide the indicated menu item.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (HideMenuEvent event)
    {
        if (null != mainMenu) {
            int hideId = event.getId();

            MenuItem menuItem = mainMenu.findItem(hideId);
            if (null != menuItem) {
                menuItem.setVisible(false);
            }
        }
    }

    /**
     * This method will be called to change the title on the toolbar.
     *
     * Green robot uses reflection to determine correct method to call based on the event type.
     * @param event - event type to process.
     */
    public void onEvent (TitleEvent event)
    {
        if (null != toolbar) {
            toolbar.setTitle(event.getTitleId());
        }
    }
}

