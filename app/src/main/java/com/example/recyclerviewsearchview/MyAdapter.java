package com.example.recyclerviewsearchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<ModelClass> arrayList;
    private ArrayList<ModelClass> tempList;
    private ArrayList<ModelClass> filteredList = new ArrayList<>();

    public MyAdapter(MainActivity context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        tempList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelClass modelClass = arrayList.get(position);
        holder.textView.setText(modelClass.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            String query = constraint.toString();

            if (query.isEmpty()) {
                //tempList ha apla backup ahe. jevha search query empty hoil tevha hya backup madhun
                //original list baher kadhaychi which is the Full proof list.
                results.values = tempList;
            } else {
                //filtered list la empty karaycha after each typed or deleted character and punha
                //filtered list loop laun populate karaychi as done below.
                filteredList.clear();

                for (ModelClass item : tempList) {
                    if (item.getName().toLowerCase().contains(query.toLowerCase().trim())) {
                        filteredList.add(item);
                    }
                }
                //finally arraylist ch publish hote, so filtered list arraylist madhe takaychi.
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList = (ArrayList<ModelClass>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.textView);
        }
    }
}
