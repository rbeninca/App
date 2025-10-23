package com.example.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.metrics.Event;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TinderButton extends androidx.appcompat.widget.AppCompatButton {
    int x0=0;
    int y0=0;
    int colorR=120; int colorG=120; int colorB=120;
    public TinderButton(Context context) {
        super(context);
        this.setText("Tinder Button");
    }
    public TinderButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setText("Tinder Button");
    }

    public TinderButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setText("Tinder Button");
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        Log.d("position", "X: " + x + " Y: " + y + " Event: " + event.getAction());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x0 = (int)x;
            y0 = (int)y;
        }
        int dx = (int)(x - x0);
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            colorR = Math.min(255, Math.max(0, 120 - dx / 5));
            colorG = Math.min(255, Math.max(0, 120 + dx / 5));
            colorB = 120;
        }
        if (event.getAction()==MotionEvent.ACTION_UP){
            colorR=120; colorG=120; colorB=120;
        }
        this.setBackgroundColor(Color.rgb(colorR, colorG, colorB));

        return super.onTouchEvent(event);
    }
}
