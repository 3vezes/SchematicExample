package com.ericrgon.schematicexample.rest;

public class Repo {
  public String name;
  public String description;

  @Override public String toString() {
    return "Repo{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
