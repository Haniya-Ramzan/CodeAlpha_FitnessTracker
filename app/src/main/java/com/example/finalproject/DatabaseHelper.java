package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FitnessApp.db";

    public static final String TABLE_USERS = "users";
    public static final String TABLE_BMI = "bmi";
    public static final String TABLE_WATER = "water";
    public static final String TABLE_CALORIES = "calories";
    public static final String TABLE_EXERCISE = "exercise";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT," +
                "password TEXT)");

        db.execSQL("CREATE TABLE bmi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "weight REAL," +
                "height REAL," +
                "bmi REAL)");

        db.execSQL("CREATE TABLE water (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "glasses INTEGER," +
                "date TEXT)");

        db.execSQL("CREATE TABLE calories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "calories INTEGER," +
                "date TEXT)");

        db.execSQL("CREATE TABLE exercise (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "completed INTEGER," +
                "date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS bmi");
        db.execSQL("DROP TABLE IF EXISTS water");
        db.execSQL("DROP TABLE IF EXISTS calories");
        db.execSQL("DROP TABLE IF EXISTS exercise");

        onCreate(db);
    }

    // ================= USERS =================

    public boolean insertUser(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("password", password);

        return db.insert(TABLE_USERS, null, values) != -1;
    }

    public boolean checkUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();

        return exists;
    }

    // ================= BMI =================

    public boolean insertBMI(double weight, double height, double bmi) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("weight", weight);
        values.put("height", height);
        values.put("bmi", bmi);

        return db.insert(TABLE_BMI, null, values) != -1;
    }

    public Cursor getBMIHistory() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM bmi ORDER BY id DESC",
                null);
    }

    // ================= WATER =================

    public boolean insertWater(int glasses) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("glasses", glasses);
        values.put("date", getDateTime());

        return db.insert(TABLE_WATER, null, values) != -1;
    }

    public Cursor getWaterHistory() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM water ORDER BY id DESC",
                null);
    }

    // ================= CALORIES =================

    public boolean insertCalories(int calories) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("calories", calories);
        values.put("date", getDateTime());

        return db.insert(TABLE_CALORIES, null, values) != -1;
    }

    public Cursor getCaloriesHistory() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM calories ORDER BY id DESC",
                null);
    }

    // ================= EXERCISE =================

    public boolean insertExercise(int completed) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("completed", completed);
        values.put("date", getDateTime());

        return db.insert(TABLE_EXERCISE, null, values) != -1;
    }

    public Cursor getExerciseHistory() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM exercise ORDER BY id DESC",
                null);
    }

    // ================= DATE METHOD =================

    private String getDateTime() {

        return new SimpleDateFormat(
                "dd-MM-yyyy hh:mm a",
                Locale.getDefault()
        ).format(new Date());
    }
    // ================= DELETE CALORIES =================
    public boolean deleteCalories(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_CALORIES,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    // ================= UPDATE CALORIES =================
    public boolean updateCalories(int id, int calories) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("calories", calories);
        values.put("date", getDateTime());

        int result = db.update(
                TABLE_CALORIES,
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }
    public boolean deleteBMI(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_BMI,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    public boolean updateBMI(int id, double weight) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("weight", weight);

        int result = db.update(
                TABLE_BMI,
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }
}