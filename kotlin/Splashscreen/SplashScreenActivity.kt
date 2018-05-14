package com.example.webse.testkotlin2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity






class SplashScreenActivity : AppCompatActivity() {

    private val TAG = SplashScreenActivity::class.java.simpleName

    private var viewModel: SplashScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        viewModel?.getTaskStatus()?.observe(this, changeObserver)
        viewModel?.initSplashScreen()


    }

    private val changeObserver = Observer<TaskModel> { value ->
        Log.d(TAG, "changeObserver$value")

        val fadeOut = AlphaAnimation(1f, 0f)  // the 1, 0 here notifies that we want the opacity to go from opaque (1) to transparent (0)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.startOffset = 500 // Start fading out after 500 milli seconds
        fadeOut.duration = 1000 // Fadeout duration should be 1000 milli seconds


        val animAfterLoad = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        animAfterLoad.interpolator = AccelerateInterpolator()
        animAfterLoad.duration = 1000

        animAfterLoad.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {

                splashScreenLayout.setVisibility(View.GONE)
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        when (value?.status) {
            TaskModel.SUCCESS -> imageView.startAnimation(animAfterLoad);
        }

    }

    override fun onBackPressed() {
        viewModel?.cancelSplashScreen()
        super.onBackPressed()
    }
}
