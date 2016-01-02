package com.dianping.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SiderMenu extends View {

	
	private static OnLetterCheckedListener listener;
	
	public SiderMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusableInTouchMode(true);
		setFocusable(true);
	}
	private int selectedId=-1;
	public SiderMenu(Context context) {
		super(context);
		setFocusableInTouchMode(true);
		setFocusable(true);
	}

	Paint p = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {

	
		p.setARGB(255, 0, 0, 0);
		p.setTypeface(Typeface.DEFAULT_BOLD);
		p.setAntiAlias(true);
		p.setTextSize(24f);
		float each_height=getHeight()/letter.length;
		for(int i=0;i<letter.length;i++)
		{
			if(i==selectedId)
			{
				p.setARGB(255, 255, 111, 33);
				p.setFakeBoldText(true);
				p.setSubpixelText(true);
				p.setTextScaleX(1.8f);
				p.setLinearText(true);
			}else
			{
				
				
				p.setLinearText(false);
				p.setARGB(50, 0, 0, 0);
				p.setFakeBoldText(false);
				p.setSubpixelText(false);
				p.setTextScaleX(1f);
				
			}
			float x=getWidth()/2-p.measureText(letter[i])/2;
			float y=(i+1)*each_height;
			
			canvas.drawText(letter[i], x, y, p);
		}
		
		invalidate();
	}
	
	String letter[] = { "All", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			setBackgroundColor(Color.TRANSPARENT);
			selectedId=-1;
			listener.onLetterChecked("");
			invalidate();
			
			break;

		default:
			float y= event.getY();
			final int  c = (int) (y/getHeight()*letter.length);
			if(listener!=null)
				listener.onLetterChecked(letter[c]);
			selectedId=c;
			invalidate();
			break;
		}
		
		return super.dispatchTouchEvent(event);
	}
	
	public static interface OnLetterCheckedListener{
		public void onLetterChecked(String letter);
	}
	public void setOnLetterCheckedListener(OnLetterCheckedListener l)
	{
		listener=l;
	}
}
