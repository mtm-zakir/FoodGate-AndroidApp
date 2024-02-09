package com.ousl.foodgate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ousl.foodgate.model.UserModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, email TEXT, mobileNumber INT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username, String email, String mobileNumber, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("mobileNumber",mobileNumber);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkuser(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkuserpasswords(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=? and password=?",new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //Get and Set Current User Details
    public ArrayList<UserModel> getUserDetails(String usedName){
        ArrayList<UserModel> aList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query="SELECT * FROM " + "users" + " WHERE username='"+usedName+"'";
        Cursor cursor=MyDB.rawQuery(query,null);

        if(cursor.moveToFirst()){
            String used_name = cursor.getString(0);
            String used_email = cursor.getString(1);
            String used_phone = cursor.getString(2);
            String used_password = cursor.getString(3);

            UserModel userModel=new UserModel();
            userModel.setUsed_name(used_name);
            userModel.setUsed_email(used_email);
            userModel.setUsed_phone(used_phone);
            userModel.setUsed_password(used_password);

            aList.add(userModel);
        }
        return aList;
    }
}

