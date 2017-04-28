package com.example.simplyvaldo.mylogins;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class loginType extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    @BindView(R.id.dropdown)
    Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_type);
        ButterKnife.bind(this);

        String[] selection = new String[]{"Login Type","Email","Social Media","Bank Account","Credit Card","Membership"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,selection);
        type.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
