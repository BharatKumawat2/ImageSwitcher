package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView t1;
    ImageButton  prev,next;
    ImageSwitcher imageSwitcher;
    int images[]={R.drawable.logo, R.drawable.cgpit ,R.drawable.gate,R.drawable.classes,R.drawable.lab};
    int count=images.length;
    int currentindex=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar=(RatingBar) findViewById(R.id.ratingbar);
        t1=(TextView) findViewById(R.id.tv);
        prev=(ImageButton) findViewById(R.id.i1);
        next=(ImageButton) findViewById(R.id.i2);
        imageSwitcher=(ImageSwitcher) findViewById(R.id.is1);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));

                return imageView;
            }
        });

        Animation in= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out=AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex++;

                if (currentindex==count)
                    currentindex=0;
                imageSwitcher.setImageResource(images[currentindex]);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --currentindex;
                currentindex=images.length-1;
                imageSwitcher.setImageResource(images[currentindex]);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                t1.setText(""+v);
            }
        });
    }
}