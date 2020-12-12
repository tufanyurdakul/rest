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

public class Foods extends AppCompatActivity {
    ListView foods;
    private SQLiteDatabase myDatabase;
    private Button btnAdd, btnDelete, btnUpdate, btnStock;
    private TextView txtTypes;
    private ArrayList<String> foodNames, foodPrice, foodStock, foodId, myFoods;
    private int selected;
    public static String valueOfSelected;
    public static String selectedId;
    ArrayAdapter<String> adapter;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        foods = findViewById(R.id.foodsList);
        File myDbPath = getApplication().getFilesDir();
        path = myDbPath + "/test";
        createFoodTable();
        selectFoodsTable();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, myFoods);
        foods.setAdapter(adapter);

        foods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                selected = position;
                valueOfSelected = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });
        btnAdd = findViewById(R.id.add);
        btnDelete = findViewById(R.id.delete);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtTypes = findViewById(R.id.types);
        btnStock = findViewById(R.id.btnStock);
        if (MainActivity.myLanguage == "Turkce") {
            btnAdd.setText("EKLE");
            btnDelete.setText("SİL");
            btnUpdate.setText("GÜNCELLE");
            btnStock.setText("STOK EKLE");
            txtTypes.setText("Yemek İsmi-Yemek Fiyatı-Yemek Stoğu");
        }
    }

    private void createFoodTable() {
        try {
            //create Db if it's not.
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            myDatabase.needUpgrade(3);


            //create table
            String tablex = "create table if not exists foods("
                    + "FID integer primary key autoincrement, "
                    + "name text,"
                    + "price text,"
                    + "stock text);";
            //execute the SQL script
            myDatabase.execSQL(tablex);
            //now we have the "student" table


            myDatabase.close();


        } catch (SQLiteException e) {

            Toast.makeText(this, " error.", Toast.LENGTH_LONG).show();
        }
    }

    private void selectFoodsTable() {
        try {
            //foodNames=new ArrayList<String>();
            foodId = new ArrayList<>();
            //foodPrice=new ArrayList<>();
            //foodStock=new ArrayList<>();
            myFoods = new ArrayList<>();
            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String input = "SELECT * FROM foods";
            Cursor result = myDatabase.rawQuery(input, null);
            result.moveToFirst();

            while (result.isAfterLast() == false) {

                //    foodNames.add(result.getString(result.getColumnIndex("name")));
                foodId.add(result.getString(result.getColumnIndex("FID")));
                //  foodPrice.add(result.getString(result.getColumnIndex("price")));
                //foodStock.add(result.getString(result.getColumnIndex("stock")));
                myFoods.add(result.getString(result.getColumnIndex("name")) + "-" +
                        result.getString(result.getColumnIndex("price")) + "-" +
                        result.getString(result.getColumnIndex("stock")));


                result.moveToNext();
            }
        } catch (SQLiteException e) {
            Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelete(View v) {
        String question = "", yes = "", no = "";
        if (valueOfSelected != null) {
            if (MainActivity.myLanguage == "Turkce") {
                question = "Silmek İstediğine Emin Misin?";
                yes = "Evet";
                no = "Hayır";
            } else {
                question = "Do You Want To Delete?";
                yes = "Yes";
                no = "No";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(valueOfSelected + " " + question);

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String getSelectedId = foodId.get(selected);
                    deleteItemOnDb(getSelectedId);

                }
            });
            builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();


        } else {
            if (MainActivity.myLanguage == "Turkce") {
                question = "Lütfen Silmek İstediğiniz Yemeği Listeden Tıklayınız.";
                yes = "Tamam";

            } else {
                question = "Please Click The Item On List Which Do You Want To Delete";
                yes = "Okey";

            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(question);

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

// Set up the buttons

            builder.setNegativeButton(yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }


    }

    public void onAdd(View v) {
        addDialog add = new addDialog();
        add.show(getSupportFragmentManager(), "addFoods");

    }
public void onUpdate(View v){
        String question="",cancel="";
        if(valueOfSelected==null){
            if (MainActivity.myLanguage == "Turkce") {
                question = "Lütfen Listeden Düzenlemek İstediğiniz Elamanı Seçiniz";
                cancel = "Tamam";

            } else {
                question = "Please Choose Item on List Which Do You Want To Edit";
                cancel = "Okey";

            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(question);

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

// Set up the buttons

            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }else {
            selectedId=foodId.get(selected);
            editFoods edit = new editFoods();
            edit.show(getSupportFragmentManager(), "editFoods");
        }
}
    public void onAddStock(View v) {
        String question = "", add = "", cancel = "";
        if(valueOfSelected!=null){
            if (MainActivity.myLanguage == "Turkce") {
                question = "Bu Yemeğe Kaç Adet Stock Eklemek İstersiniz?";
                add = "Ekle";
                cancel = "Vazgeç";
            } else {
                question = "How Much Do You Want To Add This Food";
                add = "Add";
                cancel = "Cancel";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(question + " -> " + valueOfSelected);

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton(add, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String inputs = input.getText().toString();
                    boolean isNotNullOrString=   ControlStrings(inputs);
                    if(isNotNullOrString){
                        insertStock(inputs);

                    }else{
                        showErrorMessage();
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
        }else{
            if (MainActivity.myLanguage == "Turkce") {
                question = "Lütfen Listeden Stok Eklemek İstediğiniz Elamanı Seçiniz";
                add = "Tamam";

            } else {
                question = "Please Choose Item on List Which Do You Want To Add Stock";
                add = "Okey";

            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(question);

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

// Set up the buttons

            builder.setNegativeButton(add, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }

    }

    public void deleteItemOnDb(String id) {
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String input = "delete from foods where FID='" + id + "'";
        myDatabase.execSQL(input);

        myDatabase.close();
        Intent activity2Intent = new Intent(getApplicationContext(), Foods.class);
        startActivity(activity2Intent);
    }

    public boolean ControlStrings(String inp) {
        boolean isAllNumber = false;
        if (inp != null) {

            for (int i = 0; i < inp.length(); i++) {
                if (inp.charAt(i) > 47 && inp.charAt(i) < 58) {
                    isAllNumber = true;
                } else {
                    isAllNumber = false;
                    break;
                }
            }
        }else{
            isAllNumber=false;
        }
          return isAllNumber;
    }
    public void showErrorMessage(){
        String question="",okey="";
        if (MainActivity.myLanguage == "Turkce") {
            question = "Lütfen Boş Bırakmayın Ve Sadece Sayı Girin";
            okey = "Tamam";

        } else {
            question = "Please Do Not Leave Break And Just Enter Numbers";
            okey = "Okey";

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(question);

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons

        builder.setNegativeButton(okey, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void insertStock(String additionOfStock){
        String currentStock="";
        int newStock=0;
        int count=0;
        for(int i=0; i<valueOfSelected.length(); i++){
            if(valueOfSelected.charAt(i)=='-'){
                count++;
                if(count==2){
                    currentStock=valueOfSelected.substring(i+1,valueOfSelected.length());
                    newStock=Integer.valueOf(currentStock)+Integer.valueOf(additionOfStock);
                    Toast.makeText(this, ""+newStock, Toast.LENGTH_LONG).show();
                }
            }

        }



        String id=foodId.get(selected);
        SQLiteDatabase  myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("stock",newStock);

        //These Fields should be your String values of actual column names
        myDatabase.update("foods",cv,"FID="+id,null);




        myDatabase.close();

        Toast.makeText(this, " column created.", Toast.LENGTH_LONG).show();

        Intent activity2Intent = new Intent(getApplicationContext(), Foods.class);
        startActivity(activity2Intent);

        }


public void goMenu(View v){
    Intent activity2Intent = new Intent(getApplicationContext(), AppMenu.class);
    startActivity(activity2Intent);
}
    }
