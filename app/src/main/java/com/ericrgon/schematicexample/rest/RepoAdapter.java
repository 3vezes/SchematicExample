package com.ericrgon.schematicexample.rest;

import retrofit.RestAdapter;

public class RepoAdapter {
  public static GithubRepos getAdapter() {
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(GithubRepos.SERVER)
        .build();
    return restAdapter.create(GithubRepos.class);
  }
}
