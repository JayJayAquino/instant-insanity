import java.lang.Math;
import java.io.*;
import java.util.*;

class Prism {

    int side1, side2, side3;
    int orientation = 0;
    
    public Prism(){

        side1 = 0;
        side2 = 0;
        side3 = 0;
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
    public static void main(String [] Args){
        HashMap<Integer, Prism> puzzle = generatePuzzle1();
        printPuzzle(puzzle);
    }

    public static HashMap<Integer, Prism> generatePuzzle1(){

        HashMap <Integer,Prism> output = new HashMap<Integer, Prism>();
        int currentPos = 0;
        int[] list = new int[300];


        for(int i = 1; i <= 300; i++){
            list[i-1] = 1 + (((int) Math.floor(i*Math.PI)) % 100);
        }

        for(int i = 0; i < 300; i = i+3){
            currentPos = i/3;
            output.put(currentPos, new Prism(list[i], list[i+1], list[i+2]));
        }

        return output;
    }

    public static void printPuzzle(Map<Integer, Prism> puzzle){
        for(int i = 0; i < 100; i++){
            System.out.println("Prism: " + i + "\tvalues: " + puzzle.get(i).side1 + ", " + puzzle.get(i).side2 + ", " + puzzle.get(i).side3);
        }
    }
}