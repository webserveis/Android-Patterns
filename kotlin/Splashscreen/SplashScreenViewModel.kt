package com.example.webse.testkotlin2

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.util.Log

class SplashScreenViewModel : ViewModel() {

    private var mTaskWork: MutableLiveData<TaskModel> = MutableLiveData()
    private var myWork: InitTask = InitTask(mTaskWork)

    fun getTaskStatus(): LiveData<TaskModel> {
        return mTaskWork
    }

    fun initSplashScreen() {
        if (myWork.status == AsyncTask.Status.PENDING) {
            myWork.execute()
        }


    }

    fun cancelSplashScreen() {
        if (!myWork.isCancelled) myWork.cancel(true)
    }

    private class InitTask(val mTaskWork: MutableLiveData<TaskModel>) : AsyncTask<Void, Int, Boolean>() {

        private val TAG = InitTask::class.java.simpleName

        override fun onPreExecute() {
            Log.i(TAG, "onPreExecute")
            mTaskWork.value = TaskModel(TaskModel.ENQUEUED, 0)

        }

        override fun doInBackground(vararg params: Void?): Boolean {
            Log.i(TAG, "doInBackground")

            mTaskWork.postValue(TaskModel(TaskModel.RUNNING))

            for (i in 1..10) {
                Thread.sleep(1000)  // wait for 1 second
                onProgressUpdate(i)
            }

            return true
        }

        override fun onProgressUpdate(vararg values: Int?) {
            Log.i(TAG, "wait" + values[0])
            mTaskWork.postValue(TaskModel(TaskModel.RUNNING, values[0]))
        }

        override fun onPostExecute(result: Boolean?) {
            Log.i(TAG, "onPostExecute")
            mTaskWork.value = TaskModel(TaskModel.SUCCESS, 100)

        }

        override fun onCancelled(result: Boolean?) {
            Log.d(TAG, "onCancelled$result")
            mTaskWork.value = TaskModel(TaskModel.CANCELLED)
            super.onCancelled(result)
        }
    }

}
