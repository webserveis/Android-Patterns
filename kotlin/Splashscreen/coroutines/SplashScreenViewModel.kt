package com.webserveis.batterycheck

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG: String = SplashScreenViewModel::class.java.simpleName
    }

    val mResultData = MutableLiveData<Resource<List<String>>>()
    var isJobRunning: Boolean = false

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    val notifyEvent = MutableLiveData<SingleLiveEvent<@StringRes Int>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.d(TAG, "job cancel")
    }

    fun runTask() {
        if (!isJobRunning)
            uiScope.launch {
                Log.d(TAG, "launch Data Load")

                isJobRunning = true

                val list = mutableListOf<String>()

                if (verifyAvailableNetwork(getApplication())) {

                    (1..10).forEach {
                        delay(1000L)

                        mResultData.postValue(Resource.loading(listOf("${Thread.currentThread().name} generate steep:$it")))
                        list.add("steep task $it")
                    }

                    mResultData.postValue(Resource.success(list))

                } else {
                    Log.e(TAG, "No internet connection")
                    notifyEvent.postValue(SingleLiveEvent(R.string.error_no_internet))
                    isJobRunning = false
                }


            }
    }

    private fun verifyAvailableNetwork(activity: Application): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


}
