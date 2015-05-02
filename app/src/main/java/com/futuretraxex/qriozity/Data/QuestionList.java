package com.futuretraxex.qriozity.Data;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.futuretraxex.qriozity.R;

import org.w3c.dom.Text;

/**
 * Created by lud on 5/2/2015.
 */
public class QuestionList extends BaseAdapter {
    Context mContext;
    public QuestionList(Context _context)   {
        mContext = _context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        switch(i)   {
            case 0:
                return Question.mQuestion;
            case 1:
                return Question.mOption1;
            case 2:
                return Question.mOption2;
            case 3:
                return Question.mOption3;
            case 4:
                return Question.mOption4;
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View tView;
        if(i == 0)  {
            tView = LayoutInflater.from(mContext).inflate(R.layout.question_question,null);
            TextView question = (TextView) tView.findViewById(R.id.question);
            question.setText((String)getItem(0));
            return tView;
        }
        else {
            tView = LayoutInflater.from(mContext).inflate(R.layout.question_option,null);
            TextView option = (TextView) tView.findViewById(R.id.option);
            option.setText((String)getItem(i));
            return tView;
        }
    }

    public void updateResults()  {
        notifyDataSetChanged();
    }
}
