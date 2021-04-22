/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/
package play;

 /**
  * Class defining a 2D vector.
  */
public class GameVector {
  //Attributes
  private int x;
  private int y;

  //Constructor
  public GameVector(int x,int y)
  {
    this.x = x;
    this.y = y;
  }

  //Methods
  public int getX(){return this.x;}

  /***************************************************/

  public int getY(){return this.y;}

  /***************************************************/

  public boolean compare(GameVector v)
  {
    return ((this.x == v.getX()) && (this.y == v.getY()));
  }

}
