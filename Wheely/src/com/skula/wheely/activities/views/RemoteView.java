package com.skula.wheely.activities.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.skula.wheely.constants.Cnst;
import com.skula.wheely.services.BluetoothService;
import com.skula.wheely.utils.MessageUtils;
import com.skula.wheely.utils.TrigoUtils;

public class RemoteView extends View {

	private BluetoothService btService;
	private int x;
	private int y;
	private int speed;
	private int angle;
	private Paint paint;

	/*public RemoteView(Context context, BluetoothService btService) {
		super(context);
		this.btService = btService;
		this.x = -1;
		this.y = -1;
		this.speed = Cnst.DEFAULT_SPEED;
		this.angle = Cnst.DEFAULT_ANGLE;
		this.paint = new Paint();
	}*/
	
	public RemoteView(Context context) {
		super(context);
		//this.btService = btService;
		this.x = -1;
		this.y = -1;
		this.speed = Cnst.DEFAULT_SPEED;
		this.angle = Cnst.DEFAULT_ANGLE;
		this.paint = new Paint();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		double x1 = event.getX();
		double y1 = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			speed = (int) TrigoUtils.getSpeed(x1, y1);
			angle = (int) TrigoUtils.getAngle(x1, y1);
			x = (int) x1;
			y = (int) y1;
			break;
		case MotionEvent.ACTION_MOVE:
			speed = (int) TrigoUtils.getSpeed(x1, y1);
			angle = (int) TrigoUtils.getAngle(x1, y1);
			x = (int) x1;
			y = (int) y1;
			break;
		case MotionEvent.ACTION_UP:
			x = -1;
			y = -1;
			speed = Cnst.DEFAULT_SPEED;
			angle = Cnst.DEFAULT_ANGLE;
			break;
		}
		invalidate();
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		drawCircle(canvas);
		drawValues(canvas);
		if (x != -1 && y != -1) {
			drawPoint(canvas);
		}
		drawAxes(canvas);
	}

	private void drawCircle(Canvas canvas) {
		paint.setColor(Color.BLUE);
		canvas.drawCircle(Cnst.X0, Cnst.Y0, Cnst.RADIUS, paint);
		paint.setStrokeWidth(10f);
		paint.setColor(Color.CYAN);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(Cnst.X0, Cnst.Y0, Cnst.RADIUS, paint);
		paint.setStyle(Paint.Style.FILL);
	}
	
	private void drawAxes(Canvas canvas) {
		paint.setColor(Color.WHITE);
		int xtmp = Cnst.X0 - Cnst.RADIUS - 40;
		int xtmp2 = Cnst.X0 + Cnst.RADIUS + 40;
		Rect r = new Rect(xtmp, Cnst.Y0 - 2, xtmp2, Cnst.Y0 + 2);
		canvas.drawRect(r, paint);

		int ytmp = Cnst.Y0 - Cnst.RADIUS - 40;
		int ytmp2 = Cnst.Y0 + Cnst.RADIUS + 40;
		r = new Rect(Cnst.X0 - 2, ytmp, Cnst.X0 + 2, ytmp2);
		canvas.drawRect(r, paint);
	}

	private void drawPoint(Canvas canvas) {
		paint.setColor(Color.RED);
		canvas.drawCircle(x, y, 20, paint);
	}

	private void drawValues(Canvas canvas) {
		paint.setColor(Color.GREEN);
		paint.setTextSize(40f);
		canvas.drawText("Speed: " + speed, 20, 40, paint);
		canvas.drawText("Angle: " + angle, 20, 80, paint);
		canvas.drawText("Message: " + MessageUtils.format(angle, speed), 20, 120, paint);
	}
}