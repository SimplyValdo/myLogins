package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewLoginsToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RecyclerViewAdapterLogins extends RecyclerView.Adapter<RecyclerViewAdapterLogins.loginsHolder>
{
    private ArrayList<String> logins;
    private HashMap<Integer,String> types;
    private HashSet<Integer> checkBoxStatus;
    private Context mContext;

    private boolean checkBoxVisibility;
    private boolean checkBoxSelectAll;
    private RecyclerViewLoginsToFragmentListener myListener;

    public RecyclerViewAdapterLogins(Context context, ArrayList<String> logins, HashMap<Integer,String> types, RecyclerViewLoginsToFragmentListener myListener) {
        this.logins = logins;
        this.types = types;
        this.checkBoxVisibility = false;
        this.checkBoxSelectAll = false;
        this.checkBoxStatus = new HashSet<>();

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

        if(checkBoxVisibility) {
            holder.textView.setClickable(false);
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.arrow.setVisibility(View.GONE);
        }
        else {
            holder.textView.setClickable(true);
            holder.checkBox.setVisibility(View.GONE);
            holder.arrow.setVisibility(View.VISIBLE);
        }

        if(checkBoxSelectAll)
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemViewType(int position) {

        Log.i("PositionLogins", Integer.toString(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return logins.size();
    }

    public void checkBoxesVisibility(boolean state)
    {
        checkBoxVisibility = state;
        notifyDataSetChanged();
    }

    public void setAllCheckBoxes(boolean state)
    {
        checkBoxSelectAll = state;
        notifyDataSetChanged();
    }

    public void deleteSelectedLogins(String id)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles/" + id + "/categories");

        for(Integer position: checkBoxStatus)
        {
            myRef.child(types.get(position)).child(logins.get(position)).removeValue();
        }
    }

    public class loginsHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public CheckBox checkBox;
        public ImageView arrow;
        //public View container;

        public loginsHolder(View itemView) {

            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.numID);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myListener.SendSelectLoginInfo(logins.get(getAdapterPosition()), types.get(getAdapterPosition()) );
                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b)
                        checkBoxStatus.add(getAdapterPosition());
                    else
                    {
                        myListener.setStateSomeCheckedRadioButton("green");
                        checkBoxStatus.remove(getAdapterPosition());
                    }

                    if(checkBoxStatus.isEmpty())
                    {
                        myListener.setStateSomeCheckedRadioButton("default");
                        myListener.setStateDeleteButton(false);
                    }
                    else
                        myListener.setStateDeleteButton(true);

                    if(checkBoxStatus.size() == getItemCount())
                    {
                        myListener.allCheckBoxesCheckedManually();
                    }
                }
            });
        }
    }
}
