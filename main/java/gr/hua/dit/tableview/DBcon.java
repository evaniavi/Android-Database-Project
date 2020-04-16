package gr.hua.dit.tableview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBcon extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE = "USERS";
    public static final String KEY_ID = "ID";
    public static final String NAME = "NAME";
    public static final String LNAME = "LAST_NAME";
    public static final String AGE = "AGE";

    public DBcon(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE + " ( "+
                KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME + " VARCHAR(20) NOT NULL, " +
                LNAME + " VARCHAR(20) NOT NULL, " +
                AGE + " INTEGER NOT NULL "+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String name,String surname,String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (name.isEmpty() || surname.isEmpty() || age.isEmpty()){
            return false;
        }
        values.put(NAME,name);
        values.put(LNAME,surname);
        values.put(AGE,age);
        long result =  db.insert(TABLE,null,values);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<String> viewData(String name, ArrayList<String> users){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] sname = new String[]{name};
        String[] tableColumns = new String[]{"ID","NAME","LAST_NAME","AGE"};
        Cursor cursor = db.query(TABLE,tableColumns,"NAME=?",sname,null,null,null);
        while(cursor.moveToNext()){
            users.add( cursor.getString(0));
            users.add( cursor.getString(1));
            users.add( cursor.getString(2));
            users.add( cursor.getString(3));
        }
        return users;
    }

}


