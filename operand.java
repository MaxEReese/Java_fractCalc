import java.util.ArrayList;
public class operand{
 String operand;
 int numerator;
 int whole;
 int denominator;

 static boolean validIn = true;

 static int wholeFinal; //creates a variable to hold the simplified whole
 static int numeratorFinal; //creates the variable for the final calculated numerator
 static int denominatorFinal;//creates the variable for the final calculated denominator

 static String answer;

 public static String produceAnswer(String input){
 input = input.strip();

 String[] s = input.split(" ");//breakes the initial statement equation up into 3 parts
 if(s.length<3){
 return "invalid input";
 }

 ArrayList<operand> operands = new ArrayList<operand>();
 for(int i = 0; i < s.length; i+=2){
 operands.add(new operand(s[i]));
 }

 ArrayList<String> operators = new ArrayList<String>();
 for(int e = 0; e < s.length-1; e+=2){
 operators.add(s[e+1]);
 }

 if((math(operands.get(0), operators.get(0),
operands.get(1))).equals("Invalid")){
 return "Invalid Input";
 }
 math(operands.get(0), operators.get(0), operands.get(1));

 answer = simplify(wholeFinal, numeratorFinal, denominatorFinal);

 //if there are 2 operators that means that there are 3 operands
 for(int o = 2; o < operands.size(); o++){
 math(new operand(answer), operators.get(o-1), operands.get(o));
 answer = simplify(wholeFinal, numeratorFinal, denominatorFinal);
 }

 return answer;
 }
 //constructor for operand objects
 public operand(String operString){

 this.operand = operString;

 String[] op = operand.split("[_/]");//splits the first operand into its whole number if there is one, numerator, and denominator

 if (op.length == 3)//if the length is 3 that means that there is a whole, a numerator, and a denominator
 {
 //sets the variables to thei respective sections
 whole = Integer.parseInt(op[0]);
 numerator = Integer.parseInt(op[1]);
 denominator = Integer.parseInt(op[2]);
 if(numerator < 0 || denominator <= 0 || whole == 0){ //makes sure that the numerator is not negative and that the denominator is not negative or zero
 validIn = false;
 }
 }

 else if (op.length == 2)//if the length is 2 that means that there is only a numerator and denominator
 {
 //sets the whole to 0 and the other variables to their respective parts
 whole = 0;
 numerator = Integer.parseInt(op[0]);
 denominator = Integer.parseInt(op[1]);
 if(denominator <= 0){//makes sure that the denominator is not 0 or negative
 validIn = false;
 }
 }
 else {
 //if neither is triggered that measn that the statement is only a whole number which means that it can set the numerator as the whole and the denominator to 1
 whole = 0;
 numerator = Integer.parseInt(op[0]);
 denominator = 1;
 }
 }

 //method that does all of the math
 public static String math(operand op1, String operator, operand op2){

 boolean same;

 if (op1.denominator == op2.denominator)
 {
 same = true;
 }
 else{
 same = false;
 }

 if(operator.equals("+")){//if the operator is addition
 if(same){
 numeratorFinal = op1.numerator + op2.numerator;
 denominatorFinal = op1.denominator;
 }
 else{
 //sets the numerators to the numerator + the whole * the 1st denominator then multiplies all of that by the denominator of the second operand
 //to turn a mixed number into an impropper fraction then adds them
    numeratorFinal = (((op1.numerator + (op1.whole * op1.denominator)) * op2.denominator) + ((op2.numerator + (op2.whole * op2.denominator)) * op1.denominator));
    denominatorFinal = op1.denominator * op2.denominator;//multiplies the denominators to find a common variable
    }
 }
 else if(operator.equals("-")){// if the operator is subtraction
 if(same){
    numeratorFinal = op1.numerator - op2.numerator;
    denominatorFinal = op1.denominator;
 }
 else{
 //sets the numerators to the numerator + the whole * the 1st denominator then multiplies all of that by the denominator of the second operand
 //to turn a mixed number into an impropper fraction then subtracts them
 numeratorFinal = (((op1.numerator + (op1.whole * op1.denominator)) * op2.denominator) - ((op2.numerator + (op2.whole * op2.denominator)) * op1.denominator));
 denominatorFinal = op1.denominator * op2.denominator;//multiplies the denominators to find a common variable
 }
 }
 else if(operator.equals("*")){//if the operator is multiplication
 numeratorFinal = ((op1.numerator + Math.abs(op1.whole*op1.denominator)) * (op2.numerator + Math.abs(op2.whole*op2.denominator)));//sets the calculated numerator to the two numerators multiplied together  
 denominatorFinal = op1.denominator * op2.denominator;//sets the denominator to the two denominators multiplied together
 }
 else if(operator.equals("/")){//if the operator is division
 if(op2.operand.equals("0")){
 return "Invalid";
 }
 else{
 numeratorFinal = op1.numerator * op2.denominator;//sets the calculated numerator to the denominator of operand 1 multiplied with the numerator of operand2
 denominatorFinal = op1.denominator * op2.numerator;//sets the alculated denominator to the numerator of operand 1 multiplied with the denominator of operand 2
 if (denominatorFinal < 0) {
 numeratorFinal = -numeratorFinal;
 denominatorFinal = -denominatorFinal;
 }
 }
 }
 return "Invalid Input";
 }
 //method that simplifies the fractions
 public static String simplify(int whole, int numerator, int denominator){
 wholeFinal = (int)(numeratorFinal/denominatorFinal);
 if(numeratorFinal % 2 == 0 && denominatorFinal % 2 == 0){
 while(numeratorFinal % 2 == 0 && denominatorFinal % 2 == 0){
 numeratorFinal = numeratorFinal/2;
 denominatorFinal = denominatorFinal/2;
 }
 numeratorFinal -= wholeFinal*denominatorFinal;
 }
 else{
 int gcf = 1;

 for (int i = 1; i <= denominatorFinal; i++){
 if (denominatorFinal % i == 0 && numeratorFinal % i == 0){
 gcf = i;
 }
 }
 numeratorFinal = numeratorFinal - (denominatorFinal * wholeFinal);
 numeratorFinal = numeratorFinal / gcf;
 denominatorFinal = denominatorFinal/gcf;

 if (wholeFinal < 0)
 numeratorFinal = Math.abs(numeratorFinal);
 }
 if(validIn){
 if(wholeFinal != 0)
 {
 if(numeratorFinal != 0)
 {
 return wholeFinal + "_" + numeratorFinal + "/" +
denominatorFinal; //returns the calculated numerator and the calculated denominator

 }

 if(numeratorFinal == 0)
 {
 return wholeFinal + "";

 }
 }

 if(numeratorFinal == 0){
 return "0";
 }

 if(wholeFinal == 0)
 {
 return numeratorFinal + "/" + denominatorFinal;
 }
 return "";
 }
 else{
 return "invalid Input";
 }

 }

 //method that tests set inputs with determined outputs
 public static void runTests(){
 //an array to hold the test inputs
 String[] testIn = new String[]{produceAnswer("1/4 + 1_1/2"), produceAnswer("8/4 + 2"), produceAnswer("-1 * -1/2"), produceAnswer("-11/17 - - 1/17"), produceAnswer("0 / 25_462/543")};
 //an array to hold the expected test outputs
 String[] testOut = new String[]{"1_3/4", "4", "1/2", "-10/17", "0"};
 //runs through the arrays and gets the calculaed answer and compares it to the expected answer
 for(int i = 0; i < testIn.length; i++){
 System.out.println("Answer: " + testIn[i] + " | intended answer: " + testOut[i] + " Result: " + (testIn[i]).equals(testOut[i]));
 }
 }
}


 