package org.opencv.samples.tutorial1;

import android.os.CountDownTimer;

/**
 * User: Ding
 * Date: 1/25/14
 * Time: 4:02 AM
 */
public class myCountDown extends CountDownTimer {

    private TestActivity act;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     * @param act
     */
    public myCountDown(long millisInFuture, long countDownInterval, TestActivity act) {
        super(millisInFuture, countDownInterval);
        this.act = act;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        act.onTimerTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        act.onTimerFinish();
    }
}
