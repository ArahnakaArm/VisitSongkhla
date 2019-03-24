package com.example.deimos.visitsongkhla;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormDiaryTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormDiaryTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormDiaryTab extends Fragment {
    TextInputLayout layout1,layout2,layout3,layout4;
    EditText edit1,edit2,edit3,edit4;
    Button summit;
    String android_id;
    TextView textcheck;
    Boolean formStateCheck = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Userdiary = database.getReference("User");
    private FrameLayout mFrameOverlay;
    DatabaseReference ref;
    Query Q;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

       android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        layout1 = rootView.findViewById(R.id.input_layout_name);
        layout2 = rootView.findViewById(R.id.input_layout_surname);
        layout3 = rootView.findViewById(R.id.input_layout_email);
        layout4 = rootView.findViewById(R.id.input_layout_age);
        summit = rootView.findViewById(R.id.btn_signup);
        edit1 = rootView.findViewById(R.id.input_name);
        edit2 = rootView.findViewById(R.id.input_surname);
        edit3 = rootView.findViewById(R.id.input_email);
        edit4 = rootView.findViewById(R.id.input_age);
        textcheck = rootView.findViewById(R.id.textCheck);

        ref = FirebaseDatabase.getInstance().getReference("User").child(android_id);
        Q = ref.orderByChild("name");



        new FormDiaryTab.MyTasks(FormDiaryTab.this).execute((Void) null);


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


    private class MyTasks extends AsyncTask<Void, Void, Void> {

        private FormDiaryTab mActivity;

        public MyTasks(FormDiaryTab activity) {
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            summit.setVisibility(View.INVISIBLE);
            mFrameOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // do heavy work
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("name").exists()){
                        summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                        summit.setText("แก้ไข");
                    }else {
                        summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                        summit.setText("บันทึก");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("name").exists()) {
                        Toast.makeText(getContext(),"data exists",Toast.LENGTH_SHORT).show();
                        layout1.setVisibility(View.INVISIBLE);
                        layout2.setVisibility(View.INVISIBLE);
                        layout3.setVisibility(View.INVISIBLE);
                        layout4.setVisibility(View.INVISIBLE);
                        textcheck.setVisibility(View.VISIBLE);
                        formStateCheck=true;
                       /* summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                        summit.setText("แก้ไข");*/
                        summit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(formStateCheck == true) {
                                    layout1.setVisibility(View.VISIBLE);
                                    layout2.setVisibility(View.VISIBLE);
                                    layout3.setVisibility(View.VISIBLE);
                                    layout4.setVisibility(View.VISIBLE);
                                    textcheck.setVisibility(View.INVISIBLE);
                                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                                    summit.setText("บันทึก");
                                    formStateCheck=false;
                                }
                                else {

                                    HashMap<String, Object> postValues = new HashMap<>();
                                    postValues.put("name", edit1.getText().toString());
                                    postValues.put("surname", edit2.getText().toString());
                                    postValues.put("email", edit3.getText().toString());
                                    postValues.put("age", edit4.getText().toString());
                                    Map<String, Object> childUpdates = new HashMap<>();
                                    childUpdates.put(android_id, postValues);

                                    // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                                    Userdiary.updateChildren(childUpdates);
                                    textcheck.setVisibility(View.VISIBLE);
                                    layout1.setVisibility(View.INVISIBLE);
                                    layout2.setVisibility(View.INVISIBLE);
                                    layout3.setVisibility(View.INVISIBLE);
                                    layout4.setVisibility(View.INVISIBLE);
                                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                                    summit.setText("แก้ไข");
                                    formStateCheck=true;
                                }
                            }
                        });
                        DatabaseReference refName = ref.child("name");
                        refName.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit1.setText(value);
                                edit1.setSelection(edit1.getText().length());

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });

                        DatabaseReference refSurname = ref.child("surname");
                        refSurname.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit2.setText(value);

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                        DatabaseReference refEmail = ref.child("email");
                        refEmail.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit3.setText(value);

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                        DatabaseReference refAge = ref.child("age");
                        refAge.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit4.setText(value);
                                edit4.setSelection(edit4.getText().length());

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });



                    }
                    else{
                        Toast.makeText(getContext(),"No data exists",Toast.LENGTH_SHORT).show();
                        layout1.setVisibility(View.VISIBLE);
                        layout2.setVisibility(View.VISIBLE);
                        layout3.setVisibility(View.VISIBLE);
                        layout4.setVisibility(View.VISIBLE);
                        textcheck.setVisibility(View.INVISIBLE);
                        formStateCheck=false;

                        summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                        summit.setText("บันทึก");
                        summit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(formStateCheck == true) {
                                    layout1.setVisibility(View.VISIBLE);
                                    layout2.setVisibility(View.VISIBLE);
                                    layout3.setVisibility(View.VISIBLE);
                                    layout4.setVisibility(View.VISIBLE);
                                    textcheck.setVisibility(View.INVISIBLE);
                                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconsave, 0, 0, 0);
                                    summit.setText("บันทึก");
                                    formStateCheck=false;
                                }
                                else {

                                    HashMap<String, Object> postValues = new HashMap<>();
                                    postValues.put("name", edit1.getText().toString());
                                    postValues.put("surname", edit2.getText().toString());
                                    postValues.put("email", edit3.getText().toString());
                                    postValues.put("age", edit4.getText().toString());
                                    Map<String, Object> childUpdates = new HashMap<>();
                                    childUpdates.put(android_id, postValues);
                                    Userdiary.updateChildren(childUpdates);

                                    textcheck.setVisibility(View.VISIBLE);
                                    layout1.setVisibility(View.INVISIBLE);
                                    layout2.setVisibility(View.INVISIBLE);
                                    layout3.setVisibility(View.INVISIBLE);
                                    layout4.setVisibility(View.INVISIBLE);
                                    summit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iconedit, 0, 0, 0);
                                    summit.setText("แก้ไข");
                                    formStateCheck=true;
                                }
                            }
                        });
                        DatabaseReference refName = ref.child("name");
                        refName.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit1.setText(value);
                                edit1.setSelection(edit1.getText().length());

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });

                        DatabaseReference refSurname = ref.child("surname");
                        refSurname.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit2.setText(value);

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                        DatabaseReference refEmail = ref.child("email");
                        refEmail.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit3.setText(value);

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                        DatabaseReference refAge = ref.child("age");
                        refAge.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                edit4.setText(value);
                                edit4.setSelection(edit4.getText().length());

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

        }

    }
}
