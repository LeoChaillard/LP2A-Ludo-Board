/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.lang.*;
import java.util.*;

public class Vector {
  //Attributes
  private int x;
  private int y;

  //Constructor
  public Vector(int x,int y)
  {
    this.x = x;
    this.y = y;
  }


  //Methods
  public void setx(int x) {
	  this.x = x;
  }
  public void sety(int y) {
	  this.y = y;
  }
  public int getx() {
	  return x;
  }
  public int gety() {
	  return y;
  }

  public Vector[] initialiseArrayVector() {
	 Vector[] arrayPosition;
	arrayPosition = new Vector[57];
	arrayPosition[0] = new Vector (6, 1);
	arrayPosition[1] = new Vector (6, 2);
	arrayPosition[2] = new Vector (6, 3);
	arrayPosition[3] = new Vector (6, 4);
	arrayPosition[4] = new Vector (6, 5);
	arrayPosition[5] = new Vector (5, 6);
	arrayPosition[6] = new Vector (4, 6);
	arrayPosition[7] = new Vector (3, 6);
	arrayPosition[8] = new Vector (2, 6);
	arrayPosition[9] = new Vector (1, 6);
	arrayPosition[10] = new Vector (0, 6);
	arrayPosition[11] = new Vector (0, 7);
	arrayPosition[12] = new Vector (0, 8);
	arrayPosition[13] = new Vector (1, 8);
	arrayPosition[14] = new Vector (2, 8);
	arrayPosition[15] = new Vector (3, 8);
	arrayPosition[16] = new Vector (4, 8);
	arrayPosition[17] = new Vector (5, 8);
	arrayPosition[18] = new Vector (6, 9);
	arrayPosition[19] = new Vector (6, 10);
	arrayPosition[20] = new Vector (6, 11);
	arrayPosition[21] = new Vector (6, 12);
	arrayPosition[22] = new Vector (6, 13);
	arrayPosition[23] = new Vector (6, 14);
	arrayPosition[24] = new Vector (7, 14);
	arrayPosition[25] = new Vector (8, 14);
	arrayPosition[26] = new Vector (8, 13);
	arrayPosition[27] = new Vector (8, 12);
	arrayPosition[28] = new Vector (8, 11);
	arrayPosition[29] = new Vector (8, 10);
	arrayPosition[30] = new Vector (8, 9);
	arrayPosition[31] = new Vector (9, 8);
	arrayPosition[32] = new Vector (10, 8);
	arrayPosition[33] = new Vector (11, 8);
	arrayPosition[34] = new Vector (12, 8);
	arrayPosition[35] = new Vector (13, 8);
	arrayPosition[36] = new Vector (14, 8);
	arrayPosition[37] = new Vector (14, 7);
	arrayPosition[38] = new Vector (14, 6);
	arrayPosition[39] = new Vector (13, 6);
	arrayPosition[40] = new Vector (12, 6);
	arrayPosition[41] = new Vector (11, 6);
	arrayPosition[42] = new Vector (10, 6);
	arrayPosition[43] = new Vector (9, 6);
	arrayPosition[44] = new Vector (8, 5);
	arrayPosition[45] = new Vector (8, 4);
	arrayPosition[46] = new Vector (8, 3);
	arrayPosition[47] = new Vector (8, 2);
	arrayPosition[48] = new Vector (8, 1);
	arrayPosition[49] = new Vector (8, 0);
	arrayPosition[50] = new Vector (7, 0);
	arrayPosition[51] = new Vector (7, 1);
	arrayPosition[52] = new Vector (7, 2);
	arrayPosition[53] = new Vector (7, 3);
	arrayPosition[54] = new Vector (7, 4);
	arrayPosition[55] = new Vector (7, 5);
	arrayPosition[56] = new Vector (7, 6);
	
	return arrayPosition;	
}


}
