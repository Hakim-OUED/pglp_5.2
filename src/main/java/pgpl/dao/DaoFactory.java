package pgpl.dao;

import pgpl.annuaire.Annuaire;
import pgpl.annuaire.Personnel;
import pgpl.annuaire.Section;

public class DaoFactory {
  private DaoFactory() {
  }

  public static Dao<Personnel> getPersonnelDao() {
    return new PersonnelDao();
  }

  public static Dao<Annuaire> getAnnuaireDao() {
    return new AnnuaireDao();
  }

  public static Dao<Section> getSectionDao() {
    return new SectionDao();
  }


}
