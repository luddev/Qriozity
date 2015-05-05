package com.futuretraxex.qriozity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
        RealmResults<Stats> stat = StatsPersistence.mRealm.where(Stats.class)
                .findAll();
        Toast.makeText(getActivity(),stat.toString(),Toast.LENGTH_SHORT);
        //ViewHolder.mTotal.setText();

        return view;
    }

    public static class ViewHolder {
        public static TextView mTotal;
        public static TextView mWrong;
        public static TextView mCorrect;
        public static Context mContext;

        public static void init(Context _context, View root)    {
            mContext = _context;
            mTotal = (TextView) root.findViewById(R.id.question_attempt);
            mWrong = (TextView) root.findViewById(R.id.question_wrong);
            mCorrect = (TextView) root.findViewById(R.id.question_correct);
        }
    }
}
