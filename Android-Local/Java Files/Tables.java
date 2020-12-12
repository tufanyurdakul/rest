package com.example.restapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Tables extends AppCompatActivity {

    private SQLiteDatabase myDatabase;
    private String path="",lang;
    ListView viewTables;
    private TextView txtCT;
    public static String id;
    private Button btnShow,btnChange;
    private int choosenItemonList;
    public static String value;
    ArrayList<String> tableNames,tableId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        viewTables = (ListView)findViewById(R.id.tableList);
        File myDbPath=getApplication().getFilesDir();
        path=myDbPath+"/test";
        txtCT=findViewById(R.id.cT);
        btnChange=findViewById(R.id.btnCN);
        btnShow=findViewById(R.id.btnShow);
createFoodTable();

        if(MainActivity.myLanguage=="Turkce"){
            lang="Seçilen Masa :";
            btnShow.setText("GÖSTER");
            btnChange.setText("İSİM DEĞİŞTİR");
        }
        else{
            lang="Choosen Table :";
        }
         txtCT.setText(lang);
        showTables();
        createReductionTable();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tableNames);
        viewTables.setAdapter(adapter);

        viewTables.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                choosenItemonList=position;
              value=adapter.getItem(position);
               txtCT.setText(lang+" "+value);
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
    private void createFoodTable() {
        try {
            //create Db if it's not.
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            myDatabase.needUpgrade(13);


            //create table
            String tablex = "create table if not exists  choosenFoodss("
                    + "CFID integer primary key autoincrement, "
                    + "FID text,"
                    + "MID text,"
                    + "f_date date,"
                    + "piece text);";
            //execute the SQL script
            myDatabase.execSQL(tablex);
            //now we have the "student" table


            myDatabase.close();


        } catch (SQLiteException e) {

            Toast.makeText(this, " error.", Toast.LENGTH_LONG).show();
        }

    }
    private void createReductionTable() {
        try {
            //create Db if it's not.
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            myDatabase.needUpgrade(13);


            //create table
            String tablex = "create table if not exists  moneyReduction("
                    + "MRID integer primary key autoincrement, "
                    + "MID text,"
                    + "money text);";
            //execute the SQL script
            myDatabase.execSQL(tablex);
            //now we have the "student" table


            myDatabase.close();


        } catch (SQLiteException e) {

            Toast.makeText(this, " error.", Toast.LENGTH_LONG).show();
        }

    }

    public void showTable(View v){
         id=tableId.get(choosenItemonList);
        Intent activity2Intent = new Intent(getApplicationContext(), ShowTable.class);
        startActivity(activity2Intent);
    }
    public void changeName(View v){
        String topic="",question="",cancel="",okey="";
        if(MainActivity.myLanguage=="Turkce"){
            topic="İsim Değiştirme";
            question="Masanın İsmini Değiştirmek İster Misin?("+value+")";
            cancel="Hayır";
            okey="Evet";
        }
        else{
            topic="Changing Name";
            question="Do You Want To Change Name Of Table?("+value+")";
            cancel="No";
            okey="Yes";
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
              if(!(inputs==""||inputs==" ")){
                  updateTableName(inputs);
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
    public void updateTableName(String newName){
        SQLiteDatabase  myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        String id=tableId.get(choosenItemonList);
        cv.put("name",newName);

        //These Fields should be your String values of actual column names
        myDatabase.update("tables",cv,"TID="+id,null);




        myDatabase.close();



        Intent activity2Intent = new Intent(getApplicationContext(), Tables.class);
        startActivity(activity2Intent);
    }
    public void goMenu(View v){
        Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
        startActivity(activity2Intent);
    }
}