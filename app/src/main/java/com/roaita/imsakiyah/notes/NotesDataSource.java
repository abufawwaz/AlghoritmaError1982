package com.roaita.imsakiyah.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotesDataSource {

    // Database fields
    /*
    type INFAK, CATATAN, TIMER,INFO,TAKMIR

     */
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private static final String DATABASE_NAME = "notes.db";
    public NotesDataSource(Context context) {dbHelper = new SQLiteHelper(context);}
    public void open() throws SQLException {database = dbHelper.getWritableDatabase();}
    public void close() {dbHelper.close();}

    public long insertInfak(String type,String trx, String ket,String tgl, String nominal) {
        this.open();
        ContentValues insertValues = new ContentValues();
        insertValues.put(SQLiteHelper.COLUMN_TYPE, trx);
        insertValues.put(SQLiteHelper.COLUMN_DATA_SATU, ket);
        insertValues.put(SQLiteHelper.COLUMN_DATA_DUA, tgl);
        insertValues.put(SQLiteHelper.COLUMN_DATA_TIGA, nominal);
        insertValues.put(SQLiteHelper.COLUMN_DATA_EMPAT, "");
        insertValues.put(SQLiteHelper.COLUMN_DATA_LIMA, "");
        insertValues.put(SQLiteHelper.COLUMN_DATA_ENAM, "");
        long val = database.insert(SQLiteHelper.TABLE_IMSAKIYAH, null, insertValues);
        this.close();
        return val;
    }
    public ArrayList<NoteItems> getAllNotes2() {
        this.open();
        ArrayList<NoteItems> items = new ArrayList<NoteItems>();
        Cursor  cursor = database.rawQuery("select * from notes",null);

        if (cursor .moveToFirst()) {

            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TITLE));
                String last_reviewed = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_LAST_REVIEWED));
                String total_reviews = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TOTAL_REVIEWS));
                String content  = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CONTENT));

                NoteItems item = new NoteItems(title,last_reviewed,total_reviews,content);
                items.add(item);
                cursor.moveToNext();
            }
        }
        this.close();
        return items;
    }
    public ArrayList<ImsakiyahItems> getAllImsakiyah() {
        //mode getAll, getData, cari
        // type infak timer catatan quote
        this.open();
        ArrayList<ImsakiyahItems> items = new ArrayList<ImsakiyahItems>();
        Cursor  cursor = database.rawQuery("select * from imsakiyah",null);
       /* if(mode == "getAll")cursor = database.rawQuery("select * from imsakiyah",null);
        if(mode == "getData")cursor = database.rawQuery("select * from imsakiyah",null);
        if(mode == "cari")cursor = database.rawQuery("select * from imsakiyah WHERE "+NamaCol+" = "+dicari,null);
        */

        if (cursor .moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String sType = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TYPE));
                String sDataSatu = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_SATU));
                String sDataDua = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_DUA));
                String sDataTiga = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_TIGA));
                String sDataEmpat = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_EMPAT));
                String sDataLima = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_LIMA));
                String sDataEnam = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DATA_ENAM));
                ImsakiyahItems item = new ImsakiyahItems(sType,sDataSatu,sDataDua,sDataTiga,sDataEmpat,sDataLima,sDataEnam);
                items.add(item);
                cursor.moveToNext();
            }
        }
        this.close();
        return items;
    }  // String mode, String Type, String dicari, String NamaCol
    public long deleteDataImsakiyah(String id, String Sdatasatu) {
        this.open();
        database.delete(SQLiteHelper.TABLE_IMSAKIYAH, SQLiteHelper.COLUMN_DATA_SATU + " = ?", new String[] { String.valueOf(Sdatasatu) });
        this.close();
        return 0;
    }

    public long insertNotes(String title, String content) {
        this.open();
        ContentValues insertValues = new ContentValues();
        insertValues.put(SQLiteHelper.COLUMN_TITLE, title);
        insertValues.put(SQLiteHelper.COLUMN_CONTENT, content);
        long epoch = System.currentTimeMillis();
        insertValues.put(SQLiteHelper.COLUMN_LAST_REVIEWED, epoch);
        insertValues.put(SQLiteHelper.COLUMN_TOTAL_REVIEWS, 0);
        long val = database.insert(SQLiteHelper.TABLE_NOTES, null, insertValues);
        this.close();
        return val;
    }
    public long deleteOne(String title, String content) {
        this.open();
        database.delete(SQLiteHelper.TABLE_NOTES, SQLiteHelper.COLUMN_CONTENT + " = ?", new String[] { String.valueOf(content) });
        this.close();
        return 0;
    }
    public long incrementTotalReviews(String content) {
        this.open();
        String sql = "UPDATE " + SQLiteHelper.TABLE_NOTES +
                " SET " + SQLiteHelper.COLUMN_TOTAL_REVIEWS + "=" + SQLiteHelper.COLUMN_TOTAL_REVIEWS + "+1" +
                " WHERE " + SQLiteHelper.COLUMN_CONTENT + " >= '" + content+"'";

        database.execSQL(sql);
        /*database.execSQL("UPDATE " + SQLiteHelper.TABLE_NOTES + " SET "
                + SQLiteHelper.COLUMN_TOTAL_REVIEWS + " = " + SQLiteHelper.COLUMN_TOTAL_REVIEWS + " +1 WHERE "
                + SQLiteHelper.COLUMN_CONTENT + " = " +content);*/
        this.close();
        return 0;

    }
    public long modifyLastSeen(String content) {
        this.open();
        String sql = "UPDATE " + SQLiteHelper.TABLE_NOTES +
                " SET " + SQLiteHelper.COLUMN_LAST_REVIEWED + "=" + System.currentTimeMillis()+
                " WHERE " + SQLiteHelper.COLUMN_CONTENT + " >= '" + content+"'";

        database.execSQL(sql);
        /*database.execSQL("UPDATE " + SQLiteHelper.TABLE_NOTES + " SET "
                + SQLiteHelper.COLUMN_TOTAL_REVIEWS + " = " + SQLiteHelper.COLUMN_TOTAL_REVIEWS + " +1 WHERE "
                + SQLiteHelper.COLUMN_CONTENT + " = " +content);*/
        this.close();
        return 0;

    }
    public List<NoteItems> getAllNotesForNotification() {
        this.open();
        List<NoteItems> items = new ArrayList<NoteItems>();
        Cursor  cursor = database.rawQuery("select * from notes",null);

        if (cursor .moveToFirst()) {

            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TITLE));
                String last_reviewed = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_LAST_REVIEWED));
                String total_reviews = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TOTAL_REVIEWS));
                String content  = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CONTENT));

                NoteItems item = new NoteItems(title,last_reviewed,total_reviews,content);
                long past_epoch = Long.valueOf(item.last_reviewed);
                long current_epoch = System.currentTimeMillis();
                long difference = current_epoch - past_epoch;
                if (notificationRequired(difference,Integer.valueOf(item.total_reviews))) {
                    items.add(item);
                }
                cursor.moveToNext();
            }
        }
        this.close();
        return items;
    }
    public boolean notificationRequired(long difference, int times) {
        if (times == 0){
            return true;
        }
        else if (times == 1 && difference >= 60000*5)
        {
            return true;
        }
        else if (times == 2 && difference >=60000*10)
        {
            return true;
        }
        else if (times == 3 && difference >=60000*30)
        {
            return true;
        }
        else if (times == 4 && difference >=60000*60)
        {
            return true;
        }
        else if (times == 5 && difference >=60000*60*2)
        {
            return true;
        }
        else if (times == 6 && difference >=60000*60*4)
        {
            return true;
        }
        else if (times == 7 && difference >=60000*60*8)
        {
            return true;
        }
        else if (times == 8 && difference >=60000*60*12)
        {
            return true;
        }
        else if (times == 9 && difference >=60000*60*24)
        {
            return true;
        }
        else if (times == 10 && difference >=60000*60*24*2)
        {
            return true;
        }
        else if (times == 11 && difference >=60000*60*24*4)
        {
            return true;
        }
        else if (times == 11 && difference >=60000*60*24*8)
        {
            return true;
        }
        else if (times == 11 && difference >=60000*60*24*16)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
