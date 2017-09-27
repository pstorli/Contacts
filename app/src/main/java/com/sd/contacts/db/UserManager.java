package com.sd.contacts.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pstorli 9/19/2017
 *
 * User Manager Database class
 */
public class UserManager
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    private UserHelper dbHelper;
    private SQLiteDatabase database;

    /**
     * Constructor.
     */
    public UserManager ()
    {
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Openthe db.
     *
     * @return this
     * @throws SQLException thrown is there is a problem.
     */
    public UserManager open (Context context) throws SQLException
    {
        dbHelper = new UserHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close the db.
     */
    public void close()
    {
        dbHelper.close();
    }

    /**
     * Insert into db.
     *
     * @param name  - user name
     * @param image - path to image. if empty uses user.png
     */
    public void insert (String name, String image)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(UserHelper.NAME, name);
        contentValue.put(UserHelper.IMAGE, image);
        database.insert(UserHelper.TABLE_NAME, null, contentValue);
    }

    /**
     * Retrieve cursor.
     *
     * @return the cursor
     */
    public Cursor fetch()
    {
        String[] columns = new String[] { UserHelper._ID, UserHelper.NAME, UserHelper.IMAGE };
        Cursor cursor = database.query(UserHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Update db record.
     *
     * @param _id   - this records db index
     * @param name  - user name
     * @param image - path to image. if empty uses user.png
     * @return the number of rows affected
     */
    public int update(long _id, String name, String image)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserHelper.NAME, name);
        contentValues.put(UserHelper.IMAGE, image);
        return database.update(UserHelper.TABLE_NAME, contentValues, UserHelper._ID + " = " + _id, null);
    }

    /**
     * Delete this record.
     *
     * @param _id - this records db index
     */
    public void delete(long _id)
    {
        database.delete(UserHelper.TABLE_NAME, UserHelper._ID + "=" + _id, null);
    }

}
