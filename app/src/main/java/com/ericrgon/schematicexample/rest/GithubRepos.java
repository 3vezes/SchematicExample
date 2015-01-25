package com.ericrgon.schematicexample.rest;

import java.util.List;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GithubRepos {
  public static final String SERVER = "https://api.github.com";

  @GET("/users/{user}/repos")
  public List<Repo> getUser(@Path("user") String user);

}
