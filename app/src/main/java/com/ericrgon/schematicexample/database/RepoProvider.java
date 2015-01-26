package com.ericrgon.schematicexample.database;

import android.net.Uri;
import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = RepoProvider.AUTHORITY, database = RepoDatabase.class)
public class RepoProvider {
  public static final String AUTHORITY = "com.ericrgon.schematicexample.database.RepoProvider";

  interface Path {
    String REPOS = "repos";
  }

  @TableEndpoint(table = RepoDatabase.REPOS)
  public static class Repos {
    @ContentUri(path = Path.REPOS, type = "vnd.android.cursor.dir/list")
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/repos");
  }
}
