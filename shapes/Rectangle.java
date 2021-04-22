/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/
package shapes;
import java.awt.Graphics;

 public class Rectangle extends Shape{

   //Methods
   @Override
   public void fill(Graphics g)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.fillRect(x-width/2,y-height/2,width,height);
   }
 }
