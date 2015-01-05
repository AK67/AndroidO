package com.example.anubhavkaushik.makemyband;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anubhavkaushik.makemyband.R;

public class WorldView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View worldview = inflater.inflate(R.layout.fragment_friends_view, container, false);
        return worldview;
    }}