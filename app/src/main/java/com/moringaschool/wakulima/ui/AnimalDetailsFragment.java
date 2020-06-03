package com.moringaschool.wakulima.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalDetailsFragment extends Fragment {
    @BindView(R.id.animalImageView1)
    ImageView mImage;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.breed)
    TextView mBreed;
    @BindView(R.id.description)
    TextView mDescription;

    private Farming mFarming;
    public AnimalDetailsFragment(){
    }
    public static AnimalDetailsFragment newInstance(Farming farming){
        AnimalDetailsFragment animalDetailsFragment = new AnimalDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("animals", Parcels.wrap(farming));
        animalDetailsFragment.setArguments(args);
        return animalDetailsFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mFarming = Parcels.unwrap(getArguments().getParcelable("animals"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntsnceState){
        View view = inflater.inflate(R.layout.fragment_animal_details, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mFarming.getImage()).into(mImage);
        mType.setText(mFarming.getType());
        mBreed.setText(mFarming.getBreed());
        mDescription.setText(mFarming.getDescription());
        return view;
    }
}