package com.example.educonnect;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplicationList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<ApplicationData> data;

    Dialog choose;
    Button generate, manual;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ApplicationList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplicationList.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplicationList newInstance(String param1, String param2) {
        ApplicationList fragment = new ApplicationList();
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
        return inflater.inflate(R.layout.fragment_application_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data =  new ArrayList<ApplicationData>();
        data.add(new ApplicationData("Krishna", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        data.add(new ApplicationData("Nitai", "Jay jagannath ajk ratha jatra", "Radhe", "Pollob", "nothing"));
        RecyclerView list = view.findViewById(R.id.applicationList);
        ApplicationListAdapter adptr = new ApplicationListAdapter(getActivity(), data);
        list.setAdapter(adptr);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

//1716546250

        FloatingActionButton add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose.show();
            }
        });

        choose = new Dialog(getActivity());
        choose.setContentView(R.layout.add_application_type);
        choose.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.choose_background);
        choose.getWindow().setBackgroundDrawable(drawable);


        generate = choose.getWindow().findViewById(R.id.generateApplication);
        manual = choose.getWindow().findViewById(R.id.manualApplication);


//        generate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Krishna", Toast.LENGTH_SHORT).show();
//            }
//        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hare Krishna generated", Toast.LENGTH_SHORT).show();
                FragmentActivity activity = (FragmentActivity) getActivity();
                Fragment frgmnt = new Generate_Form();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main_drawer, frgmnt) // Replace with your container ID
                        .addToBackStack(null)
                        .commit();
                choose.dismiss();
            }
        });


//        Activity d = getActivity();
//        Intent intent = new Intent(getActivity(), Generate_Form.class);
//        startActivity(intent);



        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Hare Krishna Manual", Toast.LENGTH_SHORT).show();
            }
        });






    }

    void gotoGenerateForm(){
        Intent intent = new Intent(getActivity(), Generate_Form.class);
        startActivity(intent);
    }


}