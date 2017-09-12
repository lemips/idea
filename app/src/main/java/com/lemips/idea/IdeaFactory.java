package com.lemips.idea;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desk86 on 9/13/2017.
 */

public class IdeaFactory {

    public IdeaFactory(Context context){

    }

    public void add(Idea idea){

    }

    public void update(Idea idea){

    }

    public void delete(Idea idea){

    }

    public List<Idea> getAllIdea(){
        List<Idea> ideaList = new ArrayList<>();
        String[] numbers = new String[]{"One","Two","Three","Four", "Five"};

        for(String number : numbers)
            ideaList.add(new Idea("Idea : " + number,"Content Of Idea " + number));

        return ideaList;
    }
}
