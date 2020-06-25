package com.jelly.historykgnative.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.jelly.historykgnative.Models.QuestionModel;
import com.jelly.historykgnative.Models.QuizModel;
import com.jelly.historykgnative.R;
import com.jelly.historykgnative.ui.dialogs.CloseTestDialogFragment;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity
{
    private QuizModel quizModel;
    private QuestionModel currentQuestion;

    private TextView progressText;
    private TextView questionText;
    private TextView rightsText;

    private RadioGroup radios;
    private RadioButton selectedRadio;
    private RadioButton rightAnswerRadio;

    private boolean answeredRight = false;
    public void setAnsweredRight(boolean value)
    {
        if(answeredRight == value)return;

        this.answeredRight = value;
        if(value == true) quizModel.Rights++;
    }

    private int greenColor = Color.parseColor("#7E9C6F");
    private int redColor = Color.parseColor("#DA391C");

    @Override
    public void onBackPressed()
    {
        OpenCloseTestDialog(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        quizModel = (QuizModel) getIntent().getSerializableExtra("QuizModel");
        currentQuestion = quizModel.GetNextQuestion();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        progressText = findViewById(R.id.progressText);
        questionText = findViewById(R.id.questionText);
        rightsText = findViewById(R.id.rightsText);
        radios = findViewById(R.id.answerRadios);

        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                selectedRadio = findViewById(checkedId);
                if(selectedRadio ==null) return;
                if(selectedRadio.getText().equals(currentQuestion.RightAnswer))setAnsweredRight(true);
                else setAnsweredRight(false);
                ShowResult();
            }
        });
        InitializeQuestionView();
    }

    public void OpenCloseTestDialog(View view)
    {
        CloseTestDialogFragment dialog = new CloseTestDialogFragment();
        dialog.show(getSupportFragmentManager(), "OpenCloseTestDialog");
    }

    public void LoadNextQuestion(View view)
    {
        if (quizModel.CurrentQuestionNum < quizModel.TotalQuestionsNum)
        {
            currentQuestion = quizModel.GetNextQuestion();
            quizModel.CurrentQuestionNum++;

            InitializeQuestionView();
        }
        else
        {
            Intent intent = new Intent(this, TestCompletionActivity.class);
            intent.putExtra("QuizModel", quizModel);
            startActivity(intent);

            finish();
        }
    }

    private void InitializeQuestionView()
    {
        ResetInput();
        questionText.setText(currentQuestion.QuestionText);
        rightsText.setText("Верных ответов: " + quizModel.Rights);

        ArrayList<String> options = new ArrayList<>(5);
        options.add(currentQuestion.RightAnswer);
        options.add(currentQuestion.WrongAnswer1);
        options.add(currentQuestion.WrongAnswer2);
        options.add(currentQuestion.WrongAnswer3);
        options.add(currentQuestion.WrongAnswer4);
        Collections.shuffle(options);


        for (int i = 0; i < options.size(); i++)
        {
            RadioButton child = (RadioButton) radios.getChildAt(i);
            String option = options.get(i);

            child.setText(option);
            if (option.equals(currentQuestion.RightAnswer))
                rightAnswerRadio = child;
        }
    }

    private void ShowResult()
    {
        if (!answeredRight)
            selectedRadio.setTextColor(redColor);
        rightAnswerRadio.setTextColor(greenColor);

        // disable further selection
        for (int i = 0; i < radios.getChildCount(); i++)
        {
            (radios.getChildAt(i)).setEnabled(false);
        }
    }

    private void ResetInput()
    {
        radios.clearCheck();

        String currentProgress = quizModel.CurrentQuestionNum + "/" + quizModel.TotalQuestionsNum;
        progressText.setText(currentProgress);

        for (int i = 0; i < radios.getChildCount(); i++)
        {
            RadioButton radio = (RadioButton) radios.getChildAt(i);
            radio.setEnabled(true);

            int normalColor = ResourcesCompat.getColor(getResources(), R.color.colorTextPrimary, null);
            radio.setTextColor(normalColor);
        }
    }
}
