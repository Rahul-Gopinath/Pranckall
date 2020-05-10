package com.example.pranckall.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pranckall.R;
import com.example.pranckall.RecyclerViewAdapter;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        String[] name = {"Rahul Gopinath","Priya Gopinath","V G Gopinath","Rahul Gopinath","Priya Gopinath","V G Gopinath","Rahul Gopinath","Priya Gopinath","V G Gopinath"};
        String[] phone = {"8138915895","9895798821","9895163895","8138915895","9895798821","9895163895","8138915895","9895798821","9895163895"};
        recyclerView.setAdapter(new RecyclerViewAdapter(name, phone));
        return view;
    }
}
