package com.ericrgon.schematicexample.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import com.ericrgon.schematicexample.R;
import com.ericrgon.schematicexample.database.RepoProvider;
import com.ericrgon.schematicexample.service.RepoSyncService;

public class RepoActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

  private ListView listView;
  private CursorAdapter repoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo);
    listView = (ListView) findViewById(android.R.id.list);
    getLoaderManager().initLoader(0,null,this);
  }

  @Override protected void onResume() {
    super.onResume();
    startService(new Intent(this, RepoSyncService.class));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_repo, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    return new CursorLoader(this, RepoProvider.Repos.CONTENT_URI,null,null,null,null);
  }

  @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    if(repoAdapter == null){
      repoAdapter = new RepoAdapter(this,data,0);
      listView.setAdapter(repoAdapter);
    } else {
      repoAdapter.changeCursor(data);
    }
  }

  @Override public void onLoaderReset(Loader<Cursor> loader) {
    if(repoAdapter != null){
      repoAdapter.changeCursor(null);
    }
  }
}
