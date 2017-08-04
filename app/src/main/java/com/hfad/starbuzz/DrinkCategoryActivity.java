package com.hfad.starbuzz;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static android.provider.Settings.NameValueTable.NAME;

public class DrinkCategoryActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();

        try{
             StarbuzzDatabaseHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
             db =  starbuzzDatabaseHelper.getReadableDatabase();

             cursor = db.query("DRINK",
                               new String[]{"_id","NAME"},
                               null ,null,null,null,null);

             CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                                             android.R.layout.simple_list_item_1,
                                             cursor,
                                             new String[]{"NAME"},
                                             new int[]{android.R.id.text1},
                                             0);
             listView.setAdapter(listAdapter);
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this,"Databae unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onListItemClick(ListView listview,
                                View itemview,
                                int position,
                                long id){
        Intent intent = new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int) id);
        startActivity(intent);
    }

    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
