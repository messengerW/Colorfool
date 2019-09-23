package com.example.mushr.colorfool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView touchedXY, invertedXY, imgSize, colorText;
    private ImageView imgSource1;
    private Button colorBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchedXY = (TextView) findViewById(R.id.xy);
        invertedXY = (TextView) findViewById(R.id.invertedxy);
        imgSize = (TextView) findViewById(R.id.size);
        colorText = (TextView) findViewById(R.id.colorrgb);
        colorBtn = (Button) findViewById(R.id.btn);
        imgSource1 = (ImageView) findViewById(R.id.pic);

        imgSource1.setOnTouchListener(imgSourceOnTouchListener);
    }

    View.OnTouchListener imgSourceOnTouchListener = new View.OnTouchListener() {

        // 将 drawable 格式转化为 bitmap 格式
        @NonNull
        private Bitmap drawableToBitamp(@NonNull Drawable drawable) {
            final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(bmp);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bmp;
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            float[] eventXY = new float[]{eventX, eventY};

            Matrix invertMatrix = new Matrix();
            ((ImageView) view).getImageMatrix().invert(invertMatrix);

            invertMatrix.mapPoints(eventXY);
            int x = Integer.valueOf((int) eventXY[0]);
            int y = Integer.valueOf((int) eventXY[1]);

            touchedXY.setText(
                    "touched position: "
                            + String.valueOf(eventX) + " / "
                            + String.valueOf(eventY));

            invertedXY.setText(
                    "tinverted touched position: "
                            + String.valueOf(x) + " / "
                            + String.valueOf(y));

            Drawable imgDrawable = ((ImageView) view).getDrawable();
            Bitmap bitmap = drawableToBitamp(imgDrawable);

            imgSize.setText(
                    "drawable size: "
                            + String.valueOf(bitmap.getWidth()) + " / "
                            + String.valueOf(bitmap.getHeight()));

            // 坐标超出图片范围
            if (x < 0) {
                x = 0;
            } else if (x > bitmap.getWidth() - 1) {
                x = bitmap.getWidth() - 1;
            }

            if (y < 0) {
                y = 0;
            } else if (y > bitmap.getHeight() - 1) {
                y = bitmap.getHeight() - 1;
            }

            int touchedRGB = bitmap.getPixel(x, y);
            int r = Color.red(touchedRGB);
            int g = Color.green(touchedRGB);
            int b = Color.blue(touchedRGB);
            String r1 = Integer.toHexString(r);
            String g1 = Integer.toHexString(g);
            String b1 = Integer.toHexString(b);
            String strRGB = r1 + g1 + b1;
            colorText.setText("touched color: " + "#" + strRGB);
            colorText.setTextColor(touchedRGB);
            colorBtn.setBackgroundColor(touchedRGB);

            return true;
        }
    };


}
