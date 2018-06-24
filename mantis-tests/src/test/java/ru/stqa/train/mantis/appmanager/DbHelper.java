package ru.stqa.train.mantis.appmanager;

import ru.stqa.train.mantis.model.UserData;
import ru.stqa.train.mantis.model.Users;

import java.sql.*;

public class DbHelper {

  public Users getUserListFromDB() {
    Connection conn = null;
    Users users = new Users();
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table where username != 'administrator'");
      //Users users = new Users();
      while (rs.next()) {
        users.add(new UserData().withId(rs.getInt("id")).withUserName(rs.getString("username"))
        .withMail(rs.getString("email")));
      }
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return users;
  }
}
