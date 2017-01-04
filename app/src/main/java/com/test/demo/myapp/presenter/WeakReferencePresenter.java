package com.test.demo.myapp.presenter;


import android.os.AsyncTask;
import android.os.Handler;

import java.lang.ref.WeakReference;

public class WeakReferencePresenter {

    private WeakReference<WeakReferencePresenterCallback> weakReferenceCallback;
    private WeakReferencePresenterCallback callback;

    public WeakReferencePresenter(WeakReferencePresenterCallback callback) {
        weakReferenceCallback = new WeakReference<WeakReferencePresenterCallback>(callback);
        this.callback = callback;
    }

    public void getSomeData() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (weakReferenceCallback.get() != null) {
                    weakReferenceCallback.get().showSomeDataWeak("Here is some weak data for you! " + this.toString());
                } else {
                    callback.showSomeDataStrong("Here is some strong data for you! " + this.toString());
                }
            }
        };
        task.execute();
    }

    public interface WeakReferencePresenterCallback {
        void showSomeDataWeak(String data);
        void showSomeDataStrong(String data);
    }
}
