package com.example.spoon_full_o_sugar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class RecycleAdapterDelete extends RecyclerView.Adapter<RecycleAdapterDelete.MyHolder>{

    List med_name, med_dose, med_time, DB_ID;
    int img;
    Context ctx;
    DatabaseHelper DB;
    Calendar cal;
    String DBday, frag_tag;
    AppCompatActivity frg;



    public RecycleAdapterDelete(Context ct, List s1, List s2, List s3, int i1, List d1, String day, String frgtg) {
        ctx = ct;
        med_name = s1;
        med_dose = s2;
        med_time = s3;
        img = i1;
        DB_ID = d1;
        DBday = day;
        frag_tag = frgtg;

        DB = new DatabaseHelper(ct);
        cal = Calendar.getInstance();

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myOwnView = myInflater.inflate(R.layout.sched_delete_row, parent, false);
        return new MyHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.t1.setText(med_name.get(position).toString());
        holder.t2.setText(med_dose.get(position).toString());
        holder.t3.setText(med_time.get(position).toString());
        holder.myImage.setImageResource(img);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(v.getContext());
                deleteAlert.setTitle("Delete Item");
                deleteAlert.setMessage("Confirm Deletion");
                deleteAlert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("RecycleAdapterDelete","ALERT DIALOG CONFIRMED");
                        DB.removeFromSched((Integer) DB_ID.get(position), getTable(DBday));
                        Toast.makeText(v.getContext(), "ITEM DELETED", Toast.LENGTH_SHORT).show();
                    }
                });
                deleteAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("RecycleAdapterDelete","ALERT DIALOG CANCELLED");
                        dialog.dismiss();
                    }
                });

                deleteAlert.setCancelable(false);

                Log.i("RecycleAdapterDelete","ALERT DIALOG DISPLAYED");
                deleteAlert.show();

            }
        });
    }

    @Override
    public int getItemCount() { return med_name.size(); }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView t1,t2, t3;
        ImageView myImage;
        Button delete_btn;
        public MyHolder(View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.sched_name);
            t2 = (TextView)itemView.findViewById(R.id.sched_dose);
            t3 = (TextView)itemView.findViewById(R.id.sched_time);
            myImage = (ImageView)itemView.findViewById(R.id.sched_img);
            delete_btn = (Button) itemView.findViewById(R.id.delete_btn);

        }
    }

    public String getTable(String d) {
        // cal = Calendar.getInstance();
        // day = cal.get(Calendar.DAY_OF_WEEK);
        switch (d) {
            case "mon":
                Log.i("getTable:", "MONDAY");
                return "med_sched_mon";
            case "tues":
                Log.i("getTable:", "TUESDAY");
                return "med_sched_tues";
            case "wed":
                Log.i("getTable:", "WEDNESDAY");
                return "med_sched_wed";
            case "thur":
                Log.i("getTable:", "THURSDAY");
                return "med_sched_thur";
            case "fri":
                Log.i("getTable:", "FRIDAY");
                return "med_sched_fri";
            case "sat":
                Log.i("getTable:", "SATURDAY");
                return "med_sched_sat";
            case "sun":
                Log.i("getTable:", "SUNDAY");
                return "med_sched_sun";
        }
        return null;
    }
}
