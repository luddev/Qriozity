package com.futuretraxex.qriozity.Data;

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

    public static void setQuestion(long id, String question, String option1, String option2, String option3, String option4, int answer, String datePublished)    {
        mId = id;
        mQuestion = question;
        mOption1 = option1;
        mOption2 = option2;
        mOption3 = option3;
        mOption4 = option4;
        mAnswer = answer;
        mDatePublished = datePublished;
    }

    public static boolean verifyChoice(int choice) {
        return choice == mAnswer ? true : false;
    }

}
