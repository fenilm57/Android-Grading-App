package com.example.assignment2fm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int version = 1;
    public static final String dbName = "College.db";
    public static final String TABLE_NAME = "Programs";

    // Table Structure
    public static final String COL1 = "id";
    public static final String COL2 = "firstname";
    public static final String COL3 = "lastname";
    public static final String COL4 = "course";
    public static final String COL5 = "credits";
    public static final String COL6 = "marks";

    // Create Querry
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( " + COL1 + " INTEGER PRIMARY KEY  AUTOINCREMENT, " + COL2 + " TEXT NOT NULL," + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT); ";
    // Drop Table
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
    // Insert data
    public boolean InsertProgram(GradeInfo info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,info.getFirstname());
        cv.put(COL3,info.getLastname());
        cv.put(COL4,info.getCourse());
        cv.put(COL5,info.getCredits());
        cv.put(COL6,info.getMarks());
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // View data
    public Cursor ViewData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        //Move cursor to first if not null
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }
    // Search By ID
    public Cursor ViewDataById(int searchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME+" where id="+searchId, null);

        //Move cursor to first if not null
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }

    // Search By ProgramCourse
    public Cursor ViewDataByProgramCourse(String courses) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME + " where course="+courses, null);

        //Move cursor to first if not null
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }
}

