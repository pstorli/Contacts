package com.sd.contacts.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pstorli 9/19/2017
 *
 * User Database Helper class.
 */
public class UserHelper extends SQLiteOpenHelper
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////

    // Table Name
    static final        String TABLE_NAME   = "USERS";

    // Table columns
    public static final String _ID          = "_id";
    public static final String NAME         = "name";
    public static final String IMAGE        = "image";

    // Database Information
    private static final String         DB_NAME     = "USERS.DB";

    // database version
    private static final int            DB_VERSION  = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + IMAGE + " TEXT);";

    /**
     * Constructor
     *
     * @param context for this class
     */
    UserHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param db to use
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     *
     * @param db to use
     * @param oldVersion old db ver
     * @param newVersion new db ver
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
