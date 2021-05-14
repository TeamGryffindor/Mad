package com.example.madproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input,dob_input,address_input,phone_input,position_input,salary_input;
    Button addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        dob_input = findViewById(R.id.dob_input);
        address_input = findViewById(R.id.address_input);

        addbutton = findViewById(R.id.addbutton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(name_input.getText().toString().trim(),
                        dob_input.getText().toString().trim(),
                        address_input.getText().toString().trim());
            }
        });
    }
}