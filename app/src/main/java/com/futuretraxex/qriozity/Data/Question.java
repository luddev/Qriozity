package com.futuretraxex.qriozity.Data;

import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.futuretraxex.qriozity.BackendService.QuizFetchTask;
import com.futuretraxex.qriozity.PlayFragment;
import com.futuretraxex.qriozity.R;
import com.futuretraxex.qriozity.Resource.SoundServ;

import java.net.MalformedURLException;
import java.net.URL;

import io.realm.Realm;

/**
 * Created by lud on 4/28/2015.
 * As only one instance of question will be visible at a time , this instance is linked with UI.
 */
public class Question {
    public static long mId;
    public static String mQuestion;
    public static String mOption1;
    public static String mOption2;
    public static String mOption3;
    public static String mOption4;
    public static int mAnswer;
    public static String mDatePublished;
    public static QuestionList mOptionsList;



    public static void init(Context context)    {
        mOptionsList = new QuestionList(context);
        final Context lContext = context;
        PlayFragment.PlayView.optionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0)  {
                    if(Question.verifyChoice(i))    {
                        SoundServ.playMusic(true);
                        view.setBackgroundColor(lContext.getResources().getColor(R.color.green));
                        StatsPersistence.beginTransaction();
                        StatsPersistence.mStat.setmTotalAttempted(StatsPersistence.mStat.getmTotalAttempted() + 1);
                        StatsPersistence.mStat.setmCorrect(StatsPersistence.mStat.getmCorrect() + 1);
                        Log.w("Test", "Updated Stats : Total " + StatsPersistence.mStat.getmTotalAttempted());
                        Log.w("Test", "Updated Stats : Correct Answer" + StatsPersistence.mStat.getmCorrect());
                        StatsPersistence.commitTransaction();

//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                StatsPersistence.mRealm.executeTransaction(new Realm.Transaction() {
//                                    @Override
//                                    public void execute(Realm realm) {
//
//                                    }
//                                });
//
//
//
//                            }
//                        });
                    }
                    else {
                        SoundServ.playMusic(false);
                        showCorrect(i);
                        StatsPersistence.beginTransaction();
                        StatsPersistence.mStat.setmTotalAttempted(StatsPersistence.mStat.getmTotalAttempted() + 1);
                        StatsPersistence.mStat.setmWrong(StatsPersistence.mStat.getmWrong() + 1);
                        Log.w("Test", "Updated Stats : Total " + StatsPersistence.mStat.getmTotalAttempted());
                        Log.w("Test", "Updated Stats : Wrong Answer" + StatsPersistence.mStat.getmWrong());
                        StatsPersistence.commitTransaction();
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                StatsPersistence.mRealm.executeTransaction(new Realm.Transaction() {
//                                    @Override
//                                    public void execute(Realm realm) {
//
//                                    }
//                                });
//
//
//                            }
//                        });

                    }
                    fetchNextQuestion();

                }
                else {
                    //Do Nothing
                }

            }

            public void showCorrect(int position)   {
                //View view1 = Question.mOptionsList.getView(position,null,null);
                View view1 = PlayFragment.PlayView.optionList.getChildAt(position);
                view1.setBackgroundColor(lContext.getResources().getColor(R.color.red));
                view1 = PlayFragment.PlayView.optionList.getChildAt(position);
                View view2 = PlayFragment.PlayView.optionList.getChildAt(mAnswer);
                //View view2 = Question.mOptionsList.getView(mAnswer, null, null);
                view2.setBackgroundColor(lContext.getResources().getColor(R.color.green));
            }

            void fetchNextQuestion()    {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SoundServ.reset();
                        QuizFetchTask qTask = new QuizFetchTask();
                        try {
                            qTask.execute(new URL("http://192.168.1.3:3002/v1/getRandomQuestion"));
                        } catch (MalformedURLException mfExc) {

                        }

                    }
                }, 1250);
            }

        });

    }



    public static void setQuestion(long id, String question, String option1, String option2, String option3, String option4, int answer, String datePublished)    {
        mId = id;
        mQuestion = "Q. " + cleanHTML(question) ;
        mOption1 = "A. " + cleanHTML(option1);
        mOption2 = "B. " + cleanHTML(option2);
        mOption3 = "C. " + cleanHTML(option3);
        mOption4 = "D. " + cleanHTML(option4);
        mAnswer = answer;
        mDatePublished = datePublished;
    }

    public static boolean verifyChoice(int choice) {
        return choice == mAnswer ? true : false;
    }

    public static String cleanHTML(String tstr)  {
        return Html.fromHtml(tstr).toString()
                .replaceAll("<(.*?)\\>"," ")
                .replaceAll("<(.*?)\\\n"," ")
                .replaceFirst("(.*?)\\>", " ")
                .replaceAll("&nbsp;"," ")
                .replaceAll("&amp;"," ")
                .replaceAll(";"," ");
    }
}
