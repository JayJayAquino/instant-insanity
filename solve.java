import java.lang.Math;
import java.io.*;
import java.util.*;

class Prism {

    int side1, side2, side3;
    int orientation = -1;
    
    public Prism(){
        side1 = -1;
        side2 = -1;
        side3 = -1;
    }

    public Prism(int color1, int color2, int color3){
        side1 = color1;
        side2 = color2;
        side3 = color3;
    }

    public void rotate(){

        if(orientation == 2) orientation = 0;
        else orientation++;

        int temp1, temp2, temp3;

        temp1 = side1;
        temp2 = side2;
        temp3 = side3;

        side1 = temp3;
        side2 = temp1;
        side3 = temp2;

    }

    public int getSide(int s){

        if(s == 1) return side1;
        else if (s == 2) return side2;
        else return side3;

    }

    public void setSide(int side, int color){
        if(side == 1) side1 = color;
        else if (side == 2) side2 = color;
        else side3 = color;
    }

    public int getOrientation(){
        return orientation;
    }


}

class solve{
    /**
     * Main program
     */
    public static void main(String[] Args){

        // PUZZLE 1
        HashMap<Integer, Prism> puzzle1 = generatePuzzle1();
        printPuzzle(puzzle1);
        HashMap<Integer, Integer> histogram1 = generateHistogram(puzzle1);
        int startingColor = largestColor(histogram1);
        printHistogram(histogram1, startingColor);
        if(validHistogram(histogram1, startingColor)){
            //find solution
        }else{
            int problemColor = problemColor(histogram1, startingColor, puzzle1);
            printMinObstacle(problemColor, puzzle1);
        }

        // PUZZLE 2
        HashMap<Integer, Prism> puzzle2 = generatePuzzle2();
        printPuzzle(puzzle2);
        HashMap<Integer, Integer> histogram2 = generateHistogram(puzzle2);
        startingColor = largestColor(histogram2);
        printHistogram(histogram2, startingColor);
        if(validHistogram(histogram2, startingColor)){
            //find solution
        }else{
            int problemColor = problemColor(histogram1, startingColor, puzzle1);
            printMinObstacle(problemColor, puzzle1);
        }

        // PUZZLE 3
        HashMap<Integer, Prism> puzzle3 = generatePuzzle3();
        printPuzzle(puzzle3);
        HashMap<Integer, Integer> histogram3 = generateHistogram(puzzle3);
        startingColor = largestColor(histogram3);
        printHistogram(histogram3, startingColor);
        if(validHistogram(histogram3, startingColor)){
            //find solution
        }else{
            int problemColor = problemColor(histogram1, startingColor, puzzle1);
            printMinObstacle(problemColor, puzzle1);
        }

        // PUZZLE 4
        HashMap<Integer, Prism> puzzle4 = generatePuzzle4();
        printPuzzle(puzzle4);
        HashMap<Integer, Integer> histogram4 = generateHistogram(puzzle4);
        startingColor = largestColor(histogram4);
        printHistogram(histogram4, startingColor);
        if(validHistogram(histogram4, startingColor)){
            //find solution
        }else{
            int problemColor = problemColor(histogram1, startingColor, puzzle1);
            printMinObstacle(problemColor, puzzle1);
        }
    }

    /**
     * Generates 1st puzzle using first formula
     * @return the puzzle
     */
    public static HashMap<Integer, Prism> generatePuzzle1(){

        HashMap <Integer,Prism> output = new HashMap<Integer, Prism>();
        int currentPos = 0;
        int[] list = new int[300]; // generate list with indexes 0 - 299


        //create list of values to be used by puzzle
        for(int i = 1; i <= 300; i++){
            list[i-1] = 1 + (((int) Math.floor(i*Math.PI)) % 100);
        }

        //generate the puzzle with random colors created
        for(int i = 0; i < 300; i = i+3){
            currentPos = i/3;
            output.put(currentPos, new Prism(list[i], list[i+1], list[i+2]));
        }

        return output;
    }

    public static HashMap<Integer, Prism> generatePuzzle2(){

        HashMap <Integer,Prism> output = new HashMap<Integer, Prism>();
        int currentPos = 0;
        int[] list = new int[300]; // generate list with indexes 0 - 299


        //create list of values to be used by puzzle
        for(int i = 1; i <= 300; i++){
            list[i-1] = 1 + (((int) Math.floor(i*Math.E)) % 100);
        }

        //generate the puzzle with random colors created
        for(int i = 0; i < 300; i = i+3){
            currentPos = i/3;
            output.put(currentPos, new Prism(list[i], list[i+1], list[i+2]));
        }

        return output;
    }

    public static HashMap<Integer, Prism> generatePuzzle3(){

        HashMap <Integer,Prism> output = new HashMap<Integer, Prism>();
        int currentPos = 0;
        int[] list = new int[300]; // generate list with indexes 0 - 299


        //create list of values to be used by puzzle
        for(int i = 1; i <= 300; i++){
            list[i-1] = 1 + (((int) Math.floor(i*Math.sqrt(2))) % 100);
        }

        //generate the puzzle with random colors created
        for(int i = 0; i < 300; i = i+3){
            currentPos = i/3;
            output.put(currentPos, new Prism(list[i], list[i+1], list[i+2]));
        }

        return output;
    }

    public static HashMap<Integer, Prism> generatePuzzle4(){

        HashMap <Integer,Prism> output = new HashMap<Integer, Prism>();
        int currentPos = 0;
        int[] list = new int[300]; // generate list with indexes 0 - 299


        //create list of values to be used by puzzle
        for(int i = 1; i <= 300; i++){
            list[i-1] = 1 + (((int) Math.floor(i*Math.sqrt(3))) % 100);
        }

        //generate the puzzle with random colors created
        for(int i = 0; i < 300; i = i+3){
            currentPos = i/3;
            output.put(currentPos, new Prism(list[i], list[i+1], list[i+2]));
        }

        return output;
    }

    /**
     * Prints the puzzle
     * @param puzzle
     */
    public static void printPuzzle(Map<Integer, Prism> puzzle){
        System.out.println("PUZZLE\n-----------------------\n");
        for(int i = 0; i < 100; i++){
            System.out.println("Prism: " + i + "\tvalues: " + puzzle.get(i).side1 + ", " + puzzle.get(i).side2 + ", " + puzzle.get(i).side3);
        }
        System.out.println("\n-----------------------\n");
    }

    /**
     * Prints the current histogram
     * @param histogram
     */
    public static void printHistogram(Map<Integer, Integer> histogram, int startColor){
        System.out.println("HISTOGRAM\n-----------------------\n");
        for(int i = 1; i <= startColor; i++){
          System.out.println("number of times color " + i + " occurs is: " + histogram.get(i));
        }
        System.out.println("\n-----------------------\n");

    }

    /**
     * generates a histogram based on given puzzle
     * @param puzzle
     * @return histogram
     */
    public static HashMap<Integer, Integer> generateHistogram(Map<Integer, Prism> puzzle){
        HashMap<Integer, Integer> histogram = new HashMap<Integer, Integer>();
        
        for(int i = 0; i<100; i++){
            Prism currentPrism = puzzle.get(i);
            for(int j = 1; j <= 3; j++){
                if(histogram.containsKey(currentPrism.getSide(j))){
                    histogram.replace(currentPrism.getSide(j), histogram.get(currentPrism.getSide(j)) + 1);
                } else {
                    histogram.put(currentPrism.getSide(j), 1);
                }
            }
        }

        return histogram;
    }
    
    /**
     * find the largest (int) color, used for starting point later on
     * @param histogram - current histogram
     * @return integer
     */
    public static int largestColor(Map<Integer, Integer> histogram){
        int largestColor = 1;
        Set<Integer> keySet = histogram.keySet();
        for(int key : keySet){
            if(largestColor < key){
                largestColor = key;
            } else{
                //do nothing
            }
        }

        return largestColor;
    }

    /**
     * Determines if a color appears more than 3 times, if it does then there is no solution
     * @param histogram - current histogram
     * @param startColor - biggest int (color)
     * @return boolean
     */
    public static boolean validHistogram(Map<Integer, Integer> histogram, int startColor){
        boolean output = true;
        for(int i = startColor; i > 0; i--){
            if(histogram.containsKey(i)){
                if(histogram.get(i) > 3){
                    output = false;
                } else {
                    // still valid
                }
            } else {
              //key doesn't exist  
            }
        }

        return output;
    }

    /**
     * Finds the color that occures the most
     * @param histogram - current histogram
     * @param startColor - largist (int) color in the puzzle
     * @return most occured color (int)
     */
    public static int problemColor(Map<Integer, Integer> histogram, int startColor, Map<Integer, Prism> puzzle){

        int numOfProblemPrisms = 4; //biggest min obstacle is 4
        int result = -1;

        for(int i = startColor; i > 0; i--){
            if(histogram.containsKey(i)){
                if(histogram.get(i) > 3){
                    if(numOfProblemPrisms(i, puzzle) <= numOfProblemPrisms){
                        numOfProblemPrisms = numOfProblemPrisms(i, puzzle);
                        result = i;
                    } else {
                        // current 'result' has less cubes for min obstacle
                    }
                } else {
                    // current color occurs less than stored result
                }
            } else {
                //key doesnt exist
            }
        }

        return result;
    }

    /**
     * Determine the number of prisms that use the "problem" color
     * @param color - potential problem color
     * @param puzzle - currnet puzzle
     * @return (int) num of problem prisms
     */
    public static int numOfProblemPrisms(int color, Map<Integer, Prism> puzzle){
        Set<Integer> keySet = puzzle.keySet();
        int count = 0;
        for(int key : keySet){
            if(puzzle.get(key).side1 == color || puzzle.get(key).side2 == color || puzzle.get(key).side3 == color){
                count++;
            }
        }

        return count;
    }

    /**
     * Print the min obstacle subset
     * @param color - problem color
     * @param puzzle - current puzzle
     */
    public static void printMinObstacle(int color, Map<Integer, Prism> puzzle){
        System.out.println("\nMIN OBSTACLE: \n");
        Set<Integer> keySet = puzzle.keySet();
        int count = 0;

        for(int key : keySet){
            if(puzzle.get(key).side1 == color || puzzle.get(key).side2 == color || puzzle.get(key).side3 == color){
                System.out.println("PRISM: " + key);
                if(puzzle.get(key).side1 == color) count++;
                if(count > 3) break;
                if(puzzle.get(key).side2 == color) count++;
                if(count > 3) break;
                if(puzzle.get(key).side3 == color) count++;
                if(count > 3) break;
            } else {
                //color is not on the current prism
            }
        }

        System.out.println("\n" + "--------------------------------------------" + "\n" + "--------------------------------------------");
    }

}