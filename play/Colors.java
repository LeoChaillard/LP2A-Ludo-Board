/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/
package play;
import java.awt.Color;

public class Colors{
  //Attributes
  public static final Color[] darkColors = {
      new Color(0x9acd00),
      new Color(0xffcd00),
      new Color(0x2fcdcd),
      new Color(0xff3000)
    };

  public static final Color[] colors = {
      new Color(0xcfff39),
      new Color(0xffe783),
      new Color(0x95e5e5),
      new Color(0xff896a)
  };

  public static final Color[] pawnColors = {
          new Color(0x208000),
          new Color(0xCC7A00),
          new Color(0x001133),
          new Color(0x990000)
  };

  //Methods
  public static final Color[] getDarkColors(){return darkColors;}

  /***************************************************/

  public static final Color[] getColors(){return colors;}

  /***************************************************/

  public static final Color[] getPawnColors(){return pawnColors;}

}
