public class Pieces
{
    private int[][] pieceGrid;
    private int row;
    private int col;
    private int orientation;//new
    private boolean isBlue;//new
    private double randomPiece = Math.random()*6+1;
    
    public Pieces()
    {
        orientation = 1;
        isBlue = false;
        //Deciding how big the grid should be for the piece
        if((int)randomPiece == 7)
        {
            pieceGrid = new int[4][4];
        }
        else if((int)randomPiece == 4)
        {
            pieceGrid = new int[2][2];
        }
        else
        {
            pieceGrid = new int[3][3];
        }
        
        //making the peieces
        //the t piece
        if((int)randomPiece == 1)
        {
            pieceGrid[1][1] = 1;
            pieceGrid[2][0] = 1;
            pieceGrid[2][1] = 1;
            pieceGrid[2][2] = 1;
            
            row = 1;
            col = 5;
            
        }
        //right facing s piece
        else if((int)randomPiece == 2)
        {
            pieceGrid[1][1] = 1;
            pieceGrid[1][2] = 1;
            pieceGrid[2][0] = 1;
            pieceGrid[2][1] = 1;
            
            row = 1;
            col = 5;
        }
        //left facing s piece
        else if((int)randomPiece == 3)
        {
            pieceGrid[1][0] = 1;
            pieceGrid[1][1] = 1;
            pieceGrid[2][1] = 1;
            pieceGrid[2][2] = 1;
            
            row = 1;
            col = 5;
        }
        //2x2 cube piece
        else if((int)randomPiece == 4)
        {
            pieceGrid[0][0] = 1;
            pieceGrid[0][1] = 1;
            pieceGrid[1][0] = 1;
            pieceGrid[1][1] = 1;
            
            row=1;//if suriel's idea doesnt work make it back to 2
            col=5;
            
        }
        //L piece
        else if((int)randomPiece == 5)
        {
            pieceGrid[1][2] = 1;
            pieceGrid[2][0] = 1;
            pieceGrid[2][1] = 1;
            pieceGrid[2][2] = 1;
            
            row = 1;
            col = 5;
        }
        //backward L
        else if((int)randomPiece == 6)
        {
            pieceGrid[1][0] = 1;
            pieceGrid[2][0] = 1;
            pieceGrid[2][1] = 1;
            pieceGrid[2][2] = 1;
            
            row = 1;
            col = 5;
        }
        //long piece
        else if(randomPiece > 6)
        {
            pieceGrid[3][0] = 1;
            pieceGrid[3][1] = 1;
            pieceGrid[3][2] = 1;
            pieceGrid[3][3] = 1;
            row = 1;
            col = 5;
        }
    }
    public boolean getIsBlue()
    {
        return isBlue;
    }
    //acessor for row and col
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public int[][] getPieceGrid()
    {
        return pieceGrid;
    }
    public int getRandomP()
    {
        return (int)randomPiece;
    }
    //mutator methods
    public void setRow(int r)
    {
        row = r;
    }
    public void setCol(int c)
    {
        col = c;
    }
    //moving the whole block down
    public void userDownb()
    {
        row++;
    }
    //moves left
    public void userLeft()
    {
        col--;
    }
    //moves right
    public void userRight()
    {
        col++;
    }
    public int getOrientation()
    {
        return orientation;
    }
    //makes the values into 2
    public void MakePB()
    {
        isBlue = true;
        for(int r = 0; r<pieceGrid.length; r++)//this stuff too
            for(int c = 0; c<pieceGrid.length; c++)
            {
                if(pieceGrid[r][c] == 1)
                    pieceGrid[r][c] = 2;
            }
    }
    /*public void MakeTopCube()
    {
        for(int r = -1; r<2; r++)
        {
            pieceGrid[r][this.getCol()-1] = 0;
        }
    }*/
    //rotates the piece
    public void rotateRight()
    {
        int[][] tempArr = new int[pieceGrid.length][pieceGrid[0].length];
        for(int r = 0; r<pieceGrid.length;r++)
        {
            for(int c =0; c< pieceGrid[0].length; c++)
            {
                int tempR = r-1;
                int tempC = c-1;

                int t = tempR;
                tempR = tempC;
                tempC = -t;
                tempR++;
                tempC++;

                tempArr[tempR][tempC] = pieceGrid[r][c];
            }
        }
        pieceGrid = tempArr;
        orientation++;
        if(orientation > 4)
        {
            orientation = 1;
        }
    }
    //When the piece is at the end of the border, depending on its orientation it cant move left anymore when it should
    //shifts all the 1s in the grid to the left
    public void shiftWholeLeft()
    {
        for(int r = pieceGrid.length-1; r>-1; r--)
        {
            for(int c = 1; c<pieceGrid.length; c++)
            {
                if(pieceGrid[r][c] == 1)
                {
                    pieceGrid[r][c-1] = 1;
                    pieceGrid[r][c] = 0;
                }
            }
        }
    }
    //When the piece is at the end of the border, depending on its orientation it cant move left anymore when it should
    //shifts all the 1s in the grid to the right
    public void shiftWholeRight()
    {
        for(int r = pieceGrid.length-1; r>-1; r--)
        {
            for(int c = pieceGrid.length-1; c>-1; c--)
            {
                if(pieceGrid[r][c] == 1)
                {
                    pieceGrid[r][c+1] = 1;
                    pieceGrid[r][c] = 0;
                }
            }
        }
    }
    
    
    
    
    
    
    public void printPiece()
    {
        for(int[] row : pieceGrid)
        {
            for(int elem : row)
            {
                System.out.print(elem + " ");
            }
            System.out.println("");
        }
    }
}