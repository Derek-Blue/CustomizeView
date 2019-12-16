package com.example.myviewpractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;

public class myButton extends Button {

    private final int defaultImageRes = R.drawable.icon;
    private Bitmap buttonBitmap;
    public myButton(Context context) {
        super(context);
        init(context);
    }

    public myButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public myButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Bitmap buttonBitmap) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        buttonBitmap = BitmapFactory.decodeResource(context.getResources(), defaultImageRes);
        setBackgroundColor(Color.GRAY);

        this.setClickable(true);//設定可點擊
    }
    @Override
    public void setPressed(boolean pressed) {
        //設定點擊效果
        super.setPressed(pressed);
        if (pressed){
            setBackgroundColor(Color.WHITE);
        }
        else{
            setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Measure(測量) 自定義view的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getWidth(widthMeasureSpec), getHeight(heightMeasureSpec));
    }

    //取得View寬-->
    int minWidth(){
        return buttonBitmap.getWidth();
    }
    private int getWidth(int measureSpec) {

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            // 指定大小
            return size;
        } else if (mode == MeasureSpec.AT_MOST) {
            return Math.min(minWidth(), size);
        } else {
            return minWidth();
        }
    }//<--

    //取得View高-->
    int minHeight(){
        return buttonBitmap.getHeight();
    }
    private int getHeight(int measureSpec) {

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            // 指定大小
            return size;
        } else if (mode == MeasureSpec.AT_MOST) {
            return Math.min(minHeight(), size);
        } else {
            return minHeight();
        }
    }//<--

    @Override
    protected void onDraw(Canvas canvas) {
        //繪製內容物
        Paint paint = new  Paint();
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(buttonBitmap, 10, 10, null);//圖片位置 左上起點
        paint.setTextSize(50);
        canvas.drawText("MyButton",100,50,paint);
        canvas.drawText("點擊",200,110,paint);

    }

}
