package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormDiaryTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormDiaryTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormDiaryTab extends Fragment {
    TextInputLayout layout1,layout2,layout3,layout4,layout5;
    EditText edit1,edit2,edit3,edit4,edit5;
    Button summit;
    private RadioButton radioSexButton;
    String android_id;
    Check_internet check_internet;
    TextView textcheck;
    ProgressDialog dialogpush;
    Boolean formStateCheck = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Userdiary = database.getReference("User");
    private FrameLayout mFrameOverlay;
    DatabaseReference ref;
    private RadioGroup radioSexGroup;
    Query Q;
    private TextView texttitle1;
    private boolean checkfirsttime= true;
    private String radiotext;
    Boolean checkCorrect = false;
    private Spinner spinner;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textSex,textNation;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FormDiaryTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormDiaryTab.
     */
    // TODO: Rename and change types and number of parameters
    public static FormDiaryTab newInstance(String param1, String param2) {
        FormDiaryTab fragment = new FormDiaryTab();
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
        View rootView = inflater.inflate(R.layout.fragment_form_diary_tab, container, false);
        mFrameOverlay = (FrameLayout) rootView.findViewById(R.id.overlay);
        radioSexGroup= rootView.findViewById(R.id.radiosex);
        texttitle1 = rootView.findViewById(R.id.textTitleFirst);
        spinner = rootView.findViewById(R.id.spinner);
       android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
       radiotext = "ชาย";
        layout1 = rootView.findViewById(R.id.input_layout_name);
        layout2 = rootView.findViewById(R.id.input_layout_traveltime);
        layout3 = rootView.findViewById(R.id.input_layout_email);
        layout4 = rootView.findViewById(R.id.input_layout_age);
        layout5 = rootView.findViewById(R.id.input_layout_travelbudget);
        summit = rootView.findViewById(R.id.btn_signup);
        edit1 = rootView.findViewById(R.id.input_name);
        edit2 = rootView.findViewById(R.id.input_traveltime);
        edit3 = rootView.findViewById(R.id.input_email);
        edit4 = rootView.findViewById(R.id.input_age);
        edit5 = rootView.findViewById(R.id.input_travelbudget);
        textNation =rootView.findViewById(R.id.nationtext);
        textSex =rootView.findViewById(R.id.sextext);
        textcheck = rootView.findViewById(R.id.textCheck);

        edit1.addTextChangedListener(new MyTextWatcher(edit1));
        edit2.addTextChangedListener(new MyTextWatcher(edit2));
        edit3.addTextChangedListener(new MyTextWatcher(edit3));
        edit4.addTextChangedListener(new MyTextWatcher(edit4));
        edit5.addTextChangedListener(new MyTextWatcher(edit5));

        ref = FirebaseDatabase.getInstance().getReference("User").child(android_id);
        Q = ref.orderByChild("name");


        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);
        textNation.setVisibility(View.INVISIBLE);
        textSex.setVisibility(View.INVISIBLE);
        radioSexGroup.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        summit.setVisibility(View.INVISIBLE);
        mFrameOverlay.setVisibility(View.VISIBLE);
        // }

      /*  @Override
        protected Void doInBackground(Void... params) {
            // do heavy work*/
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Activity activity = getActivity();
                if(activity != null && isAdded()) {

                if(dataSnapshot.child("name").exists()){
                    checkfirsttime=false;
                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                    summit.setText(getString(R.string.FD8));
                }else {
                    checkfirsttime=true;
                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                    summit.setText(getString(R.string.FD7));
                }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        /*    return null;
        }
*/
      /*  @Override
        protected void onPostExecute(Void result) {*/
        //   if (isAdded()) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("name").exists()) {
                    // Toast.makeText(getContext(),"data exists",Toast.LENGTH_SHORT).show();
                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.INVISIBLE);
                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.INVISIBLE);
                    layout5.setVisibility(View.INVISIBLE);
                    textNation.setVisibility(View.INVISIBLE);
                    textSex.setVisibility(View.INVISIBLE);
                    radioSexGroup.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.INVISIBLE);
                    textcheck.setVisibility(View.VISIBLE);
                    texttitle1.setVisibility(View.GONE);
                    formStateCheck = true;
                       /* summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                        summit.setText("แก้ไข");*/
                    summit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkForm();
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                            if (checkCorrect == true) {
                                //Toast.makeText(getContext(),"Yes!!",Toast.LENGTH_SHORT).show();
                                getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialogpush = new ProgressDialog(getContext());
                                                dialogpush.setCancelable(false);
                                                dialogpush.setTitle(getString(R.string.FD5));
                                                dialogpush.setMessage(getString(R.string.FD6));
                                                dialogpush.show();

                                                final Handler handler1 = new Handler();
                                                handler1.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        dialogpush.dismiss();
                                                        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                            @Override
                                                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                                                switch (i) {
                                                                    case R.id.radio_1:
                                                                        radiotext = "ชาย";
                                                                        break;
                                                                    case R.id.radio_2:
                                                                        radiotext = "หญิง";
                                                                        break;
                                                                    case R.id.radio_3:
                                                                        radiotext = "อื่นๆ";
                                                                        break;
                                                                }
                                                            }
                                                        });
                                                        if (formStateCheck == true) {
                                                            layout1.setVisibility(View.VISIBLE);
                                                            layout2.setVisibility(View.VISIBLE);
                                                            layout3.setVisibility(View.VISIBLE);
                                                            layout4.setVisibility(View.VISIBLE);
                                                            layout5.setVisibility(View.VISIBLE);
                                                            texttitle1.setVisibility(View.VISIBLE);
                                                            textNation.setVisibility(View.VISIBLE);
                                                            textSex.setVisibility(View.VISIBLE);
                                                            radioSexGroup.setVisibility(View.VISIBLE);
                                                            spinner.setVisibility(View.VISIBLE);
                                                            textcheck.setVisibility(View.INVISIBLE);
                                                            summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                                                            summit.setText(getString(R.string.FD7));
                                                            formStateCheck = false;
                                                        } else {

                                                            HashMap<String, Object> postValues = new HashMap<>();
                                                            postValues.put("name", edit1.getText().toString());
                                                            postValues.put("traveltime", edit2.getText().toString());
                                                            postValues.put("email", edit3.getText().toString());
                                                            postValues.put("age", edit4.getText().toString());
                                                            postValues.put("budget", edit5.getText().toString());
                                                            postValues.put("sex", radiotext);
                                                            postValues.put("nationality", spinner.getSelectedItem().toString());

                                                            Map<String, Object> childUpdates = new HashMap<>();
                                                            childUpdates.put(android_id, postValues);

                                                            // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                                                            Userdiary.updateChildren(childUpdates);
                                                            textcheck.setVisibility(View.VISIBLE);
                                                            layout1.setVisibility(View.INVISIBLE);
                                                            layout2.setVisibility(View.INVISIBLE);
                                                            layout3.setVisibility(View.INVISIBLE);
                                                            layout4.setVisibility(View.INVISIBLE);
                                                            layout5.setVisibility(View.INVISIBLE);
                                                            texttitle1.setVisibility(View.GONE);
                                                            textNation.setVisibility(View.INVISIBLE);
                                                            textSex.setVisibility(View.INVISIBLE);
                                                            radioSexGroup.setVisibility(View.INVISIBLE);
                                                            spinner.setVisibility(View.INVISIBLE);
                                                            summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                                                            summit.setText(getString(R.string.FD8));
                                                            formStateCheck = true;
                                                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                                        }
                                                           /* InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);*/

                                                    }
                                                }, 2200);

                                            }

                                        }, 0);

                                    }
                                });


                            } else {

                            }


                        }
                    });
                    DatabaseReference refName = ref.child("name");
                    refName.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit1.setText(value);
                                edit1.setSelection(edit1.getText().length());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    DatabaseReference refSurname = ref.child("traveltime");
                    refSurname.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit2.setText(value);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });


                    DatabaseReference refEmail = ref.child("email");
                    refEmail.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit3.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });


                    DatabaseReference refAge = ref.child("age");
                    refAge.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit4.setText(value);
                                edit4.setSelection(edit4.getText().length());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    DatabaseReference refBudget = ref.child("budget");
                    refBudget.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit5.setText(value);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                } else {
                    //Toast.makeText(getContext(),"No data exists",Toast.LENGTH_SHORT).show();
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.VISIBLE);
                    layout4.setVisibility(View.VISIBLE);
                    layout5.setVisibility(View.VISIBLE);
                    texttitle1.setVisibility(View.VISIBLE);
                    textNation.setVisibility(View.VISIBLE);
                    textSex.setVisibility(View.VISIBLE);
                    radioSexGroup.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    textcheck.setVisibility(View.INVISIBLE);
                    formStateCheck = false;

                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                    summit.setText(getString(R.string.FD7));
                    summit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                            checkForm();

                            //Toast.makeText(getContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            if (checkCorrect == true) {
                                getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialogpush = new ProgressDialog(getContext());
                                                dialogpush.setCancelable(false);
                                                dialogpush.setTitle("กำลังดำเนินการ");
                                                dialogpush.setMessage("กรุณารอสักครู่...");
                                                dialogpush.show();
                                                radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                                        switch (i) {
                                                            case R.id.radio_1:
                                                                radiotext = "ชาย";
                                                                break;
                                                            case R.id.radio_2:
                                                                radiotext = "หญิง";
                                                                break;
                                                            case R.id.radio_3:
                                                                radiotext = "อื่นๆ";
                                                                break;
                                                        }
                                                    }
                                                });
                                                final Handler handler1 = new Handler();
                                                handler1.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        dialogpush.dismiss();
                                                        if (formStateCheck == true) {
                                                            layout1.setVisibility(View.VISIBLE);
                                                            layout2.setVisibility(View.VISIBLE);
                                                            layout3.setVisibility(View.VISIBLE);
                                                            layout4.setVisibility(View.VISIBLE);
                                                            layout5.setVisibility(View.VISIBLE);
                                                            texttitle1.setVisibility(View.VISIBLE);
                                                            textNation.setVisibility(View.VISIBLE);
                                                            textSex.setVisibility(View.VISIBLE);
                                                            radioSexGroup.setVisibility(View.VISIBLE);
                                                            spinner.setVisibility(View.VISIBLE);
                                                            textcheck.setVisibility(View.INVISIBLE);
                                                            summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                                                            summit.setText(getString(R.string.FD7));
                                                            formStateCheck = false;
                                                        } else {

                                                            HashMap<String, Object> postValues = new HashMap<>();
                                                            postValues.put("name", edit1.getText().toString());
                                                            postValues.put("traveltime", edit2.getText().toString());
                                                            postValues.put("email", edit3.getText().toString());
                                                            postValues.put("age", edit4.getText().toString());
                                                            postValues.put("budget", edit5.getText().toString());
                                                            postValues.put("sex", radiotext);
                                                            postValues.put("nationality", spinner.getSelectedItem().toString());
                                                            Map<String, Object> childUpdates = new HashMap<>();
                                                            childUpdates.put(android_id, postValues);
                                                            Userdiary.updateChildren(childUpdates);

                                                            textcheck.setVisibility(View.VISIBLE);
                                                            layout1.setVisibility(View.INVISIBLE);
                                                            layout2.setVisibility(View.INVISIBLE);
                                                            layout3.setVisibility(View.INVISIBLE);
                                                            layout4.setVisibility(View.INVISIBLE);
                                                            layout5.setVisibility(View.INVISIBLE);
                                                            texttitle1.setVisibility(View.GONE);
                                                            textNation.setVisibility(View.INVISIBLE);
                                                            textSex.setVisibility(View.INVISIBLE);
                                                            radioSexGroup.setVisibility(View.INVISIBLE);
                                                            spinner.setVisibility(View.INVISIBLE);
                                                            summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                                                            summit.setText(getString(R.string.FD8));
                                                            formStateCheck = true;
                                                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                                        }
                                                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                                    }
                                                }, 2200);

                                            }

                                        }, 0);

                                    }
                                });
                            } else {

                            }


                        }
                    });
                    DatabaseReference refName = ref.child("name");
                    refName.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit1.setText(value);
                                edit1.setSelection(edit1.getText().length());
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    DatabaseReference refSurname = ref.child("surname");
                    refSurname.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit2.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });


                    DatabaseReference refEmail = ref.child("email");
                    refEmail.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit3.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });


                    DatabaseReference refAge = ref.child("age");
                    refAge.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit4.setText(value);
                                edit4.setSelection(edit4.getText().length());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    DatabaseReference refBudget = ref.child("budget");
                    refBudget.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!checkfirsttime) {
                                String value = dataSnapshot.getValue(String.class);
                                edit5.setText(value);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
                summit.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mFrameOverlay.setVisibility(View.GONE);









       // new FormDiaryTab.MyTasks(FormDiaryTab.this).execute((Void) null);


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

/*
    private class MyTasks extends AsyncTask<Void, Void, Void> {

        private FormDiaryTab mActivity;

        public MyTasks(FormDiaryTab activity) {
            mActivity = activity;
        }

        @Override*/
      //  protected void onPreExecute() {

    private void checkForm(){
        if(!validateName()){
            checkCorrect = false;
            return;
        }
          if(!validateTime()){
            checkCorrect = false;
            return;
        }
        if(!validateEmail()){
            checkCorrect = false;
            return;
        }
        if(!validateAge()){
            checkCorrect = false;
            return;
        }
        if(!validateBudget()){
            checkCorrect = false;
            return;
        }
        checkCorrect = true;
    }
    private boolean validateName(){
        if(edit1.getText().toString().trim().isEmpty()){
            layout1.setError(getString(R.string.FD9));
            requestFocus(edit1);
            return false;
        }
        else{
            layout1.setErrorEnabled(false);
        }
        return true;
    }
   private boolean validateTime(){
        if(edit2.getText().toString().trim().isEmpty()){
            layout2.setError(getString(R.string.FD10));
            requestFocus(edit2);
            return false;
        }
        else{
            layout2.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateEmail() {
        String email = edit3.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            layout3.setError(getString(R.string.FD11));
            requestFocus(edit3);
            return false;
        } else {
            layout3.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateAge(){
        if(edit4.getText().toString().trim().isEmpty()){
            layout4.setError(getString(R.string.FD12));
            requestFocus(edit4);
            return false;
        }
        else{
            layout4.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateBudget(){
        if(edit5.getText().toString().trim().isEmpty()){
            layout5.setError(getString(R.string.FD13));
            requestFocus(edit5);
            return false;
        }
        else{
            layout5.setErrorEnabled(false);
        }
        return true;
    }
    private  boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_traveltime:
                    validateTime();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_age:
                    validateAge();
                    break;
                case R.id.input_travelbudget:
                    validateBudget();
                    break;
            }
        }
    }
    @Override
    public void onStop() {

        super.onStop();
    }
}
