package com.ericrgon.schematicexample.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = RepoDatabase.VERSION)
public class RepoDatabase {
  public static final int VERSION = 1;

  @Table(RepoColumns.class) public static final String REPOS = "repos";
}
