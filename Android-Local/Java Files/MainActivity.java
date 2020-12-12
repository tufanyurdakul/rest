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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textLanguage,textCL;
    Spinner languages;
    Button buttonOkay;
    Context context=this;
    private String path;
    private SQLiteDatabase myDatabase;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> isFirst=new ArrayList<String>();
    private boolean isTR=false;
    private String[] arrayLanguage;
    public static String myLanguage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textLanguage = findViewById(R.id.textLanguage);
        languages = findViewById(R.id.languages);
        textCL = findViewById(R.id.languageIs);

        buttonOkay = findViewById(R.id.btnOk);

        File myDbPath=getApplication().getFilesDir();
        path=myDbPath+"/test";
        openAMyDatabase();
        selectLanguage();

        if(isTR) {
           arrayLanguage= new String[]{"Turkce", "Ingilizce"};
        }else{
            arrayLanguage= new String[]{"English", "Turkish"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayLanguage);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languages.setAdapter(adapter);
        if(isFirst.get(0).equals("no")){
            Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
            startActivity(activity2Intent);
        }else{

        }



    }


    private void openAMyDatabase() {
        if (!isExist()) {
        try {
               //create Db if it's not.
                myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

                //create table
                String table = "create table languagess("
                        + "recID integer primary key autoincrement, "
                         +" first text,"
                        + " language text);";
                //execute the SQL script
                myDatabase.execSQL(table);
                //now we have the "student" table
            Toast.makeText(this, " table created.", Toast.LENGTH_LONG).show();

                myDatabase.close();
                insert(("English"));

        } catch (SQLiteException e) {
            Toast.makeText(this, " error.", Toast.LENGTH_LONG).show();
        } }}
public void insert(String language){
    myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    String input="insert into languagess (first,language) values ('yes','"+language+"')";
    myDatabase.execSQL(input);
    myDatabase.close();
    Toast.makeText(this, " column created.", Toast.LENGTH_LONG).show();
    myLanguage="English";
}
private void updateLanguage(String language){
    Toast.makeText(this, language, Toast.LENGTH_LONG).show();
    myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    ContentValues cv = new ContentValues();
    cv.put("first","no");
    cv.put("language",language);
    //These Fields should be your String values of actual column names
    myDatabase.update("languagess",cv,"recID=1",null);
    myLanguage=language;

}

private void selectLanguage(){
   arrayList=new ArrayList<String>();
   isFirst=new ArrayList<String>();
    myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    String input="SELECT * FROM languagess WHERE recId=1" ;
    Cursor result=myDatabase.rawQuery(input,null);
    result.moveToFirst();
    while(result.isAfterLast() == false) {
        arrayList.add(result.getString(result.getColumnIndex("language")));
        isFirst.add(result.getString(result.getColumnIndex("first")));
        result.moveToNext();
    }
      UserChoosenLanguage lang=new UserChoosenLanguage();
         if(arrayList.get(0).equals("English")||arrayList.get(0).equals("Ingilizce")){

             lang.isEnglish();
             isTR=false;
             myLanguage="English";
             arrayLanguage=new String[]{"English","Turkish"};

         }else{
             lang.isTurkish();
             arrayLanguage=new String[]{"Turkce","Ingilizce"};
             isTR=true;
             myLanguage="Turkce";
         }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, arrayLanguage);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    languages.setAdapter(adapter);
         textCL.setText(lang.textLangis+" " + arrayList.get(0));
         buttonOkay.setText(lang.btnChoose);
         textLanguage.setText(lang.textLang);


    Toast.makeText(this, " selected"+arrayList.get(0), Toast.LENGTH_LONG).show();
    myDatabase.close();
}
    public void buttonOkay(View view) {


         final  String choosenLanguage  = languages.getSelectedItem().toString();


        if (1<0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // TOPIC
            alertDialogBuilder.setTitle("ERROR");

            // FEATURES
            alertDialogBuilder
                    .setMessage("PLEASE CHOOSE A LANGUAGE")
                    .setCancelable(false)
                    .setIcon(R.mipmap.ic_launcher_round)
                    // WHAT DOES IT DO WHEN IT CLICKES
                    .setPositiveButton("OKEY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            // CREATE A ALERT DIALOG
            AlertDialog alertDialog = alertDialogBuilder.create();

            // SHOW ALERT DIALOG
            alertDialog.show();
        }else{
            String topic="",choice ="",okey="",cancel="";
            if(choosenLanguage.equals("English")||choosenLanguage.equals("Ingilizce")) {
                topic = "CHOOSEN LANGUAGE";
                choice = "DO YOU WANT TO CHOOSE ENGLISH?";
                okey = "YES";
                cancel = "NO";
            }
            else if(choosenLanguage.equals("Turkish")||choosenLanguage.equals("Turkce")){
                topic="SEÇİLEN DİL";
                choice="DİLİN TÜRKÇE OLMASINI İSTİYOR MUSUNUZ?";
                okey="EVET";
                cancel="HAYIR";
            }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // TOPIC
                alertDialogBuilder.setTitle(topic);

                // FEATURES
                alertDialogBuilder
                        .setMessage(choice)
                        .setCancelable(false)
                        .setIcon(R.mipmap.ic_launcher_round)
                        // WHAT DOES IT DO WHEN IT CLICKES
                        .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(okey, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                          String convert="";
                                  if(choosenLanguage=="Ingilizce"){
                                        convert="English";
                                  }
                             else    if(choosenLanguage=="Turkish"){

                                    convert="Turkce";
                                }
                                else{
                                    convert=choosenLanguage;
                                }


                                    updateLanguage(convert);
                                    selectLanguage();
                                Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
                                startActivity(activity2Intent);

                            }
                        });

                // CREATE A ALERT DIALOG
                AlertDialog alertDialog = alertDialogBuilder.create();

                // SHOW ALERT DIALOG
                alertDialog.show();


        }


    }
    private boolean isExist(){
        File myFile=new File(path);
        return myFile.exists();


    }
}