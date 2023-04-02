package com.example.mynewasyncdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyProgressTask extends AsyncTask<Void, Integer, String> {
    // Void arguement above is passed to doInBackground
    // if another type is passed/needed these will need to be changed
    // the return from doInBackground is String, because String is noted above
    // these will need to match if another return is required

   Context ctx;
   ProgressDialog pd;

    public MyProgressTask(Context ct) {
        ctx = ct;
   }


    @Override
    protected void onPreExecute() {
        // UI operations go here
        pd = new ProgressDialog(ctx);
        pd.setTitle("Downlaoding");
        pd.setMessage("Please wait...");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });

        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        // for() used for demonstration
        // in real use the size of file and speed of network would determine the progress of a download
        try {
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(2000);
                Log.i("Thread","Execute "+i);
                publishProgress(i); // several variable can be passed, and they will be dealt with by onProgressUpdate by separate loops
            }
            return "Successful";
        }
        catch (Exception e) {
            Log.i("Exception", e.getMessage());
            return "Failure";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue = values[0];
        pd.setProgress(myValue);

    }

    @Override
    protected void onPostExecute(String s) {
        // called once doInBackground is done
        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}
