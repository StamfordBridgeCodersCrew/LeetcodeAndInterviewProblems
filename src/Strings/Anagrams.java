package Strings;

import java.util.LinkedHashMap;
import java.util.Map;

public class Anagrams {

        public static boolean isAnagram1(String X, String Y) {

                // If X,y differ in length
                // X,Y are obviiously not anagrams
                if(X.length() != Y.length()) {
                        return false;
                }

                // Compute frequency of characters for String X
                Map<Character, Integer> freqX = new LinkedHashMap<>();
                for(char ch : X.toCharArray()) {
                        freqX.put(ch, freqX.getOrDefault(ch, 0)+1);
                }

                // Compute frequency of characters for String Y
                Map<Character, Integer> freqY = new LinkedHashMap<>();
                for(char ch : Y.toCharArray()) {
                        freqY.put(ch, freqY.getOrDefault(ch,0)+1);
                }

                // Check whether X,Y make anagrams
                return freqX.equals(freqY);
        }

        public static void main(String[] args) {
                String X = "tommarvoloriddle";
                String Y = "iamlordvoldemort";

                if(isAnagram1(X,Y))
                        System.out.println("Anagrams");
                else
                        System.out.println("Not Anagrams");
        }
}
