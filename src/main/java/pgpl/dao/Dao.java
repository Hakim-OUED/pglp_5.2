package pgpl.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

  public static Connection conn = null;
  ArrayList<Statement> statements = new ArrayList<Statement>();
  PreparedStatement psInsert;
  PreparedStatement psUpdate;
  static PreparedStatement psSelect;
  ResultSet rs = null;

  static final String SQL_INSERT_OBJECT =
      "INSERT INTO annuaire(id, objet) VALUES (?,?)";

  static final String SQL_SELECT_OBJECT =
      "SELECT * FROM annuaire WHERE id = ?";

  static final String SQL_UPDATE_OBJECT =
      "UPDATE dessin set annuaire = ? WHERE id = ?";

  static final String SQL_DELETE_OBJECT =
      "DELETE FROM annuaire WHERE id = ?";

  abstract T get(String nom) throws SQLException, IOException, ClassNotFoundException;

  abstract List<T> getAll();

  abstract String create(T t) throws SQLException, IOException;

  abstract void update(T t, String[] params);

  abstract void delete(T t) throws SQLException;


}
