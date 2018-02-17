package com.ibragunduz.pinview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ibragunduz.pinview.Interfaces.PinLockListener;
import com.ibragunduz.pinview.Interfaces.SetPinListener;
import com.ibragunduz.pinview.View.IbraPinIndicator;
import com.ibragunduz.pinview.View.IbraPinView;

public class MainActivity extends AppCompatActivity {
    IbraPinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         pinView = ((IbraPinView)findViewById(R.id.pin_view));
        IbraPinIndicator pinIndicator = ((IbraPinIndicator)findViewById(R.id.indicator));


        pinView.attachIndicator(pinIndicator,IbraPinView.TYPE_LOCK_SCREEN);
        //pinView.attachIndicator(pinIndicator,IbraPinView.TYPE_LOCK_SCREEN);
        pinView.setCorrectPassword("1234");

        pinIndicator.setDotDrawable(R.drawable.ic_circle);
        pinIndicator.setMargin(20);
        pinIndicator.setTextSize(10);
        pinIndicator.setDotSize(40,40);
        pinIndicator.setTextColor("#ffffff");
      /*  pinView.setPinLockListener(new PinLockListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "TRY AGAIN!", Toast.LENGTH_SHORT).show();

            }
        });*/
        pinView.setSetPinListener(new SetPinListener() {
            @Override
            public void onPinChange(String pin) {
                Log.i("entered pin : ",pin);
            }
        });


    }
    public void reset(View view){
        pinView.reset();
    }
}
