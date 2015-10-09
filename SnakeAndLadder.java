
import java.util.HashMap;
import java.util.Scanner;

public class SnakeAndLadder {
   
    Scanner scan = new Scanner(System.in);
    int num_snakes;    // number of snakes
    int num_ladders;   // number of ladders;
    int num_players;   // numbers of players
   
    int board[] = new int[105];
    HashMap <Integer, String> mp = new HashMap <>();    // mapping of player number with name
    int[] player_position;                  // player position at any time
   
    int size_of_board = 100;     // size of board
   
    // input function
   
    public void input() {
       
        for (int i = 1; i < 105; i++) {
            board[i] = 0;
        }
       
        input_snakes();
        input_ladders();
        input_players();
       
    }
   
    // input snakes
   
    public void input_snakes() {
       
        System.out.println("Enter Number of SNAKES -> ");
        num_snakes = scan.nextInt();
       
        System.out.println("Enter Positions of SNAKES -> ");
       
        for (int i = 1; i <= num_snakes; i++) {
           
            int end_snake;
            end_snake = scan.nextInt();
            
            int start_snake;
            start_snake = scan.nextInt();
          
            
           
            if (end_snake > size_of_board || end_snake <= 0 || start_snake > size_of_board || start_snake <= 0) {
                System.out.println("INVALID DATA");
                i--;
                continue;
            }
           
            board[end_snake] = start_snake;
           
        }
       
    }
   
    // input ladders
   
    public void input_ladders() {
       
        System.out.println("Enter Number of LADDERS -> ");
        num_ladders = scan.nextInt();
       
        System.out.println("Enter Positions of LADDERS -> ");
       
        for (int i = 1; i <= num_ladders; i++) {
            int start_ladder;
            start_ladder = scan.nextInt();
          
            int end_ladder;
            end_ladder = scan.nextInt();
           
            if (board[start_ladder] > 0) {
                System.out.println("Starting of Ladder and Ending of Snake cannot be at same place");
                i--;
                continue;
            }
           
            if (end_ladder > size_of_board || end_ladder <= 0 || start_ladder > size_of_board || start_ladder <= 0) {
                System.out.println("INVALID DATA");
                i--;
                continue;
            }
           
            board[start_ladder] = end_ladder;
        }
       
    }
   
    // input players
   
    public void input_players() {
       
        System.out.println("Enter Number of Players");
        num_players = scan.nextInt();
       
        System.out.println("Enter Name of players");
       
        for (int i = 1; i <= num_players; i++) {
            String name = scan.next();
            mp.put(i, name);
        }
       
        player_position = new int[num_players + 1];
       
        for (int i = 1; i <= num_players; i++) {
            player_position[i] = 0;
        }
       
    }
   
    // game
    public void game_starts() {
       
        //System.out.println(num_players);
        System.out.println("\n------------------- Game Start --------------------");
       
        int turn = 1;
       
        while (true) {
            String name = mp.get(turn);
            System.out.println("\nEnter " + name + " turn");
           
            int num;
            num = scan.nextInt();
           
            if (num > 6 || num <= 0) {
                System.out.println("INVALID DATA");
                continue;
            }
           
            int temp = num + player_position[turn];
           
            if (temp == size_of_board) {
                System.out.println("\n************" + name + " WINS ************");
                break;
            }
           
            if (temp > size_of_board) {
                turn = (turn % num_players) + 1;
                
                System.out.println("\nPositions of Players");
           
                for (int i = 1; i <= num_players; i++) {
                    System.out.println(mp.get(i) + " -> " + player_position[i]);
                }
                
                continue;
            }
           
            player_position[turn] = temp;
           
            if (board[temp] > 0) {
              
                player_position[turn] = board[temp];
                System.out.print(name + " -- ");
               
                if (temp > player_position[turn]) {
                    System.out.println("SNAKE " + temp + ", " + player_position[turn]);
                }
               
                else if (temp < player_position[turn]) {
                    System.out.println("LADDER " + temp + ", " + player_position[turn]);
                }
               
            }
           
            System.out.println("\nPositions of Players");
           
            for (int i = 1; i <= num_players; i++) {
                System.out.println(mp.get(i) + " -> " + player_position[i]);
            }
           
            turn = (turn % num_players) + 1;
        }
       
    }
   
   
    public static void main(String[] args) {
       
        SnakeAndLadder obj = new SnakeAndLadder();
        obj.input();
        obj.game_starts();
      
    }
   
}
