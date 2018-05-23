package ca.uwaterloo.cs349.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class topicActivity extends AppCompatActivity implements Observer{
    Model mModel;
    TextView welcome;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mModel=Model.getInstance();
        mModel.addObserver(this);

        //add the user id to the welcome text view
        String welcome_text = "Welcome "+mModel.get_id();
        welcome = findViewById(R.id.TS_welcome);
        welcome.setText(welcome_text.subSequence(0,welcome_text.length()));

        //add the items for spinner
        spinner = findViewById(R.id.question_num);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    public void go_to_welcome(View view){// go to the welcome activity if clicked log out button
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    public void go_to_question(View view){//load questions
        String question_num = spinner.getSelectedItem().toString();
        int num =Integer.parseInt(question_num);
        mModel.set_question_num(num);

        Intent intent = new Intent(this,qustionActivity1.class);
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
