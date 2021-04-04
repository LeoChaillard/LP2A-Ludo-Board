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
  public void movePawn(Vector Array[], int resultDice)
  {
	 int currentPosition = -1;
	 for(int i = 0; i<57; i++) {
		 if(position.getx() == Array[i].getx() && position.gety() == Array[i].gety()) {
			 currentPosition = i;
			 break;
		 }
	 }
	 if (resultDice == 6 && currentPosition < 51) {
		 this.position.setx(Array[currentPosition + 6].getx());
		 this.position.sety(Array[currentPosition + 6].gety());
	 }
	 else if (resultDice == 5 && currentPosition < 52) {
		 this.position.setx(Array[currentPosition + 5].getx());
		 this.position.sety(Array[currentPosition + 5].gety());
	 }
	 else if (resultDice == 4 && currentPosition < 53) {
		 this.position.setx(Array[currentPosition + 4].getx());
		 this.position.sety(Array[currentPosition + 4].gety());
	 }
	 else if (resultDice == 3 && currentPosition < 54) {
		 this.position.setx(Array[currentPosition + 3].getx());
		 this.position.sety(Array[currentPosition + 3].gety());
	 }
	 else if (resultDice == 2 && currentPosition < 55) {
		 this.position.setx(Array[currentPosition + 2].getx());
		 this.position.sety(Array[currentPosition + 2].gety());
	 }
	 else if (resultDice == 1 && currentPosition < 56) {
		 this.position.setx(Array[currentPosition + 1].getx());
		 this.position.sety(Array[currentPosition + 1].gety());
	 }
    //position = newPos;
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
