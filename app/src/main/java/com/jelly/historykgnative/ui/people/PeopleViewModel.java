package com.jelly.historykgnative.ui.people;

import androidx.lifecycle.ViewModel;

import com.jelly.historykgnative.Core.App;
import com.jelly.historykgnative.DataAccess.AppDatabase;
import com.jelly.historykgnative.Models.PersonModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PeopleViewModel extends ViewModel
{
    private AppDatabase db = App.getInstance().getDatabase();
    public ArrayList<PersonModel> PeopleList = new ArrayList();

    public PeopleViewModel()
    {
        PeopleList.addAll(db.peopleDAO().getAll());
        Collections.sort(PeopleList, new Comparator<PersonModel>()
        {
            @Override
            public int compare(PersonModel o1, PersonModel o2)
            {
                return Integer.compare(o1.YearPos, o2.YearPos);
            }
        });
    }
}