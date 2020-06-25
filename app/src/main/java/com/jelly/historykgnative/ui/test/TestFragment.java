package com.jelly.historykgnative.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jelly.historykgnative.Core.App;
import com.jelly.historykgnative.Models.QuestionModel;
import com.jelly.historykgnative.Models.QuizModel;
import com.jelly.historykgnative.Activities.QuestionActivity;
import com.jelly.historykgnative.R;

import java.util.List;

public class TestFragment extends Fragment
{
    private RadioGroup radios;
    private RadioButton selectedRadio;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_test, container, false);

        Button startTestButton = view.findViewById(R.id.startTestButton);
        startTestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnStartQuizClick(v);
            }
        });

        radios = view.findViewById(R.id.questionRadios);
        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                selectedRadio = view.findViewById(checkedId);
            }
        });

        radios.check(R.id.radio1);
        return view;
    }

    public void OnStartQuizClick(View view)
    {
        String radioText = selectedRadio.getText().toString();

        List<QuestionModel> questions = App.getInstance().getDatabase().questionsDAO().getAll();
        QuizModel quizModel = new QuizModel(questions,Integer.parseInt(radioText));

        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("QuizModel", quizModel);

        //based on item add info to intent
        startActivity(intent);
    }
}