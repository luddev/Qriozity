package com.futuretraxex.qriozity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.futuretraxex.qriozity.BackendService.QuizFetchTask;
import com.futuretraxex.qriozity.Data.Question;
import com.futuretraxex.qriozity.Resource.SoundServ;

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
        SoundServ.setContext(getActivity());

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play, container, false);
        Context context = getActivity();
        PlayView.initPlayView(rootView);
        Question.init(context);

        PlayView.optionList.setAdapter(Question.mOptionsList);

        QuizFetchTask qTask = new QuizFetchTask(context);
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

        public static ListView optionList;

        public static void initPlayView(View rootView)    {
            try {
                optionList = (ListView)rootView.findViewById(R.id.optionList);
            }
            catch (NullPointerException ne) {
                //Do Nothing.
            }
        }
    }
}
