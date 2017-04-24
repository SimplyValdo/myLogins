package com.example.simplyvaldo.mylogins;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class loginType extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner type;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_type);


        type = (Spinner)findViewById(R.id.dropdown);
        String[] selection = new String[]{"Login Type","Email","Social Media","Bank Account","Credit Card","Membership"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,selection);
        type.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
