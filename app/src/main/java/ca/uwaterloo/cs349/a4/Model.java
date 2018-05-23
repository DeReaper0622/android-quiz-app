package ca.uwaterloo.cs349.a4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/**
 * Created by George on 3/22/2018.
 */

class Model extends Observable
{
    // Create static instance of this mModel
    private static final Model ourInstance = new Model();
    static Model getInstance()
    {
        return ourInstance;
    }

    // Private Variables
    private String userid;
    private int question_num;
    private int current_ques=1;
    private ArrayList<String> selection = new ArrayList<String>(5);
    private ArrayList<String> correct = new ArrayList<String>(5);

    /**
     * Model Constructor:
     * - Init member variables
     */
    Model() {
    }


    public String get_id()
    {
        return userid;
    }


    public void setUserid(String id){
        this.userid = id;
    }

    public int get_ques_num(){return question_num;}

    public void set_question_num(int num){question_num = num;};

    public void next_ques(){
        current_ques++;
    }
    public void prev_ques(){
        current_ques--;
    }

    public int getCurrent_ques(){return current_ques;}

    public void store_answer(String ans){
        int index = current_ques -1;
        if (selection.size()<current_ques) {
            selection.add(index, ans);
        }
        else{
            selection.set(index,ans);
        }
    }

    public String get_selected(){
        int index = current_ques-1;
        if (selection.size()<current_ques){
            return "";
        }
        else {
            return selection.get(index);
        }
    }

    public void reset(){
        current_ques=1;
        selection.clear();
    }

    public void create_correct(){
        correct.clear();
        correct.add("A");
        correct.add("AC");
        correct.add("C");
        correct.add("D");
        correct.add("CD");
    }
    public int check_correct(){
        int score = 0;
        create_correct();
        for (int i =0;i<question_num;i++){
            if (selection.get(i).equals(correct.get(i))){
                score++;
            }
        }
        return score;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observable Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}