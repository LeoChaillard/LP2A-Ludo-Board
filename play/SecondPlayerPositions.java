/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/
package play;
import java.util.*;

public class SecondPlayerPositions {
  //Attribute
  private Map<Integer,GameVector> mapPosition = new HashMap<Integer,GameVector>();

  //Method
  public Map<Integer,GameVector> getMap(){return mapPosition;}

  //Constructor
  public SecondPlayerPositions()
  {
  	mapPosition.put(0,new GameVector (13, 6));
    mapPosition.put(1,new GameVector (12, 6));
    mapPosition.put(2,new GameVector (11, 6));
  	mapPosition.put(3,new GameVector (10, 6));
  	mapPosition.put(4,new GameVector (9, 6));
  	mapPosition.put(5,new GameVector (8, 5));
  	mapPosition.put(6,new GameVector (8, 4));
  	mapPosition.put(7,new GameVector (8, 3));
  	mapPosition.put(8,new GameVector (8, 2));
  	mapPosition.put(9,new GameVector (8, 1));
  	mapPosition.put(10,new GameVector (8, 0));
  	mapPosition.put(11,new GameVector (7, 0));
  	mapPosition.put(12,new GameVector (6, 0));
  	mapPosition.put(13,new GameVector (6, 1));
  	mapPosition.put(14,new GameVector (6, 2));
  	mapPosition.put(15,new GameVector (6, 3));
  	mapPosition.put(16,new GameVector (6, 4));
  	mapPosition.put(17,new GameVector (6, 5));
  	mapPosition.put(18,new GameVector (5, 6));
  	mapPosition.put(19,new GameVector (4, 6));
  	mapPosition.put(20,new GameVector (3, 6));
  	mapPosition.put(21,new GameVector (2, 6));
  	mapPosition.put(22,new GameVector (1, 6));
  	mapPosition.put(23,new GameVector (0, 6));
  	mapPosition.put(24,new GameVector (0, 7));
  	mapPosition.put(25,new GameVector (0, 8));
  	mapPosition.put(26,new GameVector (1, 8));
  	mapPosition.put(27,new GameVector (2, 8));
  	mapPosition.put(28,new GameVector (3, 8));
  	mapPosition.put(29,new GameVector (4, 8));
  	mapPosition.put(30,new GameVector (5, 8));
  	mapPosition.put(31,new GameVector (6, 9));
  	mapPosition.put(32,new GameVector (6, 10));
  	mapPosition.put(33,new GameVector (6, 11));
  	mapPosition.put(34,new GameVector (6, 12));
  	mapPosition.put(35,new GameVector (6, 13));
  	mapPosition.put(36,new GameVector (6, 14));
  	mapPosition.put(37,new GameVector (7, 14));
  	mapPosition.put(38,new GameVector (8, 14));
  	mapPosition.put(39,new GameVector (8, 13));
  	mapPosition.put(40,new GameVector (8, 12));
  	mapPosition.put(41,new GameVector (8, 11));
  	mapPosition.put(42,new GameVector (8, 10));
  	mapPosition.put(43,new GameVector (8, 9));
  	mapPosition.put(44,new GameVector (9, 8));
  	mapPosition.put(45,new GameVector (10, 8));
  	mapPosition.put(46,new GameVector (11, 8));
  	mapPosition.put(47,new GameVector (12, 8));
  	mapPosition.put(48,new GameVector (13, 8));
  	mapPosition.put(49,new GameVector (14, 8));
  	mapPosition.put(50,new GameVector (14, 7));
  	mapPosition.put(51,new GameVector (13, 7));
  	mapPosition.put(52,new GameVector (12, 7));
  	mapPosition.put(53,new GameVector (11, 7));
  	mapPosition.put(54,new GameVector (10, 7));
  	mapPosition.put(55,new GameVector (9, 7));
  	mapPosition.put(56,new GameVector (8, 7));
}

}
