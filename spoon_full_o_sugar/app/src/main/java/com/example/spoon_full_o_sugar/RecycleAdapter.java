package com.example.spoon_full_o_sugar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyHolder>{

    List med_name, med_dose, med_date, med_time;
    int img;
    Context ctx;

    public RecycleAdapter(Context ct, List s1, List s2, List s3, List s4, int i1) {
        ctx = ct;
        med_date = s1;
        med_time = s2;
        med_name = s3;
        med_dose = s4;
        img = i1;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myOwnView = myInflater.inflate(R.layout.hist_row, parent, false);
        return new MyHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.t1.setText(med_name.get(position).toString());
        holder.t2.setText(med_dose.get(position).toString());
        holder.t3.setText(med_date.get(position).toString());
        holder.t4.setText(med_time.get(position).toString());
        holder.myImage.setImageResource(img);
    }

    @Override
    public int getItemCount() { return med_name.size(); }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView t1,t2, t3, t4;
        ImageView myImage;
        public MyHolder(View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.med_hist_name);
            t2 = (TextView)itemView.findViewById(R.id.med_hist_dose);
            t3 = (TextView)itemView.findViewById(R.id.med_hist_date);
            t4 = (TextView)itemView.findViewById(R.id.med_hist_time);
            myImage = (ImageView)itemView.findViewById(R.id.row_image);
        }
    }
}
