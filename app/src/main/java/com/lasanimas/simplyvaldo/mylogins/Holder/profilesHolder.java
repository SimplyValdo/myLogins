package com.lasanimas.simplyvaldo.mylogins.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.R;

public class profilesHolder extends RecyclerView.ViewHolder  {

    public ImageView profilePic;
    public TextView profileName;
    public CheckBox checkBox;
    public View container;

    public profilesHolder(View itemView)
    {
        super(itemView);

        profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
        profileName = (TextView)itemView.findViewById(R.id.profileName);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkbox);
        container = itemView;
    }
}
