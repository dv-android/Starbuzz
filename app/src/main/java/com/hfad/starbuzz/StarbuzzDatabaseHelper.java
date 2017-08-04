package com.hfad.starbuzz;

/**
 * Created by DEVANG on 6/16/2017.
 */
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;


    StarbuzzDatabaseHelper(Context context){
         super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0,DB_VERSION);
    }

    private static void insertDrink(SQLiteDatabase db,String name,
                                    String description,int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("DRINK",null,drinkValues);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int neewVersion){
       updateMyDatabase(db,oldVersion,neewVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion,int newVersion){

        if(oldVersion<1){
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT,"
                    +" DESCRIPTION TEXT,"
                    +"IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(db,"Latte","Espresso and steamed milk",R.drawable.latte);
            insertDrink(db,"Cappuccino","Espresso , hot milk and steamed milk foam",R.drawable.cappuccino);
            insertDrink(db,"Filter","Our best drip coffee",R.drawable.filter);

        }
        if(oldVersion<2){

            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");

        }
    }
}


1500229800000
