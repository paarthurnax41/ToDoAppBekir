package com.example.todoappbekir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifityToDoActivity extends AppCompatActivity {

    private EditText nameModifyTXT;
    private EditText contentModifyTXT;
    private long _id;

    private DataBaseHelper hilfMir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifity_to_do);
        nameModifyTXT = findViewById(R.id.name_id_modify);
       contentModifyTXT = findViewById(R.id.content_id_modify);

       hilfMir = new DataBaseHelper(this);
       hilfMir.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");

        _id = Long.parseLong(id);

        nameModifyTXT.setText(name);
        contentModifyTXT.setText(content);

    }
    private void returnZuMainActivity()
    {
        finish();
    }

    public void deleteBtn(View view) {
        hilfMir.delete(_id);
        returnZuMainActivity();
    }

    public void updateBtn(View view) {
        String name = nameModifyTXT.getText().toString();
        String content = contentModifyTXT.getText().toString();

        hilfMir.update(_id, name, content);
        returnZuMainActivity();
    }
}