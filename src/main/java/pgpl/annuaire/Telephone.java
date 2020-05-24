package pgpl.annuaire;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Telephone implements Serializable {
  public Map<String, String> getNumeros() {
    return numeros;
  }

  private Map<String, String> numeros;

  Telephone() {
    numeros = new HashMap<String, String>();
  }

  Telephone(String pro) {
    numeros = new HashMap<String, String>();
    numeros.put("numéro pro", pro);
  }


  Telephone(String pro, String perso) {
    numeros = new HashMap<String, String>();
    numeros.put("numéro pro", pro);
    numeros.put("numéro perso", perso);
  }

  Telephone(String pro, String perso, String fixe) {
    numeros = new HashMap<String, String>();
    numeros.put("numéro pro", pro);
    numeros.put("numéro perso", perso);
    numeros.put("numéro fixe", fixe);
  }

  @Override
  public String toString() {
    return "Telephone{"
        + numeros + '}';
  }

  public void addNumber(String type, String num) {
    this.numeros.put(type, num);
  }
}
