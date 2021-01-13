package com.example.todoappbekir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddToDoActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText contentEditText;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        nameEditText = findViewById(R.id.toDoNameEditTXT);
        contentEditText = findViewById(R.id.toDoContentEditTXT);

        helper = new DataBaseHelper(this);
        helper.open();
    }

    public void addBtnAdd(View view) {
        String name = nameEditText.getText().toString();
        String content = contentEditText.getText().toString();

        //      DA INSERTE ICH DIE NEU EINGEGEBENE TODO IN DIE DATENBANK REIN MIT DER ADD METHODE DIE ICH IN DATABASEHELPER ERSTELLT HABE
        helper.add(name,content);

        Intent main = new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

    public void cancelBtn(View view) {
        finish();
    }
}