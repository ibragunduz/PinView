package com.ibragunduz.pinview.View;

/**
 * Created by ibrahim on 16.02.2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ibragunduz.pinview.Interfaces.PinLockListener;
import com.ibragunduz.pinview.Interfaces.SetPinListener;
import com.ibragunduz.pinview.R;

import java.util.logging.Handler;

public class IbraPinView extends LinearLayout  {

    public static String TYPE_SET_LOCK = "type_set_lock";
    public static String TYPE_LOCK_SCREEN = "type_lock_screen";

    CardView cardx;
   final int cardViews[] = {R.id.pin_view_card_0,R.id.pin_view_card_1,R.id.pin_view_card_2,R.id.pin_view_card_3,R.id.pin_view_card_4,R.id.pin_view_card_5,
            R.id.pin_view_card_6,R.id.pin_view_card_7,R.id.pin_view_card_8,R.id.pin_view_card_9,R.id.pin_view_card_x,R.id.pin_view_card_b};


    int textViewNumbers[] = {R.id.pin_view_txt_view_num_1,R.id.pin_view_txt_view_num_2,R.id.pin_view_txt_view_num_3,R.id.pin_view_txt_view_num_4,
            R.id.pin_view_txt_view_num_5,R.id.pin_view_txt_view_num_6,R.id.pin_view_txt_view_num_7,R.id.pin_view_txt_view_num_8,R.id.pin_view_txt_view_num_9,
            R.id.pin_view_txt_view_num_0};

    int textViewsChars[] = {R.id.pin_view_txt_1,R.id.pin_view_txt_2,R.id.pin_view_txt_3,R.id.pin_view_txt_4,R.id.pin_view_txt_5,
            R.id.pin_view_txt_6,R.id.pin_view_txt_7,R.id.pin_view_txt_8,R.id.pin_view_txt_9};



    IbraPinIndicator pinIndicator;
    public IbraPinView(Context context) {
        super(context);
        initialize(context,null);
    }

    public IbraPinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);

    }



    private View rootView;


    private void initialize(Context context,AttributeSet attributeSet){

        rootView =  LayoutInflater.from(context).inflate(R.layout.pin_view, this);
    cardx =  ((CardView)rootView.findViewById(R.id.pin_view_card_x));
       setPinBackgroundDrawable(R.drawable.circle_background);
       setNumbersTextColor("#ffffff");
       setCharsTextColor("#ffffff");
       setButtonSize(160,160);
       setMargin(50);


    }

    String password = "";
    String type = TYPE_SET_LOCK;
    String correctPass = "-";
    public void setCorrectPassword(String correctPassword){
        correctPass = correctPassword;
        pinIndicator.setCorrectPass(correctPass);
    }

    private void onCorrect(){

        if (pinLockListener!=null)pinLockListener.onSuccess();
    }
    private void onUnCorrect(){
if (pinLockListener!=null)pinLockListener.onError();



    }

    private void update(){

        pinIndicator.update(password);


        if (type.equals(TYPE_LOCK_SCREEN)){
            if (password.equals(correctPass)){
                onCorrect();
            }
            if (correctPass.length()<=password.length()&&!password.equals(correctPass)){

                onUnCorrect();
            }
        }

        if (password.length()>0)cardx.setVisibility(VISIBLE);

if (setPinListener!=null)setPinListener.onPinChange(password);


    }

    PinLockListener pinLockListener;
    public void setPinLockListener(PinLockListener pinLockListener){
        this.pinLockListener = pinLockListener;
    }
    SetPinListener setPinListener;
    public void setSetPinListener(SetPinListener setPinListener){
        this.setPinListener = setPinListener;
    }
    private void write(int code){

        if (password.length()<8&&type.equals(IbraPinView.TYPE_SET_LOCK)){
        password = password+code;
        update();
        }else if(type.equals(TYPE_LOCK_SCREEN)&&correctPass.length()>password.length()){
            password = password+code;
            update();
        }





    }




    private void delete(){
        if (password.length()>1){
            password = password.substring(0,password.length()-1);

            pinIndicator.setActiveCircles(password.length());

            if (setPinListener!=null)setPinListener.onPinChange(password);

        }else {
            reset();
        }
    }

    public void reset(){

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        password = "";
        cardx.setVisibility(INVISIBLE);
        update();
            }
        },250);


    }



    public void attachIndicator(IbraPinIndicator ibraPinIndicator,String type){
        pinIndicator = ibraPinIndicator;
        this.type = type;
        pinIndicator.setType(type);

    }
    public void setMargin(int m){
        for (int i = 0 ; i<cardViews.length;i++){
            CardView cardview =   ((CardView)rootView.findViewById(cardViews[i]));
            LayoutParams ll =   (LinearLayout.LayoutParams)cardview.getLayoutParams();


            switch (i){
                case 0 :
                ll.leftMargin = m;
                ll.rightMargin =m;
                case 1 :
                    ll.rightMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 2 :
                    ll.leftMargin = m;
                    ll.rightMargin =m;
                    ll.bottomMargin = m;
                    break;
                case 3 :
                    ll.leftMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 4 :
                    ll.rightMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 5 :
                    ll.leftMargin = m;
                    ll.rightMargin =m;
                    ll.bottomMargin = m;
                    break;
                case 6 :
                    ll.leftMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 7 :
                    ll.rightMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 8 :
                    ll.leftMargin = m;
                    ll.rightMargin =m;
                    ll.bottomMargin = m;
                    break;
                case 9 :
                    ll.leftMargin = m;
                    ll.bottomMargin = m;
                    break;
                case 10:
                ll.rightMargin = m;
                    break;
                case 11:
                ll.leftMargin = m;
                    break;
            }

        }
    }

    public void setButtonSize(int w,int h){
        for (int i = 0 ; i<cardViews.length;i++){
          CardView cardview =   ((CardView)rootView.findViewById(cardViews[i]));
            ViewGroup.LayoutParams params = cardview.getLayoutParams();
            params.width = w;
            params.height = h;


          cardview.setLayoutParams(params);
        }
    }

    public void setCharsTextColor(String color){

        for (int i = 0 ; i<textViewsChars.length;i++){
            TextView text = ((TextView)findViewById(textViewsChars[i]));
            text.setTextColor(Color.parseColor(color));
        }
    }

    public void setNumbersTextColor(String color){
        for (int i = 0 ; i<textViewNumbers.length;i++){
            TextView textView = ((TextView)rootView.findViewById(textViewNumbers[i]));
            textView.setTextColor(Color.parseColor(color));
        }
    }


    public void setPinBackgroundDrawable(int resId){
        for (int i = 0 ; i<cardViews.length;i++){
            CardView c =  ((CardView)rootView.findViewById(cardViews[i]));
            c.setOnClickListener(onClickListener);
            c.setBackground(getResources().getDrawable(resId));

        }
    }
  OnClickListener onClickListener = new OnClickListener() {
      @Override
      public void onClick(View v) {

          int id = v.getId();
          for (int i = 0 ;i<cardViews.length;i++ ){
              if (i<10&&cardViews[i]==id)write(i);
              else if (i==11&&cardViews[i]==id)delete();
              else if (i==10&&cardViews[i]==id)reset();
          }
      }
  };





}

