/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;

public class FourthPlayerPositions {
  //Attribute
  private Map<Integer,Vector> mapPosition = new HashMap<Integer,Vector>();

  //Method
  public Map<Integer,Vector> getMap(){return mapPosition;}

  //Constructor
  FourthPlayerPositions()
  {
  	mapPosition.put(0,new Vector (8, 13));
    mapPosition.put(1,new Vector (8, 12));
    mapPosition.put(2,new Vector (8, 11));
  	mapPosition.put(3,new Vector (8, 10));
  	mapPosition.put(4,new Vector (8, 9));
  	mapPosition.put(5,new Vector (9, 8));
  	mapPosition.put(6,new Vector (10, 8));
  	mapPosition.put(7,new Vector (11, 8));
  	mapPosition.put(8,new Vector (12, 8));
  	mapPosition.put(9,new Vector (13, 8));
  	mapPosition.put(10,new Vector (14, 8));
  	mapPosition.put(11,new Vector (14, 7));
  	mapPosition.put(12,new Vector (14, 6));
  	mapPosition.put(13,new Vector (13, 6));
  	mapPosition.put(14,new Vector (12, 6));
  	mapPosition.put(15,new Vector (11, 6));
  	mapPosition.put(16,new Vector (10, 6));
  	mapPosition.put(17,new Vector (9, 6));
  	mapPosition.put(18,new Vector (8, 5));
  	mapPosition.put(19,new Vector (8, 4));
  	mapPosition.put(20,new Vector (8, 3));
  	mapPosition.put(21,new Vector (8, 2));
  	mapPosition.put(22,new Vector (8, 1));
  	mapPosition.put(23,new Vector (8, 0));
  	mapPosition.put(24,new Vector (7, 0));
  	mapPosition.put(25,new Vector (6, 0));
  	mapPosition.put(26,new Vector (6, 1));
  	mapPosition.put(27,new Vector (6, 2));
  	mapPosition.put(28,new Vector (6, 3));
  	mapPosition.put(29,new Vector (6, 4));
  	mapPosition.put(30,new Vector (6, 5));
  	mapPosition.put(31,new Vector (5, 6));
  	mapPosition.put(32,new Vector (4, 6));
  	mapPosition.put(33,new Vector (3, 6));
  	mapPosition.put(34,new Vector (2, 6));
  	mapPosition.put(35,new Vector (1, 6));
  	mapPosition.put(36,new Vector (0, 6));
  	mapPosition.put(37,new Vector (0, 7));
  	mapPosition.put(38,new Vector (0, 8));
  	mapPosition.put(39,new Vector (1, 8));
  	mapPosition.put(40,new Vector (2, 8));
  	mapPosition.put(41,new Vector (3, 8));
  	mapPosition.put(42,new Vector (4, 8));
  	mapPosition.put(43,new Vector (5, 8));
  	mapPosition.put(44,new Vector (6, 9));
  	mapPosition.put(45,new Vector (6, 10));
  	mapPosition.put(46,new Vector (6, 11));
  	mapPosition.put(47,new Vector (6, 12));
  	mapPosition.put(48,new Vector (6, 13));
  	mapPosition.put(49,new Vector (6, 14));
  	mapPosition.put(50,new Vector (7, 14));
  	mapPosition.put(51,new Vector (7, 13));
  	mapPosition.put(52,new Vector (7, 12));
  	mapPosition.put(53,new Vector (7, 11));
  	mapPosition.put(54,new Vector (7, 10));
  	mapPosition.put(55,new Vector (7, 9));
  	mapPosition.put(56,new Vector (7, 8));
}

}
