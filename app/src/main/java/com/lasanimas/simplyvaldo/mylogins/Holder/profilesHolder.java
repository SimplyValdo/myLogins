package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Interfaces.OnSwipeTouchListener;
import com.lasanimas.simplyvaldo.mylogins.R;

public class profilesHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

    public ImageView profilePic;
    public TextView profileName;
    public CheckBox checkBox;
    public View container;

    com.lasanimas.simplyvaldo.mylogins.Interfaces.OnSwipeTouchListener OnSwipeTouchListener;

    public profilesHolder(View itemView)
    {
        super(itemView);

        profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
        profileName = (TextView)itemView.findViewById(R.id.profileName);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkbox);
        container = itemView;
        itemView.setOnTouchListener(this);
    }

    public void setOnTouchListener(OnSwipeTouchListener OnSwipeTouchListener)
    {
        this.OnSwipeTouchListener = OnSwipeTouchListener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        this.OnSwipeTouchListener.onTouchItem(this.getLayoutPosition());

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                view.performClick();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                return false;
        }

        return true;
    }
}
