package com.example.anubhavkaushik.makemyband;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anubhavkaushik.makemyband.R;

public class FriendsView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View friendsView = inflater.inflate(R.layout.fragment_friends_view, container, false);
        return friendsView;
    }}