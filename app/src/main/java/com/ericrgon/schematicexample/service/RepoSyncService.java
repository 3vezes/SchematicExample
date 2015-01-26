package com.ericrgon.schematicexample.service;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;
import com.ericrgon.schematicexample.database.RepoColumns;
import com.ericrgon.schematicexample.database.RepoProvider;
import com.ericrgon.schematicexample.rest.GithubRepos;
import com.ericrgon.schematicexample.rest.Repo;
import com.ericrgon.schematicexample.rest.RepoAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit.RetrofitError;

public class RepoSyncService extends IntentService {

  public RepoSyncService() {
    super("Repo Sync Service");
  }

  @Override protected void onHandleIntent(Intent intent) {
    try {
      GithubRepos repos = RepoAdapter.getAdapter();
      List<Repo> repoList = repos.getUser("Eric-Gonzalez");

      ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>(repoList.size());
      batchOperations.add(ContentProviderOperation.newDelete(RepoProvider.Repos.CONTENT_URI).build());

      for(Repo repo : repoList){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(RepoProvider.Repos.CONTENT_URI);
        builder.withValue(RepoColumns.NAME, repo.name);
        builder.withValue(RepoColumns.DESCRIPTION, repo.description);
        batchOperations.add(builder.build());
      }

      getContentResolver().applyBatch(RepoProvider.AUTHORITY,batchOperations);
    } catch (RemoteException | OperationApplicationException | RetrofitError e) {
      Log.e(this.getClass().getSimpleName(),"Error when syncing repos",e);
    }
  }
}
