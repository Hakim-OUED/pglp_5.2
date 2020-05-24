package pgpl.annuaire;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class PersonnelTest {

  public Personnel init() {
    Personnel p = new Personnel.BuilederPersonnel("Hakim", "PDG", "0758841701").build();
    return p;
  }

  @Test
  public void hierarchicTest() {
    Personnel p = init();
    assertTrue(p.hierarchic().equals(p.toString()));
  }

  @Test
  public void testToStringTest() {
    Personnel p = init();
    String expected = "Nom:  Hakim "
        + "Fonction: PDG"
        + " Telephone{{numéro pro=0758841701}}";

    assertTrue(p.toString().equals(expected));
  }

  @Test
  public void serialisation() {
    String chemin = "personnel.ser";
    Personnel p = init();
    boolean test = false;
    ObjectOutputStream writer = null;
    try {
      FileOutputStream file = new FileOutputStream(chemin);
      writer = new ObjectOutputStream(file);

      writer.writeObject(p);
      writer.flush();
      writer.close();
      test = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue("Serialisation OK", test == true);
  }

  @Test
  public void deserialisation() {
    String chemin = "personnel.ser";
    ObjectInputStream reader = null;
    Personnel p = null;
    boolean test = false;
    try {
      FileInputStream file = new FileInputStream(chemin);
      reader = new ObjectInputStream(file);
      p = (Personnel) reader.readObject();
      test = true;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue("Deserialisation OK", test == true);

  }
}