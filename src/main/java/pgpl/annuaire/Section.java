package pgpl.annuaire;

import java.io.Serializable;
import java.util.ArrayList;

public class Section implements Contact, Iterable, Serializable {

  /**
   * uid Serial.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Nom de la section, groupe.
   */
  private String nomSection;

  /**
   * Liste des éléments de la section.
   */
  private ArrayList<Contact> listeElement;

  /**
   * getter du nom.
   *
   * @return nom de la section
   */
  public String getNomSection() {
    return nomSection;
  }

  /**
   * getter des element de la section.
   *
   * @return liste d'elements de la section
   */
  public ArrayList<Contact> getListeElement() {
    return listeElement;
  }


  /**
   * Constructeur de la section.
   *
   * @param nomSection nom de la section ou groupe
   */
  public Section(String nomSection) {
    this.nomSection = nomSection;
    this.listeElement = new ArrayList<>();
  }

  /**
   * Affiche les element de ce groupe.
   */
  @Override
  public void affiche() {
    for (Contact contact : listeElement) {
      contact.affiche();
    }
  }

  /**
   * retourne dans l'ordre heirarchique les element de cette section/groupe.
   *
   * @return les éléments
   */
  @Override
  public String hierarchic() {
    String chaine = this.toString();
    IterateurHierarchique it = iterator();
    Contact c;
    while (it.hasNext()) {
      c = it.next();
      chaine = chaine.concat("\n");
      chaine = chaine.concat(c.hierarchic());
    }
    chaine = chaine.concat("\nFin " + this.toString());
    return chaine;
  }

  /**
   * methode pour ajouter un contact au groupe.
   *
   * @param contact le contact à ajouter peut etre de type Section ou Personnel
   */
  public void addContact(final Contact contact) {
    this.listeElement.add(contact);
  }

  /**
   * methode pour supprimezr un element de ce groupe.
   *
   * @param contact le contact à supprimer
   */
  public void deleteContact(Contact contact) {
    if (this.listeElement.contains(contact)) {
      this.listeElement.remove(contact);
    }

  }

  /**
   * methode pour générer un iterateur.
   *
   * @return un iterateur
   */
  @Override
  public IterateurHierarchique iterator() {
    return new IterateurHierarchique(this.listeElement);
  }

  /**
   * transforme en string la section.
   *
   * @return le nom de la section
   */
  @Override
  public String toString() {
    return ("Section: " + this.nomSection);
  }
}
