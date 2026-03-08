package com.pedroyuri.decibelimetro.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.pedroyuri.decibelimetro.R;

public class GaugeView extends View {
    private final Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint orangePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF rect = new RectF();
    private int value = 0;

    public GaugeView(Context context) { super(context); init(); }
    public GaugeView(Context context, @Nullable AttributeSet attrs) { super(context, attrs); init(); }
    public GaugeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        float stroke = 26f;
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(stroke);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setColor(ContextCompat.getColor(getContext(), R.color.gauge_bg));

        Paint[] paints = new Paint[]{greenPaint, yellowPaint, orangePaint, redPaint};
        int[] colors = new int[]{R.color.green, R.color.yellow, R.color.orange, R.color.red};

        for (int i = 0; i < paints.length; i++) {
            paints[i].setStyle(Paint.Style.STROKE);
            paints[i].setStrokeWidth(stroke);
            paints[i].setStrokeCap(Paint.Cap.ROUND);
            paints[i].setColor(ContextCompat.getColor(getContext(), colors[i]));
        }
    }

    public void setValue(int value) {
        this.value = Math.max(0, Math.min(120, value));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float padding = 32f;
        rect.set(padding, padding, getWidth() - padding, getHeight() - padding);
        canvas.drawArc(rect, 135, 270, false, bgPaint);

        float sweep = (270f * value) / 120f;
        float start = 135f;

        float greenSweep = Math.min(sweep, 100f);
        if (greenSweep > 0) canvas.drawArc(rect, start, greenSweep, false, greenPaint);
        if (sweep > 100f) canvas.drawArc(rect, start + 100f, Math.min(sweep - 100f, 50f), false, yellowPaint);
        if (sweep > 150f) canvas.drawArc(rect, start + 150f, Math.min(sweep - 150f, 50f), false, orangePaint);
        if (sweep > 200f) canvas.drawArc(rect, start + 200f, sweep - 200f, false, redPaint);
    }
}
