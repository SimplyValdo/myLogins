package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapterLogins extends RecyclerView.Adapter<RecyclerViewAdapterLogins.loginsHolder>
{
    private ArrayList<String> logins;
    private HashMap<Integer,String> types;
    private Context mContext;
    private RecyclerViewToFragmentListener myListener;

    public RecyclerViewAdapterLogins(Context context, ArrayList<String> logins, HashMap<Integer,String> types, RecyclerViewToFragmentListener myListener) {
        this.logins = logins;
        this.types = types;
        mContext = context;
        this.myListener = myListener;
    }

    @Override
    public loginsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_logins, parent, false);
        return new loginsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(loginsHolder holder, int position) {

        holder.textView.setText(logins.get(position));
    }

    @Override
    public int getItemViewType(int position) {

        Log.i("Position", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return logins.size();
    }

    public class loginsHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public View container;

        public loginsHolder(View itemView) {

            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.numID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myListener.SendSelectLoginInfo(logins.get(getAdapterPosition()), types.get(getAdapterPosition()) );
                }
            });
        }
    }
}
