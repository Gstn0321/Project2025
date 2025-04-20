package com.example.hro_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hr_database";
    private static final int DATABASE_VERSION = 2;

    // Workers Table
    public static final String TABLE_WORKERS = "workers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_SKILLS = "skills";
    public static final String COLUMN_TOTAL_PAY = "total_pay";

    // Attendance Table
    public static final String TABLE_ATTENDANCE = "attendance";
    public static final String COLUMN_ATTENDANCE_ID = "attendance_id";
    public static final String COLUMN_WORKER_ID = "worker_id";
    public static final String COLUMN_CHECK_IN_TIME = "check_in_time";
    public static final String COLUMN_CHECK_OUT_TIME = "check_out_time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createWorkersTable = "CREATE TABLE " + TABLE_WORKERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_SKILLS + " TEXT, " +
                COLUMN_TOTAL_PAY + " REAL);";

        String createAttendanceTable = "CREATE TABLE " + TABLE_ATTENDANCE + " (" +
                COLUMN_ATTENDANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WORKER_ID + " INTEGER, " +
                COLUMN_CHECK_IN_TIME + " TEXT, " +
                COLUMN_CHECK_OUT_TIME + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_WORKER_ID + ") REFERENCES " + TABLE_WORKERS + "(" + COLUMN_ID + "));";

        db.execSQL(createWorkersTable);
        db.execSQL(createAttendanceTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

    // Add Worker to Database
    public long addWorker(String name, String phone, String skills, double totalPay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_SKILLS, skills);
        values.put(COLUMN_TOTAL_PAY, totalPay);

        long id = db.insert(TABLE_WORKERS, null, values);
        db.close();
        return id;
    }

    // Add Attendance
    public long addAttendance(int workerId, String checkInTime, String checkOutTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKER_ID, workerId);
        values.put(COLUMN_CHECK_IN_TIME, checkInTime);
        values.put(COLUMN_CHECK_OUT_TIME, checkOutTime);

        long id = db.insert(TABLE_ATTENDANCE, null, values);
        db.close();
        return id;
    }

    // Get All Workers
    public Cursor getAllWorkers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_WORKERS, null, null, null, null, null, null);
    }

    // Get All Attendances for a Worker
    public Cursor getAttendancesForWorker(int workerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_WORKER_ID + "=?";
        String[] selectionArgs = {String.valueOf(workerId)};
        return db.query(TABLE_ATTENDANCE, null, selection, selectionArgs, null, null, null);
    }
}
