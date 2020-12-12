package com.example.restapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class AddFoodOnTable extends AppCompatDialogFragment {
    private String path,valueOfSelected,valueOfSelected2;
    private int selected,selected2;
    private Button deleteSecond,addSecond;
    private EditText editAmount;
    private TextView txtAmount,txtChoosen;
    private SQLiteDatabase myDatabase;
    ArrayList<String> foodId,foodStock,tableFoods,tableFoods2,foodId2,foodAmounts;

    ListView foodsList,main,foodList2;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        File myDbPath=getActivity().getApplication().getFilesDir();
        path=myDbPath+"/test";
        tableFoods2=new ArrayList<String>();
        foodId2=new ArrayList<String>();
        foodAmounts=new ArrayList<String>();
        foodId=new ArrayList<String>();
        foodStock=new ArrayList<String>();
        tableFoods=new ArrayList<String>();
        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        main=getActivity().findViewById(R.id.listTable);
        String input = "SELECT * FROM foods";
        Cursor result = myDatabase.rawQuery(input, null);
        result.moveToFirst();

        while (result.isAfterLast() == false) {

            foodId.add(result.getString(result.getColumnIndex("FID")));


            foodStock.add(result.getString(result.getColumnIndex("stock")));
            tableFoods.add(result.getString(result.getColumnIndex("name")) + "-" +
                           result.getString(result.getColumnIndex("price")));


            result.moveToNext();
        }


        String language=MainActivity.myLanguage;
       String title="";
        final String amount,cf,add,cancel,error;
        if(language=="English"){
            title="Add Food";
            cancel="Cancel";
            add="Add";
            amount="Amount :";
            cf="Choosen Food :";
            error="Please enter amount or choose item on list";

        }
        else{
            title="Yemek Ekle";
            cancel="Vazgeç";
            add="Ekle";
cf="Seçilen Yemek :";
amount="Adet :";

        }
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.add_food_on_table,null);
        foodsList=view.findViewById(R.id.foodsList);
        foodList2=view.findViewById(R.id.foodsList2);

        txtChoosen=view.findViewById(R.id.txtCF);
        txtAmount=view.findViewById(R.id.txtAmount);
        editAmount=view.findViewById(R.id.editAmount);
        deleteSecond=view.findViewById(R.id.deleteSecondList);
        addSecond=view.findViewById(R.id.addSecondList);
        foodList2=view.findViewById(R.id.foodsList2);

        txtChoosen.setText(cf);
        txtAmount.setText(amount);
         deleteSecond.setEnabled(false);
      final  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, tableFoods);
        foodsList.setAdapter(adapter);

        foodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                selected = position;
               valueOfSelected =adapter.getItem(position);
               txtChoosen.setText(cf+" "+valueOfSelected);
                //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });
          final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, tableFoods2);
        foodList2.setAdapter(adapter2);

        foodList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                selected2 = position;
                valueOfSelected2 =adapter.getItem(position);


            }
        });
        addSecond.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             if(valueOfSelected!=null){
                 String imp=editAmount.getText().toString();
                 boolean isNumber=ControlStrings(imp);
                 String addId=foodId.get(selected);
                 boolean isThere=false;
                for(int i=0; i<foodId2.size();i++){
                    if(foodId2.get(i)==addId){
                        isThere=true;
                        break;
                    }else{
                        isThere=false;
                    }
                }
                if(isThere){
                    if(isNumber){
                        String amounts=editAmount.getText().toString();
                        int myAmount=Integer.parseInt(amounts);
                        int findIndex=0;
                        for(int i=0; i<foodId2.size();i++){
                            if(foodId2.get(i)==foodId.get(selected)){
                                findIndex=i;
                            }
                        }
                        String x=findT(findIndex);
                        int amount=Integer.parseInt(x);
                        int total=amount+myAmount;

                        tableFoods2.set(findIndex,total+"-"+tableFoods.get(selected));
                        foodAmounts.set(findIndex,String.valueOf(total));
                    }else{
                        int findIndex=0;
                        for(int i=0; i<foodId2.size();i++){
                            if(foodId2.get(i)==foodId.get(selected)){
                                findIndex=i;
                            }
                        }
                        String x=findT(findIndex);
                        int amount=Integer.parseInt(x);
                        amount++;
                        tableFoods2.set(findIndex,amount+"-"+tableFoods.get(selected));
                        foodAmounts.set(findIndex,String.valueOf(amount));


                    }

                }else{
                    foodId2.add(addId);
                    if(isNumber){
                        tableFoods2.add(editAmount.getText().toString()+"-"+valueOfSelected);
                        foodAmounts.add(editAmount.getText().toString());
                    }else{
                        tableFoods2.add("1-"+valueOfSelected);
                        foodAmounts.add("1");
                    }
                }



                 deleteSecond.setEnabled(true);
                 adapter2.notifyDataSetChanged();
             }
            }
        });
        deleteSecond.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(valueOfSelected2!=null){
                tableFoods2.remove(selected2);
                foodId2.remove(selected2);
                foodAmounts.remove(selected2);
                valueOfSelected2=null;
                if(tableFoods2.size()==0){
                    deleteSecond.setEnabled(false);
                }
            }else{

            }
               adapter2.notifyDataSetChanged();
            }
        });
        builder.setView(view)
                .setTitle(title)
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(add, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                           for(int i=0; i<foodId2.size();i++){
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                               Date now = new Date();
                               String fileName = sdf.format(now);
                                insert(foodId2.get(i),foodAmounts.get(i),fileName);

                            }
                    }
                });



        return builder.create();

    }
    public void insert(String id,String amount,String date){

        myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String input="insert into choosenFoodss (FID,MID,f_date,piece) values ('"+id+"','"+ShowTable.id+"','"+date+"','"+amount+"')";
        myDatabase.execSQL(input);

        myDatabase.close();
        String myStock=foodStock.get(selected);
        int stockIs=Integer.parseInt(myStock);
        int amountIs=Integer.parseInt(amount);
        int updateStock=0;
        if(stockIs>amountIs){
             updateStock=stockIs-amountIs;
        }else{
            updateStock=0;
        }
        updateStockOnDB(updateStock);


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
    public void updateStockOnDB(int newStock){
 String id=foodId.get(selected);
        SQLiteDatabase  myDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues cv = new ContentValues();
        cv.put("stock",newStock);

        //These Fields should be your String values of actual column names
        myDatabase.update("foods",cv,"FID="+id,null);




        myDatabase.close();
        Intent activity2Intent = new Intent(getActivity().getApplicationContext(), ShowTable.class);
        startActivity(activity2Intent);
    }
public String findT(int index){
        int myIndex=0;
        for(int i=0; i<(tableFoods2.get(index)).length();i++){
            if(tableFoods2.get(index).charAt(i)=='-'){
                myIndex=i;
                break;
            }
        }
        String myCount=tableFoods2.get(index).substring(0,myIndex);
        return  myCount;
}
}
