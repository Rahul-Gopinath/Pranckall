package com.example.pranckall.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pranckall.R;
import com.example.pranckall.RecyclerViewAdapter;
import com.example.pranckall.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private TextView myName;
    private TextView myPhone;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerView = view.findViewById(R.id.recycler_view2);
        myName = view.findViewById(R.id.my_name);
        myPhone = view.findViewById(R.id.my_phone);

        reference = FirebaseDatabase.getInstance().getReference();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        reference.child("User").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation user = dataSnapshot.getValue(UserInformation.class);
                myName.setText(user.getName());
                myPhone.setText(user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final String[] name = {"Rahul Gopinath","Priya Gopinath","V G Gopinath","Rahul Gopinath","Priya Gopinath","V G Gopinath","Rahul Gopinath","Priya Gopinath","V G Gopinath"};
        final String[] phone = {"8138915895","9895798821","9895163895","8138915895","9895798821","9895163895","8138915895","9895798821","9895163895"};
        recyclerView.setAdapter(new RecyclerViewAdapter(name, phone));

        return view;
    }
}
