package ca.uwaterloo.cs349.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class ResultActivity extends AppCompatActivity implements Observer {
    Model mModel;
    TextView name;
    TextView score_textview;

    TextView q2;
    TextView q2_answer;
    ImageView q2_image;
    TextView q3;
    TextView q3_answer;
    ImageView q3_image;
    TextView q4;
    TextView q4_answer;
    ImageView q4_image;
    TextView q5;
    TextView q5_answer;
    ImageView q5_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mModel = Model.getInstance();
        mModel.addObserver(this);

        //add the user id to the name text view
        String name_text = "Name: "+mModel.get_id();
        name = findViewById(R.id.name_text);
        name.setText(name_text.subSequence(0,name_text.length()));

        //add the user score
        int score=mModel.check_correct();
        String score_text = "Your Score: "+score+"/"+mModel.get_ques_num();
        score_textview=findViewById(R.id.Score);
        score_textview.setText(score_text.subSequence(0,score_text.length()));

        //get the textview and image view for q2 to q5
        q2=findViewById(R.id.q2);
        q2_answer=findViewById(R.id.q2_answer);
        q2_image=findViewById(R.id.q2_image);

        q3=findViewById(R.id.q3);
        q3_answer=findViewById(R.id.q3_answer);
        q3_image=findViewById(R.id.q3_image);

        q4=findViewById(R.id.q4);
        q4_answer=findViewById(R.id.q4_answer);
        q4_image=findViewById(R.id.q4_image);

        q5=findViewById(R.id.q5);
        q5_answer=findViewById(R.id.q5_answer);
        q5_image=findViewById(R.id.q5_image);

        //only show the answer for question asked
        if (mModel.get_ques_num()==2){
            q2.setVisibility(View.VISIBLE);
            q2_answer.setVisibility(View.VISIBLE);
            q2_image.setVisibility(View.VISIBLE);
        }
        else if(mModel.get_ques_num()==3){
            q2.setVisibility(View.VISIBLE);
            q2_answer.setVisibility(View.VISIBLE);
            q2_image.setVisibility(View.VISIBLE);
            q3.setVisibility(View.VISIBLE);
            q3_answer.setVisibility(View.VISIBLE);
            q3_image.setVisibility(View.VISIBLE);
        }
        else if(mModel.get_ques_num()==4){
            q2.setVisibility(View.VISIBLE);
            q2_answer.setVisibility(View.VISIBLE);
            q2_image.setVisibility(View.VISIBLE);
            q3.setVisibility(View.VISIBLE);
            q3_answer.setVisibility(View.VISIBLE);
            q3_image.setVisibility(View.VISIBLE);
            q4.setVisibility(View.VISIBLE);
            q4_answer.setVisibility(View.VISIBLE);
            q4_image.setVisibility(View.VISIBLE);
        }
        else if(mModel.get_ques_num()==5){
            q2.setVisibility(View.VISIBLE);
            q2_answer.setVisibility(View.VISIBLE);
            q2_image.setVisibility(View.VISIBLE);
            q3.setVisibility(View.VISIBLE);
            q3_answer.setVisibility(View.VISIBLE);
            q3_image.setVisibility(View.VISIBLE);
            q4.setVisibility(View.VISIBLE);
            q4_answer.setVisibility(View.VISIBLE);
            q4_image.setVisibility(View.VISIBLE);
            q5.setVisibility(View.VISIBLE);
            q5_answer.setVisibility(View.VISIBLE);
            q5_image.setVisibility(View.VISIBLE);
        }
    }

    public void go_to_welcome(View view){// go to the welcome activity if clicked log out button
        mModel.reset();
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    public void go_to_topic(View view){// go to topic selection
        mModel.reset();
        Intent intent = new Intent(this,topicActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
