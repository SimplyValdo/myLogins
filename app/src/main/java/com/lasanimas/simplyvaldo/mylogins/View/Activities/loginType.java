package com.lasanimas.simplyvaldo.mylogins.View.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lasanimas.simplyvaldo.mylogins.R;
import com.lasanimas.simplyvaldo.mylogins.View.Fragments.CreateLogin;
import com.lasanimas.simplyvaldo.mylogins.View.Fragments.settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginType extends AppCompatActivity
{

    @BindView(R.id.frameLayout2)
    FrameLayout frameLayout2;

    @BindView(R.id.backButton)
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintype);
        ButterKnife.bind(this);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            boolean toggle = true;

            @Override
            public void onBackStackChanged() {
                toggle ^= true;

                if(toggle)
                    frameLayout2.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.backButton)
    public void onClickbackButton()
    {
        finish();
    }

    @OnClick({R.id.buttonEmail, R.id.buttonSocial,R.id.buttonMembership, R.id.buttonMedia,
            R.id.buttonBank, R.id.buttonDevices, R.id.buttonOther})
    public void onClickButtonType(View view )
    {
        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();

       switch(view.getId())
       {
           case R.id.buttonEmail:
               bundle.putString("type", "email");
               break;
           case R.id.buttonSocial:
               bundle.putString("type", "social");
               break;
           case R.id.buttonMembership:
               bundle.putString("type", "membership");
               break;
           case R.id.buttonMedia:
               bundle.putString("type", "media");
               break;
           case R.id.buttonBank:
               bundle.putString("type", "bank");
               break;
           case R.id.buttonDevices:
               bundle.putString("type", "devices");
               break;
           case R.id.buttonOther:
               bundle.putString("type", "other");
               break;
       }

        fragmentManager.beginTransaction().replace(R.id.frameLayout2, new CreateLogin()).addToBackStack(null).commit();
        frameLayout2.setVisibility(View.VISIBLE);
    }
}
