package pgpl;

import java.sql.SQLException;

public class Helper {

  /**
   * Analyse un exception de type tableExiste.
   *
   * @param e l'exception
   * @return booleen true si c'est une exception de type tableExiste
   */
  public static boolean tableExist(SQLException e) {
    boolean exist;
    if (e.getSQLState().equals("X0Y32")) {
      exist = true;
    } else {
      exist = false;
    }
    return exist;
  }

  /**
   * Verifie si l'exception est de type KeyExiste.
   * @param e
   * @return booleen
   */
  public static boolean keyExist(SQLException e) {
    boolean exist;
    if (e.getSQLState().equals("23505")) {
      exist = true;
    } else {
      exist = false;
    }
    return exist;
  }

}

