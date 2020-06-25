package com.jelly.historykgnative.ui.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jelly.historykgnative.Activities.MainActivity;
import com.jelly.historykgnative.Core.PeopleListAdapter;
import com.jelly.historykgnative.Models.PersonModel;
import com.jelly.historykgnative.Activities.PersonDetailActivity;
import com.jelly.historykgnative.R;

public class PeopleFragment extends Fragment
{
    private PeopleViewModel peopleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        ListView peopleListView = view.findViewById(R.id.peopleListView);
        final PeopleListAdapter adapter = new PeopleListAdapter(getActivity(), R.layout.listview_twocolumn,peopleViewModel.PeopleList);
        peopleListView.setAdapter(adapter);

        peopleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                PersonModel item = adapter.getItem(position);

                Intent intent = new Intent(getActivity(), PersonDetailActivity.class);
                intent.putExtra("PersonModel", item);

                //based on item add info to intent
                startActivity(intent);
            }
        });
        return view;
    }
}