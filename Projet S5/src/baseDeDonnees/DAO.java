/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;

/**
 * @author Damien
 *
 */

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * M�thode de cr�ation
  * @param obj
  * @return boolean 
  */
  public abstract void create(T obj);

  /**
  * M�thode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract void delete(T obj);

  /**
  * M�thode de mise � jour
  * @param obj
  * @return boolean
  */
  public abstract void update(T obj);

  /**
  * M�thode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T find(int id);
}
