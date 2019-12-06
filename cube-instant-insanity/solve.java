import java.lang.Math;
import java.io.*;
import java.util.*;

class Cube {
  private int[] pair1;
  private int[] pair2;
  private int[] pair3;
  private boolean mark1, mark2, mark3;

  public Cube(int[] pair1, int[] pair2, int[] pair3)
  {
    this.pair1 = pair1;
    this.pair2 = pair2;
    this.pair3 = pair3;
    this.mark1 = false;
    this.mark2 = false;
    this.mark3 = false;
  }

  public int[] getPair(int pair){
    if(pair == 1) return pair1;
    else if(pair == 2) return pair2;
    else return pair3;
  }

  public void setMark1(boolean mark){
    this.mark1 = mark;
  }

  public void setMark2(boolean mark){
    this.mark2 = mark;
  }

  public void setMark3(boolean mark){
    this.mark3 = mark;
  }

  public boolean getMark1(){
    return mark1;
  }

  public boolean getMark2(){
    return mark2;
  }

  public boolean getMark3(){
    return mark3;
  }

}

public class solve {
  public static void main(String Args []){

    // Create puzzle
    HashMap<Integer, Cube> puzzle = new HashMap<Integer, Cube>();

    //puzzle 2
    // puzzle.put(1, new Cube( new int[]{1,8}, new int[]{8,8}, new int[]{6,8}) );
    // puzzle.put(2, new Cube( new int[]{4,8}, new int[]{1,8}, new int[]{1,3}) );
    // puzzle.put(3, new Cube( new int[]{6,7}, new int[]{8,8}, new int[]{3,3}) );
    // puzzle.put(4, new Cube( new int[]{5,7}, new int[]{8,8}, new int[]{2,5}) );
    // puzzle.put(5, new Cube( new int[]{4,8}, new int[]{3,7}, new int[]{1,7}) );
    // puzzle.put(6, new Cube( new int[]{5,5}, new int[]{2,2}, new int[]{2,3}) );
    // puzzle.put(7, new Cube( new int[]{4,5}, new int[]{1,4}, new int[]{5,8}) );
    // puzzle.put(8, new Cube( new int[]{7,8}, new int[]{1,6}, new int[]{4,8}) );

    //puzzle 3
    puzzle.put(1, new Cube( new int[]{1,8}, new int[]{8,8}, new int[]{1,6}) );
    puzzle.put(2, new Cube( new int[]{1,2}, new int[]{1,8}, new int[]{1,3}) );
    puzzle.put(3, new Cube( new int[]{6,7}, new int[]{2,8}, new int[]{1,1}) );
    puzzle.put(4, new Cube( new int[]{5,7}, new int[]{3,8}, new int[]{2,5}) );
    puzzle.put(5, new Cube( new int[]{4,7}, new int[]{3,7}, new int[]{1,7}) );
    puzzle.put(6, new Cube( new int[]{5,5}, new int[]{1,1}, new int[]{2,3}) );
    puzzle.put(7, new Cube( new int[]{1,4}, new int[]{1,4}, new int[]{5,6}) );
    puzzle.put(8, new Cube( new int[]{7,7}, new int[]{1,1}, new int[]{4,6}) );


    //puzzle 4
    // puzzle.put(1, new Cube( new int[]{1,8}, new int[]{8,8}, new int[]{5,8}) );
    // puzzle.put(2, new Cube( new int[]{4,5}, new int[]{1,8}, new int[]{1,3}) );
    // puzzle.put(3, new Cube( new int[]{6,7}, new int[]{2,8}, new int[]{3,5}) );
    // puzzle.put(4, new Cube( new int[]{5,7}, new int[]{3,8}, new int[]{2,6}) );
    // puzzle.put(5, new Cube( new int[]{4,7}, new int[]{3,7}, new int[]{1,6}) );
    // puzzle.put(6, new Cube( new int[]{5,5}, new int[]{2,2}, new int[]{2,3}) );
    // puzzle.put(7, new Cube( new int[]{4,5}, new int[]{1,4}, new int[]{5,6}) );
    // puzzle.put(8, new Cube( new int[]{7,7}, new int[]{1,6}, new int[]{4,6}) );

    printCubes(puzzle);
    System.out.println();
    solve(puzzle);


  }

  //used for testing
  public static void printCubes(Map<Integer, Cube> puzzle){
    for(int i = 1; i<puzzle.size()+1; i++){
      System.out.println("cube" + i);
      System.out.println("Pair 1: " + puzzle.get(i).getPair(1)[0] + "," + puzzle.get(i).getPair(1)[1]);
      System.out.println("Pair 2: " + puzzle.get(i).getPair(2)[0] + "," + puzzle.get(i).getPair(2)[1]);
      System.out.println("Pair 3: " + puzzle.get(i).getPair(3)[0] + "," + puzzle.get(i).getPair(3)[1]);
      System.out.println("\n");
    }
  }

  public static void printHistogram(Map<Integer, Integer> puzzle){
    System.out.println("HISTOGRAM");
    for(int i = 1; i<puzzle.size()+1; i++){
      System.out.println("number of times " + i + " occurs is: " + puzzle.get(i) + "\n");
    }
  }

  public static void updateHistogram(Map<Integer, Integer> histogram, int color, boolean remove){
    if(remove){
      histogram.replace(color, histogram.get(color)-1);
    } else {
      histogram.replace(color, histogram.get(color)+1);
    }
  }

  public static boolean validAdd(Map<Integer, Integer> histogram, Map<Integer, Integer> temp){
    boolean result = true;
    for(int i = 1; i<histogram.size()+1; i++){
      if(histogram.get(i) + temp.get(i) > 4) result = false;
    }
    return result;
  }

  public static void solve(Map<Integer,Cube> puzzle){
    HashMap<Integer, Integer> histogram = new HashMap<Integer, Integer>();
    Cube currentCube;
    int currentColor = 0;
    HashMap<Integer, Integer> tempHistogram = new HashMap<Integer, Integer>();
    Stack<int[][]> stack = new Stack<int[][]>();
    Stack<Integer> methodStack = new Stack<Integer>();

    //fill Histogram
    for(int i = 1; i<puzzle.size()+1; i++){
      currentCube = puzzle.get(i);
      for(int j = 1; j < 4; j++){
        currentColor = currentCube.getPair(j)[0];
        if(histogram.containsKey(currentColor)){
          histogram.replace(currentColor, histogram.get(currentColor)+1);
        } else {
          histogram.put(currentColor, 1);
        }
        currentColor = currentCube.getPair(j)[1];
        if(histogram.containsKey(currentColor)){
          histogram.replace(currentColor, histogram.get(currentColor)+1);
        } else {
          histogram.put(currentColor, 1);
        }
      }
    }

    printHistogram(histogram);

    histogram.replace(1, 0);
    histogram.replace(2, 0);
    histogram.replace(3, 0);
    histogram.replace(4, 0);
    histogram.replace(5, 0);
    histogram.replace(6, 0);
    histogram.replace(7, 0);
    histogram.replace(8, 0);

    tempHistogram.put(1, 0);
    tempHistogram.put(2, 0);
    tempHistogram.put(3, 0);
    tempHistogram.put(4, 0);
    tempHistogram.put(5, 0);
    tempHistogram.put(6, 0);
    tempHistogram.put(7, 0);
    tempHistogram.put(8, 0);

    stack.push(new int[][]{puzzle.get(1).getPair(1), puzzle.get(1).getPair(2)});

    updateHistogram(histogram, stack.peek()[0][0], false);
    updateHistogram(histogram, stack.peek()[0][1], false);
    updateHistogram(histogram, stack.peek()[1][0], false);
    updateHistogram(histogram, stack.peek()[1][1], false);
    methodStack.push(1); // 1: 1-2, 2:1-3, 3:2-3

    int currentPosition = 2;
    int currentMethod = 1;
    boolean done = false;
    while(!done){
      tempHistogram.replace(1,0);
      tempHistogram.replace(2,0);
      tempHistogram.replace(3,0);
      tempHistogram.replace(4,0);
      tempHistogram.replace(5,0);
      tempHistogram.replace(6,0);
      tempHistogram.replace(7,0);
      tempHistogram.replace(8,0);

      if(currentMethod == 1) {
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(1)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(1)[1], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(2)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(2)[1], false);

        if(validAdd(histogram, tempHistogram)){
          stack.push(new int[][]{puzzle.get(currentPosition).getPair(1), puzzle.get(currentPosition).getPair(2)});
          updateHistogram(histogram, stack.peek()[0][0], false);
          updateHistogram(histogram, stack.peek()[0][1], false);
          updateHistogram(histogram, stack.peek()[1][0], false);
          updateHistogram(histogram, stack.peek()[1][1], false);
          methodStack.push(1);
          if(currentPosition == 8){
            break;
          } else{
            currentPosition++;
            currentMethod = 1;
          }
        } else {
          currentMethod++;
        }
      }else if(currentMethod == 2){
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(1)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(1)[1], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(3)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(3)[1], false);
        if(validAdd(histogram, tempHistogram)){
           stack.push(new int[][]{puzzle.get(currentPosition).getPair(1), puzzle.get(currentPosition).getPair(3)});
           updateHistogram(histogram, stack.peek()[0][0], false);
           updateHistogram(histogram, stack.peek()[0][1], false);
           updateHistogram(histogram, stack.peek()[1][0], false);
           updateHistogram(histogram, stack.peek()[1][1], false);
           methodStack.push(2);
           if(currentPosition == 8){
             break;
           } else{
             currentPosition++;
             currentMethod = 1;
           }
         }else{
           currentMethod++;
         }
      }else if(currentMethod == 3){
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(2)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(2)[1], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(3)[0], false);
        updateHistogram(tempHistogram, puzzle.get(currentPosition).getPair(3)[1], false);
        if(validAdd(histogram, tempHistogram)){
          stack.push(new int[][]{puzzle.get(currentPosition).getPair(2), puzzle.get(currentPosition).getPair(3)});
          updateHistogram(histogram, stack.peek()[0][0], false);
          updateHistogram(histogram, stack.peek()[0][1], false);
          updateHistogram(histogram, stack.peek()[1][0], false);
          updateHistogram(histogram, stack.peek()[1][1], false);
          methodStack.push(3);
          if(currentPosition == 8){
            break;
          } else{
            currentPosition++;
            currentMethod = 1;
          }
        }else{
          currentMethod++;
        }
      } else {
        updateHistogram(histogram, stack.peek()[0][0], true);
        updateHistogram(histogram, stack.peek()[0][1], true);
        updateHistogram(histogram, stack.peek()[1][0], true);
        updateHistogram(histogram, stack.peek()[1][1], true);
        stack.pop();
        currentMethod = methodStack.pop()+1;
        currentPosition--;
        if(currentPosition == 1){
          if(currentMethod == 4){
            done = true;
          }else if(currentMethod == 3){
            stack.push(new int[][]{puzzle.get(1).getPair(2), puzzle.get(1).getPair(2)});
            updateHistogram(histogram, stack.peek()[0][0], false);
            updateHistogram(histogram, stack.peek()[0][1], false);
            updateHistogram(histogram, stack.peek()[1][0], false);
            updateHistogram(histogram, stack.peek()[1][1], false);
            methodStack.push(3);
            currentPosition++;
            currentMethod = 1;
          }else if(currentMethod == 2){
            stack.push(new int[][]{puzzle.get(1).getPair(1), puzzle.get(1).getPair(3)});
            updateHistogram(histogram, stack.peek()[0][0], false);
            updateHistogram(histogram, stack.peek()[0][1], false);
            updateHistogram(histogram, stack.peek()[1][0], false);
            updateHistogram(histogram, stack.peek()[1][1], false);
            methodStack.push(2);
            currentPosition++;
            currentMethod = 1;
          } else {
            System.out.println("something went wrong");
          }
        }

      }
    }//while

    if(stack.isEmpty()){
      System.out.println("NO SOLUTION");
    }else{
      System.out.println("SOLUTION");
      int temp = 8;
      while(temp != 0){
        System.out.print("Cube " + temp + ": " + stack.peek()[0][0] + "-" + stack.peek()[0][1]);
        System.out.print("  " + stack.peek()[1][0] + "-" + stack.peek()[1][1]);
        System.out.println();
        stack.pop();
        temp--;
      }
    }

  }//function


}
