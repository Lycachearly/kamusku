package com.chearly.kamusku.databases;

import static android.provider.BaseColumns._ID;
import static com.chearly.kamusku.databases.DatabaseContact.KamusColumns.KAMUS_DESCRIPTION;
import static com.chearly.kamusku.databases.DatabaseContact.KamusColumns.KAMUS_TITLE;
import static com.chearly.kamusku.databases.DatabaseContact.TABLE_KAMUS_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.chearly.kamusku.models.Kamus;

import java.util.ArrayList;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getReadableDatabase();
        return this;
    }

    public void  close(){
        databaseHelper.close();
    }

    public ArrayList<Kamus> getAllData(){
        Cursor cursor = database.query(TABLE_KAMUS_NAME, null, null, null, null, null,
                _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0){
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_TITLE)));
                kamus.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(KAMUS_DESCRIPTION)));

                arrayList.add(kamus);
                cursor.moveToNext();

                } while (!cursor.isAfterLast());
            }
            cursor.close();
            return arrayList;
        }

        public  long insertData(Kamus kamus) {
            ContentValues cv = new ContentValues();
            cv.put(KAMUS_TITLE, kamus.getTitle());
            cv.put(KAMUS_DESCRIPTION, kamus.getDescription());
            return database.insert(TABLE_KAMUS_NAME, null, cv);
        }
        
    }
}
