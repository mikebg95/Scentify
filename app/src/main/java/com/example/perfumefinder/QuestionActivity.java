package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {

    // data structures for questions and answers
    ArrayList<Question> questions;
    HashMap<String, String> answers;

    // UI views & buttons
    TextView questionText;
    ImageView nextQuestion, previousQuestion, backHome;
    RadioButton answer1, answer2, answer3, answer4, answer5, answer6;
    RadioGroup options;
    TextView progressText;

    // keep track of questions
    int qNum;
    boolean lastQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // initialization
        answers = new HashMap<>();
        questions = new ArrayList<>();

        addQuestions(); // add questions

        // link variables to views
        questionText = findViewById(R.id.question_text);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer5 = findViewById(R.id.answer5);
        answer6 = findViewById(R.id.answer6);
        nextQuestion = findViewById(R.id.next_question);
        previousQuestion = findViewById(R.id.previous_question);
        backHome = findViewById(R.id.back_home);
        options = findViewById(R.id.options);
        progressText = findViewById(R.id.progress_text);

        // start with first question
        lastQuestion = false;
        qNum = 0;

        bindViews(qNum); // bind views to question and answer options

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // change clicked button
//        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                answer1.setBackgroundResource(R.drawable.answer_btn);
//                answer2.setBackgroundResource(R.drawable.answer_btn);
//                answer3.setBackgroundResource(R.drawable.answer_btn);
//                answer4.setBackgroundResource(R.drawable.answer_btn);
//                answer5.setBackgroundResource(R.drawable.answer_btn);
//                answer6.setBackgroundResource(R.drawable.answer_btn);
//
//                RadioButton buttonClicked = findViewById(checkedId);
//                buttonClicked.setBackgroundResource(R.drawable.answer_btn_clicked);
//            }
//
//        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer1.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer2.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer3.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer4.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer5.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        answer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChecks();
                answer6.setBackgroundResource(R.drawable.btn_clicked);
            }
        });

        // when previous question clicked, delete last answer and go back
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.remove(answers.get(answers.size() - 1));
                lastQuestion = false;
                qNum--;
                bindViews(qNum);
            }
        });

        // when clicked on "next"
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get clicked answer
                boolean clicked = getClickedAnswer();

                if (clicked) {
                    // if not last question, bind views for next question
                    if (!lastQuestion) {
                        qNum++;
                        bindViews(qNum);
                    }

                    // if last question, go to result activity
                    else {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("answers", answers);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public boolean getClickedAnswer() {
        // when no option has been chosen
        if (options.getCheckedRadioButtonId() == -1) {
            makeToast("Please select an answer");
            return false;
        }
        else {
            // get subject of question
            Question currentQuestion = questions.get(qNum);
            String questionSubject = currentQuestion.getSubject();

            // find out which option has been selected
            int id = options.indexOfChild(options.findViewById(options.getCheckedRadioButtonId()));
            RadioButton answerOption = (RadioButton) options.getChildAt(id);
            String answer = answerOption.getText().toString();

            answers.put(questionSubject, answer);

//            for (int i = 0; i < answers.size(); i++) {
//                makeToast(answers.get(i));
//            }
            return true;
        }
    }

    public void addQuestions() {
        Question q1 = new Question("season", "For which season do you need perfume?", new String[]{"Summer", "Fall", "Winter", "Spring", "All seasons", "Doesn't matter"});
        Question q2 = new Question("age", "For which age do you need perfume?", new String[]{"0-35 years old", "35+ years old", "All ages", "Doesn't matter"});

        questions.add(q1);
        questions.add(q2);
//        questions.add(q3);
    }

    public void bindViews(int qNum) {
        // set progress bar
        setProgressBar(qNum);

        // make all radio buttons visible (again)
        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);
        answer5.setVisibility(View.VISIBLE);
        answer6.setVisibility(View.VISIBLE);

        // uncheck radio buttons
        options.clearCheck();

        answer1.setBackgroundResource(R.drawable.btn_unclicked);
        answer2.setBackgroundResource(R.drawable.btn_unclicked);
        answer3.setBackgroundResource(R.drawable.btn_unclicked);
        answer4.setBackgroundResource(R.drawable.btn_unclicked);
        answer5.setBackgroundResource(R.drawable.btn_unclicked);
        answer6.setBackgroundResource(R.drawable.btn_unclicked);




        if (qNum == 0) {
            previousQuestion.setVisibility(View.INVISIBLE);
        }
        else {
            previousQuestion.setVisibility(View.VISIBLE);
        }

        // on last question, change "next" to "submit"
        if (qNum == questions.size() - 1) {
            lastQuestion = true;
        }

        // set question text
        Question q = questions.get(qNum);
        String qText = q.getQuestion();
        questionText.setText(qText);

        String[] answerOptions = q.getOptions();

        // make necessary radio buttons invisible
        if (answerOptions.length == 6) {
            answer1.setText(answerOptions[0]);
            answer2.setText(answerOptions[1]);
            answer3.setText(answerOptions[2]);
            answer4.setText(answerOptions[3]);
            answer5.setText(answerOptions[4]);
            answer6.setText(answerOptions[5]);
        }

        if (answerOptions.length == 5) {
            answer6.setVisibility(View.INVISIBLE);
            answer1.setText(answerOptions[0]);
            answer2.setText(answerOptions[1]);
            answer3.setText(answerOptions[2]);
            answer4.setText(answerOptions[3]);
            answer5.setText(answerOptions[4]);
        }
        if (answerOptions.length == 4) {
            answer6.setVisibility(View.INVISIBLE);
            answer5.setVisibility(View.INVISIBLE);
            answer1.setText(answerOptions[0]);
            answer2.setText(answerOptions[1]);
            answer3.setText(answerOptions[2]);
            answer4.setText(answerOptions[3]);
        }
        if (answerOptions.length == 3) {
            answer6.setVisibility(View.INVISIBLE);
            answer5.setVisibility(View.INVISIBLE);
            answer4.setVisibility(View.INVISIBLE);
            answer1.setText(answerOptions[0]);
            answer2.setText(answerOptions[1]);
            answer3.setText(answerOptions[2]);
        }
        if (answerOptions.length == 2) {
            answer6.setVisibility(View.INVISIBLE);
            answer5.setVisibility(View.INVISIBLE);
            answer4.setVisibility(View.INVISIBLE);
            answer3.setVisibility(View.INVISIBLE);
            answer1.setText(answerOptions[0]);
            answer2.setText(answerOptions[1]);
        }





    }

    public void setProgressBar(int qNum) {
        // set progress bar text
        int totalQ = questions.size();
        int currentQ = qNum + 1;
        progressText.setText(currentQ + "/" + totalQ);
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void clearChecks() {
        answer1.setBackgroundResource(R.drawable.btn_unclicked);
        answer2.setBackgroundResource(R.drawable.btn_unclicked);
        answer3.setBackgroundResource(R.drawable.btn_unclicked);
        answer4.setBackgroundResource(R.drawable.btn_unclicked);
        answer5.setBackgroundResource(R.drawable.btn_unclicked);
        answer6.setBackgroundResource(R.drawable.btn_unclicked);
    }
}