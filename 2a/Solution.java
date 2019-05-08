package foobar_level_2_a;

import java.util.Arrays;
import java.util.Vector;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String n = "210022";
		System.out.println(solution(n, 3));
	}
	
	public static int solution(String n, int b) {
		
		Vector<String> history = new Vector<String>();
		char[] descending, ascending, product = n.toCharArray();
		int length = n.length();
		String productString;
		
		while (true) {
			System.out.println(product);
			Arrays.sort(product);
			ascending = product.clone(); // smaller operand
			
			reverse(product, length);
			descending = product.clone(); // larger operand
			
			product = subtract(descending, ascending, length, b);
			productString = new String(product);
			
			if (history.contains(productString))
				return history.size() - history.indexOf(productString);
			else 
				history.add(productString);
		}
		//return -1;
	}
	
	//Assumes first operand is always larger than second operand
	static char[] subtract(char[] descending, char[] ascending, int length, int b) {
		char[] difference = new char[length];
		
		for (int i = length - 1;  i >= 0; i--) {
			 //if first operand is higher than second, borrow
			if (descending[i] < ascending[i]) {
				descending[i] += b; // add base to operand
				
				//find higher power to borrow from, add b - 1 to lower powers while searching
				int j = i - 1;
				while(descending[j] < '0') { 
					descending[j] += b - 1;
					j--;
				}
				descending[j]--; // borrow		
			}
			difference[i] = (char)(descending[i] - ascending[i] + 48); //re-add offset for ASCII
		}
		return difference;
	}

	static void reverse(char[] array, int len) {
		char temp;
		for(int i = 0; i < len / 2; i++) {
			temp = array[i];
			array[i] = array[len -i -1]; 
			array[len - i - 1] = temp; 
		}
	}
}
