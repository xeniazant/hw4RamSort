/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ramsort;

/**
 *
 * @author xeniazantello
 */

import java.util.Arrays;
import java.util.Random;





public class RamSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//  12 points: In the main method, invoke all the methods you've implemented (described below) at least five times 
//  each on different inputs, for testing purposes.  Write all the inputs and the results into the console.
//  This is the "testing" portion of your code.

    int[] test0 = {0,1,3,1,4,2};
    int[] test1 = {2, 3, 10, 0, 4, 7, 8, 9, 2, 6};
    int[] test2 = {4, 9, 3, 5, 8, 10, 17, 0, 11};
    int[] test3 = {15, 1, 20, 21, 7, 17, 2, 8, 14, 6};
    int[] test4 = {15, 37, 1, 25, 8, 39, 18, 34, 33, 27, 2, 10, 35};
    
    int[] test0Out = new int[test0.length];
    int[] test1Out = new int[test1.length];
    int[] test2Out = new int[test2.length];
    int[] test3Out = new int[test3.length];
    int[] test4Out = new int[test4.length];
    
    System.out.println("/////------------------ COUNTING SORT TESTING ----------------------/////");
    
    System.out.println("Test0 original: " + Arrays.toString(test0));
    countingSort(test0, test0Out, 4);
    System.out.println("Test0 after countingSorted: " + Arrays.toString(test0Out));
     
    System.out.println("Test1 original: " + Arrays.toString(test1));
    countingSort(test1, test1Out, 10);
    System.out.println("Test1 after countingSorted: " + Arrays.toString(test1Out));
    
    System.out.println("Test2 original: " + Arrays.toString(test2));
    countingSort(test2, test2Out, 17);
    System.out.println("Test2 after countingSorted: " + Arrays.toString(test2Out));
    
    System.out.println("Test3 original: " + Arrays.toString(test3));
    countingSort(test3, test3Out, 21);
    System.out.println("Test3 after countingSorted: " + Arrays.toString(test3Out));
    
    System.out.println("Test4 original: " + Arrays.toString(test4));
    countingSort(test4, test4Out, 39);
    System.out.println("Test4 after countingSorted: " + Arrays.toString(test4Out));
    
    System.out.println("/////------------------ END OF COUNTING SORT TESTING ----------------------/////");
    
    System.out.println("/////------------------ RANDOMIZED QUICK SELECT TESTING ----------------------/////");
    
    System.out.println("test0 randomizedQuickSelect on test0 for order statistic 2: " + randomizedQuickSelect(test0, 0 , 5 , 2));
    System.out.println("test1 randomizedQuickSelect on test1 for order statistic 5: " + randomizedQuickSelect(test1, 0 , 9 , 5));
    System.out.println("test2 randomizedQuickSelect on test2 for order statistic 5: " + randomizedQuickSelect(test2, 0 , 8 , 5));
    System.out.println("test3 randomizedQuickSelect on test3 for order statistic 4: " + randomizedQuickSelect(test3, 0 , 8 , 4));
    System.out.println("test4 randomizedQuickSelect on test4 for order statistic 6: " + randomizedQuickSelect(test4, 0 , 12 , 6));
    
    }
    
//  24 points: Define a method named countingSort. Implement Counting Sort as in the slides/textbook. One parameter will
// be the input array to sort. Another parameter will be a memory allocated array of the same length, for storing the 
// output. The third parameter will be the largest single input value. You may write your code one-indexed or
// zero-indexed, but be aware the pseudocode one-indexes the input and output while zero-indexing the auxiliary array C.
    
    public static void countingSort(int[] array, int[] out, int largest){
        int[] countArr = new int[largest + 1];
        for(int i : countArr){
            countArr[i] = 0; //set all values in our count array to 0.
        }
        for(int i = 1; i < array.length  ; i++ ){
            countArr[array[i]] ++;
        }
        for(int i = 1 ; i < countArr.length ; i++  ){
            countArr[i] = countArr[i] + countArr[i - 1];
        }
        //System.out.println(Arrays.toString(countArr));
        for(int i = array.length - 1; i > 0 ; i-- ){
            out[countArr[array[i]]] = array[i];
            countArr[array[i]] --;
        }
        
    }
    
    
    
    
    
//  24 points: Define a method named randomizedQuickselect. Implement Randomized Quickselect as in the slides/textbook. 
//  One parameter is the input array.  Two more parameters are the inclusive bounds of the subarray to consider. 
//  One more parameter is i, the order statistic number to find the value of in the input array. This method must
//  be nondestructive, meaning it does not reorder or modify the original input array! So, you will want to make a local 
//  copy of the input array using java.util.Arrays.copyOf, then use the local copy for everything. You will also need 
//  to reuse the same partition method from homework 4, which you used for quicksort.  If your partition method is not 
//  working properly, I strongly recommend you debug it and get it working correctly before moving on to the rest of 
//  this assignment.
    
    public static int randomizedQuickSelect(int[] array, int lBound, int uBound, int orderStat){
        int[] arrayCop = java.util.Arrays.copyOf(array, array.length);
        if(lBound == uBound){
            return arrayCop[lBound];
        }
        Random rndm = new Random(); // made a new random number generator
        int ran = rndm.nextInt((uBound - lBound) + 1 ) + lBound; // this will be our random pivot choice
        int holder = arrayCop[uBound];
        arrayCop[uBound] = arrayCop[ran];
        arrayCop[ran] = holder;
        int q = partition(arrayCop, lBound, uBound);
        int pivStat = q - lBound + 1; // the order statistic of the pivot
        if(orderStat == pivStat){
            return arrayCop[q];
        }
        if(orderStat < pivStat){
            return randomizedQuickSelect(arrayCop , lBound , q - 1 , orderStat );
        }
        return randomizedQuickSelect(arrayCop , q + 1 , uBound , orderStat - pivStat);
        
        
    }
    
    
    
    
    private static int partition(int[] array, int start, int end){
        int pivot = array[end];
        int sb = start -1; // sb (small bucket) is a variable describing the largest inclusive index of the smaller bucket
        for(int i = start; i <= end -1; i ++){
            if(array[i] <= pivot){
                sb ++;
                int holder = array[sb]; // holder simply hold one of the values we are swapping so it is not destroyed.
                array[sb] = array[i];
                array[i] = holder;
            }
        }
        int holder1 = array[sb + 1]; //holder 1 works the same as holder except for this swap
        array[sb + 1] = array[end];
        array[end] = holder1;
        return sb + 1;
    }
    
//    
//    10 points extra credit: Define a method named radixSort, implementing a base-10 Radix Sort as in the 
//    slides/textbook. This will only be a few lines of code to define the radixSort method, but you 
//    will also need to create some private helper methods:


//    A private helper method named getDigit to obtain and return the value of a single given base-10 digit's value from
//    a given int value. For example, getDigit(1, 493) would return the value of the "1's place" in 493, which is 
//    the number 3. You can make that helper method a purely math function, or instead make it with String methods.
    /**
     * 
     * @param place describes the the digits place which we are looking for Must be 1 or a multiple of 10
     * @param orgNum
     * @return 
     */
//    public static int getDigit(int place, int orgNum){
//        int digit = orgNum % (10^(place));
//        digit = digit / ((place - 1) * 10);
//        return digit;
//    }
//    
    
//    A private helper method named countingSortOnDigit, which is a modified version of countingSort with an additional
//    parameter indicating which base-10 digit is to be used for comparisons. (1 for rightmost digit, 2 for second 
//    digit from the right, etc.) Make sure your countingSort works properly before you make this new version.  
//    The only difference will be the line(s) of code that directly compare two elements, which will invoke digitOf 
//    and use that single digit value, instead of comparing one entire input value with another.





}
