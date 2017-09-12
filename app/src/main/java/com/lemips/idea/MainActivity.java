package com.lemips.idea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by desk86 on 9/13/2017.
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {
    private ListView listViewIdea;
    private IdeaAdapter adapter;
    private IdeaFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        factory = new IdeaFactory(this);
        adapter = new IdeaAdapter(this, factory.getAllIdea());
        listViewIdea.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    private void bindActivity(){
        listViewIdea = (ListView)findViewById(R.id.listView_idea);
    }
}
