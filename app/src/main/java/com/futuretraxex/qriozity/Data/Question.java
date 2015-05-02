package com.futuretraxex.qriozity.Data;

import android.content.Context;
import android.text.Html;

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
