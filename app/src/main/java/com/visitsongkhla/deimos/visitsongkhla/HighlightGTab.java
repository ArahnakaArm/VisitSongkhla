package com.visitsongkhla.deimos.visitsongkhla;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class HighlightGTab extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private static final int ACTIVITY_NUM = 1;
    FirebaseDatabase mFirebaseDatabase;
    Query Q;
    int count;
    private LinearLayout textError;

    private FirebaseRecyclerAdapter<CommonModel, HighlightGTab.NewsViewHolder> RVAdapter;
    DatabaseReference mRef;
    public static int positionIndex = -1;
    public static int topView = -1;
    LinearLayoutManager linearLayoutManager ;
    private HighlightGTab mActivity;
    private FrameLayout mFrameOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_tab, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Travel-Highlight").child(getString(R.string.Language));
        Q = mRef.orderByChild("type").equalTo("google");
        mRef.keepSynced(true);
        textError = rootView.findViewById(R.id.texterror);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFrameOverlay = (FrameLayout)rootView.findViewById(R.id.overlay);

        new HighlightGTab.MyTasks(HighlightGTab.this).execute((Void) null);

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
            TextView post_title = (TextView) mView.findViewById(R.id.name_item);
            post_title.setText(title);
        }

        /* public void setDes(String desc){
             TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
             post_desc.setText(desc);
         }*/
        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_item);
            Picasso.get().load(image).into(post_image);
        }

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class MyTasks extends AsyncTask<Void, Void, Void> {
        ListView theList;
        public MyTasks(HighlightGTab activity) {
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
            RVAdapter = new FirebaseRecyclerAdapter<CommonModel, NeturalTab.NewsViewHolder>(foodOptions) {
                @Override
                protected void onBindViewHolder(@NonNull NeturalTab.NewsViewHolder holder, int position, final CommonModel model) {
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
                public NeturalTab.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.listitem_places, parent, false);

                    return new NeturalTab.NewsViewHolder(view);
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


                                    count = RVAdapter.getItemCount();

                                    if(count == 0){
                                        textError.setVisibility(View.VISIBLE);
                                    }
                                }
                            },4000);
                            FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(Q,CommonModel.class).build();
                            RVAdapter = new FirebaseRecyclerAdapter<CommonModel, HighlightGTab.NewsViewHolder>(foodOptions) {
                                @Override
                                protected void onBindViewHolder(@NonNull HighlightGTab.NewsViewHolder holder, int position, final CommonModel model) {
                                    holder.setTitle(model.getTitle());
                                    // holder.setDes(model.getDescription());
                                    holder.setImage(getContext(), model.getUrl());
                                    holder.mView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            final String url = model.getUrl();
                                            Intent intent = new Intent(getContext(), GalleryNomoreplace.class);
                                            intent.putExtra("image_name", model.getTitle());
                                            intent.putExtra("image_url", model.getUrl());
                                            intent.putExtra("Des", model.getDes());
                                            intent.putExtra("Local",model.getLocation());
                                            intent.putExtra("Tel",model.getTel());
                                            intent.putExtra("Lat",model.getLat());
                                            intent.putExtra("Lng",model.getLng());
                                            intent.putExtra("Id",model.getId());
                                            intent.putExtra("Category","Travel-Highlight");
                                            startActivity(intent);
                                        }
                                    });

                                }

                                @NonNull
                                @Override
                                public HighlightGTab.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.listitem_places, parent, false);

                                    return new HighlightGTab.NewsViewHolder(view);
                                }
                            };
                            mRecyclerView.setAdapter(RVAdapter);
                            RVAdapter.startListening();

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
}
