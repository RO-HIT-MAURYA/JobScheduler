package apollo.hospitals.jobscheduler;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
{
    static ArrayList<Bitmap> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageView = findViewById(R.id.imageView1);

        imageView.setImageBitmap(arrayList.get(0));

        imageView = findViewById(R.id.imageView2);
        imageView.setImageBitmap(arrayList.get(1));
    }
}
