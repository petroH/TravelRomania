package com.example.petro.travelromania.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.GalleryImage;
import com.squareup.picasso.Picasso;

/**
 * Created by Petro on 05-Feb-17.
 */

public class GalleryImageAdapter extends ArrayAdapter<GalleryImage> {
    public GalleryImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if( convertView == null ) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.view_gallery_thumbnail, parent, false );
            holder.image = (ImageView) convertView.findViewById( R.id.image );
            convertView.setTag( holder );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with( getContext() ).load( getItem( position ).getThumbnail() ).into( holder.image );

        return convertView;
    }

    private class ViewHolder {
        ImageView image;
    }
}
