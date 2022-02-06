package com.bhoiwala;

import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        // write your code here
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};


        printGameBoard(gameBoard);

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement:(1-9): ");
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position already taken!");
                System.out.println("Enter your placement:(1-9): ");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();
            if(!result.isEmpty()) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;       //does 1-9
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            result = checkWinner();
            if(!result.isEmpty()) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }

    }


    public static void printGameBoard(char[][]gameBoard) {
        for(char[]row: gameBoard) {       //each row in the 2 d array
            for (char c : row) {          //for each char in row we want to print the symbol
                System.out.print(c);
            }
            System.out.println();

        }
    }
    public static void placePiece(char[][]gameBoard, int playerPos, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(playerPos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(playerPos);
        }
        switch (playerPos) {
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
    }
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);


        List<List>winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);
        for(List l : winning) {
            if(playerPositions.containsAll(l)) {        //list contains all the elements in whichever row or column
                return "Congrats you won!";
            } else if(cpuPositions.containsAll(l)) {
                return "Cpu wins! sorry:(";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {      //none win and board full
                return "TIE!";

            }

        }



        return "";
    }

}


