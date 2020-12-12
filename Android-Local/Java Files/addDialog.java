package com.example.restapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.File;

public class addDialog extends AppCompatDialogFragment {
   private EditText name,price,stock;
   private TextView txtName,txtPrice,txtStock;
   private SQLiteDatabase myDatabase;
   private String path;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String title="",cancel="",add="",nameIs="",priceIs="",stockIs="";
        File myDbPath=getActivity().getApplication().getFilesDir();
        path=myDbPath+"/test";
        String language=MainActivity.myLanguage;
        if(language=="English"){
           title="Add Food";
           cancel="Cancel";
           add="Add";
           nameIs="Food Name: ";
           priceIs="Food's Price: ";
           stockIs="Food's Stock: ";
        }
        else{
           title="Yemek Ekle";
           cancel="Vazgeç";
           add="Ekle";
           nameIs="Yemek İsmi: ";
           priceIs="Yemeğin Fiyatı: ";
           stockIs="Yemeğin Stoğu: ";
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.addfood,null);
        builder.setView(view)
                .setTitle(title)
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        addFoodToDB();
                    }
                });

        name=view.findViewById(R.id.editName);
        price=view.findViewById(R.id.editPrice);
        stock=view.findViewById(R.id.editStock);
        txtName=view.findViewById(R.id.txtName);
        txtPrice=view.findViewById(R.id.txtPrice);
        txtStock=view.findViewById(R.id.txtStock);
        txtName.setText(nameIs);
        txtPrice.setText(priceIs);
        txtStock.setText(stockIs);

       return builder.create();
    }
    public void addFoodToDB(){


        String foodName=name.getText().toString();
        String foodPrice=price.getText().toString();
        String foodStock=stock.getText().toString();


            myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            String input="insert into foods (name,price,stock) values ('"+foodName+"','"+foodPrice+"','"+foodStock+"')";
            myDatabase.execSQL(input);

        myDatabase.close();
        Intent activity2Intent = new Intent(getActivity().getApplicationContext(), Foods.class);
        startActivity(activity2Intent);


    }
}
