/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import javax.swing.JComponent;
import java.awt.Image;
import java.awt.Graphics;

/**
 * This class enables us to paint on a JPcomponent.
 */
public class ImagePanel extends JComponent{
  //Attributes
  private Image image;

  //Constructor
  public ImagePanel(Image image)
  {
    this.image = image;
  }

  //Methods
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    int h= this.getHeight();
    int w = this.getWidth();
    g.drawImage(image,(this.getWidth()-w)/2,(this.getHeight()-h)/2,w,h,this);
  }
}
