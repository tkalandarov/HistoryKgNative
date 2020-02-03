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

import com.jelly.historykgnative.Models.DateModel;
import com.jelly.historykgnative.R;

import java.util.ArrayList;

public class DatesListAdapter extends ArrayAdapter<DateModel> implements Filterable
{
    private Context mContext;
    private int resourceLayout;
    private LayoutInflater mInflater;
    ArrayList<DateModel> originalData;
    ArrayList<DateModel> filteredData;
    private DatesFilter mFilter = new DatesFilter();

    public DatesListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DateModel> objects)
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
    public DateModel getItem(int position)
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

        DateModel date = getItem(position);

        if (date != null)
        {
            TextView dateColumn = (TextView) convertView.findViewById(R.id.leftColumnText);
            TextView eventColumn = (TextView) convertView.findViewById(R.id.rightColumnText);

            if (dateColumn != null)
            {
                dateColumn.setText(date.DateText);
            }
            if (eventColumn != null)
            {
                eventColumn.setText(date.EventName);
            }

        }

        return convertView;
    }

    private class DatesFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            final ArrayList<DateModel> list = originalData;

            int count = list.size();
            final ArrayList<DateModel> nlist = new ArrayList<DateModel>(count);

            for (DateModel date : originalData)
            {
                if (date.EventName.toLowerCase().contains(filterString) || date.DateText.toLowerCase().contains(filterString))
                    nlist.add(date);
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            filteredData = (ArrayList<DateModel>) results.values;
            notifyDataSetChanged();
        }
    }
}