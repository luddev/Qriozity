package com.futuretraxex.qriozity.Data;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by lud on 5/4/2015.
 */
public class StatsPersistence {
    public static Context mContext;
    public static Stats mStat;
    public static Realm mRealm;

    public static void initStatsDB(Context _context){

        mContext = _context;
        Realm realm = Realm.getInstance(_context);
        mStat = mRealm.createObject(Stats.class);
    }

    public static void beginTransaction()   {
        mRealm.beginTransaction();
    }
    public static void commitTransaction()   {
        mRealm.commitTransaction();
    }
}
