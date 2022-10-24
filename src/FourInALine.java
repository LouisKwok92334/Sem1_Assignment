/*
   Filename: FourInALine.java
   Programmer:  Kwok Pui Nam
   Description:	Four-in-a-Line is a two-players game.
*/
import java.util.Scanner;

public class FourInALine {
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);

        int [] [] mTable = new int [6] [7];
        int playerNumber, column = 0, columnWin = 0, rowWin = 0, leftSlashWin = 0, rightSlashWin = 0, checkFull;
        boolean startGame = false, player = true, game = true;

        while (true) { //Start Game
            playerNumber = player ? 1 : 2; //Change player number
            checkFull = 0; //Count how many locations are used

            if (startGame) { //This is implemented only after the game's framework has been shown once
                System.out.print("Player " + playerNumber + " type a column (0-6) or 9 to quit current game: ");
                column = input.nextInt(); //Enter the number of columns you want to put in

                if (column == 9) { //If you type 9, the game ends
                    System.out.println("Bye Bye!");
                    break;
                } else if (column < 0 || column > 6) { //Do not enter numbers other than 0 to 6
                    System.out.println("Range of column should be 0 to 6!");
                    continue;
                } else if (mTable[mTable.length - 1][column] != 0) { //If the column is full, you cannot enter anymore
                    System.out.println("Column " + column + " is full!");
                    continue;
                } else {
                    for (int x = 0; x <= mTable.length; x++) { //If the column is not full, it can be entered
                        if(mTable[x][column] == 0) {
                            mTable[x][column] = playerNumber;
                            break;
                        }
                    }
                }
            }

            for (int x = mTable.length - 1; x >= 0; x--) { //Show the current game progress
                System.out.format("%4d |", x);
                for (int y = 0; y < mTable[x].length; y++) {
                    System.out.format("%4d", mTable[x][y]);
                }
                System.out.println();
            }
            System.out.println("         -------------------------");
            System.out.format("      ");
            for (int x = 0; x <= mTable.length; x++) {
                System.out.format("%4d", x);
            }
            System.out.println();

            if (!startGame) { //When start game, show the frame of the game once
                startGame = true;
                continue;
            }

            for (int x = 0; x < mTable.length; x++) { //Determine if anyone wins on the columns
                if (rowWin == 4 || rightSlashWin == 4) { //When someone wins, the game stops
                    game = false;
                    break;
                } else if (mTable[x][column] == playerNumber) {
                    columnWin++;
                    if (columnWin == 4) { //When someone wins, the game stops
                        game = false;
                        break;
                    } else if (x == mTable.length - 1) {
                        columnWin = 0;
                    }
                } else {
                    columnWin = 0;
                }
                for (int y = 0; y < mTable[x].length; y++) { //Determine if anyone wins on the rows
                    if (mTable[x][y] != 0) {
                        checkFull++;
                    }
                    if (rightSlashWin == 4) { //When someone wins, the game stops
                        game = false;
                        break;
                    } else if (mTable[x][y] == playerNumber) {
                        rowWin++;
                        if (rowWin == 4) { //When someone wins, the game stops
                            game = false;
                            break;
                        } else if (y == mTable[x].length - 1) {
                            rowWin = 0;
                        }
                    } else {
                        rowWin = 0;
                    }
                    for (int z = 0; z < mTable[x].length - 1; z++) { //Determine if the diagonal line from right to left wins
                        if (x + z >= mTable.length || y + z >= mTable[x].length) {
                            rightSlashWin = 0;
                            break;
                        } else if (mTable[x + z][y + z] == playerNumber) {
                            rightSlashWin++;
                            if (rightSlashWin == 4) { //When someone wins, the game stops
                                game = false;
                                break;
                            }
                        } else {
                            rightSlashWin = 0;
                        }
                    }
                    for (int z = 0; z < mTable[x].length - 1; z++) { //Determine if the diagonal line from left to right wins
                        if (x + z >= mTable.length || y - z < 0) {
                            leftSlashWin = 0;
                            break;
                        } else if (mTable[x + z][y - z] == playerNumber) {
                            leftSlashWin++;
                            if (leftSlashWin == 4) { //When someone wins, the game stops
                                game = false;
                                break;
                            }
                        } else {
                            leftSlashWin = 0;
                        }
                    }
                }
            }

            if (checkFull >= 42) { //If all the numbers are filled, no one wins
                System.out.println("No player win this game!");
                break;
            } else if (game) { //Change player
                player = !player;
            } else { //That's what happens when someone wins
                System.out.println("player " + playerNumber + " win this game!");
                break;
            }
        }
    }
}