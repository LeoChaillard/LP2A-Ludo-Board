/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Board game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.awt.Graphics;
import java.util.*;

 public abstract class Shape{
   //Attributes
   private float width;
   private float height;
   private float scale;
   private Direction dir;


   //Methods
   abstract void fill(Graphics g);

   /***************************************************/

   public void setDirection(Direction d){this.dir = d;}

   /***************************************************/

   public void setSize(float w,float h)
   {
     this.width = w;
     this.height = h;
   }

   /***************************************************/

   public void setSide(float s)
   {
     this.setSize(s,s);
   }

   /***************************************************/

   public int getWidth(){return Math.round(this.width*this.scale);}

   /***************************************************/

   public int getHeight(){return Math.round(this.height*this.scale);}

   /***************************************************/

   public int getX() { return Math.round(this.dir.getX()); }

   /***************************************************/

   public int getY() { return Math.round(this.dir.getY()); }

   /***************************************************/

   public void setScale(float s){this.scale = s;}




 }
