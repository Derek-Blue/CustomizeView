package com.example.myviewpractice;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class myEditTextView extends EditText implements OnFocusChangeListener {

    private Drawable clearDrawable;
    private boolean hasFocus;

    public myEditTextView(Context context) {
        this(context,null);
    }

    public myEditTextView(Context context, AttributeSet attrs) {
        this(context,attrs,R.attr.editTextStyle);
    }

    public myEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        clearDrawable = getCompoundDrawables()[2];//設定圖片放在控件的位置 ( 0:Left ; 1:Top ; 2:Right ; 3:Bottom)
        if(clearDrawable == null){
            clearDrawable = getResources().getDrawable(R.drawable.delete);
        }
        clearDrawable.setBounds(0,0,clearDrawable.getIntrinsicWidth(),clearDrawable.getIntrinsicHeight());
        setOnFocusChangeListener(this);
        setClearIconVisible(false);//預設尚未輸入文字時 隱藏刪除圖示
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //宣告布林值isInnerWidth, isInnerHeight 為true，*點擊在删除圖示之内，清除文字
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height)/2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y <(distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //當移動焦點時，即使有文字也隱藏刪除圖案
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        this.hasFocus = hasFocus;
    }
    protected void setClearIconVisible(boolean visible) {
        //宣告一個Drawable 並賦予布林值:true顯示圖片;false則隱藏圖片
        Drawable right = visible ? clearDrawable : null; // ? = if
        //圖片放在控件的位置 ( 0:Left ; 1:Top ; 2:Right ; 3:Bottom)
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        //輸入文字後顯示圖片
        if(hasFocus){
            setClearIconVisible(text.length()>0);
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }



}
