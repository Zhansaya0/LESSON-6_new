package com.e.myapplication_database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.e.myapplication_database.StoreDB.COLUMN_EMAIL;
import static com.e.myapplication_database.StoreDB.COLUMN_GROUP_INFO;
import static com.e.myapplication_database.StoreDB.COLUMN_INFO;
import static com.e.myapplication_database.StoreDB.COLUMN_PASSWORD;
import static com.e.myapplication_database.StoreDB.COLUMN_STUDENT_GROUP_ID;
import static com.e.myapplication_database.StoreDB.COLUMN_SUM;
import static com.e.myapplication_database.StoreDB.COLUMN_UNIVERSITY_CHOICE;
import static com.e.myapplication_database.StoreDB.TABLE_GROUPS;
import static com.e.myapplication_database.StoreDB.TABLE_STUDENTS;
import static com.e.myapplication_database.StoreDB.TABLE_UNIVERSITY;

public class RegistrActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_name,editText_email,editText_password;
    Button button_signup,button_login;

    StoreDB storeDb;
    SQLiteDatabase sqLiteDatabase;
    Spinner group_spinner,group_spinner2;
    List<String> list = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }
    public void initViews(){

        editText_name=findViewById(R.id.editText_name);
        editText_email= findViewById(R.id.editText_email);
        editText_password= findViewById(R.id.editText_password);
        button_signup= findViewById(R.id.button_signup);
        button_login= findViewById(R.id.button_login);
        group_spinner= findViewById(R.id.group_spinner);
        group_spinner2=findViewById(R.id.group_spinner2);
        storeDb = new StoreDB(this);
        sqLiteDatabase = storeDb.getWritableDatabase();
        initSpinner();
        initSpinner2();
        button_signup.setOnClickListener(this);
        button_login.setOnClickListener(this);

    }
    public void initSpinner(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if((cursor != null && cursor.getCount()>0)) {
            while (cursor.moveToNext()) {
                String group_name = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_INFO));
                String group_sum = cursor.getString(cursor.getColumnIndex(COLUMN_SUM));
                String group_id = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_GROUP_ID));

                Log.i("Database","fullname: "+group_name );
                Log.i("Database","email: "+group_sum );
                Log.i("Database","password: "+group_id );
                list.add(group_name);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_group , list);
        group_spinner.setAdapter(adapter);
    }

    public void initSpinner2(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_UNIVERSITY, null);
        if((cursor != null && cursor.getCount()>0)) {
            while (cursor.moveToNext()) {
                String choise_university = cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_CHOICE));

                Log.i("Database","fullname: "+choise_university );
                list2.add(choise_university);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_group, list);
        group_spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_signup:

                boolean createAccount = true;

                if(TextUtils.isEmpty(editText_name.getText())){
                    editText_name.setError("Fill again");
                    createAccount = false;
                }
                if(TextUtils.isEmpty(editText_email.getText())){
                    editText_email.setError("Fill again");
                    createAccount = false;
                }
                if(TextUtils.isEmpty(editText_password.getText())){
                    editText_password.setError("Fill again");
                    createAccount = false;
                }
                if(createAccount) {
                    ContentValues versionValues = new ContentValues();
                    versionValues.put(COLUMN_INFO, editText_name.getText().toString());
                    versionValues.put(COLUMN_EMAIL, editText_email.getText().toString());
                    versionValues.put(COLUMN_PASSWORD, editText_password.getText().toString());
                    sqLiteDatabase.insert(TABLE_STUDENTS, null, versionValues);
                    Toast.makeText(this, "User engizildi", Toast.LENGTH_SHORT).show();
                    showDatabaseData();
                }else{
                    Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        TextView selection = (TextView) findViewById(R.id.selection);
        if (checkBox.isChecked()) {
            checkBox.setText("Yes");
        } else {
            checkBox.setText("No");
        }
    }

    public void showDatabaseData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
        if((cursor != null && cursor.getCount()>0)) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_INFO));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                Log.i("Database","fullname: "+name );
                Log.i("Database","email: "+email );
                Log.i("Database","password: "+password );
            }
        }
    }
}