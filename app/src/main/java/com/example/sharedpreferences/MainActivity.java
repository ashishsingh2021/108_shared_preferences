package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;                                                   // 1st Step Declare Shared Preferences
    public int SaveDataAs(String tag,String value)                         // Step3 Save Data
    {

        sp.edit().putString(tag,value).apply();
        return 1;
    }
    public String  GetData(String tag)                                  //Step4 Retrive Data
    {
        String tagData =sp.getString(tag,"");
        Log.i(tag,tagData);
        return tagData;
    }
    public int SaveArrayListAs(String name,ArrayList ArrayName)                         // Step3 Save Data
    {
        try
        {
            sp.edit().putString(name,ObjectSerializer.serialize(ArrayName)).apply();
            return 1;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public ArrayList<String> GetArrayList(String SavedName)
    {
        ArrayList<String> newArray=new ArrayList<String>();
        try
        {
           newArray=(ArrayList<String>)ObjectSerializer.deserialize(sp.getString(SavedName,ObjectSerializer.serialize(new ArrayList<String>())));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return newArray;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sp=this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE); // Step2 Passing this context



//        SaveDataAs("Name","Ashish Singh");  // FOR STRINGS
//        String name=GetData("Name");
//        Log.i("NAME",name);
//        TextView tv=(TextView)findViewById(R.id.tv);
//        tv.setText(name);

        ArrayList<String> names=new ArrayList<String>();
        names.add(" Abhishek Singh");
        names.add("Annu Singh");
        names.add("Sushil Singh");

        SaveArrayListAs("myNames",names );
        ArrayList<String> n=GetArrayList("myNames");
        Log.i("ArrayContent       ",n.toString());
        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setText(n.get(0));
    }
}

