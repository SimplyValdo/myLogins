package com.lasanimas.simplyvaldo.mylogins.Helper;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class SwipeHelperFireBase extends ItemTouchHelper.SimpleCallback
{
    private FirebaseRecyclerAdapter adapter;

    public SwipeHelperFireBase(FirebaseRecyclerAdapter adapter) {
        super(ItemTouchHelper.UP, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(FirebaseRecyclerAdapter recyclerView, FirebaseRecyclerAdapter.ViewHolder viewHolder, FirebaseRecyclerAdapter.ViewHolder target) {

        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        /*profilesDB clickedProfile = (profilesDB) adapter.getItem(viewHolder.getAdapterPosition());
        String clickedFireBaseKey = adapter.getRef(viewHolder.getAdapterPosition()).getKey();

        Intent intent = new Intent(PrimaryActivity.getContext(), viewProfile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key", clickedFireBaseKey);
        intent.putExtra("name", clickedProfile.getName());
        intent.putExtra("lastName", clickedProfile.getLastName());
        intent.putExtra("dateCreation", clickedProfile.getDateCreation());
        intent.putExtra("relationship", clickedProfile.getRelationship());
        PrimaryActivity.getContext().startActivity(intent);*/

        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
