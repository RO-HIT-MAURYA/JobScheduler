package apollo.hospitals.jobscheduler;

import android.app.WallpaperManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private Timer timer = new Timer();
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<Bitmap> al = new ArrayList<>();
    private ArrayList<Target> l = new ArrayList<>();

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "Job started");

        arrayList.add("https://static.bhphoto.com/images/images500x500/canon_eos_r_mirrorless_digital_1536120359_1433711.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536120910_IMG_1061036.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536120910_IMG_1061037.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536859820_IMG_1065170.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536859452_IMG_1061038.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536859452_IMG_1061039.jpg");
        arrayList.add("https://static.bhphoto.com/images/multiple_images/images500x500/1536859452_IMG_1061040.jpg");

        for (int i=0; i<arrayList.size(); i++)
        {
            String string = arrayList.get(i);

            l.add(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    if (bitmap!=null)
                        al.add(bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

            Picasso.get().load(string).into(l.get(i));
        }

        printSomething();
        Main2Activity.arrayList = al;

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "Job cancelled before completion");
        return true;
    }

    private boolean bool;
    void printSomething()
    {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
                String  string = simpleDateFormat.format(date);
                if (string.contains("15:14"))
                {
                    Log.e("conditionIs",true+"");
                    if (!bool)
                    {
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        al.get(1).compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();
                        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);

                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(ExampleJobService.this);
                        try {
                            wallpaperManager.setStream(bs,null,true,WallpaperManager.FLAG_LOCK);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bool = true;
                    }
                }
                Log.e("tagIs",al.size() + "," + string);
                printSomething();
            }
        },1230);
    }
}