package Strings;

public class ReverseString {

        public static void reverseString1_2Pointer(String str) {
                StringBuilder sb = new StringBuilder(str);

                int n = sb.length();

                for(int i=0,j=n-1; i<j; i++,j--) {
                        /*
                        i = Left pointer for traversing the String in --> dirn = l
                        j = Right pointer for traversing the String in <-- dirn = r

                        Swapping is as follows
                         char ch = str[i];
                         str[i] = str[j];
                          str[j] = ch;
                        */
                        char tmp = sb.charAt(i);
                        sb.setCharAt(i, sb.charAt(j));
                        sb.setCharAt(j, tmp);
                }

                System.out.println("Original String = "+str);
                System.out.println("Reversed String = "+sb.toString());
        }

        public static void reverseString1_1Pointer(String str) {
                StringBuilder sb = new StringBuilder(str);
                int n = sb.length();

                for(int i=0; i<n/2; i++) {
                        /*
                        Swapping logic is as follows
                         char ch = str[i];
                         str[i] = str[n-1-i];
                          str[n-1-i] = ch;
                        */
                        char temp_ch = sb.charAt(i);
                        sb.setCharAt(i, sb.charAt(n-1-i));
                        sb.setCharAt(n-1-i, temp_ch);
                }

                System.out.println("Original String = "+str);
                System.out.println("Reversed String = "+sb.toString());
        }

        public static void main(String[] args) {

        }
}
