/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;

public class Pawn {
  //Attributes
  private Vector position;
  private boolean isOnSafeZone;
  private boolean formBlock;
  private boolean home;
  private int square;
  private ImageIcon pawnImage;

  //Constructor
  public Pawn()
  {
    this.square = -1;
    this.position = new Vector(-1,-1);
    this.isOnSafeZone = true;
    this.formBlock = false;
    this.home = false;
  }

  //Methods
  public int getSquare(){return this.square; }

  public void setSquare(int s){this.square = s; }

  public void setHome(boolean h){this.home = h;}

  public boolean getHome(){return this.home;}

  public void setBlock(boolean b){this.formBlock = b;}

  /***************************************************/

  public void getOut()
  {
    this.square = 0;
    this.position = new Vector(6,1);
  }

  /***************************************************/

  public boolean canMoveOnBoard(int diceResult)
  {
    if ( (diceResult == 6 && square < 51 && square != -1)
    || (diceResult == 5 && square < 52 && square != -1)
    || (diceResult == 4 && square < 53 && square != -1)
    || (diceResult == 3 && square < 54 && square != -1)
    || (diceResult == 2 && square < 55 && square != -1)
    || (diceResult == 1 && square < 56 && square != -1) ) return true;

    return false;
  }


  /***************************************************/

  public void moveOnBoard(Map<Integer,Vector> coords,int diceResult)
  {

    this.position = coords.get(square+=diceResult);
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
    if(square == 0 || square ==  8 || square == 21 || square == 34 || square == 47 || square >= 51 ) isOnSafeZone = true;
    else isOnSafeZone = false;
  }




}
