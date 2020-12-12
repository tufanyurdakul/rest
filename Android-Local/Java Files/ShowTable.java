package com.example.restapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.os.Build;
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


public class ShowTable extends AppCompatActivity {
    SQLiteDatabase myDatabase;
    private ArrayList<String> amount, foodPrice, tableFoods, tableId, stock, reduces,theMoney;
    TextView txtId, txtTotal, txtType;
    private Button btnAdd, btnDelete, btnPay, btnTransfer,btnReduction;
    private int selected;
    private String valueOfSelected;
    ArrayAdapter<String> adapter;
    public static String id = "", path;
    ListView choosenfoods;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_table);
        File myDbPath = getApplication().getFilesDir();
        choosenfoods = findViewById(R.id.listTable);
        path = myDbPath + "/test";
        Intent intent = getIntent();
        id = Tables.id;
        txtId = findViewById(R.id.id);
        txtTotal = findViewById(R.id.txtTotal);
        btnReduction=findViewById(R.id.btnReduction);
        txtType = findViewById(R.id.types);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnPay = findViewById(R.id.btnPay);
        btnTransfer = findViewById(R.id.btnTransfer);

        // Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();
        if (MainActivity.myLanguage == "Turkce") {
            txtId.setText("Masa İsmi : " + Tables.value);
            txtTotal.setText("Toplam :");
            txtType.setText("Tarih-Adet-İsim-Fiyat");
            btnAdd.setText("YEMEK EKLE");
            btnDelete.setText("YEMEK SİL");
            btnPay.setText("ÖDE");
            btnTransfer.setText("AKTAR");
            btnReduction.setText("HESAPTAN DÜŞ");
        } else {
            txtId.setText("Table Name: " + Tables.value);
        }

        tableId = new ArrayList<String>();
        reduces=new ArrayList<String>();
        theMoney=new ArrayList<String>();
        foodPrice = new ArrayList<>();
        tableFoods = new ArrayList<>();
        stock = new ArrayList<>();
        amount = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tableFoods);
        choosenfoods.setAdapter(adapter);

        choosenfoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                selected = position;
                valueOfSelected = adapter.getItem(position);


            }
        });
        selectMoneyReduction();
        selectFoodsTable();


    }

    public void selectFoodsTable() {
        try {

            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String input = "SELECT CFID,piece,name,price,stock,f_date FROM choosenFoodss INNER JOIN foods ON choosenFoodss.FID=foods.FID and MID='" + id + "'";
            Cursor result = myDatabase.rawQuery(input, null);
            result.moveToFirst();

            while (result.isAfterLast() == false) {

                tableId.add(result.getString(result.getColumnIndex("CFID")));
                foodPrice.add(result.getString(result.getColumnIndex("price")));
                amount.add(result.getString(result.getColumnIndex("piece")));
                stock.add(result.getString(result.getColumnIndex("stock")));
                tableFoods.add(result.getString(result.getColumnIndex("f_date")) + "-" +
                        result.getString(result.getColumnIndex("piece")) + "-" +
                        result.getString(result.getColumnIndex("name")) + "-" +
                        result.getString(result.getColumnIndex("price")));


                result.moveToNext();
            }
            adapter.notifyDataSetChanged();
            double total = 0;
            for (int i = 0; i < foodPrice.size(); i++) {


                double br = (Double)(Double.parseDouble(foodPrice.get(i).trim())) * (Integer.parseInt(amount.get(i).trim()));
                total += br;
            }
            String reduce="";
            if(reduces.size()>0) {
                 reduce = reduces.get(0);
            }else{
                reduce="0";
            }
            double myReduce=Double.parseDouble(reduce);
            total-=myReduce;
            if (MainActivity.myLanguage == "Turkce") {
                txtTotal.setText("Toplam :" + String.valueOf(total) + " TL");
            } else {
                txtTotal.setText("Total :" + String.valueOf(total) + " $");
            }

        } catch (SQLiteException e) {
            Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
        }
    }

    public void addItem(View v) {
        AddFoodOnTable add = new AddFoodOnTable();
        add.show(getSupportFragmentManager(), "addF");


    }

    public void onPay(View v) {
        String topic = "", sure = "", yes = "", no = "";
        if (MainActivity.myLanguage == "Turkce") {
            topic = "Emin Misin?";
            sure = "Ödemek İstediğine Emin Misin?";
            yes = "Evet";
            no = "Hayır";

        } else {
            topic = "Are You Sure?";
            sure = "Are You Sure About Paying?";
            yes = "Yes";
            no = "No";

        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // TOPIC
        alertDialogBuilder.setTitle(topic);

        // FEATURES
        alertDialogBuilder
                .setMessage(sure + " -> " + txtTotal.getText())
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher_round)
                // WHAT DOES IT DO WHEN IT CLICKES
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        delete();


                    }
                });

        // CREATE A ALERT DIALOG
        AlertDialog alertDialog = alertDialogBuilder.create();

        // SHOW ALERT DIALOG
        alertDialog.show();
    }

    public void delete() {

        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String input = "DELETE FROM choosenFoodss WHERE MID='" + id + "'";
        myDatabase.execSQL(input);
        myDatabase.close();
        deleteReduce();


    }
    public void deleteReduce(){
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String input = "DELETE FROM moneyReduction WHERE MID='" + id + "'";
        myDatabase.execSQL(input);
        myDatabase.close();
        Intent activity2Intent = new Intent(getApplicationContext(), ShowTable.class);
        startActivity(activity2Intent);
    }

    public void onDelete(View v) {
        if (valueOfSelected != null) {
            String topic = "", question = "", yes = "", no = "";
            if (MainActivity.myLanguage == "Turkce") {
                topic = "Emin Misiniz?";
                question = "Silmek İstiyor Musunuz?";
                yes = "Evet";
                no = "Hayır";
            } else {
                topic = "Are U Sure?";
                question = "Do You Want To Delete?";
                yes = "Yes";
                no = "No";
            }
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // TOPIC
            alertDialogBuilder.setTitle(topic);

            // FEATURES
            alertDialogBuilder
                    .setMessage(tableFoods.get(selected) + " -> " + question)
                    .setCancelable(false)
                    .setIcon(R.mipmap.ic_launcher_round)
                    // WHAT DOES IT DO WHEN IT CLICKES
                    .setNegativeButton(no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            String select = tableId.get(selected);
                            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                            String input = "DELETE FROM choosenFoodss WHERE CFID='" + select + "'";
                            myDatabase.execSQL(input);
                            myDatabase.close();
                            Intent activity2Intent = new Intent(getApplicationContext(), ShowTable.class);
                            startActivity(activity2Intent);


                        }
                    });

            // CREATE A ALERT DIALOG
            AlertDialog alertDialog = alertDialogBuilder.create();

            // SHOW ALERT DIALOG
            alertDialog.show();

        } else {
            String topic = "", error = "", yes = "";
            if (MainActivity.myLanguage == "Turkce") {
                topic = "HATA";
                error = "Lütfen Silmek İstediğiniz Elamanı Listeden Seçiniz.";
                yes = "Tamam";
            } else {
                topic = "ERROR";
                error = "Please Choose Item On List Which Do You Want To Delete";
                yes = "Okey";
            }
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // TOPIC
            alertDialogBuilder.setTitle(topic);

            // FEATURES
            alertDialogBuilder
                    .setMessage(error)
                    .setCancelable(false)
                    .setIcon(R.mipmap.ic_launcher_round)
                    // WHAT DOES IT DO WHEN IT CLICKES

                    .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

            // CREATE A ALERT DIALOG
            AlertDialog alertDialog = alertDialogBuilder.create();

            // SHOW ALERT DIALOG
            alertDialog.show();
        }

    }

    public void goMenu(View v) {
        Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
        startActivity(activity2Intent);
    }

    public void toTransfer(View v) {
        Intent activity2Intent = new Intent(getApplicationContext(), Transfer.class);
        startActivity(activity2Intent);
    }

    public void moneyReduction(View v) {
        String question="",cancel="",okey="";
        if(MainActivity.myLanguage=="Turkce"){

            question="Hesaptan Kaç Para Düşmek İstiyor sunuz?";
            cancel="Düşme";
            okey="Düş";
        }
        else{

            question="How Much Do You Want To Reduce?";
            cancel="Not Reduce";
            okey="Reduce";
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
                    insertReduce(inputs);
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

    public void selectMoneyReduction() {
        try {
            reduces = new ArrayList<String>();
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String input = "SELECT money FROM moneyReduction WHERE MID='" + id + "'";
            Cursor result = myDatabase.rawQuery(input, null);

            result.moveToFirst();

            while (result.isAfterLast() == false) {

                reduces.add(result.getString(result.getColumnIndex("money")));


                result.moveToNext();
            }
        }catch (Exception e){}
    }
    public void insertReduce(String myReduce){


            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String input = "SELECT money FROM moneyReduction WHERE MID='"+id+"'";
            Cursor result = myDatabase.rawQuery(input, null);
           if(result.getCount()>0){
               result.moveToFirst();

               while (result.isAfterLast() == false) {

                   theMoney.add(result.getString(result.getColumnIndex("money")));


                   result.moveToNext();
               }
               String oldMoney=theMoney.get(0);
               double myMoney=Double.parseDouble(oldMoney);
             double newMoney=Double.parseDouble(myReduce);
               double newTotal=myMoney+newMoney;
               String myTotal=String.valueOf(newTotal);
               justUpdate(myTotal);

           }else{
               justInsert(myReduce);
           }
            Intent activity2Intent = new Intent(getApplicationContext(), ShowTable.class);
            startActivity(activity2Intent);

    }
    public void justUpdate(String myReduce){
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("money",myReduce);

        //These Fields should be your String values of actual column names
        myDatabase.update("moneyReduction",cv,"MID="+id,null);
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();

    }
    public void justInsert(String myReduce){
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String input="insert into moneyReduction (MID,money) values ('"+id+"','"+myReduce+"')";
        myDatabase.execSQL(input);
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
        myDatabase.close();
    }
}