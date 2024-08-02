package com.example.educonnect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Generate_Form#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Generate_Form extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment generate_form.
     */
    // TODO: Rename and change types and number of parameters
    public static Generate_Form newInstance(String param1, String param2) {
        Generate_Form fragment = new Generate_Form();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Generate_Form() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_generate_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String key = "AIzaSyAZJnHDozsRK7rkMl-nT5bKT7ZT1G6Fn3c";

        GenerativeModel gm = new GenerativeModel( "gemini-1.5-flash", key);

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);



        Executor executor =  Executors.newFixedThreadPool(4);



        Button generate = view.findViewById(R.id.applicationGenerate);
        EditText titleTxt = view.findViewById(R.id.applicationTitle);
        EditText bodyTxt = view.findViewById(R.id.applicationBody);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate.setText("Please Wait...");
                String title = titleTxt.getText().toString();
                String body = bodyTxt.getText().toString();
                Toast.makeText(getActivity(), title+" "+body, Toast.LENGTH_SHORT).show();
                FragmentActivity activity = (FragmentActivity) getActivity();
                Fragment frgmnt = new ApplicationActivity();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();

                Content content = new Content.Builder()
                        .addText("My name is Arnab. my background is computer science and engineering from leading university. application subject is: '"+title+"' and some text has provided about this application. text is: '"+body+"' now provide an formal application for me. ")
                        .build();

                ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
                Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                    @Override
                    public void onSuccess(GenerateContentResponse result) {
                        String resultText = result.getText();

                        ApplicationActivity.applicationBody = resultText;
                        System.out.println(resultText);
                        fragmentManager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main_drawer, frgmnt) // Replace with your container ID
                                .addToBackStack(null)
                                .commit();
//                Toast.makeText(getActivity(), "Hare Krishna", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, executor);




            }
        });










    }
}