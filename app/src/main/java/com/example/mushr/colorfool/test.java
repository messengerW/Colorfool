package com.example.mushr.colorfool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class test extends AppCompatActivity {

    private ImageView imgSource1;
    private TextView touchedXY, invertedXY, imgSize, colorText;
    private Button colorBtn1, colorBtn2, colorBtn3, colorBtn4,
            colorBtn5, colorBtn6, colorBtn7, colorBtn8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchedXY = (TextView) findViewById(R.id.xy);
        invertedXY = (TextView) findViewById(R.id.invertedxy);
        imgSize = (TextView) findViewById(R.id.size);
        colorBtn1 = (Button) findViewById(R.id.btn1);
        colorBtn2 = (Button) findViewById(R.id.btn2);
        colorBtn3 = (Button) findViewById(R.id.btn3);
        colorBtn4 = (Button) findViewById(R.id.btn4);
        colorBtn5 = (Button) findViewById(R.id.btn5);
        colorBtn6 = (Button) findViewById(R.id.btn6);
        colorBtn7 = (Button) findViewById(R.id.btn7);
        colorBtn8 = (Button) findViewById(R.id.btn8);

        imgSource1 = (ImageView) findViewById(R.id.pic);

        imgSource1.setOnTouchListener(imgSourceOnTouchListener);
    }

    int flag = 0;
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

        // 点击事件
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            float eventX;
            float eventY;
            float[] eventXY = new float[]{0,0};
            Drawable img;
            Bitmap bitmap;

            // 坐标超出图片范围
            int x;
            int y;

            int touchedRGB;
            String r1;
            String g1;
            String b1;
            String strRGB;               //  获取到这一点颜色的RGB值

                if (flag == 0){
                    eventX = event.getX();
                    eventY = event.getY();
                    eventXY[0] = eventX;
                    eventXY[1] = eventY;
                    x = Integer.valueOf((int) eventXY[0]);
                    y = Integer.valueOf((int) eventXY[1]);

                    img = ((ImageView) view).getDrawable();
                    bitmap = drawableToBitamp(img);

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
                    touchedRGB = bitmap.getPixel(x, y);
                    r1 = Integer.toHexString(Color.red(touchedRGB));
                    g1 = Integer.toHexString(Color.green(touchedRGB));
                    b1 = Integer.toHexString(Color.blue(touchedRGB));
                    strRGB = r1 + g1 + b1;
                    colorBtn1.setBackgroundColor(touchedRGB);
                    colorBtn1.setText("#" + strRGB);
                } else if (flag == 1) {
                    eventX = event.getX();
                    eventY = event.getY();
                    eventXY[0] = eventX;
                    eventXY[1] = eventY;
                    x = Integer.valueOf((int) eventXY[0]);
                    y = Integer.valueOf((int) eventXY[1]);

                    img = ((ImageView) view).getDrawable();
                    bitmap = drawableToBitamp(img);

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
                    touchedRGB = bitmap.getPixel(x, y);
                    r1 = Integer.toHexString(Color.red(touchedRGB));
                    g1 = Integer.toHexString(Color.green(touchedRGB));
                    b1 = Integer.toHexString(Color.blue(touchedRGB));
                    strRGB = r1 + g1 + b1;
                    colorBtn2.setBackgroundColor(touchedRGB);
                    colorBtn2.setText("#" + strRGB);
                } else if (flag == 2) {
                    eventX = event.getX();
                    eventY = event.getY();
                    eventXY[0] = eventX;
                    eventXY[1] = eventY;
                    x = Integer.valueOf((int) eventXY[0]);
                    y = Integer.valueOf((int) eventXY[1]);

                    img = ((ImageView) view).getDrawable();
                    bitmap = drawableToBitamp(img);

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
                    touchedRGB = bitmap.getPixel(x, y);
                    r1 = Integer.toHexString(Color.red(touchedRGB));
                    g1 = Integer.toHexString(Color.green(touchedRGB));
                    b1 = Integer.toHexString(Color.blue(touchedRGB));
                    strRGB = r1 + g1 + b1;
                    colorBtn3.setBackgroundColor(touchedRGB);
                    colorBtn3.setText("#" + strRGB);
                } else if (flag == 3) {
                    eventX = event.getX();
                    eventY = event.getY();
                    eventXY[0] = eventX;
                    eventXY[1] = eventY;
                    x = Integer.valueOf((int) eventXY[0]);
                    y = Integer.valueOf((int) eventXY[1]);

                    img = ((ImageView) view).getDrawable();
                    bitmap = drawableToBitamp(img);

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
                    touchedRGB = bitmap.getPixel(x, y);
                    r1 = Integer.toHexString(Color.red(touchedRGB));
                    g1 = Integer.toHexString(Color.green(touchedRGB));
                    b1 = Integer.toHexString(Color.blue(touchedRGB));
                    strRGB = r1 + g1 + b1;
                    colorBtn4.setBackgroundColor(touchedRGB);
                    colorBtn4.setText("#" + strRGB);
                }
                flag = (flag+1)%4;

            return true;
        }
    };


}
