package com.lasanimas.simplyvaldo.mylogins.Helper;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.lasanimas.simplyvaldo.mylogins.Model.profilesDB;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.PrimaryActivity;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.viewProfile;

public class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback
{
    private FirebaseRecyclerAdapter adapter;

    public ItemTouchHelperCallback(FirebaseRecyclerAdapter adapter) {
        super(ItemTouchHelper.UP, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        profilesDB clickedProfile = (profilesDB) adapter.getItem(viewHolder.getAdapterPosition());
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
