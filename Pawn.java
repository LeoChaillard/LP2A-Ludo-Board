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
  private int square;
  private ImageIcon pawnImage;

  //Constructor
  public Pawn()
  {
    this.square = -1;
    this.position = new Vector(-1,-1);
    this.isOnSafeZone = true;
    this.formBlock = false;
  }

  //Methods
  public int getSquare(){return this.square;}

  /***************************************************/

  public void getOut()
  {
    this.square = 0;
    this.position = new Vector(6,1);
  }

  /***************************************************/

  public boolean canMove(int resultDice)
  {
   if ( (resultDice == 6 && this.square < 51)
 	 || (resultDice == 5 && this.square < 52)
 	 || (resultDice == 4 && this.square < 53)
 	 || (resultDice == 3 && this.square < 54)
 	 || (resultDice == 2 && this.square < 55)
 	 || (resultDice == 1 && this.square < 56) ) return true;

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
