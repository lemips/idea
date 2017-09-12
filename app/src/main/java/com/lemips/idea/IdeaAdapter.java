package com.lemips.idea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by desk86 on 9/13/2017.
 */

public class IdeaAdapter extends BaseAdapter {
    private List<Idea> ideaList;
    private LayoutInflater inflater;

    public IdeaAdapter(Context context, List<Idea> ideaList){
        this.inflater = LayoutInflater.from(context);
        this.ideaList = ideaList;
    }

    @Override
    public int getCount() {
        return ideaList.size();
    }

    @Override
    public Idea getItem(int position) {
        return ideaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.idea_standard, null);

        Idea idea = getItem(position);

        TextView textViewTitle = (TextView)view.findViewById(R.id.textView_title);
        TextView textViewContent = (TextView)view.findViewById(R.id.textView_content);

        textViewTitle.setText(idea.getTitle());
        textViewContent.setText(idea.getContent());

        return view;
    }
}
