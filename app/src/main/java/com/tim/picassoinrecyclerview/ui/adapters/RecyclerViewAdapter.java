package com.tim.picassoinrecyclerview.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tim.picassoinrecyclerview.model.Person;
import com.tim.picassoinrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderPerson> {

    private Context mContext;
    private List<Person> persons;

    public RecyclerViewAdapter(Context mContext, ArrayList persons) {
        this.mContext = mContext;
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolderPerson onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        ViewHolderPerson holder = new ViewHolderPerson(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderPerson holder, int position) {

        final Person person = (Person) persons.get(position);

        holder.tvName.setText(person.getmName());
        Picasso.with(holder.circleImageView.getContext())
                .load(person.getmPhoto())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.circleImageView);
    }

    public void resetData (ArrayList al)
    {
        persons = al;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

//    private class PersonFilter extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//            if (constraint == null || constraint.length() == 0) {
//                results.values = origPersonList;
//                results.count = origPersonList.size();
//            } else {
//                List<Person> newPersonList = new ArrayList<Person>();
//
//                for (Person p : persons) {
//                    if (p.getmName().toUpperCase().contains(constraint.toString().toUpperCase())) {
//                        newPersonList.add(p);
//                    }
//                }
//                results.values = newPersonList;
//                results.count = newPersonList.size();
//            }
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            if (results.count == 0) {
//                persons = (List<Person>) results.values;
//                notifyDataSetChanged();
//                Toast.makeText(mContext, "Совпадений не найдено", Toast.LENGTH_SHORT).show();
//            } else {
//                persons = (List<Person>) results.values;
//                notifyDataSetChanged();
//            }
//        }
//    }
//
//    @Override
//    public Filter getFilter() {
//        if (personFilter == null)
//            // creating new PersonFilter if not exists
//            personFilter = new PersonFilter();
//        return personFilter;
//    }
}
