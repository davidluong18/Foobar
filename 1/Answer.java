package foobar_1;

import java.util.Arrays;

public class Answer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1, 2, 3};
		int[] ans = answer(test, 6);
		System.out.println(Arrays.toString(ans));
		System.out.println(ans.length);
	}
	
    private static int [][] hashTable;
    private static int hash = 13;
    private static int[] result;
    private static int size;
    
    public static int[] answer(int[] data, int n) { 
        
        buildTable(data);
            
        checkValues(data, n);
        
        int[] resultFinal = new int[size];
        for (int i = 0; i < size; i++)
            resultFinal[i] = result[i];
           
        return resultFinal;
    }
    
    //This function builds a hash table based on frequency
    public static void buildTable(int[] data) {
    	hashTable = new int[100][2];
    	
    	for(int i = 0; i < data.length; i++){
            int index = data[i] % hash;
            
            //find in table and increment count or add new value
            boolean found = false; 
            do{
	            if (hashTable[index][0] == data[i])
	                found = true;
	            else if (hashTable[index][0] == 0) {
	                hashTable[index][0] = data[i];
	                found = true;
	            }
	            else
	                index++;
            }while (!found);
            hashTable[index][1]++;
        }
    }
    
    //This function checks each frequency of each value in data and places it
    //in result if its frequency is less than n
    public static void checkValues(int[] data, int n) {
    	result = new int[100];
        size = 0;
        
    	for(int i = 0; i < data.length; i++){
            int index = data[i] % hash;
            
            //find in hash table and check frequency
           boolean found = false; 
            do{
	            if (hashTable[index][0] == data[i])
	                found = true;
	            else
	                index++;
            }while (!found);
            
            if (hashTable[index][1] <= n){ //if less than k push back
                result[size] = data[i];    
                size++;
            }
        }
    }
 } 
    
