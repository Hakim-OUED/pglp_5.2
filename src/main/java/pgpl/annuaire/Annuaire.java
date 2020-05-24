package pgpl.annuaire;

import java.io.Serializable;
import java.util.ArrayList;

public class Annuaire implements Serializable {

  /**
   * uid Serial.
   */
  private static final long serialVersionUID = 1L;
  private String intitule;
  private Contact noeud;

  public Annuaire(final String intitule, final Contact c) {
    this.intitule = intitule;
    this.noeud = c;
  }

  public String getIntitule() {
    return intitule;
  }

  public Contact getNoeud() {
    return noeud;
  }

  @Override
  public String toString() {
    return "Intitule annuaire : " + intitule;
  }

  public void hierarchicPrint() {
    System.out.println("Affichage hierarchique");
    System.out.println(noeud.hierarchic());
  }

  /**
   *methode d'affichage par groupe.
   */
  public void groupPrint() {
    ArrayList<Contact> list = new ArrayList<Contact>();
    ArrayList<Contact> groupe = new ArrayList<Contact>();
    groupe.add(noeud);
    Contact c;
    String chaine = "";
    while (!list.isEmpty() || !groupe.isEmpty()) {
      if (list.isEmpty()) {
        list.addAll(groupe);
        groupe.clear();
      }
      c = list.remove(0);
      chaine = chaine.concat(c.toString() + "\n");
      if (c instanceof Section) {
        int i = 1;
        IterateurHierarchique it = ((Section) c).iterator();
        while (it.hasNext()) {
          chaine = chaine.concat(i + "- ");
          Contact e = it.next();
          chaine = chaine.concat(e.toString() + "\n");
          if (e instanceof Section) {
            groupe.add(e);
          }
          i++;

        }
        chaine = chaine.concat("************\n");
      }
    }
    System.out.println("Affichage par groupe");
    System.out.println(chaine);
  }
}
