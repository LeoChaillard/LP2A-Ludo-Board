/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;


public class Player {
  //Attributes
  private String name;
  private Color color;
  private Random rand;
  private int pawnsHome;
  private List<Pawn> pawns = new ArrayList<Pawn>(4);
  private Map<Integer,Vector> arrayPosition = new HashMap<Integer,Vector>();

  //Constructor
  public Player(String n)
  {
    this.pawnsHome = 0;
    this.name = n;
    this.color = null;
    this.rand = new Random();
    pawns.add(new Pawn());
    pawns.add(new Pawn());
    pawns.add(new Pawn());
    pawns.add(new Pawn());
  }

  //Methods
  public List<Pawn> getPawns(){return this.pawns;}

  /***************************************************/

  public Color getColor() {return this.color;}

  /***************************************************/

  public void setColor(Color c) {this.color = c;}

  /***************************************************/

  public boolean checkWin()
  {
    return false;
  }

  /***************************************************/

  public void pawnStatus(int diceResult)
  {
    /*int askIndex = -1;
    do {


    } while (!movePawn(askIndex,diceResult));*/
  }

  /***************************************************/

  public void play()
  {
    int diceResult = rollDice();

    //Vérifier si c'est possible
    //Si possible alors appeler pawnStatus
    pawnStatus(diceResult);

  }
  /***************************************************/

  public int rollDice()
  {
    int n = 0;
    while( n <= 0) n = this.rand.nextInt(7);
    return n;
  }

  /***************************************************/

  private boolean movePawn(int pawnIndex, int diceResult)
  {
    if(diceResult == 6 && this.pawns.get(pawnIndex).getSquare() == -1)
    {
      this.pawns.get(pawnIndex).getOut();
      return true;
    }
    else if (this.pawns.get(pawnIndex).canMove(diceResult))
    {
      this.pawns.get(pawnIndex).moveOnBoard(arrayPosition,diceResult);
      //Checker si un pion se fait bouffer
      return true;
    }

    return false;
  }

  /***************************************************/
  // si le pions est arrivée à "home", le compteur est is à jours.
  public void updatePawnHome(){
	  for(int i = 0; i<4; i++){
		  if(pawns.get(i).getSquare() == 56 && pawns.get(i).getHome() == false){
			  pawns.get(i).getHome() = true;
			  this.pawnsHome += 1;
			  break;
		  }
	  }
  }

// check si la partie est finis 
  public boolean checkWin(){
	  if(this.pawnsHome == 4){
		  return true;
	  }
	  else{
		  return false;
	  }

  }

  private void initializePositionMapping()
  {
  	arrayPosition.put(0,new Vector (6, 1));
    arrayPosition.put(1,new Vector (6, 2));
    arrayPosition.put(2,new Vector (6, 3));
  	arrayPosition.put(3,new Vector (6, 4));
  	arrayPosition.put(4,new Vector (6, 5));
  	arrayPosition.put(5,new Vector (5, 6));
  	arrayPosition.put(6,new Vector (4, 6));
  	arrayPosition.put(7,new Vector (3, 6));
  	arrayPosition.put(8,new Vector (2, 6));
  	arrayPosition.put(9,new Vector (1, 6));
  	arrayPosition.put(10,new Vector (0, 6));
  	arrayPosition.put(11,new Vector (0, 7));
  	arrayPosition.put(12,new Vector (0, 8));
  	arrayPosition.put(13,new Vector (1, 8));
  	arrayPosition.put(14,new Vector (2, 8));
  	arrayPosition.put(15,new Vector (3, 8));
  	arrayPosition.put(16,new Vector (4, 8));
  	arrayPosition.put(17,new Vector (5, 8));
  	arrayPosition.put(18,new Vector (6, 9));
  	arrayPosition.put(19,new Vector (6, 10));
  	arrayPosition.put(20,new Vector (6, 11));
  	arrayPosition.put(21,new Vector (6, 12));
  	arrayPosition.put(22,new Vector (6, 13));
  	arrayPosition.put(23,new Vector (6, 14));
  	arrayPosition.put(24,new Vector (7, 14));
  	arrayPosition.put(25,new Vector (8, 14));
  	arrayPosition.put(26,new Vector (8, 13));
  	arrayPosition.put(27,new Vector (8, 12));
  	arrayPosition.put(28,new Vector (8, 11));
  	arrayPosition.put(29,new Vector (8, 10));
  	arrayPosition.put(30,new Vector (8, 9));
  	arrayPosition.put(31,new Vector (9, 8));
  	arrayPosition.put(32,new Vector (10, 8));
  	arrayPosition.put(33,new Vector (11, 8));
  	arrayPosition.put(34,new Vector (12, 8));
  	arrayPosition.put(35,new Vector (13, 8));
  	arrayPosition.put(36,new Vector (14, 8));
  	arrayPosition.put(37,new Vector (14, 7));
  	arrayPosition.put(38,new Vector (14, 6));
  	arrayPosition.put(39,new Vector (13, 6));
  	arrayPosition.put(40,new Vector (12, 6));
  	arrayPosition.put(41,new Vector (11, 6));
  	arrayPosition.put(42,new Vector (10, 6));
  	arrayPosition.put(43,new Vector (9, 6));
  	arrayPosition.put(44,new Vector (8, 5));
  	arrayPosition.put(45,new Vector (8, 4));
  	arrayPosition.put(46,new Vector (8, 3));
  	arrayPosition.put(47,new Vector (8, 2));
  	arrayPosition.put(48,new Vector (8, 1));
  	arrayPosition.put(49,new Vector (8, 0));
  	arrayPosition.put(50,new Vector (7, 0));
  	arrayPosition.put(51,new Vector (7, 1));
  	arrayPosition.put(52,new Vector (7, 2));
  	arrayPosition.put(53,new Vector (7, 3));
  	arrayPosition.put(54,new Vector (7, 4));
  	arrayPosition.put(55,new Vector (7, 5));
  	arrayPosition.put(56,new Vector (7, 6));
}

}
