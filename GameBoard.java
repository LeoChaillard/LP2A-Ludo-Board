/************************************************************************
 * LP2A Project - Spring semester 2021 - Creation of a Ludo Game
 * Authors : Eléanore RENAUD - eleanore.renaud@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : April, 2021
 ************************************************************************/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Polygon;

import javax.swing.JPanel;

import java.util.*;

/**
 * Class defining the board panel of our game.
 * It is drawing the game board and the pawns
 * based on their orientations and positions.
 */
public class GameBoard extends JPanel{
  //Attributes
  private static final long serialVersionUID = 1L;
  private static final float ELEMENTS = 15f;
  private static final float ELEMENT_SIZE = 0.85f;
  private static final float BLOCK_LENGTH = 6f;
  private static final int QUARTER = 13;

  //Constructor
  public GameBoard()
  {

  }

  //Methods
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    this.setBackground(Color.WHITE);

    float width = getWidth();
    float height = getHeight();

    //Direction
    Direction d = new Direction( ELEMENTS/2.f, ELEMENTS/2.f);
    Direction.Orientation ori;
    d.setScale(height/ELEMENTS);

    //Rectangles
    Rectangle rec = new Rectangle();
    rec.setDirection(d);
    rec.setScale(height/ELEMENTS);

    //Circles
    Circle cir = new Circle();
    cir.setDirection(d);
    cir.setScale(height/ELEMENTS);

    //Pawns
    DrawPawn pawn = new DrawPawn();
    pawn.setDirection(d);
    pawn.setScale(height/ELEMENTS);

    //Drawing board
    for(int i = 0;i<4;++i) //For each orientation we're drawing the same scheme
    {
      //Drawing starting block
      d.resetMove();

      ori = Direction.Orientation.values()[i];
      d.setOrientation(ori);
      d.move((0.5f + 1 + BLOCK_LENGTH/2.f), -(0.5f + 1 + BLOCK_LENGTH/2.f)); //Going at the center of the starting block

      g.setColor(Colors.getDarkColors()[i]);
      rec.setSide(BLOCK_LENGTH);
      rec.fill(g);

      g.setColor(Colors.getColors()[i]);
      rec.setSide(BLOCK_LENGTH*0.90f);
      rec.fill(g);

      //Drawing middle triangle
      d.resetMove();

      g.setColor(Colors.getColors()[i]);
      Polygon p = new Polygon();

      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      d.up(1.5f);
      d.left(1.5f);
      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      d.right(3f);
      p.addPoint(Math.round(d.getX()), Math.round(d.getY()));
      g.fillPolygon(p);

      //Drawing centered squares
      d.resetMove();

      rec.setSide(ELEMENT_SIZE);
      d.up(1);
      for(int j = 0;j<BLOCK_LENGTH-1;++j)
      {
        d.up(1);
        rec.fill(g);
      }

      //Drawing circles
      g.setColor(Colors.getDarkColors()[i]);
      cir.setSide(ELEMENT_SIZE);

      d.up(1);
      cir.fill(g);
      d.right(1);
      cir.fill(g);
      d.down(1);
      rec.fill(g);

      for(int k = 0;k<BLOCK_LENGTH-2;++k)
      {
        d.down(1);
        cir.fill(g);
      }

      d.down(1);

      for(int k = 0;k<4;++k)
      {
        d.right(1);
        cir.fill(g);
      }

      //Safe zone
      cir.setSide(0.30f);
      g.setColor(Color.WHITE);
      cir.fill(g);

      g.setColor(Colors.getDarkColors()[i]);
      cir.setSide(ELEMENT_SIZE);
      for(int k = 0;k<2;++k)
      {
        d.right(1);
        cir.fill(g);
      }

    }


    //Drawing pawns BASED on their positions
    List<Player> players = Game.getPlayers();
    pawn.setSide(0.70f);

    for (int direction = 0;direction<Direction.Orientation.values().length;++direction)
    {
      g.setColor(Colors.getPawnColors()[direction]);
      int pawnIndex = 0;

      //Looking for the player that has index "direction" color
      int index = 0;
      for(int i = 0;i<4;++i)
      {
        if (players.get(i).isSameColor(direction))
        {
          index = i;
          break;
        }
      }

      for(Pawn p : players.get(index).getPawns())
      {
        d.setOrientation(Direction.Orientation.values()[direction]); //Getting the orientation of the player's starting block
        d.resetMove();

        if(p.isStartingBlock()) //If it is at starting block
        {
          d.move((0.5f + 1 + BLOCK_LENGTH/2.f), -(0.5f + 1 + BLOCK_LENGTH/2.f)); //Going at the center of the starting block
          if(pawnIndex % 2 == 0) d.left(1); //Even paws go left
          else d.right(1); //Odd pawns go right

          if(pawnIndex < 2) d.up(1);
          else d.down(1);
        }
        else if(p.getSquare() >= 50) //If it is on the straight line to go home
        {
          d.up(1);
          d.up(56 - p.getSquare());
          if(p.getSquare() == 56) p.setHome(true);
        }
        else if(p.getSquare() < 50 && !p.isStartingBlock()) //If it is moving on the board
        {
          d.up(6);
          d.right(1);


          int squares = p.getSquare();

          int i = direction;
          while (squares > 12) { //Changing orientation for each travelled quarter
            d.setOrientation(Direction.Orientation.values()[(++i) % Direction.Orientation.values().length]);
            squares -= QUARTER;
          }

          int tmp = Math.round(BLOCK_LENGTH)-2; //First line length from square 0
          while(squares > 0 && tmp > 0 )
          {
            d.down(1);
            --tmp;
            --squares;
          }

          if(squares > 0) //Second line
          {
            d.down(1);
            tmp = Math.round(BLOCK_LENGTH);
            while(squares > 0 && tmp > 0)
            {
              d.right(1);
              --tmp;
              --squares;
            }
          }
          d.down(squares); //Going down to next quarter
        }

        pawn.fill(g); //Drawing pawn

        //Getting pawn's position on screen
        p.setX(pawn.getX());
        p.setY(pawn.getY());

        ++pawnIndex;
      }
    }
    Toolkit.getDefaultToolkit().sync();
  }

}
