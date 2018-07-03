package me.niccorder.news.splash;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.niccorder.news.NewsApplication;
import me.niccorder.news.R;
import me.niccorder.news.list.NewsListActivity;

public class SplashActivity extends AppCompatActivity {

    /**
     * Used to retrieve the home activity start delay, if the activity was rotated.
     */
    private static final String KEY_START_DELAY = "key_start_delay";

    /**
     * A reference to our root view, or container view for the activity.
     */
    private View container;

    /**
     * This handler is used to start the home activity after an initial delay to make the initial
     * load process looks nice.
     */
    private Handler mainHandler;

    /**
     * The animation drawable for our gradient.
     */
    private AnimationDrawable gradientAnimation;

    /**
     * The delay we want to start our "home" at.
     */
    private long startDelay;

    /**
     * The time (in milliseconds) the screen was visually displayed
     */
    private long visibleAtMillis;

    /**
     * The time the activity was hidden from the user at. This is used to calculate the animation
     * length when the activity is restarted.
     */
    private long hiddenAtMillis;

    /**
     * We create this runnable as an instance variable so we can remove it if the user rotates the
     * screen before the delay.
     *
     * If we do not do this and the screen is rotated before the delay, we will start two home
     * activities and that is not what we want.
     */
    private final Runnable startHomeActivityRunnable = new Runnable() {
        @Override
        public void run() {
            Intent startHomeIntent = new Intent(SplashActivity.this, NewsListActivity.class);

            startActivity(startHomeIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Grab a reference to our container/root view.
        container = findViewById(R.id.root);

        // Grab a reference to our gradient animation set on the root view.
        gradientAnimation = (AnimationDrawable) container.getBackground();

        // The delay which to start the activity at
        startDelay = getAnimationDuration(gradientAnimation);

        // If the screen was rotated we want the to not still be 200ms long
        // we want to grab the calculated time to set the delay appropriately
        // so the users will always have the same delay.
        //
        // We check if the saved state is not null here because the first time the activity is
        // opened we want the delay at 200ms
        if (savedInstanceState != null) {
            startDelay = savedInstanceState.getLong(KEY_START_DELAY);
        }

        // grab a reference to the main handler dependency.
        // which is created and lives in our application class.
        mainHandler = ((NewsApplication) getApplication()).getMainHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Grab the time we visually
        visibleAtMillis = SystemClock.uptimeMillis();

        // This is used to help smooth the animation itself.
        gradientAnimation.setEnterFadeDuration(250);
        gradientAnimation.setExitFadeDuration(250);

        // This tells the handler to run the code inside of the runnable after the startDelay
        mainHandler.postDelayed(startHomeActivityRunnable, startDelay);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // The time which the activity was no longer visible to the user at
        hiddenAtMillis = SystemClock.uptimeMillis();

        // If the screen is rotated, we want to immediately remove the runnable from the queue
        // so that way it doesn't run twice!
        mainHandler.removeCallbacks(startHomeActivityRunnable);
    }

    /**
     * @param drawable to calculate the duration for
     * @return the animation duration of the given drawable.
     */
    private long getAnimationDuration(AnimationDrawable drawable) {
        long totalDuration = 0;
        for (int i = 0; i < drawable.getNumberOfFrames(); ++i) {
            totalDuration = drawable.getDuration(i);
        }
        return totalDuration;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Calculate the start delay, and then store it in the outState
        // so that way when the activity was recreated we can easily retrieve this data
        // and start the activity after the specified amount of time.
        long delay = startDelay - (hiddenAtMillis - visibleAtMillis);
        outState.putLong(KEY_START_DELAY, delay);
    }
}
