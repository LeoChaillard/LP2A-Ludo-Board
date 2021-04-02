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

  //Constructor
  public Player(String n,Color c)
  {
    this.pawnsHome = 0;
    this.name = n;
    this.color = c;
    rand = new Random();
  }

  //Methods
  public int rollDice()
  {
    int n = 0;
    while( n <= 0) n = this.rand.nextInt(7);
    return n;
  }

  /***************************************************/

  //Tests
  public static void main(String [] args)
  {
    Player p = new Player("jean",Color.BLEU);
    p.rollDice();


	}

}
