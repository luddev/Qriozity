package com.futuretraxex.qriozity;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.futuretraxex.qriozity.BackendService.QuizFetchTask;
import com.futuretraxex.qriozity.Data.Question;
import com.futuretraxex.qriozity.Data.QuestionList;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play, container, false);
        PlayView.initPlayView(rootView);
        Question.init(getActivity());


        PlayView.optionList.setAdapter(Question.mOptionsList);
        SoundServ.setContext(getActivity());
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
