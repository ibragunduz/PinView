package com.ibragunduz.pinview.View;

/**
 * Created by ibrahim on 16.02.2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibragunduz.pinview.Cache;
import com.ibragunduz.pinview.R;


public class IbraPinIndicator extends LinearLayout {


    public IbraPinIndicator(Context context) {
        super(context);
        initialize(context,null);
    }

    public IbraPinIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);

    }


    int layouts[] = {R.id.pin_indicator_layout_1,R.id.pin_indicator_layout_2,R.id.pin_indicator_layout_3,R.id.pin_indicator_layout_4,
            R.id.pin_indicator_layout_5,R.id.pin_indicator_layout_6,R.id.pin_indicator_layout_7,
            R.id.pin_indicator_layout_8};
    int circles[] = {R.id.pin_indicator_circle_1,R.id.pin_indicator_circle_2,R.id.pin_indicator_circle_3,R.id.pin_indicator_circle_4,
            R.id.pin_indicator_circle_5,R.id.pin_indicator_circle_6,R.id.pin_indicator_circle_7,R.id.pin_indicator_circle_8};
    int txts[] = {R.id.pin_indicator_txt_1,R.id.pin_indicator_txt_2,R.id.pin_indicator_txt_3,
            R.id.pin_indicator_txt_4,R.id.pin_indicator_txt_5,R.id.pin_indicator_txt_6,R.id.pin_indicator_txt_7,
            R.id.pin_indicator_txt_8};

    private View rootView;


    private void initialize(Context context,AttributeSet attributeSet){

        rootView =  LayoutInflater.from(context).inflate(R.layout.pin_indicator, this);

        setTextColor("#ffffff");
    }
    int circlePasiveDrawable = R.drawable.circle_background;
    int circleActiveDrawable = R.drawable.ic_circle;


    protected void setCorrectPass(String pass){
        this.correctPass = pass;

        for (int i = correctPass.length();i<8;i++){
                ((RelativeLayout)rootView.findViewById(layouts[i])).setVisibility(GONE);
        }
    }


    protected void setType(String type){
        this.type = type;
        if (IbraPinView.TYPE_SET_LOCK.equals(type)){
            setActiveCircles(0);
            setPassiveCircles(8);
        }
    }
    protected void setPassiveCircles(int counts){
        for (int i = 0 ; i<counts;i++){
            ((ImageView)rootView.findViewById(circles[i])).setImageResource(circlePasiveDrawable);
        }
    }
    protected void setActiveCircles(int counts){
        for (int i = 0 ; i<counts;i++){
            ((ImageView)rootView.findViewById(circles[i])).setImageResource(circleActiveDrawable);
        }
        for (int i = counts ; i<8;i++){
            ((ImageView)rootView.findViewById(circles[i])).setImageResource(circlePasiveDrawable);
        }
    }
    String type = IbraPinView.TYPE_SET_LOCK;
    String correctPass = "-";



    public void setColorFilter(int color){

        for (int i = 0 ; i<circles.length;i++){
            ImageView c =  ((ImageView)rootView.findViewById(circles[i]));
            c.setColorFilter(color);

        }

    }


    protected void update(String password1){
      final  String password = password1;
      if (password.length()>0){
       setActiveCircles(password.length()-1);
       final ImageView img =  ((ImageView)rootView.findViewById(circles[password.length()-1]));
       img.setVisibility(View.INVISIBLE);
      final TextView txt =   ((TextView)rootView.findViewById(txts[password.length()-1]));
      txt.setVisibility(VISIBLE);
      txt.setText(password.charAt(password.length()-1)+"");
      try {
          new android.os.Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  txt.setVisibility(INVISIBLE);
                  img.setVisibility(VISIBLE);
                  setActiveCircles(password.length());
              }
          },250);
      }catch (Exception e ){
      }

    }else{
          if (type.equals(IbraPinView.TYPE_LOCK_SCREEN)){
              setPassiveCircles(correctPass.length());
          }else{
              setPassiveCircles(8);
          }
      }

    }

    public void setTextColor(String color){

        for (int i = 0 ; i<txts.length;i++){
            ((TextView)rootView.findViewById(txts[i])).setTextColor(Color.parseColor(color));
        }
    }
    public void setMargin(int margin){
        for (int i = 0 ; i<circles.length;i++){
            ImageView circle = ((ImageView)rootView.findViewById(circles[i]));
            MarginLayoutParams marginParams = new MarginLayoutParams(circle.getLayoutParams());
            marginParams.setMargins(margin, margin, margin, margin);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
            circle.setLayoutParams(layoutParams);
        }
    }


    public void setTextsFontAndSize(String font){

        for (int i = 0 ; i<txts.length;i++){
            TextView text = ((TextView)findViewById(txts[i]));
            text.setTypeface(Cache.getTypeface(font,getContext()));
        }
    }

    public void setTextSize(int textSize){
        for (int i = 0 ; i<txts.length;i++){
            TextView textView = ((TextView)rootView.findViewById(txts[i]));
            textView.setTextSize(textSize);
        }
    }
    public void setDotSize(int w,int h){
        for (int i = 0 ; i<circles.length;i++){
            ImageView circle = ((ImageView)rootView.findViewById(circles[i]));
           ViewGroup.LayoutParams params =  circle.getLayoutParams();

           params.width = w;
           params.height = h;
           circle.setLayoutParams(params);
        }
    }

    public void setDotDrawable(int resId){
        circleActiveDrawable = resId;
    }
}

