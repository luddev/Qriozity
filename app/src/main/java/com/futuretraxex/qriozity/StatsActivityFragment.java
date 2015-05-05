package com.futuretraxex.qriozity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.futuretraxex.qriozity.Data.Stats;
import com.futuretraxex.qriozity.Data.StatsPersistence;

import io.realm.RealmResults;


/**
 * A placeholder fragment containing a simple view.
 */
public class StatsActivityFragment extends Fragment {

    public StatsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ViewHolder.init(getActivity(), view);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        try {

            RealmResults<Stats> stat = StatsPersistence.getData();
            for( Stats st : stat) {
                Log.w("Total", " " + st.getmTotalAttempted());
                Log.w("Correct", " " + st.getmCorrect());
                Log.w("Wrong", " " + st.getmWrong());
                ViewHolder.mUsername.setText(settings.getString("username","Cool User"));
                ViewHolder.mTotal.setText("Total : " + st.getmTotalAttempted());
                ViewHolder.mCorrect.setText("Correct : " + st.getmCorrect());
                ViewHolder.mWrong.setText("Wrong : " + st.getmWrong());
                if(st.getmTotalAttempted() != 0 )   {
                    ViewHolder.mAccuracy.setText("Accuracy : " + ((float)st.getmCorrect()/(float)st.getmTotalAttempted())*100.f+"%");
                }
                else {
                    ViewHolder.mAccuracy.setText("Accuracy : TBD");
                }

            }

        }
        catch(NullPointerException nExc) {
            Log.w("Test", "Null Pointer ? ");
        }
        catch(Exception exc)    {
            Log.w("Test", "Exc ? " + exc.toString());
        }


        //Toast.makeText(getActivity(),stat.toString(),Toast.LENGTH_SHORT);
        //ViewHolder.mTotal.setText();

        return view;
    }

    public static class ViewHolder {
        public static TextView mTotal;
        public static TextView mWrong;
        public static TextView mCorrect;
        public static TextView mAccuracy;
        public static TextView mUsername;
        public static Context mContext;

        public static void init(Context _context, View root)    {
            mContext = _context;
            mUsername = (TextView) root.findViewById(R.id.username);
            mTotal = (TextView) root.findViewById(R.id.question_attempt);
            mWrong = (TextView) root.findViewById(R.id.question_wrong);
            mCorrect = (TextView) root.findViewById(R.id.question_correct);
            mAccuracy = (TextView) root.findViewById(R.id.question_accuracy);
        }
    }
}
