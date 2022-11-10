package com.example.fsm;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnSimpan;
    EditText nama, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new Database(this);
        nama = findViewById(R.id.et_name);
        kapasitas = findViewById(R.id.et_kapasitas);
        btnSimpan = findViewById(R.id.btn_submit);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kelas WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'", null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE kelas SET nama='" +
                        nama.getText().toString() +"', kapasitas= '" +
                        kapasitas.getText().toString() +"' WHERE nama= '" +
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(Update.this, "Data berhasil di update", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });


    }
}