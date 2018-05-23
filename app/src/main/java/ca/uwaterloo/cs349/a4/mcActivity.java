package ca.uwaterloo.cs349.a4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class mcActivity extends AppCompatActivity implements Observer{
    Model mModel;

    TextView name;
    TextView question_text;
    TextView page_num;
    Button next_button;
    ImageView flag;

    CheckBox option_A;
    CheckBox option_B;
    CheckBox option_C;
    CheckBox option_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc);

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

        //get the checkbox
        option_A = findViewById(R.id.checkBoxA);
        option_B = findViewById(R.id.checkBoxB);
        option_C = findViewById(R.id.checkBoxC);
        option_D = findViewById(R.id.checkBoxD);

        if (mModel.getCurrent_ques()==5){//change the image and checkbox text if it is the fifth question
            //set question text
            question_text=findViewById(R.id.ques_text);
            String question = "Q"+mModel.getCurrent_ques()+": Select the countries that have these flags";
            question_text.setText(question.subSequence(0,question.length()));

            //set image view
            flag = findViewById(R.id.flag_image);
            flag.setImageResource(R.drawable.image5);//change the flag image source

            String A_text="Canada";
            String B_text="Taiwan";
            String C_text="South Africa";
            String D_text="United Kingdom";
            option_A.setText(A_text.subSequence(0,A_text.length()));
            option_B.setText(B_text.subSequence(0,B_text.length()));
            option_C.setText(C_text.subSequence(0,C_text.length()));
            option_D.setText(D_text.subSequence(0,D_text.length()));
            //change the checkbox text
        }
        String selected_option = mModel.get_selected();
        for(int i = 0;i<selected_option.length();i++){
            if (selected_option.charAt(i)=='A'){
                option_A.setChecked(true);
            }
            else if(selected_option.charAt(i)=='B'){
                option_B.setChecked(true);
            }
            else if (selected_option.charAt(i)=='C'){
                option_C.setChecked(true);
            }
            else if(selected_option.charAt(i)=='D') {
                option_D.setChecked(true);
            }
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
            mModel.next_ques();
            Intent intent = new Intent(this,qustionActivity1.class);
            startActivity(intent);
        }

    }

    public void go_to_previous(View view){
        store_selection();
        mModel.prev_ques();
        Intent intent = new Intent(this,qustionActivity1.class);
        startActivity(intent);
    }

    public void store_selection(){
        String answer="";
        if (option_A.isChecked()){
            answer=answer+"A";
        }
        if(option_B.isChecked()){
            answer=answer+"B";
        }
        if (option_C.isChecked()){
            answer=answer+"C";
        }
        if(option_D.isChecked()) {
            answer = answer + "D";
        }
        mModel.store_answer(answer);
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
