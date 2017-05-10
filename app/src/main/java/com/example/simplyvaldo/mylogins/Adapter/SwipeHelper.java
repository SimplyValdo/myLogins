package com.example.simplyvaldo.mylogins.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.simplyvaldo.mylogins.Model.ProfilesDB;
import com.example.simplyvaldo.mylogins.View.Activities.PrimaryActivity;
import com.example.simplyvaldo.mylogins.View.Activities.viewProfile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class SwipeHelper extends ItemTouchHelper.SimpleCallback
{
    private FirebaseRecyclerAdapter adapter;

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(FirebaseRecyclerAdapter adapter) {
        super(ItemTouchHelper.UP, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        ProfilesDB clickedProfile = (ProfilesDB) adapter.getItem(viewHolder.getAdapterPosition());
        String clickedFireBaseKey = adapter.getRef(viewHolder.getAdapterPosition()).getKey();

        Intent intent = new Intent(PrimaryActivity.getContext(), viewProfile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key", clickedFireBaseKey);
        intent.putExtra("name", clickedProfile.getName());
        intent.putExtra("lastName", clickedProfile.getLastName());
        intent.putExtra("dateCreation", clickedProfile.getDateCreation());
        intent.putExtra("relationship", clickedProfile.getRelationship());
        PrimaryActivity.getContext().startActivity(intent);
    }
}
