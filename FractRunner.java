/*Contributions:
Max:
runTests method
Calculations
Separated equations into parts
Shawn:
Simplification
Valid inputs and outputs
Runner class
*/
import java.util.Scanner;
public class FractRunner{

 private static String quit = "quit";
 private static String input = "";
    public static void main(String[] args)
       {
          Scanner sc = new Scanner(System.in);
          System.out.println("Weclome to and thank you for using the Fraction Calculator");
      
       while (!(input.equalsIgnoreCase(quit))){
          System.out.println("Please enter an equaltion or type quit to quit");
          input = sc.nextLine();
         
       if(input.equalsIgnoreCase("test")){
          operand.runTests();
          }
       else if(input.equalsIgnoreCase(quit)){
          System.out.println("Goodbye and have a nice day");
       }
       else{
         System.out.println(operand.produceAnswer(input));
       }
    }
 }

}