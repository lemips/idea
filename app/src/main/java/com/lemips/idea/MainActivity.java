package com.lemips.idea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/**
 * Created by desk86 on 9/13/2017.
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {
    private ListView listViewIdea;
    private IdeaAdapter adapter;
    private IdeaFactory factory;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        factory = new IdeaFactory(this);
        bindActivity();

        adapter = new IdeaAdapter(this, factory.getAllIdea());
        listViewIdea.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onActionAdd();}});
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setIdeaList(factory.getAllIdea());
        adapter.notifyDataSetChanged();
    }

    private void onActionAdd(){
        Intent intentIdea = new Intent(this, IdeaDetailActivity.class);
        startActivity(intentIdea);
    }

    private void bindActivity(){
        listViewIdea = (ListView)findViewById(R.id.listView_idea);
        fabAdd = (FloatingActionButton)findViewById(R.id.fav_newIdea);
    }
}
