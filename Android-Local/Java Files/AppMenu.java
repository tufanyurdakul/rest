package com.example.restapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class AppMenu extends AppCompatActivity {
    private String language,path="";
    private TextView addTable,addFood,changeLanguage,tables;
    private ImageView imgCL,imgAddTable,imgTables,imgAddFood;
    private SQLiteDatabase myDatabase;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_menu);
        language=MainActivity.myLanguage;

        tables=findViewById(R.id.txtTables);
        addTable=findViewById(R.id.txtAddTables);
        addFood=findViewById(R.id.txtaddFood);
        changeLanguage=findViewById(R.id.txtlang);
        imgTables=findViewById(R.id.imgTables);
        openAMyDatabase();

        File myDbPath=getApplication().getFilesDir();
         path=myDbPath+"/test";
       imgCL = (ImageView) findViewById(R.id.changeL);
       openAMyDatabase();
        imgCL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateLanguage();

            }
        });
        imgAddTable = (ImageView) findViewById(R.id.imgTable);
        imgAddTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              dialogs();

            }
        });
        imgTables.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), Tables.class);
                startActivity(activity2Intent);
            }
        });
        imgAddFood=findViewById(R.id.imgaddfood);
        imgAddFood.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), Foods.class);
                startActivity(activity2Intent);
            }
        });

        if(language=="Turkce"){
            tables.setText("Masalar");
            addTable.setText("Masa Ekle");
            addFood.setText("Yemek Ekle");
            changeLanguage.setText("Dil Değiştir");
        }else{
            tables.setText("Tables");
            addTable.setText("Add Tables");
            addFood.setText("Add Food");
            changeLanguage.setText("Change Language");
        }

    }
    private void updateLanguage(){

        SQLiteDatabase  myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("first","yes");

        //These Fields should be your String values of actual column names
        myDatabase.update("languagess",cv,"recID=1",null);
        Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activity2Intent);


    }
    public void dialogs(){
        boolean isNumber;
        String topic="",question="",cancel="",okey="";
        if(language=="Turkce"){
           topic="Masa Ekle";
           question="Kaç Masa Eklemek İstiyorsunuz?";
           cancel="iptal";
           okey="ekle";
        }
        if(language=="English"){
            topic="Add Table";
            question="How much table do you want to add?";
            cancel="cancel";
            okey="add";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(question);

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton(okey, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              String inputs= input.getText().toString();
          boolean isNumber=control(inputs);

              if(isNumber) {
                  int lastItem = getLastId();
                  insert(inputs, lastItem);
              }
              else{
                  getErrorMessage();
              }
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
public void insert(String inputs,int lasItem){
        lasItem+=1;

        int how=Integer.parseInt(inputs);
        for(int i=0; i<how; i++){
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String adding=String.valueOf(lasItem);
            String input="insert into tables (name) values ('"+adding+"')";
            myDatabase.execSQL(input);
            lasItem++;

        }

    myDatabase.close();
    Toast.makeText(this, " column created.", Toast.LENGTH_LONG).show();

}
    private void openAMyDatabase() {

            try {
                //create Db if it's not.
                myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

                myDatabase.needUpgrade(5);
                int x=myDatabase.getVersion();

                //create table
                String tablex = "create table if not exists tables("
                        +"TID integer primary key autoincrement, "
                        +" name text);";
                //execute the SQL script
                myDatabase.execSQL(tablex);
                //now we have the "student" table


                myDatabase.close();
                Toast.makeText(this, " table created."+x, Toast.LENGTH_LONG).show();

            } catch (SQLiteException e) {
                int x=myDatabase.getVersion();
                Toast.makeText(this, " error."+x, Toast.LENGTH_LONG).show();
            } }
    private boolean isExist(){
        File myFile=new File(path);
        return myFile.exists();


    }
    private int getLastId(){
        ArrayList<String> last=new ArrayList<String>();
        int lastIdIs;
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String input="SELECT * from tables order by TID DESC limit 1" ;
        Cursor result=myDatabase.rawQuery(input,null);
        result.moveToFirst();
        while(result.isAfterLast() == false) {
            last.add(result.getString(result.getColumnIndex("TID")));

            result.moveToNext();
        }
        if(last.size()!=0) {
            lastIdIs = Integer.parseInt(last.get(0));
        }else{
            lastIdIs=0;
        }
        return  lastIdIs;
    }
    public boolean control(String inputs){
       boolean isNumber=false;
        for(int i=0; i<inputs.length();i++){
            if(!(inputs.charAt(i)>47&&inputs.charAt(i)<58)){

                isNumber=false;
                break;
            }else{
                isNumber=true;
            }

        }
        return isNumber;
    }
    public void getErrorMessage(){
       String error="",name="",btn="";
        if(language=="English"){
            error="error";
            name="please enter number";
            btn="Ok";
        }else{
            error="Hata";
            name="Lütfen Sayı Giriniz";
            btn="Tamam";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // TOPIC
        alertDialogBuilder.setTitle(error);

        // FEATURES
        alertDialogBuilder
                .setMessage(name)
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher_round)
                // WHAT DOES IT DO WHEN IT CLICKES
                .setNegativeButton(btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
dialog.cancel();
                    }
                });

        // CREATE A ALERT DIALOG
        AlertDialog alertDialog = alertDialogBuilder.create();

        // SHOW ALERT DIALOG
        alertDialog.show();
    }
}