package com.example.blurbackground;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    private BlurView blurView;
    private SeekBar seekBarRadius;
    private TextView textRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float defaultRadius = 1f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);

        Drawable windowBackground = decorView.getBackground();
        blurView = findViewById(R.id.blurView);
        seekBarRadius = findViewById(R.id.seekBarRadius);
        textRadius = findViewById(R.id.textRadius);

        blurView.setupWith(rootView, new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(defaultRadius);

        seekBarRadius.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float maxRadius = 25f;
                        float radius =
                                (progress / 25f)
                                        * maxRadius; // Adjust this multiplier based on your desired
                        // sensitivity
                        if (radius > maxRadius) {
                            radius = maxRadius;
                        }
                        blurView.setBlurRadius(radius);
                        textRadius.setText(getString(R.string.radius_label, radius));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // Not needed for this example
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // Not needed for this example
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        blurView = null;
    }
}
