package com.jelly.historykgnative.Models;

import java.util.ArrayList;
import java.util.List;

public class QuizModel
{
    public int TotalQuestionsNum;
    public int CurrentQuestionNum;

    public List<Integer> AskedQuestionsIds;

    public int Rights;

    public QuizModel(int totalQuestionsNum)
    {
        TotalQuestionsNum = totalQuestionsNum;
        CurrentQuestionNum = 1;
        AskedQuestionsIds = new ArrayList<>();
        Rights = 0;
    }
}
