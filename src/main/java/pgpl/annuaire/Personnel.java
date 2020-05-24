package pgpl.annuaire;

import java.io.Serializable;
import java.time.LocalDate;

public class Personnel implements Contact, Serializable {
  /**
   * uid Serial.
   */
  private static final long serialVersionUID = 1L;

  public String getNom() {
    return nom;
  }

  private String nom;
  private String prenom;
  private String fonction;
  private LocalDate ddn;
  private Telephone telephones;

  Personnel(BuilederPersonnel builder) {
    this.nom = builder.nom;
    this.fonction = builder.fonction;
    this.telephones = new Telephone();
    this.telephones = builder.telephones;
    this.ddn = builder.ddn;
    this.prenom = builder.prenom;
  }

  @Override
  public void affiche() {
    System.out.println(this.nom + " " + this.telephones.toString());
  }

  @Override
  public String hierarchic() {
    return this.toString();
  }

  @Override
  public String toString() {
    return "Nom: " + this.prenom + " " + this.nom + " "
        + "Fonction: " + this.fonction + " "
        + this.telephones.toString();
  }

  public static class BuilederPersonnel {
    private String nom;
    private String fonction;
    //facultatif
    private String prenom = "";
    private LocalDate ddn = LocalDate.now();
    // au moins le numero pro est obligatoire
    private Telephone telephones;

    /**
     * Builder de Personnel.
     * @param nom nom du contact obligatoire
     * @param fonction Fonction du contact obligatoire
     * @param numPro numero professionel obligatoire
     */
    public BuilederPersonnel(String nom, String fonction, String numPro) {
      //obligatoire
      this.nom = nom;
      this.fonction = fonction;
      this.telephones = new Telephone(numPro);
    }

    public BuilederPersonnel prenom(String prenom) {
      this.prenom = prenom;
      return this;
    }

    public BuilederPersonnel addDdn(LocalDate ddn) {
      this.ddn = ddn;
      return this;
    }

    public BuilederPersonnel addTelephone(String type, String numero) {
      this.telephones.addNumber(type, numero);
      return this;
    }

    public pgpl.annuaire.Personnel build() {
      return new pgpl.annuaire.Personnel(this);
    }

  }
}
