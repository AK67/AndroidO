package com.example.anubhavkaushik.makemyband;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.view.animation.Animation.AnimationListener;
import customDataStructures.CircullarArrayList;

// idea is to display a home screen with fading images of rockstars and some navigational buttons
// we add imageviews with visiblity none and add them in circular list
// startanimation is a function whcih start animation on passed imageview
// onAnimation stop imageview visiblity is again set to hide.
// and startanimation is called on another imageview saved in circularList
public class welcomeActivity extends Activity implements AnimationListener {

    protected CircullarArrayList<ImageView>  imageViewArrayList =new CircullarArrayList<ImageView>();
    private ImageView imageView ;
    public final static String startAnimTAG = "startAnim";
    public final static String onAnimEndTAG = "onAnimEnd";
    public final static String onCreateTAG = "onCreate";

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
   @Override
    public void onAnimationStart(Animation animation) {
        Log.i("onANimationStart","I am Working......");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
       Log.i(onAnimEndTAG,"on end start,will get the next element now ......");
       this.getImageView().setVisibility(View.INVISIBLE);

       this.setImageView(imageViewArrayList.getTheNextElement(this.getImageView()));
       // Log.i(onAnimEndTAG,"hello"+(String)this.getImageView().getTag());
       startAnimation(this.getImageView());
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final ImageView homeImageSlash =(ImageView)findViewById(R.id.home_slash);
        final ImageView homeImageBaker =(ImageView)findViewById(R.id.home_baker);
        final ImageView homeImageburton =(ImageView)findViewById(R.id.home_burton);
        final Button logIn= (Button)findViewById(R.id.login);
        // adding pics to the list to display on our home
        imageViewArrayList.add(homeImageBaker);
        imageViewArrayList.add(homeImageSlash);
        imageViewArrayList.add(homeImageburton);

        logIn.setOnClickListener(new LoginClickHandler());
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        this.setImageView(imageViewArrayList.get(0));
        Log.i(onCreateTAG,"setting imageview using setter");
        String imageViewName=(String)this.getImageView().getTag();
        Log.i(onCreateTAG,"hello "+imageViewName);
        startAnimation(this.getImageView());
    }
    public void startAnimation( ImageView imageview )
    {
        Animation animFadeOut = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        animFadeOut.setAnimationListener(this);
        Log.i(startAnimTAG,"starting animation ........");
        // make the current imageview visible
        imageview.setVisibility(View.VISIBLE);
        Log.i(startAnimTAG,"seting visiblity asdasdasdas.......");
        //load animation
        imageview.startAnimation(animFadeOut);


    }

    public class LoginClickHandler implements View.OnClickListener {
        //When button is clicked
        public void onClick(View view) {
            Intent intObj = new Intent(welcomeActivity.this,login.class);
            startActivity(intObj);
        }
    }


}

