
import java.util.*;

public class TicTacToe
{
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions  = new ArrayList<Integer>();

    public static void main(String[] args)
    {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};

        //calling printGameBoard method.
        printGameBoard(gameBoard);

        //While true iterates until a winner is declared.
        while(true)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPos = keyboard.nextInt();

            //Checking if position is taken.
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos))
            {
                System.out.println("position taken! Enter a correct position.");
                playerPos = keyboard.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            //Checking for result after user places piece, breaks out of while(true) loop if winner is declared.
            String result = checkWinner();
            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }

            //Generating a random CPU position.
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos))
            {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            //Checking for result after user places piece, breaks out of while(true) loop if winner is declared.
            result = checkWinner();
            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }
        }
        

    }//End of main method.

    /**
    * The printGameBoard method will print the game board.
     */
    public static void printGameBoard(char[][] gameBoard)
    {
         //Printing out the game board.
        for(char[] row : gameBoard)
        {
            for(char c : row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }//End of printGameBoard method.

    public static void placePiece(char[][] gameBoard, int pos, String user)
    {
        char symbol = ' ';

        //User symbol is 'X'.
        if(user.equals("player"))
        {
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")) //CPU symbol is 'O'.
        {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        
        //Adding symbols to the gameboard.
        switch(pos)
        {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }//End of placePiece method.

    public static String checkWinner()
    {
        //Creating Lists for each winnable position.
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        //Adding each List to a new ArrayList.
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        //Displaying end condition.
        for(List l : winning)
        {
            if(playerPositions.containsAll(l))
            {
                return "Congratulations you won!";
            }
            else if(cpuPositions.containsAll(l))
            {
                return "CPU wins! Sorry";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9)
            {
                return "TIE!";
            }
        }

        return "";
    }//End of checkWinner method.
}