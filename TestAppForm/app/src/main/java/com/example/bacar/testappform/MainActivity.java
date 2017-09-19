package com.example.bacar.testappform;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;

import com.heinrichreimersoftware.singleinputform.SingleInputFormActivity;
import com.heinrichreimersoftware.singleinputform.steps.Step;
import com.heinrichreimersoftware.singleinputform.steps.TextStep;

import java.util.ArrayList;
import java.util.List;

public abstract class MainActivity extends SingleInputFormActivity{
    private static final String DATA_KEY_EXAMPLE = "example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected List<Step> getSteps(Context context){
        List<Step> steps = new ArrayList<Step>();
        steps.add(new TextStep(
                context,
                DATA_KEY_EXAMPLE,
                InputType.TYPE_CLASS_TEXT,
                "Test 1",
                "Test 2",
                1)
        );
        //Add more steps here...
        return steps;
    }

    @Override
    protected void onFormFinished(Bundle data){
        //Get the form data
        String text = TextStep.text(data, DATA_KEY_EXAMPLE);
        //...
    }
}