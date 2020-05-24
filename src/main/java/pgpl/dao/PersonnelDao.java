package pgpl.dao;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pgpl.annuaire.Personnel;

public class PersonnelDao extends Dao<Personnel>
    implements Serializable {


  private static final long serialVersionUID = 1L;

  private ArrayList<Personnel> personnels;

  PersonnelDao() {
    this.personnels = new ArrayList<Personnel>();
  }

  @Override
  public Personnel get(String intitule) throws SQLException, IOException, ClassNotFoundException {
    psSelect = conn.prepareStatement(SQL_SELECT_OBJECT);
    psSelect.setString(1, intitule);
    ResultSet rs = psSelect.executeQuery();
    rs.next();
    byte[] b = rs.getBytes(2);
    ByteArrayInputStream is = new ByteArrayInputStream(b);
    ObjectInputStream ois = new ObjectInputStream(is);
    Personnel personnel = null;
    personnel = (Personnel) ois.readObject();
    rs.close();
    is.close();
    ois.close();
    psSelect.close();
    return personnel;
  }

  @Override
  public List getAll() {
    return (ArrayList<Personnel>) personnels.clone();
  }

  @Override
  public String create(Personnel p) throws SQLException, IOException {
    psInsert = conn.prepareStatement(SQL_INSERT_OBJECT);
    statements.add(psInsert);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(p);
    byte[] b = out.toByteArray();
    ByteArrayInputStream objectIn = new ByteArrayInputStream(b);
    psInsert.setString(1, p.getNom());
    psInsert.setBinaryStream(2, objectIn, b.length);
    psInsert.executeUpdate();
    objectIn.close();
    os.flush();
    os.close();
    out.reset();
    out.close();
    return p.getNom() + " a été bien sauvegardé";

  }

  @Override
  public void update(Personnel personnel, String[] params) {

  }

  @Override
  public void delete(Personnel personnel) throws SQLException {
    psUpdate = conn.prepareStatement(
        SQL_DELETE_OBJECT);
    psUpdate.setString(1, personnel.getNom());
    psUpdate.executeUpdate();
  }

  /**
   * Methode de Sérialisation de Personnel.
   * @param personnel objet à serialiser
   */
  public void serialisation(Personnel personnel) {
    String chemin = personnel.getNom() + ".ser";
    ObjectOutputStream writer = null;
    try {
      FileOutputStream file = new FileOutputStream(chemin);
      writer = new ObjectOutputStream(file);
      writer.writeObject(personnel);
      writer.flush();
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Methode de désérialisation de Personnel.
   * @param chemin chemin du fichier avec l'extension
   * @return l'objet correspondant
   */
  public Personnel deserialisation(String chemin) {
    ObjectInputStream reader = null;
    Personnel p = null;

    try {
      FileInputStream file = new FileInputStream(chemin);
      reader = new ObjectInputStream(file);
      p = (Personnel) reader.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      System.out.println("le fichier introuvable");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return p;
  }

}
