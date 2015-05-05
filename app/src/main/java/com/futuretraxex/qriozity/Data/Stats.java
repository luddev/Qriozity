package com.futuretraxex.qriozity.Data;

import io.realm.RealmObject;

/**
 * Created by lud on 5/4/2015.
 */
public class Stats extends RealmObject {

    private int mTotalAttempted = 0;
    private int mCorrect = 0;
    private int mWrong = 0;

    public int getmTotalAttempted() {
        return mTotalAttempted;
    }
    public int getmCorrect() {
        return mCorrect;
    }
    public int getmWrong() {
        return mWrong;
    }

    public void setmTotalAttempted(int _total)    {
        mTotalAttempted = _total;
    }
    public void setmCorrect(int _correct)    {
        mCorrect = _correct;
    }
    public void setmWrong(int _wrong)    {
        mWrong = _wrong;
    }

}
