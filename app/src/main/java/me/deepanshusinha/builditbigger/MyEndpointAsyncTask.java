package me.deepanshusinha.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import me.deepanshusinha.builditbigger.backend.myApi.MyApi;
import timber.log.Timber;

class MyEndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private ExecutionListener listener;
    private static MyApi myApiService = null;

    MyEndpointAsyncTask(ExecutionListener listener) {
        this.listener = listener;
    }

    interface ExecutionListener {
        void changeProgressBarViewStatus(boolean var);

        void startDisplayActivity(String result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.changeProgressBarViewStatus(true);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null
            ).setRootUrl(BuildConfig.ROOT_URL)
                    .setGoogleClientRequestInitializer(
                            request -> request.setDisableGZipContent(true)
                    );
            myApiService = builder.build();
        }
        try {
            return myApiService.getJokeFromBackend().execute().getData();
        } catch (IOException e) {
            Timber.d(e);
            return "";
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.changeProgressBarViewStatus(false);
        listener.startDisplayActivity(s);
    }
}
