package com.example.madproject2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, dob_input, address_input;
    Button update_button,deletebutton;
    String id, name, dob, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        dob_input = findViewById(R.id.dob_input2);
        address_input = findViewById(R.id.address_input2);

        update_button = findViewById(R.id.updatebutton);
        deletebutton = findViewById(R.id.deletebutton);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, name, dob, address);

                name = name_input.getText().toString().trim();
                dob = dob_input.getText().toString().trim();
                address = address_input.getText().toString().trim();
            }
        });
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("dob")
                && getIntent().hasExtra("address")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            dob = getIntent().getStringExtra("dob");
            address = getIntent().getStringExtra("address");

            name_input.setText(name);
            dob_input.setText(dob);
            address_input.setText(address);
        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  " + name + "?");
        builder.setMessage("Are you sure you want to delete  " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}