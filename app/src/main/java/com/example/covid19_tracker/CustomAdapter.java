package com.example.covid19_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<country_modal> {

    private Context context;
    private List<country_modal> country_modalList;
    private List<country_modal> country_modalListFiltered;

    public CustomAdapter( Context context, List<country_modal>country_modalList) {
        super(context, R.layout.list_item,country_modalList);

        this.context= context;
        this.country_modalList =country_modalList;

        this.country_modalListFiltered = country_modalList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);
        TextView tvcountry = view.findViewById(R.id.tvcountry);
        ImageView imgFlag = view.findViewById(R.id.imgFlag);


        tvcountry.setText(country_modalListFiltered.get(position).getCountry());

        Glide.with(context).load(country_modalListFiltered.get(position).getFlag()).into(imgFlag);
        return view;
    }

    @Override
    public int getCount() {
        return country_modalListFiltered.size();
    }

    @Nullable
    @Override
    public country_modal getItem(int position) {
        return country_modalListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }





    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length()==0) {
                    filterResults.count = country_modalList.size();
                    filterResults.values = country_modalList;
                }

                else {
                    List<country_modal> resultsModal = new ArrayList<>();
                    String searchstr = charSequence.toString().toLowerCase();

                    for (country_modal itemsModal: country_modalList) {
                        if (itemsModal.getCountry().toLowerCase().contains(searchstr)) {
                            resultsModal.add(itemsModal);
                        }

                        filterResults.count = resultsModal.size();
                        filterResults.values = resultsModal;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                country_modalListFiltered = (List<country_modal>) filterResults.values;
                Countries.country_modalList = (List<country_modal>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
