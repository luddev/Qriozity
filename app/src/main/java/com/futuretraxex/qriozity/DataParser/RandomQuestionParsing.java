package com.futuretraxex.qriozity.DataParser;

import android.text.Html;
import android.util.Log;

import com.futuretraxex.qriozity.Data.Question;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by lud on 4/28/2015.
 */
public class RandomQuestionParsing {

    public RandomQuestionParsing() {

    }

    public static boolean parseRandomQuestion(InputStream in) {

        try {
            Log.w("Fetch","Executing Parsing.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String questionJsonStr;
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return false;
            }
            questionJsonStr = buffer.toString();

            Log.w("Fetch",questionJsonStr);

            JSONObject qJson = (JSONObject) new JSONTokener(questionJsonStr).nextValue();
            Log.w("JSON DATA",qJson.toString());
            Question.setQuestion(qJson.getLong("id"),
                    Html.fromHtml(qJson.getString("q_text")).toString().replaceAll("<(.*?)\\>"," ").replaceAll("&nbsp",""),
                    Html.fromHtml(qJson.getString("q_options_1")).toString().replaceAll("<(.*?)\\>"," ").replaceAll("&nbsp",""),
                    Html.fromHtml(qJson.getString("q_options_2")).toString().replaceAll("<(.*?)\\>"," ").replaceAll("&nbsp",""),
                    Html.fromHtml(qJson.getString("q_options_3")).toString().replaceAll("<(.*?)\\>"," ").replaceAll("&nbsp",""),
                    Html.fromHtml(qJson.getString("q_options_4")).toString().replaceAll("<(.*?)\\>"," ").replaceAll("&nbsp",""),
                    qJson.getInt("q_correct_option"),
                    qJson.getString("q_date_added"));

            Log.w("Fetch", Question.mQuestion);
        }
        catch(UnsupportedEncodingException unExc)   {
            Log.w("Exception",unExc.toString());
        }
        catch(IOException ioExc)    {
            Log.w("Exception",ioExc.toString());

        }
        catch(JSONException jsonExc)    {
            Log.w("Exception",jsonExc.toString());

        }

        return true;
    }
}
