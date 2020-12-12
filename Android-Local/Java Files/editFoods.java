package com.example.restapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.File;

public class editFoods extends AppCompatDialogFragment {
    TextView txtName,txtPrice,txtStock;
    EditText name,price,stock;
    private String path;
    public Dialog onCreateDialog(Bundle  savedInstanceState){
        String title="",edit="",cancel="",nameIs,priceIs,stockIs;
        if(MainActivity.myLanguage=="Turkce"){
            title="Seçilen Yemeği Düzenle";
            edit="Düzenle";
            cancel="Vazgeç";
            nameIs="Yemek İsmi: ";
            priceIs="Yemek Fiyatı: ";
            stockIs="Yemek Stoğu: ";
        }else{
            title="Edit Your Choosen Food";
            edit="Edit";
            cancel="Cancel";
            nameIs="Food Name: ";
            priceIs="Food Price: ";
            stockIs="Food Stock: ";
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.edit_foods,null);
        builder.setView(view)
                .setTitle(title)
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                    }
                })
                .setPositiveButton(edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         boolean numberStock=controlStock();
                         if(numberStock){
                             updateFoodToDB();
                         }else{
                             getErrorMessage();
                         }

                    }
                });
        File myDbPath =getActivity().getApplication().getFilesDir();
        path = myDbPath + "/test";
        name=view.findViewById(R.id.editName);
        price=view.findViewById(R.id.editPrice);
        stock=view.findViewById(R.id.editStock);
        txtName=view.findViewById(R.id.txtName);
        txtPrice=view.findViewById(R.id.txtPrice);
        txtStock=view.findViewById(R.id.txtStock);
        txtName.setText(nameIs);
        txtPrice.setText(priceIs);
        txtStock.setText(stockIs);
        String choosen=Foods.valueOfSelected;
        String cName="",cPrice="",cStock="";
        int count=0,first=0;
        for(int i=0; i<choosen.length();i++){
            if(choosen.charAt(i)=='-'){
                if(count==0) {
                    cName = choosen.substring(0, i);
                    first = i;
                    count++;
                }
                else if(count==1){
                    cPrice=choosen.substring(first+1,i);
                    cStock=choosen.substring(i+1,choosen.length());
                }

            }
        }
        name.setText(cName);
        price.setText(cPrice);
        stock.setText(cStock);

        return builder.create();
    }
    public void updateFoodToDB(){
        String foodName=name.getText().toString();
        String foodPrice=price.getText().toString();
        String foodStock=stock.getText().toString();
        SQLiteDatabase myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("name",foodName);
        cv.put("price",foodPrice);
        cv.put("stock",foodStock);
        String id=Foods.selectedId;

        //These Fields should be your String values of actual column names
        myDatabase.update("foods",cv,"FID="+id,null);




        myDatabase.close();



        Intent activity2Intent = new Intent(getActivity().getApplicationContext(), Foods.class);
        startActivity(activity2Intent);
    }
    public boolean controlStock(){
        boolean isStock=false;
        String myStock=stock.getText().toString();
        for(int i=0; i<myStock.length();i++){
            if(myStock.charAt(i)>47&&58>myStock.charAt(i)){
                isStock=true;
            }else{
                isStock=false;
                break;
            }
        }
        return isStock;
    }
    public void getErrorMessage(){
        String errorMessage="";
        if(MainActivity.myLanguage=="Turkce"){
            errorMessage="Stoğa Lütfen Sayı Giriniz";
        }else{
            errorMessage="Please Enter Number Of Stock";
        }
        Toast.makeText(getActivity(), ""+errorMessage, Toast.LENGTH_LONG).show();
    }
}
