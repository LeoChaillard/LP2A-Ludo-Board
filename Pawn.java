/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;

public class Pawn {
  //Attributes
  private Vector position;
  private boolean isOnSafeZone;

  //Constructor
  public Pawn()
  {
    this.position = new Vector(-1,-1);
    this.isOnSafeZone = true;
  }

  //Methods
  public void movePawn(Vector newPos)
  {
    position = newPos;
    updateSafeZone();
  }

  /***************************************************/

  public boolean isOnSafeZone()
  {
    return isOnSafeZone;
  }

  /***************************************************/

  private void updateSafeZone()
  {

  }


}
