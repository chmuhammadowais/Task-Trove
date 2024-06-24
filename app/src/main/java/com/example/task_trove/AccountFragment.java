package com.example.task_trove;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        EditText name_edt = view.findViewById(R.id.name_edt);
        EditText phone_edt = view.findViewById(R.id.phone_edt);
        EditText email_edt = view.findViewById(R.id.email_edt);
        EditText password_edt = view.findViewById(R.id.password_edt);
        Button update_btn = view.findViewById(R.id.update_btn);

        update_btn.setOnClickListener(v -> {
            if(name_edt.getText().toString().isEmpty() || phone_edt.getText().toString().isEmpty() || email_edt.getText().toString().isEmpty() || password_edt.getText().toString().isEmpty()){
                Toast.makeText(requireContext(), "Please provide all fields", Toast.LENGTH_SHORT).show();
            } else if (!email_edt.getText().toString().contains("@") || !email_edt.getText().toString().endsWith(".com")) {
                Toast.makeText(requireContext(), "Please provide a valid email", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(requireContext(), "Everything's good", Toast.LENGTH_SHORT).show();
                //Perform relevant API call
            }
        });

        // Return the inflated view with the event listeners set up
        return view;
    }
}
