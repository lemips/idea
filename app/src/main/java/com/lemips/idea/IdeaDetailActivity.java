package com.lemips.idea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class IdeaDetailActivity extends AppCompatActivity {
    public static final String KEY_IDEA_ID = "idea_id";

    private EditText editTextTitle;
    private EditText editTextContent;
    private IdeaFactory factory;

    private Idea idea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_detail);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        factory = new IdeaFactory(this);
        bindActivity();

        if (!hasIdea())
            idea = new Idea();

        editTextTitle.setText(idea.getTitle());
        editTextContent.setText(idea.getContent());

        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                idea.setTitle(charSequence.toString());
            }
        });
        editTextContent.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                idea.setContent(charSequence.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.idea_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean status = true;
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                saveIdea();
                break;
            default:
                status = super.onOptionsItemSelected(item);
        }
        return status;
    }

    private boolean hasIdea(){
        try{
            if (!getIntent().hasExtra(KEY_IDEA_ID))
                return false;
            int ideaId = getIntent().getIntExtra(KEY_IDEA_ID, -1);
            if (ideaId == -1) return false;
            idea = factory.get(ideaId);
            return true;
        }
        catch (Exception e){e.printStackTrace();}
        return false;
    }

    private void bindActivity(){
        editTextTitle = (EditText)findViewById(R.id.editText_title);
        editTextContent = (EditText)findViewById(R.id.editText_content);
    }

    private void saveIdea(){
        if (factory.get(idea.getId()) == null){
            factory.add(idea);
            finish();
            Toast.makeText(this, "Idea saved!", Toast.LENGTH_LONG).show();
        }
        else {
            factory.update(idea);
            finish();
            Toast.makeText(this, "Idea Updated!", Toast.LENGTH_LONG).show();
        }
    }
}
