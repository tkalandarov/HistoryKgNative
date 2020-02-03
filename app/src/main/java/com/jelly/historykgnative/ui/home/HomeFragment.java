package com.jelly.historykgnative.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jelly.historykgnative.Core.DatesListAdapter;
import com.jelly.historykgnative.DateDetailActivity;
import com.jelly.historykgnative.Models.DateModel;
import com.jelly.historykgnative.R;

public class HomeFragment extends Fragment
{

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListView datesListView = view.findViewById(R.id.datesListView);
        final DatesListAdapter adapter = new DatesListAdapter(getActivity(), R.layout.listview_twocolumn,homeViewModel.DatesList);
        datesListView.setAdapter(adapter);

        datesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DateModel item = adapter.getItem(position);

                Intent intent = new Intent(getActivity(), DateDetailActivity.class);
                intent.putExtra("DateModel", item);

                //based on item add info to intent
                startActivity(intent);
            }
        });
        return view;
    }
}