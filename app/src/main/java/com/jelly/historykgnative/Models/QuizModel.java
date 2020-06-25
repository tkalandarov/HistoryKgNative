package com.jelly.historykgnative.Models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizModel implements Serializable
{
    public int TotalQuestionsNum;
    public int CurrentQuestionNum;

    public List<Integer> AskedQuestionsIds;

    private ArrayList<QuestionModel> questionList = new ArrayList<>();

    public int Rights;

    public QuizModel(List<QuestionModel> questions, int totalQuestionsNum)
    {
        this.questionList.addAll(questions);

        Collections.shuffle(questionList);
        TotalQuestionsNum = totalQuestionsNum;
        CurrentQuestionNum = 1;
        AskedQuestionsIds = new ArrayList<>();
        Rights = 0;
    }

    public QuestionModel GetNextQuestion()
    {
        for (QuestionModel q : questionList)
        {
            if (!AskedQuestionsIds.contains(q.Id))
            {
                AskedQuestionsIds.add(q.Id);
                return q;
            }
        }
        return null;
    }
}
