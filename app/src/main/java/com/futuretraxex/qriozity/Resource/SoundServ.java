package com.futuretraxex.qriozity.Resource;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.futuretraxex.qriozity.R;

/**
 * Created by lud on 5/1/2015.
 */
public class SoundServ {


    public static final int SOUND_CORRECT = R.raw.menuselect;
    public static final int SOUND_INCORRECT = R.raw.neagtivebeeps;

    public static Context mContext;

    static MediaPlayer correctMp;
    static MediaPlayer incorrectMp;
    public static void setContext(Context c)    {
        mContext = c;
        correctMp = MediaPlayer.create(SoundServ.mContext,SoundServ.SOUND_CORRECT);
        incorrectMp = MediaPlayer.create(SoundServ.mContext,SoundServ.SOUND_INCORRECT);
    }

    public static void playMusic(boolean isCorrect)    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(isCorrect && settings.getBoolean("sound",true))   {
            correctMp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    correctMp.start();
                }
            });


        }
        else {
            incorrectMp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    incorrectMp.start();
                }
            });

        }

    }

    public static void reset()  {
        correctMp.reset();
        incorrectMp.reset();
    }

}
