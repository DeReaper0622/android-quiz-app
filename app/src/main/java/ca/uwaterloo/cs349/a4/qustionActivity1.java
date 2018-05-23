package ca.uwaterloo.cs349.a4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.Observable;
import java.util.Observer;

public class qustionActivity1 extends AppCompatActivity implements Observer {
    Model mModel;

    TextView name;
    TextView question_text;
    TextView page_num;
    Button previous_button;
    Button next_button;
    ImageView flag;
    RadioButton option_A;
    RadioButton option_B;
    RadioButton option_C;
    RadioButton option_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qustion1);

        mModel = Model.getInstance();
        mModel.addObserver(this);

        //add the user id to the name text view
        String name_text = "Name: "+mModel.get_id();
        name = findViewById(R.id.name_text);
        name.setText(name_text.subSequence(0,name_text.length()));

        //set up the page text
        String page = mModel.getCurrent_ques()+"/"+mModel.get_ques_num();
        page_num = findViewById(R.id.current_text);
        page_num.setText(page.subSequence(0,page.length()));

        //get the radio button
        option_A = findViewById(R.id.optionA);
        option_B = findViewById(R.id.optionB);
        option_C = findViewById(R.id.optionC);
        option_D = findViewById(R.id.optionD);
        //

        //change the question according to current question
        if(mModel.getCurrent_ques()==1){//if it is the first question
            previous_button = findViewById(R.id.previous_button);
            previous_button.setEnabled(false);
            previous_button.setBackgroundColor(Color.rgb(210,212,216));//set background color to light grey
            previous_button.setTextColor(Color.rgb(119,121,124));
        }
        else if(mModel.getCurrent_ques()==3 || mModel.getCurrent_ques()==4){
            //set question text
            question_text=findViewById(R.id.ques_text);
            String question = "Q"+mModel.getCurrent_ques()+": Select the country that has this flag";
            question_text.setText(question.subSequence(0,question.length()));

            //set image view
            flag = findViewById(R.id.flag_image);

            String A_text;
            String B_text;
            String C_text;
            String D_text;

            if (mModel.getCurrent_ques()==3) {
                flag.setImageResource(R.drawable.image3);//change the flag image source
                A_text="Netherlands";
                option_A.setText(A_text.subSequence(0,A_text.length()));
                B_text="Taiwan";
                option_B.setText(B_text.subSequence(0,B_text.length()));
                C_text="China";
                option_C.setText(C_text.subSequence(0,C_text.length()));
                D_text="Slovakia";
                option_D.setText(D_text.subSequence(0,D_text.length()));
                //change the radio button text
            }
            else if(mModel.getCurrent_ques()==4){
                flag.setImageResource(R.drawable.image4);
                A_text="Canada";
                option_A.setText(A_text.subSequence(0,A_text.length()));
                B_text="India";
                option_B.setText(B_text.subSequence(0,B_text.length()));
                C_text="Brazil";
                option_C.setText(C_text.subSequence(0,C_text.length()));
                D_text="South Korea";
                option_D.setText(D_text.subSequence(0,D_text.length()));
            }
        }

        String selected_option = mModel.get_selected();
        if (selected_option=="A"){
            option_A.setChecked(true);
        }
        else if(selected_option=="B"){
            option_B.setChecked(true);
        }
        else if(selected_option=="C"){
            option_C.setChecked(true);
        }
        else if(selected_option=="D"){
            option_D.setChecked(true);
        }

        if (mModel.getCurrent_ques()==mModel.get_ques_num()){//if this is the last question
            next_button=findViewById(R.id.next_button);
            String finish = "Finish";
            next_button.setText(finish.subSequence(0,finish.length()));//change the button text to finish
        }
    }

    public void go_to_welcome(View view){// go to the welcome activity if clicked log out button
        mModel.reset();
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    public void go_to_next(View view){//go to next question
        if (mModel.get_ques_num()==mModel.getCurrent_ques()){//if this is the last question
            store_selection();
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
        else{//if there are more question to go
            store_selection();
            if (mModel.getCurrent_ques()==3) {
                Intent intent = new Intent(this, qustionActivity1.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this,mcActivity.class);
                startActivity(intent);
            }
            mModel.next_ques();
        }

    }

    public void go_to_previous(View view){
        store_selection();
        if (mModel.getCurrent_ques()==4) {
            Intent intent = new Intent(this, qustionActivity1.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this,mcActivity.class);
            startActivity(intent);
        }
        mModel.prev_ques();
    }

    public void store_selection(){
        RadioGroup ques_radio = findViewById(R.id.single_ques);
        int checkedid = ques_radio.getCheckedRadioButtonId();
        String answer;
        if (checkedid==-1){//nothing is selected
            answer = "None";
            mModel.store_answer(answer);
        }
        else if (checkedid==R.id.optionA){
            answer ="A";
            mModel.store_answer(answer);
        }
        else if (checkedid==R.id.optionB){
            answer ="B";
            mModel.store_answer(answer);
        }
        else if (checkedid==R.id.optionC){
            answer ="C";
            mModel.store_answer(answer);
        }
        else if (checkedid==R.id.optionD){
            answer ="D";
            mModel.store_answer(answer);
        }

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
