package com.futuretraxex.qriozity.BackendService;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.futuretraxex.qriozity.Data.Question;
import com.futuretraxex.qriozity.DataParser.RandomQuestionParsing;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lud on 4/23/2015.
 */
public class QuizFetchTask extends AsyncTask<URL, Integer, String> {

    public String API_URL_TYPE = "http://";
    public String API_URL;
    public String API_FETCH_RANDOM = "v1/getRandomQuestion";
    public String API_PREF_URL;

    public QuizFetchTask(Context context){
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(context);
        API_PREF_URL = setting.getString("urlname", "");
        Log.w("FP", API_PREF_URL);
        API_URL = API_URL_TYPE + API_PREF_URL + "/";
    }


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
        Log.w("UI UPDATE", "Updating Result");
        Question.mOptionsList.updateResults();
    }



    void showCorrectAnswer()    {
    }

}
