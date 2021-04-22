/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

 /**
  * Class defining a x and y movement relatively to the north.
  */
public class Direction{
  //Attributes
  public enum Orientation {NORTH, EAST, SOUTH, WEST};

  private final float originX;
  private final float originY;
  private float xMove;
  private float yMove;
  private float scale = 1f;
  private Direction.Orientation dir = Orientation.NORTH;

  //Constructor
  public Direction(float originX,float originY)
  {
    this.originX = originX;
    this.originY = originY;
  }

  //Methods
  public void setOrientation(Orientation dir) {this.dir = dir;}

  /***************************************************/

  public void setScale(float s){this.scale = s;}

  /***************************************************/

  public void move(float x, float y)
  {
    this.xMove += x;
    this.yMove += y;
  }

  /***************************************************/

  public void left (float l){this.move(-l,0);}

  /***************************************************/

  public void right (float r){this.move(r,0);}

  /***************************************************/

  public void up (float u){this.move(0,-u);}

  /***************************************************/

  public void down (float d){this.move(0,d);}

  /***************************************************/

  public float getMoveX()
  {
    switch(this.dir)
    {
      case NORTH:
        return xMove;
      case EAST:
        return -yMove;
      case SOUTH:
        return -xMove;
      default:
        return yMove;
    }

  }

  /***************************************************/

  public float getMoveY()
  {
    switch(this.dir)
    {
      case NORTH:
        return yMove;
      case EAST:
        return xMove;
      case SOUTH:
        return -yMove;
      default:
        return -xMove;
    }
  }

  /***************************************************/

  public float getX() {return (originX + getMoveX()) * this.scale;}

  /***************************************************/

  public float getY() {return (originY + getMoveY()) * this.scale;}

  /***************************************************/

  public void setMove(float x,float y)
  {
    this.xMove = x;
    this.yMove = y;
  }

  /***************************************************/

  public void resetMove() {setMove(0, 0);}
}
