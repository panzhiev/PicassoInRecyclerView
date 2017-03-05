package com.tim.picassoinrecyclerview.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tim.picassoinrecyclerview.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tim on 22.01.2017.
 */

public class ViewHolderPerson extends RecyclerView.ViewHolder {

    public CircleImageView circleImageView;
    public TextView tvName;

    public ViewHolderPerson(View itemView) {
        super(itemView);
        this.tvName = (TextView) itemView.findViewById(R.id.text_view_item_short_name);
        this.circleImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
    }
}
