package com.futuretraxex.qriozity.BackendService;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ListAdapter;

import com.futuretraxex.qriozity.Data.Question;
import com.futuretraxex.qriozity.DataParser.RandomQuestionParsing;
import com.futuretraxex.qriozity.PlayFragment;
import com.futuretraxex.qriozity.R;
import com.futuretraxex.qriozity.Resource.SoundServ;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lud on 4/23/2015.
 */
public class QuizFetchTask extends AsyncTask<URL, Integer, String> {

    public String API_URL = "http://api.futuretraxex.com/";
    public String API_FETCH_RANDOM = "v1/getRandomQuestion";


    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(URL... urls) {

        //Get random Question :3
        try {


            Uri tUrl = Uri.parse(API_URL).buildUpon()
                    .appendEncodedPath(API_FETCH_RANDOM).build();

            URL nUrl = new URL(tUrl.toString());

            Log.w("Fetch", tUrl.toString());

            HttpURLConnection connection = (HttpURLConnection) nUrl.openConnection();
            Log.w("Fetch", "Connecting");
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(5000);
            connection.connect();
            Log.w("Fetch", "Connect Success");
            InputStream rJson = connection.getInputStream();
            Log.w("Fetch", "Input Stream Fetched");
            RandomQuestionParsing.parseRandomQuestion(rJson);

            connection.disconnect();

        }
        catch(MalformedURLException urlExc) {
            //Do Nothing.
        }
        catch(IOException ioExc)    {

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String aData) {

        super.onPostExecute(aData);

        updateUI();
    }

    public void updateUI()  {
        //Do nothing?
        Question.mOptionsList.updateResults();

    }



    void showCorrectAnswer()    {
    }

    void fetchNextQuestion()    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                QuizFetchTask qTask = new QuizFetchTask();
                try {
                    qTask.execute(new URL("http://192.168.1.3:3002/v1/getRandomQuestion"));
                }
                catch (MalformedURLException mfExc) {

                }

            }
        },2000);
    }
}
