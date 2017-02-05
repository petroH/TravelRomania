package com.example.petro.travelromania.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Regions;

/**
 * Created by Petro on 05-Feb-17.
 */

public class RegionsAdaptor  extends ArrayAdapter<Regions> {

    public RegionsAdaptor(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if( convertView == null ) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.view_regions_list_item, parent, false );

            holder.name = (TextView) convertView.findViewById( R.id.name );
            holder.description = (TextView) convertView.findViewById( R.id.description );
            holder.thumbnail = (ImageView) convertView.findViewById( R.id.thumbnail );

            convertView.setTag( holder );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText( getItem( position ).getName() );
        holder.description.setText( getItem( position ).getDescription() );

        Picasso.with(getContext()).load( getItem( position ).getThumbnail() ).into( holder.thumbnail );

        return convertView;
    }

    class ViewHolder {
        ImageView thumbnail;
        TextView name;
        TextView description;
    }
}

