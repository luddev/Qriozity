package com.futuretraxex.qriozity.Data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by lud on 5/4/2015.
 */
public class StatsPersistence {
    public static Context mContext;
    public static Stats mStat;
    public static Realm mRealm;

    public static void initStatsDB(Context _context){

        mContext = _context;
        mRealm = Realm.getInstance(_context);
        mRealm.beginTransaction();
        if(StatsPersistence.getData().isEmpty() == true)  {
            mStat = mRealm.createObject(Stats.class);
        }
        else {
            mStat = StatsPersistence.getData().get(0);
        }
        mRealm.commitTransaction();
    }

    public static void beginTransaction()   {
        mRealm.beginTransaction();
    }
    public static void commitTransaction()   {
        mRealm.commitTransaction();
    }

    public static RealmResults<Stats> getData()    {
        return mRealm.where(Stats.class)
               .findAll();
    }
}
