package com.jelly.historykgnative.ui.dates;

import androidx.lifecycle.ViewModel;

import com.jelly.historykgnative.Core.App;
import com.jelly.historykgnative.DataAccess.AppDatabase;
import com.jelly.historykgnative.Models.DateModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DatesViewModel extends ViewModel
{
    private AppDatabase db = App.getInstance().getDatabase();
    public ArrayList<DateModel> DatesList = new ArrayList();

    public DatesViewModel()
    {
        DatesList.addAll(db.datesDAO().getAll());
        Collections.sort(DatesList, new Comparator<DateModel>()
        {
            @Override
            public int compare(DateModel o1, DateModel o2)
            {
                return Integer.compare(o1.DatePos, o2.DatePos);
            }
        });
    }
}