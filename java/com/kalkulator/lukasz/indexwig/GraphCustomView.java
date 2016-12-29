package com.kalkulator.lukasz.indexwig;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukasz on 2016-12-28.
 */

class GraphCustomView extends View {
    private List<Float> WigDataForGraph;

    public GraphCustomView(Context context) {//constructor
        super(context);
        WigDataForGraph=new ArrayList<Float>();
    }
    public GraphCustomView(Context context, AttributeSet attrs) {//constructor
        super(context, attrs);
        WigDataForGraph=new ArrayList<Float>();
    }
    public GraphCustomView(Context context, AttributeSet attrs, int defStyle) {//constructor
        super(context, attrs, defStyle);
        WigDataForGraph=new ArrayList<Float>();
    }



    public void SetWigData(List<Float> wigdata) {
        WigDataForGraph=wigdata;
    }

    private Float GetMaxValue() {
        Float max=WigDataForGraph.get(0);
        for(Float f:WigDataForGraph)
            if(f>max)max=f;
        return max;
    }

    private Float GetMinValue() {
        Float min=WigDataForGraph.get(0);
        for(Float f:WigDataForGraph)
            if(f<min)min=f;
        return min;
    }

    private Float CalculateValueForGraphX(int Value, int length) {
        return (Value/((float)length))*getWidth();
    }

    private Float CalculateValueForGraphY(Float Value) {
        Float minV=GetMinValue();
        Float maxV=GetMaxValue();
        Float maxSubMin=maxV-minV;
        Float out=((Value-minV)/maxSubMin);
        return (1.0f-out)*getHeight();//reverse (drawLine 0,0 point is in top left corner)
    }





    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF33B5E5);
        paint.setStrokeWidth(4);

        if(!WigDataForGraph.isEmpty()) {
            int WigDataForGraphSize = WigDataForGraph.size();
            for (int i = 0; i < WigDataForGraphSize; i++)
                if (WigDataForGraph.size() > i + 1)
                    canvas.drawLine(CalculateValueForGraphX(i, WigDataForGraphSize), CalculateValueForGraphY(WigDataForGraph.get(i)), CalculateValueForGraphX(i + 1, WigDataForGraphSize), CalculateValueForGraphY(WigDataForGraph.get(i + 1)), paint);
        }
        //invalidate();//invalidate is now called in activity
    }
}
