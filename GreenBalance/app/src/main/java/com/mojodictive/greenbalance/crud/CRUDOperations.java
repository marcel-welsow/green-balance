package com.mojodictive.greenbalance.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mojodictive.greenbalance.model.IUser;
import com.mojodictive.greenbalance.model.User;

public class CRUDOperations implements ICRUDOperations {

    private static final String TABLE = "USER";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String DATE = "DATE";

    private SQLiteDatabase database;

    public CRUDOperations(Context context) {

        database = context.openOrCreateDatabase("mojodictive.green.balance", Context.MODE_PRIVATE, null);

        if (database.getVersion() == 0) {

            database.setVersion(1);
            database.execSQL("CREATE TABLE" + TABLE + " (ID INTEGER PRIMARY KEY, " + NAME + " TEXT, " + EMAIL + " TEXT, " + DATE + " INTEGER)");
        }
    }

    @Override
    public IUser readUser() {

        Cursor cursor = database.query(TABLE, new String[]{"ID", NAME, EMAIL, DATE}, null, null, null, null, "ID");

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            IUser user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndex("ID")));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            user.setDate(cursor.getLong(cursor.getColumnIndex(DATE)));

            return user;
        }

        return null;
    }

    @Override
    public IUser createUser(IUser user) {

        Long id = database.insert(TABLE, null, buildContentValues(user));

        user.setId(id);

        return user;
    }

    @Override
    public IUser updateUser(IUser user) {

        database.update(TABLE, buildContentValues(user), "ID=?", new String[]{String.valueOf(user.getId())});

        return user;
    }

    @Override
    public Boolean deleteUser(IUser user) {

        int numOfRows = database.delete(TABLE, "ID=?", new String[]{String.valueOf(user.getId())});

        return numOfRows > 0;
    }

    private ContentValues buildContentValues(IUser user) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user.getName());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(DATE, user.getDate());

        return contentValues;
    }
}
