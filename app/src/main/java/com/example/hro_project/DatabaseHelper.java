package com.example.hro_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LaborOffice.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Workers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, skills TEXT)");
        db.execSQL("CREATE TABLE Attendance (id INTEGER PRIMARY KEY AUTOINCREMENT, worker_id INTEGER, date TEXT, check_in TEXT, check_out TEXT, FOREIGN KEY (worker_id) REFERENCES Workers(id))");
        db.execSQL("CREATE TABLE Payroll (id INTEGER PRIMARY KEY AUTOINCREMENT, worker_id INTEGER, date TEXT, hours_worked REAL, wage REAL, total_pay REAL, FOREIGN KEY (worker_id) REFERENCES Workers(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Workers");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Payroll");
        onCreate(db);
    }

    // 인력 추가
    public void addWorker(String name, String phone, String skills) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("skills", skills);
        db.insert("Workers", null, values);
        db.close();
    }

    // 모든 인력 조회
    public Cursor getAllWorkers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id as _id, name, phone, skills FROM Workers", null);
    }

    // 인력 수정
    public void updateWorker(int id, String name, String phone, String skills) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("skills", skills);
        db.update("Workers", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // 인력 삭제
    public void deleteWorker(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Workers", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}