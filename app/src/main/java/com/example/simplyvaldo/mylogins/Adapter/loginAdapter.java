package com.example.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplyvaldo.mylogins.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loginAdapter extends RecyclerView.Adapter<loginsHolder>
{
    private ArrayList<String> logins;
    private Context mContext;

    public loginAdapter(Context context, ArrayList<String> logins) {
        this.logins = logins;
        mContext = context;
    }

    @Override
    public loginsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_logins, parent, false);

        return new loginsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final loginsHolder holder, final int position) {

       holder.textView.setText(logins.get(position));
    }

    @Override
    public int getItemCount() {
        return logins.size();
    }

    private Context getContext() {
        return mContext;
    }
}
