package com.shruti.school;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
ImageSlider imageSlider;
VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider=view.findViewById(R.id.issHomeImageSlider);
        videoView=view.findViewById(R.id.vvHomeVideo);
        ArrayList<SlideModel> slideModelArrayList=new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.clang,"slide 1", ScaleTypes.FIT));
slideModelArrayList.add(new SlideModel(R.drawable.cpp,"Slide 2",ScaleTypes.FIT) );
slideModelArrayList.add(new SlideModel(R.drawable.java,"Slide 3",ScaleTypes.FIT));
slideModelArrayList.add(new SlideModel(R.drawable.mad,"Slide 4",ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.datastucture,"Slide 5",ScaleTypes.FIT) );
        slideModelArrayList.add(new SlideModel(R.drawable.database,"Slide 6",ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.python,"Slide 7",ScaleTypes.FIT));
imageSlider.setImageList(slideModelArrayList);
imageSlider.setSlideAnimation(AnimationTypes.ZOOM_IN);
String videoPath="android.resource://"+getActivity().getPackageName()+
        "/raw/bumbole";
        videoView.setVideoPath(videoPath);
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();


        return view;
    }
}