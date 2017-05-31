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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lasanimas.simplyvaldo.mylogins.Model.BankDB;
import com.lasanimas.simplyvaldo.mylogins.Model.DevicesDB;
import com.lasanimas.simplyvaldo.mylogins.Model.EmailDB;
import com.lasanimas.simplyvaldo.mylogins.Model.MediaDB;
import com.lasanimas.simplyvaldo.mylogins.Model.MembershipDB;
import com.lasanimas.simplyvaldo.mylogins.Model.OtherDB;
import com.lasanimas.simplyvaldo.mylogins.Model.SocialDB;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.lang.reflect.Member;

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

    @BindView(R.id.customName)
    EditText customName;
    @BindView(R.id.EditText1)
    EditText editText1;
    @BindView(R.id.EditText2)
    EditText editText2;
    @BindView(R.id.EditText3)
    EditText editText3;

    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = this.getArguments();
        return inflater.inflate(R.layout.fragment_createlogin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        done.setEnabled(false);

        configureLayout(bundle);
    }
    public void configureLayout(Bundle bundle)
    {
        if(bundle != null)
        {
            switch(bundle.getString("type"))
            {
                case "email":
                    textView1.setText("Email:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "social":
                    textView1.setText("Email:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "membership":
                    textView1.setText("Username:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "media":
                    textView1.setText("Username:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "bank":
                    textView1.setText("Username:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "devices":
                    textView1.setText("Username:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
                case "other":
                    textView1.setText("Username:");
                    textView2.setText("Password:");
                    textView3.setText("Creation Date:");
                    break;
            }
        }
    }

    @OnTextChanged({R.id.customName, R.id.EditText1, R.id.EditText2, R.id.EditText3})
    public void onChange()
    {
        if(!customName.getText().toString().isEmpty() && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty() &&
                !editText3.getText().toString().isEmpty())
            done.setEnabled(true);
        else
            done.setEnabled(false);
    }

    @OnClick(R.id.done)
    public void onClickDoneButton()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Profiles/" + bundle.getString("id") +
            "/categories/" + bundle.getString("type") + "/" + customName.getText().toString());

        switch(bundle.getString("type"))
        {
            case "email":
                EmailDB newEmail = new EmailDB();
                newEmail.setEmail(editText1.getText().toString());
                newEmail.setPassword(editText2.getText().toString());
                newEmail.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newEmail);
                break;
            case "social":
                SocialDB newSocial = new SocialDB();
                newSocial.setEmail(editText1.getText().toString());
                newSocial.setPassword(editText2.getText().toString());
                newSocial.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newSocial);
                break;
            case "membership":
                MembershipDB newMembership = new MembershipDB();
                newMembership.setUsername(editText1.getText().toString());
                newMembership.setPassword(editText2.getText().toString());
                newMembership.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newMembership);
                break;
            case "media":
                MediaDB newMedia = new MediaDB();
                newMedia.setUsername(editText1.getText().toString());
                newMedia.setPassword(editText2.getText().toString());
                newMedia.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newMedia);
                break;
            case "bank":
                BankDB newBank = new BankDB();
                newBank.setUsername(editText1.getText().toString());
                newBank.setPassword(editText2.getText().toString());
                newBank.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newBank);
                break;
            case "devices":
                DevicesDB newDevice = new DevicesDB();
                newDevice.setDeviceName(editText1.getText().toString());
                newDevice.setPassword(editText2.getText().toString());
                newDevice.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newDevice);
                break;
            case "other":
                OtherDB newOther = new OtherDB();
                newOther.setUsername(editText1.getText().toString());
                newOther.setPassword(editText2.getText().toString());
                newOther.setDateCreation(editText3.getText().toString());
                databaseReference.setValue(newOther);
                break;
        }

        getActivity().finish();
    }

    @OnClick(R.id.cancel)
    public void onClickbackButton()
    {
        getFragmentManager().popBackStack();
    }
}
