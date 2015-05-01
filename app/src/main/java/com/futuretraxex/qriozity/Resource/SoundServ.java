package com.futuretraxex.qriozity.Resource;

import android.content.Context;
import android.media.MediaPlayer;

import com.futuretraxex.qriozity.R;

/**
 * Created by lud on 5/1/2015.
 */
public class SoundServ {


    public static final int SOUND_CORRECT = R.raw.menuselect;
    public static final int SOUND_INCORRECT = R.raw.neagtivebeeps;

    public static Context self;

    static MediaPlayer correctMp;
    static MediaPlayer incorrectMp;
    public static void setContext(Context c)    {
        self = c;
        correctMp = MediaPlayer.create(SoundServ.self,SoundServ.SOUND_CORRECT);
        incorrectMp = MediaPlayer.create(SoundServ.self,SoundServ.SOUND_INCORRECT);
    }

    public static void playMusic(boolean isCorrect)    {
        MediaPlayer mp;
        if(isCorrect)   {
            correctMp.start();

        }
        else {
            incorrectMp.start();
        }

    }

}
