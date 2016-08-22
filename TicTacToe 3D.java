package javaapplication2;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class ttt3d 
{
    private char b[][][]=new char[4][4][4];
    private int ctr = 0; // counts the number of moves

    Scanner keyboard = new Scanner(System.in);
    
    
    int cPlane, cRow, cCol; 
    public ttt3d(int p, int r, int c)
    {
	// this instantiates an object with initial 
	// move in plane p, row r and col c
	// initialize the b to all ?
	for (int i=0; i<4; i++)
		for (int j=0; j<4; j++)
			for (int k=0; k<4; k++)
				b[i][j][k]='-';
	// make the move for the user who is 'X'
	// assume that p,r,c is a legal move
	b[p][r][c]='X'; // initial move made by user
	cPlane=p;
	cRow=r;
	cCol=c;
	ctr++; // add 1 to move count

	startGame();
    }
    private void startGame()
    {
        st:
        do
        {
            computerMove(); // make only random move
            printb();
            if (win('O')) // did the computer win
            {
                System.out.println("sorry, you lost");
                printb();
                break st;
            }
            else 
		if (ctr==64)
		{
                    System.out.println("draw");
                    printb();
                    break st;
		}
                else 
                    printb();
            userMove();
            if (win('X')) // did the computer win
            {
                System.out.println("congrats, you won!");
                printb();
                break st;
            }
            ctr+=2;
        } // end do
	while (ctr<64);
	
    }
    private boolean win(char w)
    {
	for(int i=0;i<4;i++)
        {
            for (int k=0;k<4;k++)
            {
                if((b[i][0][k]==w)&&(b[i][1][k]==w)&&(b[i][2][k]==w)&&(b[i][3][k]==w))
                return true;
            }
        }

	for(int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                if((b[i][j][0]==w)&&(b[i][j][1]==w)&&(b[i][j][2]==w)&&(b[i][j][3]==w))
                return true;
            }
        }

	for(int j=0;j<4;j++)
        {
            for (int k=0;k<4;k++)
            {
                if((b[0][j][k]==w)&&(b[1][j][k]==w)&&(b[2][j][k]==w)&&(b[3][j][k]==w))
                return true;
            }
        }

	for(int i=0;i<4;i++)
        {
            if((b[i][0][0]==w)&&(b[i][1][1]==w)&&(b[i][2][2]==w)&&(b[i][3][3]==w))
                return true;

            if((b[i][3][0]==w)&&(b[i][2][1]==w)&&(b[i][1][2]==w)&&(b[i][0][3]==w))
                return true;
        } 

	for(int j=0;j<4;j++)
        {
            if((b[0][j][0]==w)&&(b[1][j][1]==w)&&(b[2][j][2]==w)&&(b[3][j][3]==w))
                return true;

            if((b[3][j][0]==w)&&(b[2][j][1]==w)&&(b[1][j][2]==w)&&(b[0][j][3]==w))
                return true;
        }

	for(int k=0;k<4;k++)
        {
            if((b[0][0][k]==w)&&(b[1][1][k]==w)&&(b[2][2][k]==w)&&(b[3][3][k]==w))
            return true;

            if((b[3][0][k]==w)&&(b[2][1][k]==w)&&(b[1][2][k]==w)&&(b[0][3][k]==w))
            return true;
        }
	if((b[0][0][0]==w)&&(b[1][1][1]==w)&&(b[2][2][2]==w)&&(b[3][3][3]==w))
            return true;

	if((b[3][0][0]==w)&&(b[2][1][1]==w)&&(b[1][2][2]==w)&&(b[0][3][3]==w))
            return true;

	if((b[3][3][0]==w)&&(b[2][2][1]==w)&&(b[1][1][2]==w)&&(b[0][0][3]==w))
            return true;

	if((b[3][0][3]==w)&&(b[2][1][2]==w)&&(b[1][2][1]==w)&&(b[0][3][0]==w))
            return true;
	
        return false;
    }
    private void computerMove()
    {
	// the new strategy is to first check to see if the computer can win
	// if so, make the winning move
        if (winOrBlockCheck('O')) // true means
            // computer made the winning move
            return;
	// if no winning move is possible the next strategy is
	// is to check to see if the user can win on the next move
	// if so, block his/her winning move
	if (winOrBlockCheck('X')) // true means
            // computer blocked a potential winning move
            return;
	// use a random number generator to find the 
	// first legal move 
	int p,r,c;
	do 
        {
            p=(int)(Math.random()*4);
            r=(int)(Math.random()*4);
            c=(int)(Math.random()*4);
        }
        while (b[p][r][c]!='-');
        b[p][r][c]='O';
		
    }
    private void printb()
    {
	for (int p=0; p<4; p++) // plane
	{
            for(int i=0;i<4;i++)// row
            {
                for (int k=0;k<4;k++) //col
                    System.out.print(""+ b[p][i][k]+"\t");
                System.out.println(); // print the row
            }
            System.out.println(); //skip 1 lines between planes 
        }
	System.out.println("* * * * * * * * * * * * *");
	System.out.println();
    }
    private void userMove()
    {
	int p,r,c;
        do 
        {
            System.out.print("ENTER PLANE (1-4): ");
            p=(keyboard.nextInt())-1;
            System.out.print("ENTER ROW (1-4): ");
            r=(keyboard.nextInt())-1;
            System.out.print("ENTER COLUMN (1-4): ");
            c=(keyboard.nextInt())-1;
        }
        while (b[p][r][c]!='-');
            b[p][r][c]='X';

    }
    private boolean winOrBlockCheck(char c)
    {
	for (int i=0; i<4; i++)
            for (int j=0; j<4; j++)
                for (int k=0; k<4; k++)
                    if (b[i][j][k]=='-') // try the move
                    {	
                        b[i][j][k]=c;    // for every empty square
                        // is it a winning move?
			if (win(c)) // if true make the move for computer
			{ 
                            b[i][j][k]='O';
                            return true;
                        }
                        else
                            // if not a winning 00move then undo the move
                            b[i][j][k]='-';
                    }
        return false; // there was no winning move for char c
				    	
    }
    public static void main(String args[])
    {
	ttt3d t = new ttt3d(0,0,0);
    }
}

