package pgpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public abstract class InitBdd {

  static final String nomBd = "annuaire";
  static Connection conn = null;
  static ArrayList<Statement> statements = new ArrayList<Statement>();
  static Statement s;

  /**
   * crée et retourne une connection.
   *
   * @return la  connection créé
   */
  public static Connection getConn() {
    Properties props = new Properties(); // connection properties
    props.put("user", "user");
    props.put("password", "user");
    try {
      conn = DriverManager.getConnection("jdbc:derby:" + nomBd
          + ";create=true", props);
      s = conn.createStatement();
      statements.add(s);

    } catch (SQLException e) {
      System.out.println("Erreur de connexion à la Base de donnée");
      System.out.println(e.getMessage());
    }
    return conn;
  }

  /**
   * Créé les tables.
   */
  public static void createTables() throws SQLException {
    try {
      s.execute("create table annuaire(id varchar(20),"
          + " objet BLOB,"
          + "PRIMARY KEY (id))");
    } catch (SQLException e) {
      if (Helper.tableExist(e)) {
        return;
      } else {
        throw e;
      }
    }


    //s.execute("drop table dessin");
  }

}
