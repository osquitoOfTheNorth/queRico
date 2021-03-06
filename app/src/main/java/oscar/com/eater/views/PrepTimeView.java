package oscar.com.eater.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import oscar.com.eater.R;

/**
 * Created by omenji on 3/22/17.
 */

public class PrepTimeView extends View {


    String mTimeNumberText;
    float mTextSize;
    int mTimerIcon;
    int mColor;
    Paint mPainterText;
    public PrepTimeView(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PrepTime,0,0);
        try{
            mTextSize = array.getDimension(R.styleable.PrepTime_textSize, 10.0f);
            mTimerIcon = array.getResourceId(R.styleable.PrepTime_timerIcon,R.drawable.timer);
            mColor = array.getColor(R.styleable.QueRicoTextView_color, getContext().getColor(R.color.textColor));
            init();
        } finally {
            array.recycle();
        }
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        onVisibleAttributeChanged();
    }


    public int getTimerIcon() {
        return mTimerIcon;
    }

    public void setTimerIcon(int timerIcon) {
        mTimerIcon = timerIcon;
        onVisibleAttributeChanged();
    }


    private void onVisibleAttributeChanged(){
        invalidate();
        requestLayout();
    }

    public String getText() {
        return mTimeNumberText;
    }

    public void setText(String text) {
        mTimeNumberText = text;
        onVisibleAttributeChanged();
    }

    private void init(){
        mPainterText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPainterText.setColor(mColor);
        mPainterText.setStyle(Paint.Style.STROKE);
        mPainterText.setTextSize(mTextSize);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = (getWidth() / 2);
        float posY = getHeight();
        if(mPainterText != null && mTimeNumberText != null) {
            mPainterText.setTextAlign(Paint.Align.CENTER);
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),mTimerIcon);
            int iconWidth = Math.round(icon.getWidth() * 0.5f);
            canvas.drawBitmap(icon,centerX - iconWidth,0,mPainterText);
            canvas.drawText(mTimeNumberText, centerX , (posY - mPainterText.getTextSize()), mPainterText);
        }
    }
}
