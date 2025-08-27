package lastpencil;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int number = 0;                               // input validation for number of pencils
        while (true) {
            System.out.println("How many pencils would you like to use:");
            String input = scanner.nextLine();

            try {
                number = Integer.parseInt(input);
                if (number <= 0) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric"); 
            }   
        }
        
        String first = "";                            // input validation for first player
        while (true) {
            System.out.println("Who will be the first (John, Jack):");
            first = scanner.nextLine();

            if (first.equals("John") || first.equals("Jack")) {
                break;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }
        
        for (int i = 1; i <= number; i++) {            // display initial pencils
            System.out.print("|");
        }
        System.out.println();

        String currentPlayer = first;       
        
        while (number > 0) {                          // main game loop
            System.out.println(currentPlayer + "'s turn:");
            
            int reduce = 0;            
            
            if (currentPlayer.equals("John")) {       // check if current player is John (human) or Jack (bot)                
                boolean validMove = false;            // John: get input and validate
                while (!validMove) {
                    String moveInput = scanner.nextLine();

                    try {
                        reduce = Integer.parseInt(moveInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");   
                        continue;
                    }                    
                    
                    if (reduce < 1 || reduce > 3) {    
                        System.out.println("Possible values: '1', '2' or '3'");
                        continue;
                    } else if (reduce > number) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }

                    validMove = true;                   
                }
            } else {                
                reduce = getBotMove(number);
                System.out.println(reduce);
            }
            
            number -= reduce;                         // execute the move
            
            for (int i = 1; i <= number; i++) {
                System.out.print("|");
            }
            System.out.println();
            
            if (number == 0) {                        // winner
                String winner = currentPlayer.equals("John") ? "Jack" : "John";
                System.out.println(winner + " won!");
                break;
            }
            
            currentPlayer = currentPlayer.equals("John") ? "Jack" : "John";
        }
    }    
    
    public static int getBotMove(int pencils) {
        Random rand = new Random();
        
        if (pencils == 1) {
            return 1; 
        } else if (pencils % 4 == 1) {
            return rand.nextInt(3) + 1;             // random move (losing position anyway)
        } else if (pencils % 4 == 0) {
            return 3; 
        } else if (pencils % 4 == 3) {
            return 2; 
        } else { 
            return 1; 
        }
    }
}
