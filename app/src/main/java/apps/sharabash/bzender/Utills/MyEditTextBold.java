package apps.sharabash.bzender.Utills;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MyEditTextBold extends AppCompatEditText {


    private final Context context;
    private AttributeSet attrs;

    public MyEditTextBold(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.attrs = attrs;
        int defStyle1 = defStyle;
        init();
    }

    private void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Aileron-Bold.otf");
        this.setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "Aileron-Bold.otf");
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf = Typeface.createFromAsset(getContext().getAssets(), "Aileron-Bold.otf");
        super.setTypeface(tf);
    }
}