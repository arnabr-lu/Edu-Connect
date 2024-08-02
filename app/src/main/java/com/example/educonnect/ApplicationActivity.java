package com.example.educonnect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationActivity extends Fragment {


    public static String applicationBody = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ApplicationActivity() {
        // Required empty public constructor
    }






    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplicationActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplicationActivity newInstance(String param1, String param2) {
        ApplicationActivity fragment = new ApplicationActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText body = view.findViewById(R.id.applicationBody);
        body.setText(applicationBody);

//        FloatingActionButton fab = view.findViewById(R.id.application_decision);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View
//                    view) {
//                // Create a PopupMenu
//                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
//                popupMenu.getMenuInflater().inflate(R.menu.application_button, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if(item.getItemId()==R.id.allow) return true;
//                        else if(item.getItemId()==R.id.decline) return true;
//                        else return false;
//
//                    }
//
//                });
//
//                popupMenu.show();
//            }
//        });

    }
}