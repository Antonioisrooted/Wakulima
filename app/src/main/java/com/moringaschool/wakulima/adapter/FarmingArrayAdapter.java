package com.moringaschool.wakulima.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.R;
import com.moringaschool.wakulima.ui.AnimalDetails;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmingArrayAdapter extends RecyclerView.Adapter<FarmingArrayAdapter.farmingViewHolder> {

    private Context mContext;
    private List<Farming> mFarming;

    public FarmingArrayAdapter(Context context, List<Farming>farming) {

        this.mContext=context;
        this.mFarming=farming;
    }

    @Override
    public FarmingArrayAdapter.farmingViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farming_list_items, parent,false);
        farmingViewHolder viewHolder = new farmingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FarmingArrayAdapter.farmingViewHolder holder, int position){
        holder.bindFarming(mFarming.get(position));
    }

    @Override
    public int getItemCount (){
        return mFarming.size();
    }

    public class farmingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.typeTextView)
        TextView mType;
        @BindView(R.id.breedTextView)
        TextView mBreed;
        @BindView(R.id.imageView)
        ImageView mImage;

        private Context mContext;
        public farmingViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindFarming(Farming farming){
            mType.setText(farming.getType());
            mBreed.setText(farming.getBreed());
            Picasso.get().load(farming.getImage()).into(mImage);
        }
        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent (mContext, AnimalDetails.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("animals", Parcels.wrap(mFarming));
            mContext.startActivity(intent);
        }
    }
}