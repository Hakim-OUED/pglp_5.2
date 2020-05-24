package pgpl.annuaire;

import java.util.ArrayList;
import java.util.Iterator;

public class IterateurHierarchique implements Iterator {

  private ArrayList<Contact> listeElement;

  public IterateurHierarchique(ArrayList<Contact> contacts) {
    this.listeElement = new ArrayList<Contact>();
    this.listeElement.addAll(contacts);
  }

  @Override
  public Contact next() {
    return listeElement.remove(0);
  }

  @Override
  public boolean hasNext() {
    return !this.listeElement.isEmpty();
  }

}
