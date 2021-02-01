package com.e.myapplication_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Registration.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_GROUPS = "user_groups";
    public static final String TABLE_UNIVERSITY = "univetsity";

    public static final String COLUMN_INFO = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_STUDENT_GROUP_ID = "group_id";
    public static final String COLUMN_UNIVERSITY_CHOICE = "choise_university";

    public static final String COLUMN_GROUP_INFO = "group_name";
    public static final String COLUMN_SUM= "group_sum";


    Context context;

    public StoreDB (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_STUDENTS + "(" +
                COLUMN_INFO + "TEXT," +
                COLUMN_EMAIL + "TEXT," +
                COLUMN_STUDENT_GROUP_ID + "TEXT," +
                COLUMN_PASSWORD + "TEXT)" );

        db.execSQL("CREATE TABLE " + TABLE_GROUPS + "(" +
                COLUMN_GROUP_INFO + "TEXT," +
                COLUMN_SUM + "INTEGER," +
                COLUMN_STUDENT_GROUP_ID + "TEXT)" );

        db.execSQL("CREATE TABLE " + TABLE_UNIVERSITY +
                " (" + COLUMN_UNIVERSITY_CHOICE + " TEXT)");

        initGroups(db);
        initUniversity(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIVERSITY);

        onCreate(db);


    }

    private void initUniversity(SQLiteDatabase db) {
        ContentValues university1 = new ContentValues();
        university1.put(COLUMN_UNIVERSITY_CHOICE,"SDU");
        db.insert(TABLE_UNIVERSITY, null, university1);

        ContentValues university2 = new ContentValues();
        university2.put(COLUMN_UNIVERSITY_CHOICE,"NU");
        db.insert(TABLE_UNIVERSITY, null, university2);

        ContentValues university3 = new ContentValues();
        university3.put(COLUMN_UNIVERSITY_CHOICE,"AstanaIT");
        db.insert(TABLE_UNIVERSITY, null, university3);

        ContentValues university4 = new ContentValues();
        university4.put(COLUMN_UNIVERSITY_CHOICE,"MUIT");
        db.insert(TABLE_UNIVERSITY, null, university4);
    }

    public void initGroups(SQLiteDatabase db){
        ContentValues group1 = new ContentValues();
        group1.put(COLUMN_GROUP_INFO,"Marketing");
        group1.put(COLUMN_SUM,25);
        group1.put(COLUMN_STUDENT_GROUP_ID,"3A");
        db.insert(TABLE_GROUPS, null, group1);

        ContentValues group2 = new ContentValues();
        group2.put(COLUMN_GROUP_INFO,"Translators");
        group2.put(COLUMN_SUM,25);
        group2.put(COLUMN_STUDENT_GROUP_ID,"3B");
        db.insert(TABLE_GROUPS, null, group2);

        ContentValues group3 = new ContentValues();
        group3.put(COLUMN_GROUP_INFO,"Accounting");
        group3.put(COLUMN_SUM,25);
        group3.put(COLUMN_STUDENT_GROUP_ID,"3D");
        db.insert(TABLE_GROUPS, null, group3);

        ContentValues group4 = new ContentValues();
        group4.put(COLUMN_GROUP_INFO,"IT-4 years");
        group4.put(COLUMN_SUM,25);
        group4.put(COLUMN_STUDENT_GROUP_ID,"3E");
        db.insert(TABLE_GROUPS, null, group4);

        ContentValues group5 = new ContentValues();
        group5.put(COLUMN_GROUP_INFO,"IT-3 years");
        group5.put(COLUMN_SUM,25);
        group5.put(COLUMN_STUDENT_GROUP_ID,"3F");
        db.insert(TABLE_GROUPS, null, group5);
    }
}