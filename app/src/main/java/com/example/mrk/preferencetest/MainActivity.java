package com.example.mrk.preferencetest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//preferenceファイルにname, valueをセットして、その内容を読みだすテスト

public class MainActivity extends AppCompatActivity {
    private static String PREFFILE_NAME = "my_data_xxx";
    private static String tag = "MainActivity";

    private SharedPreferences dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set
        Button setButton = (Button)findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tag, "set button clicked!");

                EditText prefNameEdit = (EditText)findViewById(R.id.prefNameIn);
                String name =((SpannableStringBuilder)prefNameEdit.getText()).toString();

                EditText prefValEdit = (EditText)findViewById(R.id.prefValIn);
                String val =((SpannableStringBuilder)prefValEdit.getText()).toString();

                setStringPreference(name, val);
            }
        });

        //get
        Button getButton = (Button)findViewById(R.id.getButton);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tag, "get button clicked!");

                EditText prefValEdit = (EditText)findViewById(R.id.prefNameOut);
                String name =((SpannableStringBuilder)prefValEdit.getText()).toString();
                String val = getStringPreference(name);
                Log.d(tag, "pref value out=" + val);

                TextView prefValResult = (TextView)findViewById(R.id.prefValOut);
                prefValResult.setText(val);
            }
        });
    }

    ////////////////////////
    void setStringPreference(String name, String val) {
        dataStore = getSharedPreferences(PREFFILE_NAME, MODE_PRIVATE);;
        SharedPreferences.Editor editor = dataStore.edit();
        editor.putString(name, val);
        boolean ret = editor.commit();
        Log.d(tag, "set to preference result=" + Boolean.toString(ret));

        Toast.makeText(this, "set to preference result=" + Boolean.toString(ret), Toast.LENGTH_LONG).show();
    }

    String getStringPreference(String name) {
        dataStore = getSharedPreferences(PREFFILE_NAME, MODE_PRIVATE);
        if (dataStore == null) {
            Log.e(tag, "ERROR: preference is null");
        }
        String str = dataStore.getString(name, "Nothing");
        if(str.equals("Nothing")) {
//            str = "";
            Log.e(tag, "ERROR: preference name is not set");
            Toast.makeText(this, "get from preference error", Toast.LENGTH_LONG).show();
        }
        return str;
    }
}
