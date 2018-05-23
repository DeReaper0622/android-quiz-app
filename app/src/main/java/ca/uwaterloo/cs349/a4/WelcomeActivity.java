package ca.uwaterloo.cs349.a4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

public class WelcomeActivity extends AppCompatActivity implements Observer{

    Model mModel;
    private EditText name_text;
    private Button next_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mModel = Model.getInstance();
        mModel.addObserver(this);//add this as a observer to model

        name_text = findViewById(R.id.Name_editText);
        next_button = findViewById(R.id.Welcome_page_button);
        name_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()!=0){
                    next_button.setEnabled(true);
                    next_button.setBackgroundColor(Color.rgb(229,202,27));//set background color to orange
                    next_button.setTextColor(Color.WHITE);
                }
                else{
                    next_button.setEnabled(false);
                    next_button.setBackgroundColor(Color.rgb(210,212,216));//set background color to light gray
                    next_button.setTextColor(Color.rgb(119,121,124));
                }
            }
        });
    }

    public void go_to_next(View view){
        String name_str = name_text.getText().toString();
        mModel.setUserid(name_str);
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
        //do not have to do anything during update
    }
}
