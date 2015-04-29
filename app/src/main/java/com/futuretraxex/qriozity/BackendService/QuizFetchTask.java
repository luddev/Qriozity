package com.futuretraxex.qriozity.BackendService;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;

import com.futuretraxex.qriozity.Data.Question;
import com.futuretraxex.qriozity.DataParser.RandomQuestionParsing;
import com.futuretraxex.qriozity.PlayFragment;
import com.futuretraxex.qriozity.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lud on 4/23/2015.
 */
public class QuizFetchTask extends AsyncTask<URL, Integer, String> {

    public String API_URL = "http://192.168.1.3:3002/";
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
        PlayFragment.PlayView.question.setText("Q. " + Question.mQuestion);
        PlayFragment.PlayView.option1.setText("A. " + Question.mOption1);
        PlayFragment.PlayView.option2.setText("B. " + Question.mOption2);
        PlayFragment.PlayView.option3.setText("C. " + Question.mOption3);
        PlayFragment.PlayView.option4.setText("D. " + Question.mOption4);

        PlayFragment.PlayView.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Question.verifyChoice(1) == true)    {
                    PlayFragment.PlayView.option1.setBackgroundResource(R.color.green);
                }
                else {
                    PlayFragment.PlayView.option1.setBackgroundResource(R.color.red);
                    showCorrectAnswer();
                }
                fetchNextQuestion();
            }
        });
        PlayFragment.PlayView.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Question.verifyChoice(2) == true)    {
                    PlayFragment.PlayView.option2.setBackgroundResource(R.color.green);
                }
                else {
                    PlayFragment.PlayView.option2.setBackgroundResource(R.color.red);
                    showCorrectAnswer();
                }
                fetchNextQuestion();
            }
        });
        PlayFragment.PlayView.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Question.verifyChoice(3) == true)    {
                    PlayFragment.PlayView.option3.setBackgroundResource(R.color.green);
                }
                else {
                    PlayFragment.PlayView.option3.setBackgroundResource(R.color.red);
                    showCorrectAnswer();
                }
                fetchNextQuestion();
            }
        });
        PlayFragment.PlayView.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Question.verifyChoice(4) == true)    {
                    PlayFragment.PlayView.option4.setBackgroundResource(R.color.green);
                }
                else {
                    PlayFragment.PlayView.option4.setBackgroundResource(R.color.red);
                    showCorrectAnswer();
                }
                fetchNextQuestion();
            }
        });

    }

    void showCorrectAnswer()    {
        switch(Question.mAnswer)    {
            case 1:
                PlayFragment.PlayView.option1.setBackgroundResource(R.color.green);
                break;
            case 2:
                PlayFragment.PlayView.option2.setBackgroundResource(R.color.green);
                break;
            case 3:
                PlayFragment.PlayView.option3.setBackgroundResource(R.color.green);
                break;
            case 4:
                PlayFragment.PlayView.option4.setBackgroundResource(R.color.green);
                break;
            default:
        }
    }

    void fetchNextQuestion()    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PlayFragment.PlayView.option1.setBackgroundResource(0);
                PlayFragment.PlayView.option2.setBackgroundResource(0);
                PlayFragment.PlayView.option3.setBackgroundResource(0);
                PlayFragment.PlayView.option4.setBackgroundResource(0);
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
