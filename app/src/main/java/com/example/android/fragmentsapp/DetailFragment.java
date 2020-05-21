package com.example.android.fragmentsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DetailFragment extends Fragment {

    private EditText textFirstName, textLastName, textAge;

    private FragmentListener mListener;
    private String mFirstName;
    private String mLastName;
    private int mAge;

    public DetailFragment() {
    }

    //To receive data from the Activity
    //To pass the Person object in a bundle, need to implement the parcelable interface in the Person POJO
    public static DetailFragment newInstance(Person person) {
        Bundle args = new Bundle();
        //Bundle the Person data object
        args.putParcelable("PERSON_KEY", person);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (FragmentListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        textFirstName = (EditText) rootView.findViewById(R.id.textFirstName);
        textLastName = (EditText) rootView.findViewById(R.id.textLastName);
        textAge = (EditText) rootView.findViewById(R.id.textAge);

        Person person = getArguments().getParcelable("PERSON_KEY");
        textFirstName.setText(person.getFirstName());
        textLastName.setText(person.getLastName());
        textAge.setText(String.valueOf(person.getAge()));

        Button doneButton = (Button) rootView.findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        return rootView;
    }

    private void done() {
        if (mListener == null) {
            getInput();
        } else {
            getInput();
            Person person = new Person (mFirstName, mLastName, mAge);
            mListener.onFragmentFinish(person);
        }
    }

    private void getInput() {
        mFirstName = textFirstName.getText().toString().trim();
        mLastName = textLastName.getText().toString().trim();
        mAge = Integer.parseInt(textAge.getText().toString());
    }

    public interface FragmentListener {
        void onFragmentFinish (Person person);
    }





}
