package apps.sharabash.bzender.Utills;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CountTinerDownTimer extends CountDownTimer {
    private final MyTextViewBold mTxtDay;
    private final MyTextViewBold mTxtHrs;
    private final MyTextViewBold mTxtMin;
    private final MyTextViewBold mTxtSec;

    public CountTinerDownTimer(long millisInFuture, long countDownInterval, MyTextViewBold mTxtDay, MyTextViewBold mTxtHrs, MyTextViewBold mTxtMin, MyTextViewBold mTxtSec) {
        super(millisInFuture, countDownInterval);

        this.mTxtDay = mTxtDay;
        this.mTxtHrs = mTxtHrs;
        this.mTxtMin = mTxtMin;
        this.mTxtSec = mTxtSec;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        long days = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));

        long hours = ((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))));

        long min = ((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))));

        long sec = (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));


        String daysString = String.format(Locale.US,"%02d", days);
        String hrsString = String.format(Locale.US,"%02d", hours);
        String minsString = String.format(Locale.US,"%02d", min);
        String secsString = String.format(Locale.US,"%02d", sec);


        mTxtDay.setText(daysString);
        mTxtHrs.setText(hrsString);
        mTxtMin.setText(minsString);
        mTxtSec.setText(secsString);
//
//            TIMER_DAYS = daysString;
//            TIMER_HRS = hrsString;
//            TIMER_MINS = minsString;
//            TIMER_SECS = secsString;

//            allTendersInterface.setTimerDays(daysString);
//            allTendersInterface.setTimerHours(hrsString);
//            allTendersInterface.setTimerMinuts(minsString);
//            allTendersInterface.setTimerSeconds(secsString);

    }


    @Override
    public void onFinish() {
        Log.d("TAG", "onFinish: DONE");


    }


}