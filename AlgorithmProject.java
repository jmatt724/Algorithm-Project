//Justin Mattix
//CS-330
//Algorithms Final Project

import java.util.Scanner;
import java.security.SecureRandom;
import java.util.Arrays;

public class AlgorithmProject {
    static int merge_counter = 0;
    
    public static void main(String args[]){
        int[] list_sizes;
        list_sizes = new int[5];
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a n value: ");
        list_sizes[0] = s.nextInt();
        for(int i = 1; i < 5; i++){
            list_sizes[i] = list_sizes[i-1] * 2;
        }
        s.close();
        //sum of the time in milliseconds of each sort
        double sumTimesSelect = 0;
        double sumTimesMerge = 0; 
        //sum of the number of operations of each sort
        int sumOperationsSelect = 0;
        int sumOperationsMerge = 0;
        int counter = 0;
        //convert nano to milli
        double conversion = 1000000;
        for(int number : list_sizes){
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            if(number != 0){
                int[] list = randomList(number);
                int[] list2 = randomList(number);
                //for timing the sorting time
                double start = System.nanoTime();
                int s_operations = SelectionSort(list);
                double stop = System.nanoTime();
                System.out.println(Arrays.toString(list));
                System.out.println("Selection Sort time: " + ((stop - start) / conversion) + " milliseconds");
                System.out.println("Selection Sort operations: " + (s_operations) + " operations");
                sumTimesSelect += ((stop - start) / conversion);
                sumOperationsSelect += s_operations;

                System.out.println("---------------------");
                double start2 = System.nanoTime();
                MergeSort(list2);
                double stop2 = System.nanoTime();
                System.out.println(Arrays.toString(list2));
                System.out.println("MergeSort time: " + ((stop2 - start2) / conversion) + " milliseconds");
                System.out.println("MergeSort Sort operations: " + (merge_counter) + " merges");

                sumTimesMerge += ((stop2 - start2) / conversion);
                sumOperationsMerge += merge_counter;
                counter++;
            }
        }
        System.out.println("--------------------------");
        System.out.println("Averages");
        System.out.println(Arrays.toString(list_sizes));
        System.out.println(counter);
        System.out.println("---------------------------");
        System.out.println("Average Runtime Selection: " + sumTimesSelect / counter + " milliseconds");
        System.out.println("Average Runtime Merge: " + sumTimesMerge / counter + " operations");
        System.out.println("---------------------------");
        System.out.println("Average Operations Selection: " + sumOperationsSelect / counter + " milliseconds");
        System.out.println("Average Operations Merge: " + sumOperationsMerge / counter + " operations");


    }

    public static int[] randomList(int n) {
        int temp = 0;
        SecureRandom rand = new SecureRandom();
        int randomList[] = new int[n];
        boolean unique = true;
        for(int i = 0; i < n; i++){
            temp = rand.nextInt(10000);
            for(int j = 0; j < n; j++){
                if(randomList[j] == temp){
                    //checks if number is unique
                    unique = false;
                    if(i > 0){
                        i--;
                    }
                }
            }
            if(unique == true){
                randomList[i] = temp;
            }
            unique = true;
        }
        
        return randomList;
    }

    public static int SelectionSort(int a[]){
        int length = a.length;
        int operations = 0;
        for(int i = 0; i < length; i++){
            int min_index = i;
            for(int j = i+1; j < length; j++){
                if(a[j] < a[min_index]){
                    min_index = j;
                }
            }
            //swap the minimum element with the first element
            int temp = a[min_index];
            a[min_index] = a[i];
            a[i] = temp;
            operations++;
        }
        System.out.println(Arrays.toString(a));
        return operations;
    }

    public static void MergeSort(int[] a){
        //split the array into its smallest form
        if(a.length < 2){
            merge_counter++;
            return;
        }
        int middle = a.length/2;

        int[] leftSide = new int[middle];
        int[] rightSide = new int[a.length - middle];

        for(int i = 0; i < middle; i++){
            leftSide[i] = a[i];
        }
        for(int i = middle; i < a.length; i++){
            rightSide[i - middle] = a[i];
        }

        MergeSort(leftSide);
        MergeSort(rightSide);
        merge(a, leftSide, rightSide);
    }
    public static void merge(int[] a, int[] leftSide, int[] rightSide){
        int i = 0, j = 0, k = 0;
        while(i < leftSide.length && j < rightSide.length){
            
            if(leftSide[i] < rightSide[j]){
                a[k++] = leftSide[i++];
            }
            else{
                a[k++] = rightSide[j++];
            }

            while(i < leftSide.length){
                a[k++] = leftSide[i++];
            }
            while(j < rightSide.length){
                a[k++] = rightSide[j++];
            }

        }
    }
}