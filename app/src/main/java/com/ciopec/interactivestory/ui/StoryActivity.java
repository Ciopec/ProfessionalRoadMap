package com.ciopec.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciopec.interactivestory.R;
import com.ciopec.interactivestory.model.Page;
import com.ciopec.interactivestory.model.Story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack<Integer>();
    private List<String> choices = new ArrayList<String>();
    private List<String> choices2 = Arrays.asList("Lawyer","Judge","Singer","Producer","Surgeon","Researcher","Software Engineer","Hardware Engineer");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));

        if(name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        story = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);

        final Page page = story.getPage(pageNumber);

        Drawable image;

        String pageText;
        pageText = getString(page.getTextId());

        if (page.isFinalPage()) {
            image = ContextCompat.getDrawable(this, page.getImageId() + choices2.indexOf(choices.get(choices.size()-1)));
            storyImageView.setImageDrawable(image);
            pageText = String.format(pageText, name, choices.get(choices.size()-3), choices.get(choices.size()-2), choices.get(choices.size()-1));
            storyTextView.setText(pageText);
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pageStack.clear();
                    loadPage(0);
                }
            });
        }
        else {

            image = ContextCompat.getDrawable(this, page.getImageId());
            storyImageView.setImageDrawable(image);
            pageText = String.format(pageText, name);
            storyTextView.setText(pageText);
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                choices.add((String) choice1Button.getText());

                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());

        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                choices.add((String) choice2Button.getText());
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if (pageStack.isEmpty()) {
            super.onBackPressed();
        } else {
            choices.remove(choices.size() - 1);
            loadPage(pageStack.pop());
        }
    }
}

