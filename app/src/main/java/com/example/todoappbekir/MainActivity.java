package com.example.todoappbekir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private DataBaseHelper meineHelper;

    private ListView listeView;
    private SimpleCursorAdapter adapter;

    private static final int LOADER_ID = 1976;

    final String [] from = new String[] {DataBaseHelper._ID,DataBaseHelper.NAME,DataBaseHelper.TODOCONTENT};

    final int [] zu = new int[] { R.id.id,R.id.name,R.id.content};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leere_list);

        meineHelper = new DataBaseHelper(this);
        meineHelper.open();

        listeView = findViewById(R.id.list_view);
        listeView.setEmptyView(findViewById(R.id.empty));

       LoaderManager.getInstance(this).initLoader(LOADER_ID,null,this);



        adapter = new SimpleCursorAdapter(this,R.layout.activity_view_todo, null,from,zu,0);
        listeView.setAdapter(adapter);

        listeView.setOnItemClickListener((parent, view, position, id) -> {
            TextView idTextView = view.findViewById(R.id.id);
            TextView nameTextView = view.findViewById(R.id.name);
            TextView contentTextView = view.findViewById(R.id.content);

            String iT = idTextView.getText().toString();
            String name = nameTextView.getText().toString();
            String content = contentTextView.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(),ModifityToDoActivity.class);
            modify_intent.putExtra("name",name);
            modify_intent.putExtra("content",content);
            modify_intent.putExtra("id",iT);

            startActivity(modify_intent);

        });


    }



    public void addButtonClick(View view) {
        Intent intent = new Intent(this,AddToDoActivity.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyLoader(this,meineHelper);
    }
 @Override
    public void onResume(){
        super.onResume();
        LoaderManager.getInstance(this).restartLoader(LOADER_ID,null,this);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        adapter.swapCursor(null);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.add_record) {
//            Intent add_mem = new Intent(this, AddToDoActivity.class);
//            startActivity(add_mem);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}