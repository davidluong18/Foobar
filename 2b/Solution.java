import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
        String[][] testCases = {{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"}, {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"}, {"1", "1.0", "1.0.0"}};
        String[] sol = {"0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0", "1.0,1.0.2,1.0.12,1.1.2,1.3.3", "1,1.0,1.0.0"};
        int n = testCases.length;

        for(int i = 0; i < n; i++){
            System.out.println("Test case " + Arrays.toString(testCases[i])); 
            String answer = solution(testCases[i]);
            if( answer.equals(sol[i]) ){
                System.out.println("Test case " + i + " is correct");
                System.out.println("Solution: " + sol[i]);
                System.out.println("Returned: " + answer);
            }
            else {
                System.out.println("Test case " + i + " is wrong");
                System.out.println("Solution: " + sol[i]);
                System.out.println("Returned: " + answer);
            }
            System.out.println("");
        }
    }

/* FooBar solution()
    public static String[] solution(String[] l){  
        sort(l, 0, l.length - 1);
        return l;
    }
*/

    public static String solution(String[] l){
        
        sort(l, 0, l.length - 1);
        String str = "";
        int i = 0, n = l.length;
        while (i < n){
            str = str.concat(l[i] + ",");
            i++;
        }
        return str.substring(0, str.length() - 1);
    }

    //Returns: '=' -> 0, '>' -> 1, '<' -> 2
    static int compare( String str1, String str2){
        String[] sep1 = str1.split("\\.");
        String[] sep2 = str2.split("\\.");
        int n = 3;

        for (int i = 0; i < n; i++){
            if (i >= sep1.length) // 1 vs 1.1
                return 2;
            if (i >= sep2.length) // 1.1 vs 1
                return 1;
             
            int val1 = Integer.parseInt(sep1[i]);
            int val2 = Integer.parseInt(sep2[i]);

            if (val1 > val2)
                return 1;
            if (val1 < val2)
                return 2;
            //If equal move down one layer
        } 
        return 0; //Default case equals
    }

    // Merges two subarrays of arr[]: subarray 1 arr[l..m], subarray 2 arr[m+1..r] 
    static void merge(String arr[], int l, int m, int r) { 
        //Sizes
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        String temp[] = new String [arr.length];

        //Temp arrays and copy
        String L[] = new String [n1]; 
        String R[] = new String [n2]; 
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
    
        int k = l; // Initial index of merged subarry array
        int i = 0, j = 0; 
        while (i < n1 && j < n2) 
        { 
            int comp = compare(L[i], R[j]);
            if (comp == 2 || comp == 0 ) { //L[i] <= R[j]
                arr[k] = L[i];
                i++; 
            } 
            else { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 

        // Copy remaining elements if any
        while (i < n1) { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r]
    static void sort(String arr[], int l, int r) { 
        if (l < r) { 
            int m = (l+r)/2; 
  
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            merge(arr, l, m, r); 
        } 
    } 
}