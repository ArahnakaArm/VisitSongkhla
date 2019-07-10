package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHoler> {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDes = new ArrayList<>();
    private ArrayList<String> mTel = new ArrayList<>();
    private ArrayList<String> mLocation = new ArrayList<>();
    private ArrayList<String> mLat = new ArrayList<>();
    private ArrayList<String> mLng = new ArrayList<>();
    private ArrayList<String> mImage1 = new ArrayList<>();
    private ArrayList<String> mImage2 = new ArrayList<>();
    private ArrayList<String> mImage3 = new ArrayList<>();
    private ArrayList<String> mImage4 = new ArrayList<>();
    private ArrayList<String> mImage5 = new ArrayList<>();
    private ArrayList<String> mImageMore = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context mContext,ArrayList<String> mNames, ArrayList<String> mImageUrls, ArrayList<String> mDes,ArrayList<String> mTel,ArrayList<String>mLocation
    ,ArrayList<String>mLat,ArrayList<String>mLng,ArrayList<String>mImage1,ArrayList<String>mImage2,ArrayList<String>mImage3,ArrayList<String>mImage4,ArrayList<String>mImage5
    ,ArrayList<String>mImageMore) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mContext = mContext;
        this.mDes = mDes;
        this.mTel = mTel;
        this.mLocation = mLocation;
        this.mLat = mLat;
        this.mLng = mLng;
        this.mImage1 = mImage1;
        this.mImage2 = mImage2;
        this.mImage3 = mImage3;
        this.mImage4 = mImage4;
        this.mImage5 = mImage5;
        this.mImageMore = mImageMore;


    }
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_home,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);
        holder.name.setText(mNames.get(position));
       holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(mContext,mNames.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,Gallery.class);
                intent.putExtra("image_url",mImageUrls.get(position));
                intent.putExtra("image_name",mNames.get(position));
                intent.putExtra("Des",mDes.get(position));
                intent.putExtra("Tel",mTel.get(position));
                intent.putExtra("Local",mLocation.get(position));
                intent.putExtra("Lat",mLat.get(position));
                intent.putExtra("Lng",mLng.get(position));
                intent.putExtra("Image1",mImage1.get(position));
                intent.putExtra("Image2",mImage2.get(position));
                intent.putExtra("Image3",mImage3.get(position));
                intent.putExtra("Image4",mImage4.get(position));
                intent.putExtra("Image5",mImage5.get(position));
                intent.putExtra("ImageMore",mImageMore);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHoler(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);

        }
    }

}
