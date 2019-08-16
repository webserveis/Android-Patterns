package com.webserveis.batterycheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_splash_screen.*


/*
https://medium.com/@elye.project/android-architecture-components-for-dummies-in-kotlin-50-lines-of-code-29b29d3a381
https://stackoverflow.com/questions/52811685/kotlin-does-not-understand-viewmodelproviders-ofactivity-fragment
 */
class SplashScreen : AppCompatActivity() {

    companion object {
        val TAG: String = SplashScreen::class.java.simpleName
    }

    private val mViewModel: SplashScreenViewModel by lazy {
        ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
    }

    private val changeObserver2 = Observer<Resource<List<String>>> { value ->
        value?.let {
            when (value.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "Loading...." + value.data)
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "Success....")
                    Log.d(TAG, value.data.toString())
                    //onTaskFinished()
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "Error....")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        mViewModel.mResultData.observe(this, changeObserver2)
        mViewModel.notifyEvent.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.runTask()
    }

    private fun onTaskFinished() {

        val animAfterLoad = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        animAfterLoad.interpolator = AccelerateInterpolator()
        animAfterLoad.duration = 1000L

        animAfterLoad.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {

                Log.i(TAG, "Animation END: animAfterLoad ")
                splashscreen.visibility = View.GONE

                val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
                this@SplashScreen.startActivity(mainIntent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                this@SplashScreen.finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}
        })
        splashscreen.startAnimation(animAfterLoad)

    }

}
