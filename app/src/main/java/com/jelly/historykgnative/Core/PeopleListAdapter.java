package com.jelly.historykgnative.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.jelly.historykgnative.Models.PersonModel;
import com.jelly.historykgnative.R;

import java.util.ArrayList;

public class PeopleListAdapter extends ArrayAdapter<PersonModel> implements Filterable
{
    private Context mContext;
    private int resourceLayout;
    private LayoutInflater mInflater;
    ArrayList<PersonModel> originalData;
    ArrayList<PersonModel> filteredData;
    private PeopleListAdapter.PeopleFilter mFilter = new PeopleListAdapter.PeopleFilter();

    public PeopleListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PersonModel> objects)
    {
        super(context, resource, objects);
        this.mContext = context;
        this.resourceLayout = resource;
        mInflater = LayoutInflater.from(context);

        this.originalData = objects;
        this.filteredData = objects;
    }

    public int getCount()
    {
        return filteredData.size();
    }
    public PersonModel getItem(int position)
    {
        return filteredData.get(position);
    }
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public Filter getFilter()
    {
        return mFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        RecyclerView.ViewHolder holder;

        if (convertView == null)
            convertView = mInflater.inflate(resourceLayout, null);

        PersonModel person = getItem(position);

        if (person != null)
        {
            TextView lifespanColumn = convertView.findViewById(R.id.leftColumnText);
            TextView nameColumn = convertView.findViewById(R.id.rightColumnText);

            if (lifespanColumn != null)
            {
                lifespanColumn.setText(person.YearsOfLife);
            }
            if (nameColumn != null)
            {
                nameColumn.setText(person.Name);
            }

        }

        return convertView;
    }

    private class PeopleFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            final ArrayList<PersonModel> list = originalData;

            int count = list.size();
            final ArrayList<PersonModel> nlist = new ArrayList<PersonModel>(count);

            for (PersonModel person : originalData)
            {
                if (person.YearsOfLife.toLowerCase().contains(filterString) || person.Name.toLowerCase().contains(filterString))
                    nlist.add(person);
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            filteredData = (ArrayList<PersonModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
