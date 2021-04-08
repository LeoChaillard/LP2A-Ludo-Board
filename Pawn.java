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
  public int getSquare(){return this.square };
  
  public boolean getHome(){return this.home};

  public void getOut()
  {
    this.square = 0;
    this.position = new Vector(6,1);
  }

  /***************************************************/

  public void moveOnBoard(Map<Integer,Vector> coords,int resultDice)
  {

	 if (resultDice == 6 && square < 51) {this.position = coords.get(square+=6);}

	 else if (resultDice == 5 && square < 52) {this.position = coords.get(square+=5);}

	 else if (resultDice == 4 && square < 53) {this.position = coords.get(square+=4);}

	 else if (resultDice == 3 && square < 54) {this.position = coords.get(square+=3);}

	 else if (resultDice == 2 && square < 55) {this.position = coords.get(square+=2);}

	 else if (resultDice == 1 && square < 56) {this.position = coords.get(square+=1);}

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
