package com.e.myapplication_database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.e.myapplication_database.StoreDB.COLUMN_EMAIL;
import static com.e.myapplication_database.StoreDB.COLUMN_INFO;
import static com.e.myapplication_database.StoreDB.COLUMN_PASSWORD;
import static com.e.myapplication_database.StoreDB.TABLE_STUDENTS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    StoreDB storeDb;
    SQLiteDatabase sqLiteDatabasedb;

    EditText editText_email;
    EditText editText_password;
    Button button_submit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        storeDb = new StoreDB(this);
        sqLiteDatabasedb = storeDb.getWritableDatabase();

        editText_email= findViewById(R.id.editText_email);
        editText_password= findViewById(R.id.editText_password);
        button_submit2= findViewById(R.id.button_submit2);
        button_submit2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(editText_email.getText())){
            editText_email.setError("Fill again");
            return;
        }
        if(TextUtils.isEmpty(editText_password.getText())){
            editText_password.setError("Fill again");
            return;
        }
        Cursor userCursor = sqLiteDatabasedb.rawQuery(" SELECT * FROM " + TABLE_STUDENTS +
                " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[] {editText_email.getText().toString(),editText_password.getText().toString()});

        if (((userCursor != null) && (userCursor.getCount()>0))) {
            userCursor.moveToFirst();
            String userName = userCursor.getString(userCursor.getColumnIndex(COLUMN_INFO));
            Toast.makeText(this, "Siz satti kirdiniz! Welcome," + userName, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
        }

    }
}
