/**
 * When something is correct in most circumstances but is different or off-beat once in a while, it is called an exception. In Java, it is the same. Exceptions are events that disrupt the normal flow of a program. Say for example when you are dividing numbers based on numbers input by a user. The expectation is that the user will never enter 0 as the divisor as zero division is invalid. But if the user enters 0, it is an exceptional case which you have to handle.
 * There are two types of exceptions.

Checked Exceptions - These are exceptions that are checked at compile-time. The programmer must handle or declare them.

Unchecked Exceptions - These are exceptions that are checked at runtime. They are usually caused by logical errors in the code. You may have encountered this kind of exceptions in some of the labs you have done so far in the course.
 */
import java.util.Scanner;
public class ExceptionTrial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strArr[] = new String[5];
        int strIdx = 0;

        while(true) {
            System.out.println(
                "Press 1 to add String, " +
                "\n2 to get String from a particular index, " +
                "\n3 to get the length string in any index, " +
                "\n4 to get all the strings in the array " +
                "\nany other key to exit");
            String userAction = scanner.nextLine();

            if(userAction.equals("1")) {
                if(strIdx == 5) {
                    System.out.println("There are already 5 strings in the array !");
                }
                else { 
                    System.out.println("Enter the string ");
                    String inputStr = scanner.nextLine();
                    strArr[strIdx++] = inputStr;
                }
            }
            else if (userAction.equals("2")) {
                System.out.println("Enter the index you want to retrieve ");
                int retIdx = Integer.parseInt(scanner.nextLine());

                System.out.println(strArr[retIdx]);
            }
            else if (userAction.equals("3")) {
                System.out.println("Enter the index you want the length of ");
                int retIdx = Integer.parseInt(scanner.nextLine());
                System.out.println(strArr[retIdx].length());
            }
            else if (userAction.equals("4")) {
                for (int i=0; i<strArr.length;i++){
                    System.out.println(strArr[i] + "\n");
                }
            }
            else {
                break;
            }
        }
    }
}