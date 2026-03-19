
import java.util.ArrayList;

public class Game
{
    private Grid grid;
    private int userRow;
    private int userCol;
    private int msElapsed;
    private int timesGet;
    private int timesAvoid;
    private int score;
    private Pieces p;
  
    public Game()
    {
        grid = new Grid(20, 10);
        userRow = 0;
        userCol=4;
        msElapsed = 0;
        timesGet = 0;
        timesAvoid = 0;
        score=0;
        updateTitle();
        
        p = new Pieces();
        
    }
  
    public void play()
    {
        setBackround();
        int key=grid.checkLastKeyPressed();
        while (!isGameOver())
        {
            
            grid.pause(150);
            handleKeyPress();
            drawPiece();
            
            if (msElapsed % 100 == 0)
            {
                
                userDown();
                
                checkLines();
            }
            updateTitle();
            msElapsed += 100;
        }
    
    }
    public void setBackround()
    {
        for(int r=0;r<grid.getNumRows();r++)
        {
            for(int c=0;c<grid.getNumCols();c++)
            {
                grid.setImage(new Location(r,c),"cube.gif");
            }
        }
    }
    public void clearExtraPinks()//new
    {
        for(int r=0;r<grid.getNumRows();r++)
        {
            for(int c=0;c<grid.getNumCols();c++)
            {
                if(grid.getImage(new Location(r,c)) != null &&grid.getImage(new Location(r,c)).equals("pink.gif")&&!(r>=p.getRow()-1 && r<=p.getRow()+1 && c>=p.getCol()-1&&c<=p.getCol()+1))
                     grid.setImage(new Location(r,c),"cube.gif");
            }
        }
    }
    //moving the piece around
    public void handleKeyPress()
    {
        
        int key=grid.checkLastKeyPressed();
        System.out.println(key);
        if(key==32)
        {
            if(p.getRandomP()!= 4)
            {
                //clearExtraPinks();
                int[][] oldPiece = p.getPieceGrid();
                p.rotateRight();
                //clearExtraPinks();
                //int[][] oldPiece = p.getPieceGrid();
                for(int r = 0; r<oldPiece.length;r++)
                {
                    for(int c =0; c< oldPiece[0].length; c++)
                    {
                        grid.setImage(new Location(p.getRow()-1+r,p.getCol()-1+c),"cube.gif");
                    }

                }
            }
        }
        else if(p.getRandomP()==7)
        {
            //right
            if(key==39&&p.getCol()<grid.getNumCols()-3)
            {
                if(grid.getImage(new Location(userRow,userCol+1))=="blue.gif")
                {

                }
                else
                {
                   p.userRight();
                   for(int i = 0; i<3; i++)
                   {
                       grid.setImage(new Location(p.getRow()-2+i,p.getCol()-2),"cube.gif");
                   }
                }
            }
            //left
            if(key==37&&p.getCol()>1)
            {
                if(grid.getImage(new Location(userRow,userCol-1))=="blue.gif")
                {

                }
                else
                {
                    p.userLeft();
                    for(int i = 0; i<3; i++)
                    {
                       grid.setImage(new Location(p.getRow()-2+i,p.getCol()+3),"cube.gif");
                    }
                }        
            }
        }
        else if (p.getRandomP()==4)
        {
            //right
            if(key==39&&p.getCol()<grid.getNumCols()-1)
            {
                if(grid.getImage(new Location(userRow,userCol+1))=="blue.gif")
                {

                }
                else
                {
                   p.userRight();
                   for(int i = 0; i<2; i++)
                   {
                       grid.setImage(new Location(p.getRow()-2+i,p.getCol()-2),"cube.gif");
                   }
                }
                //drawPiece();
                
            }
            
            //left
            if(key==37&&p.getCol()>1)//
            {
                if(grid.getImage(new Location(userRow,userCol-1))=="blue.gif")
                {

                }
                else
                {
                    p.userLeft();
                    for(int i = 0; i<2; i++)
                    {
                       grid.setImage(new Location(p.getRow()-2+i,p.getCol()+1),"cube.gif");
                    }
                }        
            }
        }
        else
        {
            //right
            if(key==39&&p.getCol()<grid.getNumCols()-2)
            {
                if(grid.getImage(new Location(userRow,userCol-1))=="blue.gif")
                {
                    
                }
                else
                {
                    p.userRight();
                    for(int i = 0; i<3; i++)
                    {
                        grid.setImage(new Location(p.getRow()-2+i,p.getCol()-2),"cube.gif");
                    }
                }
            }
            else if(key == 39 && p.getCol() == 8 && checkRightPieceEdge() == false)
            {
                p.shiftWholeRight();
            }
            //left
            if(key==37&&p.getCol()>1)//
            {
                if(grid.getImage(new Location(userRow,userCol-1))=="blue.gif")
                {

                }
                else
                {
                    p.userLeft();
                    for(int i = 0; i<3; i++)
                    {
                       grid.setImage(new Location(p.getRow()-2+i,p.getCol()+2),"cube.gif");
                    }
                }        
            }
            else if(key == 37 && p.getCol() == 1 && checkLeftPieceEdge() == false)
            {
                p.shiftWholeLeft();
            }
        }
        if(key == 40)
        {
            while(p.getRow()<18&&grid.getImage(new Location(p.getRow()+1,p.getCol()))!="blue.gif")
            {
                userRow++;
            }
            
        }
    }
    public void userDown()//new
    {
        if(p.getRandomP()==4)//cube collision detection (CUBE ONLY)
        {
            if((p.getRow()==grid.getNumRows()-1||grid.getImage(new Location(p.getRow()+1,p.getCol())).equals("blue.gif"))||(p.getRow()==grid.getNumRows()-1||grid.getImage(new Location(p.getRow()+1,p.getCol()-1)).equals("blue.gif")))
            {
                p.MakePB();
                drawPiece();
                p = new Pieces();
            }
            else
            {
                clearExtraPinks();
                p.userDownb();
            }
        }
        else if(p.getRow()+1==grid.getNumRows()-1|| canMoveDown() == false)//if state must change  //else
        {
            p.MakePB();
            drawPiece();
            p = new Pieces();

        }
        else
        {
            clearExtraPinks();
            p.userDownb();
        }
        
    }
    public void removeTop()
    {
        for(int r = 0; r<3; r++)
            for(int c =0; c<3; c++)
            {
             //   if()
            }
    }
    public int numAvoidInCol(int col)
    {
        int count=0;
        for(int r=0;r<grid.getNumRows();r++)
        {
            if(grid.getImage(new Location(r,col))=="blue.gif")
            {
                count++;   
            }
        }
        return count;
    }
    //colors in the cubes
    public void drawPiece()//new
    {
        int[][] x = p.getPieceGrid();

        for(int r = 0; r<p.getPieceGrid().length; r++)
        {
            for(int c = 0; c<p.getPieceGrid()[0].length; c++)
            {
                if(!p.getIsBlue())
                {
                    if(x[r][c] == 1)
                    {
                        grid.setImage(new Location(p.getRow()+r-1,p.getCol()+c-1), "pink.gif");
                    }
                    else if(!grid.getImage(new Location(p.getRow()+r-1,p.getCol()+c-1) ).equals("blue.gif"))
                    {
                        grid.setImage(new Location(p.getRow()+r-1,p.getCol()+c-1), "cube.gif");
                    }
                }
                else
                {
                    if(x[r][c] == 2)
                    {
                        grid.setImage(new Location(p.getRow()+r-1,p.getCol()+c-1), "blue.gif");
                    }
                    else if(!grid.getImage(new Location(p.getRow()+r-1,p.getCol()+c-1) ).equals("blue.gif"))
                    {
                        grid.setImage(new Location(p.getRow()+r-1,p.getCol()+c-1), "cube.gif");
                    }
                }
            }
        }
    }
  
    //checks to see if line is full and shifts down if it is
    public void checkLines()
    {
        for(int r = 0; r<grid.getNumRows(); r++)
        {
            for(int c = 0; c<grid.getNumCols(); c++)
            {
                if(checkRowFull(r)==true)
                {
                    setRowBack(r);
                    shiftDown();
                }
            }  
        }
    }
   
    //helpermethod to checklines; shifts everything down one
    public void shiftDown()
    {
        for(int c = grid.getNumCols()-1; c>=0; c--)
        {
            for(int r = grid.getNumRows()-2; r>0; r--)
            {
                if(grid.getImage(new Location(r,c))=="blue.gif")
                {
                    grid.setImage(new Location(r+1,c), "blue.gif");
                    grid.setImage(new Location(r,c), "cube.gif");
                }
            } 
        }
    }
    //makes the full row back into empty to allow shift down; adds 100 to yout score per line
    public void setRowBack(int row)
    {
        for(int c=0;c<grid.getNumCols();c++)
        {
            grid.setImage(new Location(row,c),"cube.gif");
            
        }
        score+=100;
    } 
    //checks to see if the row is full
    public boolean checkRowFull(int row)
    {
        for(int c=0;c<grid.getNumCols();c++)
        {
            if(grid.getImage(new Location(row,c))!="blue.gif")
                return false;
        }
        return true;
    }
    //returns true if the leftMost side has a pink(only use when piece is on the side of grid)
    public boolean checkLeftPieceEdge()
    {
        for(int i = 0; i<3; i++)
        {
            if(grid.getImage(new Location(p.getRow()-1+i,p.getCol()-1)).equals("pink.gif"))
            {
                return true;
            }
        }
        return false;
    }
    public boolean checkRightPieceEdge()
    {
        for(int i = 0; i<3; i++)
        {
            if(grid.getImage(new Location(p.getRow()-1+i,p.getCol()+1)).equals("pink.gif"))
            {
                return true;
            }
        }
        return false;
    }
    
    
    //getting the lowest part of the pieces to check the pieces following those in order to make curr piece blue
    /*public ArrayList<Location> getBottomPieces()
    {
        ArrayList<Location> output=new ArrayList<Location>();
        int [][] piece=p.getPieceGrid();
        for(int r=-1;r<0;r++)
        {
            for(int c=-1;c<=1;c++)
            {
                if(piece[p.getRow()+r][p.getCol()+c]==1&&piece[p.getRow()+r+1][p.getCol()+c]!=1)
                {
                    output.add(new Location(p.getRow()+r,p.getCol()+c));
                }
            }    
        }
        for(int c = -1; c<1; c++)
        {
            if(piece[2][c] == 1)
            {
                output.add(new Location(p.getRow()+1,p.getCol()+c));
            }
        }
        return output;
        
    }
    
    /*
    public void saveDeletedPieces()
    {
        if(p.getRow()+1==grid.getNumRows()-1||grid.getImage(new Location(p.getRow()+2,p.getCol())).equals("blue.gif"))
        {
            for(int i = 0; i<p.getPieceGrid().length-1; i++)
            {
                if(grid.getImage(new Location(p.getRow()+1,p.getCol()-1+i)).equals("blue.gif"))
                {
                    grid.setImage(loc, imageFileName);
                }
            }
        }
    }
    */
    
    public boolean canMoveDown()
    {
        for(int r = -1; r<2; r++)
        {
            for(int c = -1; c<2; c++)
            {
                if(grid.getImage(new Location(p.getRow()+r,p.getCol()+c)).equals("pink.gif"))
                {
                    if((!grid.getImage(new Location(p.getRow()+r+1,p.getCol()+c)).equals("pink.gif"))&&grid.getImage(new Location(p.getRow()+r+1,p.getCol()+c)).equals("blue.gif"))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    
    
    
    
    public void updateTitle()
    {
        if(isGameOver()==true)
        {
            grid.setTitle("GAME OVER");
        }
        else
        {
            grid.setTitle("Game:  " + score);
        }
  }
  
    public boolean isGameOver()
    {
        if(grid.getImage(new Location(0,4))=="blue.gif")
        {
            return true;
        }
        else
            return false;
    }
 
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}