package Strings;

public class ReverseStringWithoutAffectingSpecialCharacters {
        public static boolean isSpecialCharacter(char ch) {
                return Character.isLetterOrDigit(ch);
        }

        /*
        1. Time Complexity  = O(n)

        2. Space Complexity =

        3. In Place Reversal
        */
        public static void reverseString1(String str) {
                StringBuilder sb = new StringBuilder(str);
                int n = sb.length();
                int l = 0;
                int r = n - 1;

                while(l < r) {
                        // Ignore special characters while traversing --> from left
                        // If str[l] , sb[l] is a special character, Do ignore that
                        if(isSpecialCharacter(sb.charAt(l))) {
                                l++;
                        }

                        // Ignore special characters while traversing <-- from right
                        // If str[r] , sb[r] is a special character, Do ignore that
                        else if(isSpecialCharacter(sb.charAt(r))) {
                                r--;
                        }

                        // Else, Do the fuckin swapping
                        else {

                                /*
                                Swapping is as follows
                                char ch = str[l];
                                str[l] = str[r];
                                str[r] = ch;
                                **/
                                char tmp_ch = sb.charAt(l);
                                sb.setCharAt(l, sb.charAt(r));
                                sb.setCharAt(r, tmp_ch);

                                l++;
                                r--;
                        }
                }

                System.out.println("Original String = "+str);
                System.out.println("Reversed String = "+sb.toString());
        }

        public static void main(String[] args) {

        }
}
