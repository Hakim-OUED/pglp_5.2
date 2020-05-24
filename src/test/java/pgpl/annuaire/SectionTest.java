package pgpl.annuaire;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class SectionTest {


  public Section init() {
    Personnel p = new Personnel.BuilederPersonnel("Hakim", "PDG", "0758841701").build();
    Section section = new Section("Direction");
    section.addContact(p);
    return section;
  }


  @Test
  public void serialisation() {
    String chemin = "section.ser";
    Section section = init();
    boolean test = false;
    ObjectOutputStream writer = null;
    try {
      FileOutputStream file = new FileOutputStream(chemin);
      writer = new ObjectOutputStream(file);

      writer.writeObject(section);
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
    String chemin = "section.ser";
    ObjectInputStream reader = null;
    Section section = null;
    boolean test = false;
    try {
      FileInputStream file = new FileInputStream(chemin);
      reader = new ObjectInputStream(file);
      section = (Section) reader.readObject();
      test = true;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue("Deserialisation OK", test == true);

  }

}