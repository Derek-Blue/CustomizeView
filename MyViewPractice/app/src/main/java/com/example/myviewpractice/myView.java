package com.example.myviewpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myView extends View {
    private Paint paint;

    public myView(Context context) {
        super(context);
    }

    public myView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //繪製內容物
        super.onDraw(canvas);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);// new一個畫筆  ANTI_ALIAS_FLAG抗鋸齒
        paint.setColor(Color.BLUE);// 設定畫筆顏色
        paint.setStyle(Paint.Style.FILL_AND_STROKE);// 設定畫筆填充
        canvas.drawCircle(25, 25, 80, paint);// 用畫筆在畫布上添加一個圓
        paint.setColor(Color.WHITE);// 設定畫筆顏色
        paint.setTextSize(50);//設定要繪製的文字大小
        canvas.drawText("MyView", 50, 80, paint);// 用畫筆在畫布上添加文字，中間兩個參數對應的是座標。
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Measure(測量) 自定義view的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getSize(50, widthMeasureSpec);
        int height = getSize(100, heightMeasureSpec);

//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }                          44~48行code:固定比例大小
        setMeasuredDimension(width, height);
    }

    private int getSize(int defaulSize, int measureSpec){
        int setSize = defaulSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode){
            //UNSPECIFIED 如果沒有指定大小，就設定為默認值
            case MeasureSpec.UNSPECIFIED:
                setSize = defaulSize;
                break;
            //EXACTLY 如果是固定大小，則不改變。取設定值
            case MeasureSpec.EXACTLY:
                setSize = size;
                break;
            //AT_MOST 如使用wrap_content 取當下值
            case MeasureSpec.AT_MOST:
                setSize = size;
                break;
        }
        return setSize;
    }

}
