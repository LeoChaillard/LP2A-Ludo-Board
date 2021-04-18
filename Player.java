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
  private List<Integer> movablePawns = new ArrayList<Integer>(4);
  private Map<Integer,Vector> mapPosition = new HashMap<Integer,Vector>();

  //Constructor
  public Player(String n)
  {
    this.pawnsHome = 0;
    this.name = n;
    this.color = null;
    this.rand = new Random();
    this.pawns.add(new Pawn());
    this.pawns.add(new Pawn());
    this.pawns.add(new Pawn());
    this.pawns.add(new Pawn());
  }

  //Methods
  public List<Integer> getMovablePawns(){return this.movablePawns;}

  /***************************************************/

  public Map<Integer,Vector> getMapPosition(){return this.mapPosition;}

  /***************************************************/

  public void setMap(Map<Integer,Vector> map){this.mapPosition = map;}

  /***************************************************/

  public String getName(){return this.name;}

  /***************************************************/

  public List<Pawn> getPawns(){return this.pawns;}

  /***************************************************/

  public Color getColor(){return this.color;}

  /***************************************************/

  public void setColor(Color c){this.color = c;}

  /***************************************************/

  public boolean checkWin(){return this.pawnsHome == 4;}

  /***************************************************/

  public boolean canPlay(int diceResult)
  {
    boolean canPlay = false;
    for(int i = 0;i<4;++i)
    {
      if(canMovePawn(i, diceResult))
      {
        canPlay = true;
        if (!movablePawns.contains(i)) movablePawns.add(i);
      } else if (movablePawns.contains(i)) movablePawns.remove(Integer.valueOf(i));

    }
    return canPlay;
  }

  /***************************************************/

  public int rollDice()
  {
    int n = 1 + this.rand.nextInt(6);
    return n;
  }

  /***************************************************/

  public boolean canMovePawn(int pawnIndex, int diceResult)
  {
    if(diceResult == 6 && this.pawns.get(pawnIndex).getSquare() == -1) return true;
    else if (this.pawns.get(pawnIndex).canMoveOnBoard(diceResult)) return true;
    return false;
  }

  /***************************************************/

  public void movePawn(int pawnIndex, int diceResult)
  {
    if(diceResult == 6 && this.pawns.get(pawnIndex).getSquare() == -1) this.pawns.get(pawnIndex).getOut();
    else if (this.pawns.get(pawnIndex).canMoveOnBoard(diceResult))
    {
      this.pawns.get(pawnIndex).moveOnBoard(mapPosition,diceResult);
      updateBlockPawn(pawnIndex);
      updatePawnHome();
    }
  }

  /***************************************************/

  public void updatePawnHome(){
	  for(int i = 0; i<4; i++){
		  if(pawns.get(i).getSquare() == 56 && pawns.get(i).getHome() == false){
			  pawns.get(i).setHome(true);
			  this.pawnsHome += 1;
			  break;
		  }
	  }
  }

/***************************************************/

public void updateBlockPawn(int pawnIndex)
{
  for(int j = 0;j<4;++j) pawns.get(j).setBlock(false);

  for(int i = 0; i<4; i++)
  {
    if(pawns.get(pawnIndex).getSquare() == pawns.get(i).getSquare() && pawnIndex != i )
    {
      pawns.get(pawnIndex).setBlock(true);
      pawns.get(i).setBlock(true);
    }
  }
}


}
