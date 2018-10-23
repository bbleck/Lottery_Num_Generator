package edu.cnm.deepdive;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MMGenerator implements Generator{

  public static final int POOL_UPPER_LIMIT = 70;
  public static final int BONUS_UPPER_LIMIT = 25;
  public static final int POOL_PICK_SIZE = 5;
  private SecureRandom rand;
  private int countLottoNums = 6;
  private int[] pool;

  public MMGenerator(SecureRandom rand){
    this.rand = rand;
    pool = IntStream.rangeClosed(1,POOL_UPPER_LIMIT).toArray();
  }

  @Override
  public int[] generate() {
//    List<Integer> toReturn = new LinkedList<>();
//    int whiteBall = rand.nextInt(70)+1;
//    for (int i = 0; i < 5; i++) {
//      while(toReturn.contains(whiteBall)){
//        whiteBall = rand.nextInt(70)+1;
//      }
//      toReturn.add(whiteBall);
//    }
//    Collections.sort(toReturn);
//    toReturn.add(rand.nextInt(25)+1);
//    int[] intReturn = new int[toReturn.size()];
//    for (int i = 0; i < 6; i++) {
//      intReturn[i] = toReturn.get(i);
//    }
//
//    return intReturn;
    int[] pick = new int[POOL_PICK_SIZE + 1];
    for (int target = pool.length - 1; target >= pool.length - POOL_PICK_SIZE; target--) {
      int source = rand.nextInt(target + 1);
      int temp = pool[target];
      pool[target] = pool[source];
      pool[source] = temp;
      pick[pool.length - 1 - target] = pool[target];
    }
    pick[POOL_PICK_SIZE] = rand.nextInt(BONUS_UPPER_LIMIT + 1);
    Arrays.sort(pick, 0, POOL_PICK_SIZE);
    return pick;
  }

//  public static void main(String[] args) {
//    MMGenerator mmGenerator = new MMGenerator(new Random());
//    int[] lottoNumbers = mmGenerator.generate();
//    for (int i = 0; i < mmGenerator.countLottoNums; i++) {
//      System.out.print(lottoNumbers[i] + ", ");
//    }
//  }

}
