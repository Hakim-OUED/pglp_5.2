package pgpl.dao;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pgpl.annuaire.Annuaire;

public class AnnuaireDao extends Dao<Annuaire> implements Serializable {

  private static final long serialVersionUID = 1L;
  private ArrayList<Annuaire> list;

  AnnuaireDao() {
    this.list = new ArrayList<Annuaire>();
  }

  @Override
  public Annuaire get(String intitule) throws SQLException, IOException, ClassNotFoundException {
    psSelect = conn.prepareStatement(SQL_SELECT_OBJECT);
    psSelect.setString(1, intitule);
    ResultSet rs = psSelect.executeQuery();
    rs.next();
    byte[] b = rs.getBytes(2);
    ByteArrayInputStream is = new ByteArrayInputStream(b);
    ObjectInputStream ois = new ObjectInputStream(is);
    Annuaire annuaire = null;
    annuaire = (Annuaire) ois.readObject();
    rs.close();
    is.close();
    ois.close();
    psSelect.close();
    return annuaire;
  }

  @Override
  public List<Annuaire> getAll() {
    return (ArrayList<Annuaire>) list.clone();
  }

  @Override
  public String create(Annuaire annuaire) throws SQLException, IOException {
    psInsert = conn.prepareStatement(SQL_INSERT_OBJECT);
    statements.add(psInsert);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(annuaire);
    byte[] b = out.toByteArray();
    ByteArrayInputStream objectIn = new ByteArrayInputStream(b);
    psInsert.setString(1, annuaire.getIntitule());
    psInsert.setBinaryStream(2, objectIn, b.length);
    psInsert.executeUpdate();
    objectIn.close();
    os.flush();
    os.close();
    out.reset();
    out.close();
    return annuaire.getIntitule() + " a été bien sauvegardé";

  }

  @Override
  public void update(Annuaire annuaire, String[] params) {

  }

  @Override
  public void delete(Annuaire annuaire) throws SQLException {
    psUpdate = conn.prepareStatement(
        SQL_DELETE_OBJECT);
    psUpdate.setString(1, annuaire.getIntitule());
    psUpdate.executeUpdate();
  }

  /**
   * methode de Serialisation d'annuaire.
   * @param annuaire l'annuaire à sérialiser
   */
  public void serialisation(Annuaire annuaire) {
    String chemin = annuaire.getIntitule() + ".ser";
    ObjectOutputStream writer = null;
    try {
      FileOutputStream file = new FileOutputStream(chemin);
      writer = new ObjectOutputStream(file);
      writer.writeObject(annuaire);
      writer.flush();
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Methode de désérialisation d'annuaire.
   * @param chemin le chemin du fichier avec l'extension
   * @return l'objet correspondant
   */
  public Annuaire deserialisation(String chemin) {
    ObjectInputStream reader = null;
    Annuaire a = null;

    try {
      FileInputStream file = new FileInputStream(chemin);
      reader = new ObjectInputStream(file);
      a = (Annuaire) reader.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      System.out.println("Le fichier est introuvable");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return a;
  }
}
