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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewToFragmentListener;
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
    private RecyclerViewToFragmentListener myListener;

    public RecyclerViewAdapterLogins(Context context, ArrayList<String> logins, HashMap<Integer,String> types, RecyclerViewToFragmentListener myListener) {
        this.logins = logins;
        this.types = types;
        this.checkBoxVisibility = false;
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

        if(checkBoxVisibility)
        {
            holder.textView.setClickable(false);
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.arrow.setVisibility(View.GONE);
        }
        else
        {
            holder.textView.setClickable(true);
            holder.checkBox.setVisibility(View.GONE);
            holder.arrow.setVisibility(View.VISIBLE);
        }

        /*if(checkBoxStatus.contains(holder.checkBox))
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);*/
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
        public CheckBox checkBox;
        public ImageView arrow;
        public View container;

        public loginsHolder(final View itemView) {

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

                    if(checkBoxStatus.contains(getAdapterPosition()))
                        checkBoxStatus.remove(getAdapterPosition());
                    else
                        checkBoxStatus.add(getAdapterPosition());

                    if(checkBoxStatus.isEmpty())
                        myListener.setStateDeleteButton(false);
                    else
                        myListener.setStateDeleteButton(true);

                }
            });
        }
    }

    public void checkBoxesVisibility(boolean state)
    {
        if(state)
            checkBoxVisibility = true;
        else
            checkBoxVisibility = false;

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
}
