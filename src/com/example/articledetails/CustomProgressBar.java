package com.example.articledetails;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CustomProgressBar extends View {

	private Context context;
	private int mFirstColor, mSecondColor;
	private int mCircleWidth, mSpeed, mProgress, mCount=0;
	private static int Max = 100;
	private static int position[][] = new int[Max][2];  
	private int mLinkColor, mStartX, mStartY, mEndX, mEndY, mLinkWidth;
	private int mStartX1, mStartY1;
	
	private Paint mPaint;
	private boolean isNext;
	public CustomProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.linkAttr, defStyleAttr, 0);
		int n = ta.getIndexCount();
		for(int i = 0; i < n; i ++){
			switch (ta.getIndex(i)) {
			case R.styleable.linkAttr_linkColor:
				mLinkColor = ta.getColor(ta.getIndex(i), Color.WHITE);
				break;
			case R.styleable.linkAttr_startX:
				mStartX1 = mStartX = ta.getDimensionPixelSize(ta.getIndex(i), (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				
				break;
			case R.styleable.linkAttr_startY:
				mStartY1 = mStartY = ta.getDimensionPixelSize(ta.getIndex(i), (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				break;
			case R.styleable.linkAttr_linkWidth:
				mLinkWidth = ta.getDimensionPixelSize(ta.getIndex(i), (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
		/*for(int i = 0; i < n; i ++)
			switch (ta.getIndex(i)) {
			case R.styleable.CustomProgressBar_firstColor:
				mFirstColor = ta.getColor(ta.getIndex(i), Color.WHITE);
				break;
			case R.styleable.CustomProgressBar_secondColor:
				mSecondColor = ta.getColor(ta.getIndex(i), Color.BLACK);
				break;
			case R.styleable.CustomProgressBar_circleWidth:
				mCircleWidth = ta.getDimensionPixelSize(ta.getIndex(i), (int) TypedValue.applyDimension(  
                        TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				break;
			case R.styleable.CustomProgressBar_speed:
				mSpeed = ta.getInt(ta.getIndex(i), 20);
				break;
			default:
				break;
		}*/
		ta.recycle();
	}

	public static void setOnPositionListener(int[][] ttposition){
		position = ttposition;
	}
	
	interface OnCanvasLister{
		void setOncanvasLister();
	}
	public OnCanvasLister onCanvasLister;
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		mPaint = new Paint();
		mProgress++;
		mEndX = position[mCount][0];
		mEndY = position[mCount][1];
        if (mProgress >= 100)  
        {  
        	mStartX = mEndX;
        	mStartY = mEndY;
        	mCount++;
        	if(mCount >= position.length){
        		mCount = 0;
        		mStartX = mStartX1;
        		mStartY = mStartY1;
        	}
            mProgress = 0;  
            if (!isNext)  
                isNext = true;  
            else  
                isNext = false;  
        }
        postInvalidate();  
        try  
        {  
            Thread.sleep(mSpeed);  
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }
		
        mPaint.setColor(mLinkColor);
        canvas.drawColor(Color.WHITE);
        mPaint.setStrokeWidth(mLinkWidth);
        float f = (float) (mProgress/100.00);
        System.out.println("f:"+f);
        for(int i = 0; i < position.length; i ++){
        	if(mCount > 0){
        		canvas.drawLine(mStartX1, mStartY1, position[0][0], position[0][1], mPaint);
        	}
        	if(mCount == i){
        		canvas.drawLine(mStartX, mStartY, f*(mEndX-mStartX)+mStartX, f*(mEndY-mStartY)+mStartY, mPaint);
        	}
        	if(i < mCount && i > 0){
        		canvas.drawLine(position[i-1][0], position[i-1][1], position[i][0], position[i][1], mPaint);
        	}
        	if (i > mCount){
        		canvas.drawLine(0, 0, 0, 0, mPaint);
        	}
        }
        
        
        
		/*int center = getWidth()/2;
		int radius = center-mCircleWidth;
		Toast.makeText(context, mCircleWidth+"--"+radius, 1).show();
		mPaint.setStrokeWidth(mCircleWidth);
		mPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断电形状为圆头  
		mPaint.setStyle(Paint.Style.STROKE);
		RectF oval = new RectF(center-radius, center-radius, center+radius, center+radius);
		if(isNext){
			mPaint.setColor(mFirstColor);
			canvas.drawCircle(getWidth()/2, getHeight()/2, radius, mPaint);
			mPaint.setColor(mSecondColor);
			canvas.drawArc(oval, -90, mProgress, false, mPaint);
		}else{
			mPaint.setColor(mSecondColor);
			canvas.drawCircle(getWidth()/2, getHeight()/2, radius, mPaint);
			mPaint.setColor(mFirstColor);
			canvas.drawArc(oval, -90, mProgress, false, mPaint);
		}*/
	}
	
	public CustomProgressBar(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	
	public CustomProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
}
