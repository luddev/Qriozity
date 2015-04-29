package com.futuretraxex.qriozity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futuretraxex.qriozity.BackendService.QuizFetchTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lud on 4/24/2015.
 */
public class PlayFragment extends Fragment {

    public PlayFragment() {
        super();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play, container, false);
        PlayView.initPlayView(rootView);
        QuizFetchTask qTask = new QuizFetchTask();
        try {
            Log.w("Do In BACKGROUND", "EWxecuting Task");
            qTask.execute(new URL("http://192.168.1.3:3002/v1/getRandomQuestion"));


        }
        catch(MalformedURLException murlExc) {

        }


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static class PlayView {
        public static TextView question;
        public static TextView option1;
        public static TextView option2;
        public static TextView option3;
        public static TextView option4;

        public static void initPlayView(View rootView)    {
            try {
                question = (TextView)rootView.findViewById(R.id.question);
                option1 = (TextView)rootView.findViewById(R.id.option1);
                option2 = (TextView)rootView.findViewById(R.id.option2);
                option3 = (TextView)rootView.findViewById(R.id.option3);
                option4 = (TextView)rootView.findViewById(R.id.option4);
            }
            catch (NullPointerException ne) {
                //Do Nothing.
            }
        }
    }
}
