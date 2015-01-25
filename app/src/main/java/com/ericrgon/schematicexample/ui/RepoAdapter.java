package com.ericrgon.schematicexample.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.ericrgon.schematicexample.database.RepoColumns;

/**
 * Created by ericrgon on 1/24/15.
 */
public class RepoAdapter extends CursorAdapter {

  public RepoAdapter(Context context, Cursor c, int flags) {
    super(context, c, flags);
  }

  @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
    View view =
        LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
    view.setTag(new ViewHolder(view));
    return view;
  }

  @Override public void bindView(View view, Context context, Cursor cursor) {
    ViewHolder viewHolder = (ViewHolder) view.getTag();
    final String name = cursor.getString(cursor.getColumnIndex(RepoColumns.NAME));
    final String description = cursor.getString(cursor.getColumnIndex(RepoColumns.DESCRIPTION));
    viewHolder.name.setText(name);
    viewHolder.description.setText(description);
  }

  private class ViewHolder {
    TextView name;
    TextView description;

    public ViewHolder(View view) {
      name = (TextView) view.findViewById(android.R.id.text1);
      description = (TextView) view.findViewById(android.R.id.text2);
    }
  }
}
