package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CreateLogin extends Fragment
{
    @BindView(R.id.done)
    Button done;
    @BindView(R.id.cancel)
    Button cancel;

    @BindView(R.id.TextView1)
    TextView textView1;
    @BindView(R.id.TextView2)
    TextView textView2;
    @BindView(R.id.TextView3)
    TextView textView3;

    @BindView(R.id.EditText1)
    EditText editText1;
    @BindView(R.id.EditText2)
    EditText editText2;
    @BindView(R.id.EditText3)
    EditText editText3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_createlogin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        done.setEnabled(false);
    }

    @OnTextChanged({R.id.EditText1, R.id.EditText2, R.id.EditText3})
    public void onChange()
    {
        if(!editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty() &&
                !editText3.getText().toString().isEmpty())
            done.setEnabled(true);
        else
            done.setEnabled(false);
    }

    @OnClick(R.id.cancel)
    public void onClickbackButton()
    {
        getFragmentManager().popBackStack();
    }
}
