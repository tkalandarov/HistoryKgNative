package com.jelly.historykgnative.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jelly.historykgnative.Core.App;
import com.jelly.historykgnative.DataAccess.AppDatabase;
import com.jelly.historykgnative.Models.DateModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel
{
    private AppDatabase db = App.getInstance().getDatabase();
    public ArrayList<DateModel> DatesList = new ArrayList();

    public HomeViewModel()
    {
        DatesList.addAll(db.datesDAO().getAll());
    }
}