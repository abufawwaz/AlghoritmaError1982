package com.roaita.imsakiyah.media;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.widget.TextView;

import com.roaita.imsakiyah.R;

public class RainbowTextView extends android.support.v7.widget.AppCompatTextView {
    public RainbowTextView(Context context) {
        super(context);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int[] rainbow = getRainbowColors();
        Shader shader = new LinearGradient(0, 0, 0, w, rainbow,
                null, Shader.TileMode.MIRROR);

        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);

        getPaint().setShader(shader);
    }

    private int[] getRainbowColors() {
        return new int[] {
                getResources().getColor(R.color.md_red_900),
                getResources().getColor(R.color.md_yellow_900),
                getResources().getColor(R.color.blue_grey_900),
                getResources().getColor(R.color.grey_800),
                getResources().getColor(R.color.md_deep_purple_900)
        };
    }
}