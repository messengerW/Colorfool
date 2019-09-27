package com.example.mushr.colorfool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    private View view;
    private ImageView imgSource1;
    private TextView touchedXY, invertedXY;
    private Button colorBtn1, colorBtn2, colorBtn3, colorBtn4,
            colorBtn5, colorBtn6, colorBtn7, colorBtn8;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(),R.layout.fragment2,null);

        touchedXY = (TextView) view.findViewById(R.id.xy);
        invertedXY = (TextView) view.findViewById(R.id.invertedxy);
        colorBtn1 = (Button) view.findViewById(R.id.btn1);
        colorBtn2 = (Button) view.findViewById(R.id.btn3);
        colorBtn3 = (Button) view.findViewById(R.id.btn4);
        colorBtn4 = (Button) view.findViewById(R.id.btn2);
        colorBtn5 = (Button) view.findViewById(R.id.btn5);
        colorBtn6 = (Button) view.findViewById(R.id.btn7);
        colorBtn7 = (Button) view.findViewById(R.id.btn8);
        colorBtn8 = (Button) view.findViewById(R.id.btn6);
        imgSource1 = (ImageView) view.findViewById(R.id.pic);
        imgSource1.setOnTouchListener(imgSourceOnTouchListener);
        return view;
    }

    int flag = 0;
    View.OnTouchListener imgSourceOnTouchListener = new View.OnTouchListener() {

        // 将 drawable 格式转化为 bitmap 格式
        @NonNull
        private Bitmap DrawableToBitamp(@NonNull Drawable drawable) {
            final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(bmp);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bmp;
        }

        /*
          这个函数的功能是给按钮“染色”。
          首先根据onTouch函数传来的参数获取到触摸点的x，y坐标；
          然后进行矩阵转换（目的是使找点更准确，少了这一步取色点位置明显有偏差）；
          获取布局文件的图片并转换成 bitmap 格式；
          得到触摸点的rgb色值并转换为16进制，最后给按钮涂色
         */
        private void DyeButton(View view, MotionEvent event, Button btn) {
            float eventX = event.getX();
            float eventY = event.getY();
            float[] eventXY = new float[]{eventX,eventY};

            Matrix invertMatrix = new Matrix();
            ((ImageView) view).getImageMatrix().invert(invertMatrix);
            invertMatrix.mapPoints(eventXY);

            Drawable img = ((ImageView) view).getDrawable();
            Bitmap bitmap = DrawableToBitamp(img);

            int x = (int) eventXY[0];
            int y = (int) eventXY[1];
            touchedXY.setText(
                    "touched position: "
                            + String.valueOf(eventX) + " / "
                            + String.valueOf(eventY));
            invertedXY.setText(
                    "touched position: "
                            + String.valueOf(x) + " / "
                            + String.valueOf(y));

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
            btn.setBackgroundColor(touchedRGB);
            btn.setText("#" + strRGB);
        }

        // 触摸事件,return = false 时相当于点击事件
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (flag == 0) {
                DyeButton(view, event, colorBtn1);
            } else if (flag == 1) {
                DyeButton(view, event, colorBtn2);
            } else if (flag == 2) {
                DyeButton(view, event, colorBtn3);
            } else if (flag == 3) {
                DyeButton(view, event, colorBtn4);
            } else if (flag == 4) {
                DyeButton(view, event, colorBtn5);
            } else if (flag == 5) {
                DyeButton(view, event, colorBtn6);
            } else if (flag == 6) {
                DyeButton(view, event, colorBtn7);
            } else if (flag == 7) {
                DyeButton(view, event, colorBtn8);
            }

            flag = (flag+1)%8;
            return false;
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}