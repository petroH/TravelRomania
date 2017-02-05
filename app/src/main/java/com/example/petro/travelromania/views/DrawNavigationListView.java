package com.example.petro.travelromania.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.petro.travelromania.adaptors.DrawerNavigationListAdapter;
import com.example.petro.travelromania.events.DrawerSelectionItemEvent;
import com.example.petro.travelromania.utils.EventBus;

/**
 * Created by Petro on 04-Feb-17.
 */

public class DrawNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawNavigationListView(Context context) {
        this(context, null);
    }

    public DrawNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter( getContext(), 0 );
        adapter.add( "Regions" );
        adapter.add( "Gallery" );
        adapter.add( "Maps" );

        setAdapter( adapter );

        setOnItemClickListener( this );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post( new DrawerSelectionItemEvent( (String) parent.getItemAtPosition( position ) ) );
    }
}
