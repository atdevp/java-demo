// Copyright 2016-2018 Diffblue limited. All rights reserved.

package com.diffblue.javademo;

import com.diffblue.javademo.serveraccess.DatabaseDao;
import org.bson.Document;

public class UserAccess extends Search {

  private String currentUser;

  public String getCurrentUser() {
    return currentUser;
  }

  /**
   * Attempt to login the user.
   * @param username in plain text
   * @param password in encrypted form
   * @return true if logged in
   */
  public boolean loginUser(String username, String password) {

    if (username.length() == 0 || password.length() == 0) {
      return false;
    }

    Document searchFor = new Document("userName", username).append("password", password);
    DatabaseDao dao = DatabaseDao.getInstance();
    int matches = dao.getCountFromDb("users", searchFor);

    // If we get a record then the user is valid
    if (matches == 1) {
      currentUser = username;
      return true;
    }

    currentUser = null;
    return false;
  }

  public String search(int[] array, Integer code) {
    boolean exist = contains(array, code);
    if (exist) {
      return "Found";
    }
    else {
      return null;
    }
  }

}
