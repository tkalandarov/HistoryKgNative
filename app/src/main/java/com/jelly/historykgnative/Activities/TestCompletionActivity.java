package com.jelly.historykgnative.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.jelly.historykgnative.Models.QuizModel;
import com.jelly.historykgnative.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class TestCompletionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_completion_activity);

        QuizModel quizModel = (QuizModel) getIntent().getSerializableExtra("QuizModel");
        TextView titleText = findViewById(R.id.titleText);
        TextView resultText = findViewById(R.id.resultText);
        TextView commentText = findViewById(R.id.commentText);

        resultText.setText("Результат: " + quizModel.Rights + "/" + quizModel.TotalQuestionsNum);

        double rightsPercentage = (double) quizModel.Rights / quizModel.TotalQuestionsNum * 100;
        if (rightsPercentage < 30)
        {
            titleText.setText("Плохо");
            commentText.setText("Кто-то давно не наведывался в раздел со справками :)");
        } else if (rightsPercentage >= 30 && rightsPercentage < 60)
        {
            titleText.setText("Неплохо");
            commentText.setText("Еще есть к чему стремиться");
        } else if (rightsPercentage >= 60 && rightsPercentage < 80)
        {
            titleText.setText("Хорошо");
            commentText.setText("А сможешь лучше? Мы уверены, что да!");
        } else if (rightsPercentage >= 80 && rightsPercentage < 100)
        {
            titleText.setText("Отлично");
            commentText.setText("Серьезная заявка на успешную сдачу экзаменов!");
        } else if (rightsPercentage == 100)
        {
            titleText.setText("Великолепно!");
            commentText.setText("Вершина совершентсва!");
        }

        if (rightsPercentage < 60) return;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.parseColor("#00796B"), Color.parseColor("#FFEB3B"), Color.parseColor("#E91E63"))
                .setDirection(0.0, 359.0)
                .setSpeed(5f, 7f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(0, width, -50f, 0f)
                .streamFor(100, 1000L);
    }

    public void FinishTest(View view)
    {
        finish();
    }
}
