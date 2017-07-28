package com.example.administrator.metroanimationtest;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MetroImageView extends ImageView {

    private static final int ANIMATION_START_FLAG = 0;
    private static final int ANIMATION_SCALLING = 1;
    private static final int ANIMATION_UPSET = 2;

    //动画是否已经播放结束
    private boolean animationIsFinish = true;
    private int mWidth;
    private int mHeight;
    private int mCenterWidth;
    private int mCenterHeight;

    //初始化缩放系数
    private float scaleNum = 0.85f;

    public MetroImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MetroImageView(Context context, AttributeSet attrs) {
       this(context, attrs, 0);
    }

    public MetroImageView(Context context) {
        this(context, null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

            mCenterWidth = mWidth/2;
            mCenterHeight = mHeight/2;

            Drawable drawable = getDrawable();
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bd.setAntiAlias(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mHandler.sendEmptyMessage(ANIMATION_START_FLAG);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessage(ANIMATION_UPSET);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private Handler mHandler = new Handler(){

        private Matrix matrix = new Matrix();
        private int count = 0;
        private float s;

        @Override
        public void handleMessage(Message msg) {
            matrix.set(getImageMatrix());
            switch (msg.what){
                case ANIMATION_START_FLAG:
                    if(!animationIsFinish){
                        mHandler.sendEmptyMessage(ANIMATION_START_FLAG);
                    }else{
                        animationIsFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(scaleNum));
                        matrix.postScale(s, s, mCenterWidth, mCenterHeight);
                        setImageMatrix(matrix);
                        mHandler.sendEmptyMessage(ANIMATION_SCALLING);
                    }
                    break;
                case ANIMATION_SCALLING:
                    matrix.postScale(s, s, mCenterWidth, mCenterHeight);
                    setImageMatrix(matrix);
                    if(count < 4){
                        mHandler.sendEmptyMessage(ANIMATION_SCALLING);
                    }else{
                        animationIsFinish = true;

                    }
                    count++;
                    break;
                case ANIMATION_UPSET:
                    if(!animationIsFinish){
                        mHandler.sendEmptyMessage(ANIMATION_UPSET);
                    }else{
                        animationIsFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(1.0f / scaleNum));
                        matrix.postScale(s, s, mCenterWidth, mCenterHeight);
                        setImageMatrix(matrix);
                        mHandler.sendEmptyMessage(ANIMATION_SCALLING);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
