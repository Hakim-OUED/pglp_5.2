package pgpl.dao;


import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pgpl.annuaire.Section;

public class SectionDao extends Dao<Section>
    implements Serializable {

  private static final long serialVersionUID = 1L;
  private ArrayList<Section> listSection;

  SectionDao() {
    listSection = new ArrayList<Section>();
  }

  @Override
  public Section get(String intitule) throws SQLException, IOException, ClassNotFoundException {
    psSelect = conn.prepareStatement(SQL_SELECT_OBJECT);
    psSelect.setString(1, intitule);
    ResultSet rs = psSelect.executeQuery();
    rs.next();
    byte[] b = rs.getBytes(2);
    ByteArrayInputStream is = new ByteArrayInputStream(b);
    ObjectInputStream ois = new ObjectInputStream(is);
    Section section = null;
    section = (Section) ois.readObject();
    rs.close();
    is.close();
    ois.close();
    psSelect.close();
    return section;
    }

  @Override
  public List<Section> getAll() {
    return (ArrayList<Section>) listSection.clone();
  }

  @Override
  public String create(Section section) throws SQLException, IOException {
    psInsert = conn.prepareStatement(SQL_INSERT_OBJECT);
    statements.add(psInsert);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(section);
    byte[] b = out.toByteArray();
    ByteArrayInputStream objectIn = new ByteArrayInputStream(b);
    psInsert.setString(1, section.getNomSection());
    psInsert.setBinaryStream(2, objectIn, b.length);
    psInsert.executeUpdate();
    objectIn.close();
    os.flush();
    os.close();
    out.reset();
    out.close();
    return section.getNomSection() + " a été bien sauvegardé";
  }

  @Override
  public void update(Section section, String[] params) {
      }

  @Override
  public void delete(Section section) throws SQLException {
    psUpdate = conn.prepareStatement(
        SQL_DELETE_OBJECT);
    psUpdate.setString(1, section.getNomSection());
    psUpdate.executeUpdate();
  }

  /**
   * Mérhode de sérialiisation de Section (groupe).
   * @param section la section à sérialiser
   */
  public void serialisation(Section section) {
    String chemin = section.getNomSection() + ".ser";
    ObjectOutputStream writer = null;
    try {
      FileOutputStream file = new FileOutputStream(chemin);
      writer = new ObjectOutputStream(file);
      writer.writeObject(section);
      writer.flush();
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Mérhode de désérialiisation de Section (groupe).
   * @param chemin le chemin du fichier avec lextension
   * @return l'objet correspondant
   */
  public Section deserialisation(String chemin) {
    ObjectInputStream reader = null;
    Section s = null;

    try {
      FileInputStream file = new FileInputStream(chemin);
      reader = new ObjectInputStream(file);
      s = (Section) reader.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      System.out.println("le fichier introuvable");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s;
  }
}
