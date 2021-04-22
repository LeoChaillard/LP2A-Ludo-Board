/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.util.*;

/**
 * Class defining a pawn and its actions.
 * It contains a vector object in order to keep track
 * of its position relatively to other pawns.
 */
public class Pawn {
  //Attributes
  private Vector position;
  private boolean isOnSafeZone;
  private boolean formBlock;
  private boolean home;
  private int square;
  private boolean isStarting;
  private int xScreen; //Pawn's x position on screen
  private int yScreen; //Pawn's y position on screen

  //Constructor
  public Pawn()
  {
    this.square = -1;
    this.position = new Vector(-1,-1);
    this.isOnSafeZone = true;
    this.formBlock = false;
    this.home = false;
    this.isStarting = true;
    this.xScreen = 0;
    this.yScreen = 0;
  }

  //Methods
  public void setY(int y){this.yScreen = y;}

  /***************************************************/

  public void setX(int x){this.xScreen = x;}

  /***************************************************/

  public int getY(){return this.yScreen;}

  /***************************************************/

  public int getX(){return this.xScreen;}

  /***************************************************/

  public boolean isStartingBlock(){return this.isStarting;}

  /***************************************************/

  public void setStarting(boolean s){this.isStarting = s;}

  /***************************************************/

  public int getSquare(){return this.square;}

  /***************************************************/

  public void setSquare(int s){this.square = s;}

  /***************************************************/

  public void setHome(boolean h){this.home = h;}

  /***************************************************/

  public boolean isHome(){return this.home;}

  /***************************************************/

  public void setBlock(boolean b){this.formBlock = b;}

  /***************************************************/

  public boolean getBlock(){return this.formBlock;}

  /***************************************************/

  public Vector getPosition(){return this.position;}

  /***************************************************/

  public boolean isOnSafeZone(){return isOnSafeZone;}

  /***************************************************/

  public void backStartingBlock()
  {
    this.square = -1;
    this.position = new Vector(-1,-1);
    this.isStarting = true;
    this.isOnSafeZone = true;
  }

  /***************************************************/

  public void getOut()
  {
    this.square = 0;
    this.position = new Vector(6,1);
    this.isStarting = false;
  }

  /***************************************************/

  public boolean canMoveOnBoard(int diceResult)
  {
    if(square != -1) //If it's not at starting block
    {
      if ( (diceResult == 6 && square < 51)
      || (diceResult == 5 && square < 52)
      || (diceResult == 4 && square < 53)
      || (diceResult == 3 && square < 54)
      || (diceResult == 2 && square < 55)
      || (diceResult == 1 && square < 56) ) return true;
    }

    return false;
  }

  /***************************************************/

  public void moveOnBoard(Map<Integer,Vector> coords,int diceResult)
  {
    this.position = coords.get(square+=diceResult);
    updateSafeZone();
  }

  /***************************************************/

  private void updateSafeZone()
  {
    if(square == 0 || square ==  8 || square == 21 || square == 34 || square == 47 || square >= 51 ) isOnSafeZone = true;
    else isOnSafeZone = false;
  }


}
