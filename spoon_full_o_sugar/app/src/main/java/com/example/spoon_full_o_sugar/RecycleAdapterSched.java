package com.example.spoon_full_o_sugar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class RecycleAdapterSched extends RecyclerView.Adapter<RecycleAdapterSched.MyHolder>{

    List med_name, med_dose, med_time, DB_ID;
    int img;
    Context ctx;
    DatabaseHelper DB;
    Calendar cal;


    public RecycleAdapterSched(Context ct, List s1, List s2, List s3, int i1, List d1) {
        ctx = ct;
        med_name = s1;
        med_dose = s2;
        med_time = s3;
        img = i1;
        DB_ID = d1;

        DB = new DatabaseHelper(ct);
        cal = Calendar.getInstance();

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myOwnView = myInflater.inflate(R.layout.sched_row, parent, false);
        return new MyHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.t1.setText(med_name.get(position).toString());
        holder.t2.setText(med_dose.get(position).toString());
        holder.t3.setText(med_time.get(position).toString());
        holder.myImage.setImageResource(img);

        holder.taken_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.insertHist(
                        DateFormat.getDateInstance().format(cal.getTime()),
                        cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE),
                        med_name.get(position).toString(),
                        med_dose.get(position).toString(),
                        1,
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.HOUR_OF_DAY),
                        cal.get(Calendar.MINUTE));

                // Necesary when a daily Table can be created
                // DB.removeFromSched((Integer) DB_ID.get(position), "med_sched_daily");

                // Toast.makeText(v.getContext(), "Time: "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return med_name.size(); }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView t1,t2, t3;
        ImageView myImage;
        Button taken_btn;
        public MyHolder(View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.sched_name);
            t2 = (TextView)itemView.findViewById(R.id.sched_dose);
            t3 = (TextView)itemView.findViewById(R.id.sched_time);
            myImage = (ImageView)itemView.findViewById(R.id.sched_img);
            taken_btn = (Button) itemView.findViewById(R.id.delete_btn);

        }
    }
}
