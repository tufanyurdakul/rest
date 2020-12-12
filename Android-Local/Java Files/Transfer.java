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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Transfer extends AppCompatActivity {
   private String id="",path="",value="";
   private ArrayList<String> tableNames,tableId;
   Context context;
   Button btnTransfer;
   SQLiteDatabase myDatabase;
   private int choosenItemonList;
   ListView tList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        File myDbPath=getApplication().getFilesDir();
        path=myDbPath+"/test";
        id=Tables.id;
        tList=findViewById(R.id.tableList);
        btnTransfer=findViewById(R.id.btnTransfer);
        if(MainActivity.myLanguage=="Turkce"){
            btnTransfer.setText("Aktar");
        }
        showTables();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tableNames);
        tList.setAdapter(adapter);

        tList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                choosenItemonList=position;
                 value=adapter.getItem(position);


                // Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void showTables(){
        tableNames=new ArrayList<String>();

        tableId=new ArrayList<String>();

        try {

            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String input = "SELECT * FROM tables";
            Cursor result = myDatabase.rawQuery(input, null);
            result.moveToFirst();
            while (result.isAfterLast() == false) {
                tableNames.add(result.getString(result.getColumnIndex("name")));
                tableId.add(result.getString(result.getColumnIndex("TID")));





                result.moveToNext();
            }
        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateOnDB(){

           String myId=tableId.get(choosenItemonList);
           String oldId=id;
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            ContentValues cv = new ContentValues();
            cv.put("MID",myId);

            //These Fields should be your String values of actual column names
            myDatabase.update("choosenFoodss",cv,"MID="+oldId,null);
            updateOnReduce(myId);
        Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
        startActivity(activity2Intent);


    }
    public void updateOnReduce(String myId){
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("MID",myId);

        //These Fields should be your String values of actual column names
        myDatabase.update("moneyReduction",cv,"MID="+id,null);
    }
    public void message(){
        String title="",name="",yes="",no="";
        if(MainActivity.myLanguage=="Turkce"){
            title="Emin Misin?";
            name="Masa : "+value+" 'e aktarılsın mı?";
            yes="Evet";
            no="Hayır";
        }
        else{
            title="Are U Sure";
            name="Transfer to Table : "+value;
            yes="Yes";
            no="No";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // TOPIC
        alertDialogBuilder.setTitle(title);

        // FEATURES
        alertDialogBuilder
                .setMessage(name)
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher_round)
                // WHAT DOES IT DO WHEN IT CLICKES
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent activity2Intent = new Intent(getApplicationContext(), ShowTable.class);
                        startActivity(activity2Intent);
                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateOnDB();
                    }
                });

        // CREATE A ALERT DIALOG
        AlertDialog alertDialog = alertDialogBuilder.create();

        // SHOW ALERT DIALOG
        alertDialog.show();
    }
    public void transfer(View view){
        message();
    }
}