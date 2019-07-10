package com.visitsongkhla.deimos.visitsongkhla;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



/**
 * Created by arahnaka on 6/20/2018.
 */

public class  HistoryDiaryTab extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    String android_id;
    private static final int ACTIVITY_NUM = 1;
    FirebaseDatabase mFirebaseDatabase;
    Query Q;
    Check_internet check_internet;
    private FirebaseRecyclerAdapter<CommonModel, HistoryDiaryTab.NewsViewHolder> RVAdapter;
    DatabaseReference mRef;
    public static int positionIndex = -1;
    public static int topView = -1;
    LinearLayoutManager linearLayoutManager ;
    private HistoryDiaryTab mActivity;
    private int count;
    private LinearLayout textError;
    private FrameLayout mFrameOverlay;
    private int stateInternet=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_diary_tab, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        textError = rootView.findViewById(R.id.texterror);
        //check_connection();
        stateInternet = InternetCheckState.getState();
        android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        mRef = mFirebaseDatabase.getReference("User-Places").child(android_id).child("CheckIn");
        Q = mRef.orderByChild("type").equalTo("ประวัติศาสตร์");
        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFrameOverlay = (FrameLayout)rootView.findViewById(R.id.overlay);
        new HistoryDiaryTab.MyTasks(HistoryDiaryTab.this).execute((Void) null);

/*


        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(Q,CommonModel.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<CommonModel, CommonTab.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull CommonTab.NewsViewHolder holder, int position, final CommonModel model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                holder.setImage(getContext(), model.getUrl());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getContext(), Gallery_MorePlaces.class);
                        intent.putExtra("image_name", model.getTitle());
                        intent.putExtra("image_url", model.getUrl());
                        intent.putExtra("Des", model.getDes());
                        intent.putExtra("Local",model.getLocation());
                        intent.putExtra("Tel",model.getTel());
                        intent.putExtra("Lat",model.getLat());
                        intent.putExtra("Lng",model.getLng());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public CommonTab.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_places, parent, false);

                return new CommonTab.NewsViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(RVAdapter);
        RVAdapter.startListening();*/
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        positionIndex= linearLayoutManager.findFirstVisibleItemPosition();
        View startView = mRecyclerView.getChildAt(0);
        topView = (startView == null) ? 0 : (startView.getTop() - mRecyclerView.getPaddingTop());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (positionIndex!= -1) {
            linearLayoutManager.scrollToPositionWithOffset(positionIndex, topView);
        }

    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.history_text_name);
            post_title.setText(title);
        }

        /* public void setDes(String desc){
             TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
             post_desc.setText(desc);
         }*/
        public void setDate(String date) {
            TextView setdate = (TextView) mView.findViewById(R.id.history_text_date);
            setdate.setText("  "+date);
        }

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class MyTasks extends AsyncTask<Void, Void, Void> {
        ListView theList;
        public MyTasks(HistoryDiaryTab activity) {
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {
            // the AsyncTask it's about to start so show the overlay

            // set a touch listener and consume the event so the ListView
            // doesn't get clicked
            mFrameOverlay.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {
            // do heavy work

         /*   FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(Q,CommonModel.class).build();
            RVAdapter = new FirebaseRecyclerAdapter<CommonModel, HistiricalTab.NewsViewHolder>(foodOptions) {
                @Override
                protected void onBindViewHolder(@NonNull HistiricalTab.NewsViewHolder holder, int position, final CommonModel model) {
                    holder.setTitle(model.getTitle());
                    // holder.setDes(model.getDescription());
                    holder.setImage(getContext(), model.getUrl());
                    holder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final String url = model.getUrl();
                            Intent intent = new Intent(getContext(), Gallery_MorePlaces.class);
                            intent.putExtra("image_name", model.getTitle());
                            intent.putExtra("image_url", model.getUrl());
                            intent.putExtra("Des", model.getDes());
                            intent.putExtra("Local",model.getLocation());
                            intent.putExtra("Tel",model.getTel());
                            intent.putExtra("Lat",model.getLat());
                            intent.putExtra("Lng",model.getLng());
                            startActivity(intent);
                        }
                    });

                }

                @NonNull
                @Override
                public HistiricalTab.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.listitem_places, parent, false);

                    return new HistiricalTab.NewsViewHolder(view);
                }
            };
            mRecyclerView.setAdapter(RVAdapter);
            RVAdapter.startListening();
*/
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            mFrameOverlay.setVisibility(View.VISIBLE);
                            final  Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    mFrameOverlay.setVisibility(View.GONE);

//                                    Toast.makeText(getContext(),stateInternet,Toast.LENGTH_SHORT).show();
                                    count = RVAdapter.getItemCount();
                                    if(stateInternet==0 || count==0){
                                        textError.setVisibility(View.VISIBLE);
                                        RVAdapter.stopListening();
                                    }
                                   /* if(count == 0){
                                        textError.setVisibility(View.VISIBLE);
                                        RVAdapter.stopListening();
                                    }*/
                                }
                            },4000);
                            FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(mRef,CommonModel.class).build();
                            RVAdapter = new FirebaseRecyclerAdapter<CommonModel, HistoryDiaryTab.NewsViewHolder>(foodOptions) {
                                @Override
                                protected void onBindViewHolder(@NonNull HistoryDiaryTab.NewsViewHolder holder, int position, final CommonModel model) {
                                    holder.setTitle(model.getTitle());
                                    holder.setDate(model.getDate());


                                }

                                @NonNull
                                @Override
                                public HistoryDiaryTab.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.history_item, parent, false);

                                    return new HistoryDiaryTab.NewsViewHolder(view);
                                }
                            };
                            mRecyclerView.setAdapter(RVAdapter);
                            RVAdapter.startListening();

                            stateInternet = InternetCheckState.getState();
                            if(stateInternet==0){
                                final  Handler handler2 = new Handler();
                                handler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        textError.setVisibility(View.VISIBLE);
                                        RVAdapter.stopListening();

                                    }
                                },4000);

                            }



                        }

                    }, 0);

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            mFrameOverlay.setVisibility(View.GONE);


        }


    }
    public void check_connection(){
        check_internet = new Check_internet(getContext());
        check_internet.execute();

    }
}