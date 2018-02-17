package com.ibragunduz.pinview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ibragunduz.pinview.Interfaces.PinLockListener;
import com.ibragunduz.pinview.Interfaces.SetPinListener;
import com.ibragunduz.pinview.View.IbraPinIndicator;
import com.ibragunduz.pinview.View.IbraPinView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IbraPinView pinView = ((IbraPinView)findViewById(R.id.pin_view));
        IbraPinIndicator pinIndicator = ((IbraPinIndicator)findViewById(R.id.indicator));


        pinView.attachIndicator(pinIndicator,IbraPinView.TYPE_SET_LOCK);
        //pinView.setCorrectPassword("1234");

        pinIndicator.setMargin(20);
        pinIndicator.setTextSize(10);
        pinIndicator.setDotSize(40,40);
        pinIndicator.setTextColor("#ffffff");
        pinView.setPinLockListener(new PinLockListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Hoaaaydaaa Doğru girdin", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "ohooo Yanlış girdin", Toast.LENGTH_SHORT).show();

            }
        });
        pinView.setSetPinListener(new SetPinListener() {
            @Override
            public void onPinChange(String pin) {
                Log.i("dd : ",pin);
            }
        });


    }
}
