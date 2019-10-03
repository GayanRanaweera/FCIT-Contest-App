import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedWriter;

public class ScannerFileWriter {
    static int numOfUni; // declare number of universities
    static int numOfDay; // declare number of days and dates
    static int[] intArray; // declare number of student participating array
    static int numOfCriteria; // declare number of criterias
    static String[] university; // declare university array
    static String[] day; // declare day array
    static String[] date; // declare date array
    static String[][] studentsName; // declare name 2 dimensional student array
    static String[] awardCriteria; // declare award criteria array
    static int[][][] score; // declare 3 dimensional score array

    public static void main(String[] args) {

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file); // scan file using scanner
            FileWriter writer = new FileWriter("print.txt");
            PrintWriter printWriter = new PrintWriter(writer); // write file using file writer
            FileWriter writerAppend = new FileWriter("print.txt", true);
            PrintWriter printWriterAppend = new PrintWriter(writerAppend); // append file using file writer
            FileWriter writerAppend2 = new FileWriter("print.txt", true);
            PrintWriter printWriterAppend2 = new PrintWriter(writerAppend2); // append file using file writer

            numOfUni = scanner.nextInt();
            numOfDay = scanner.nextInt();
            intArray = new int[numOfUni];
            for (int i = 0; i < numOfUni; i++) {
                intArray[i] = scanner.nextInt();
            }
            numOfCriteria = scanner.nextInt();
            while (scanner.hasNext()) {
                switch (scanner.next()) { // check the commands
                case "addUniversity":
                    university = addUniversity(scanner);
                    break;
                case "addDays":
                    day = addDays(scanner);
                    break;
                case "addDates":
                    date = addDates(scanner);
                    break;
                case "addStudentsName":
                    studentsName = addStudentsName(scanner);
                    break;
                case "addAwardCriteria":
                    awardCriteria = addAwardCriteria(scanner);
                    break;
                case "addScore":
                    score = addScore(scanner);
                    break;
                case "printcontestDetails":
                    printcontestDetails(printWriter);
                    break;
                case "printcontestdetailResults":
                    printcontestdetailResults(printWriterAppend);
                    break;
                case "printwinnerAwardByEachCriteria":
                    printwinnerAwardByEachCriteria(printWriterAppend2);
                    break;
                case "Quit":
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { // check file is exist
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] addUniversity(Scanner scanner) { // to add universities for university array
        String[] university = new String[numOfUni];
        for (int i = 0; i < numOfUni; i++) {
            university[i] = scanner.next().replace("_", " ");
        }
        return university;
    }

    public static String[] addDays(Scanner scanner) { // to add days for day array
        String[] day = new String[numOfDay];
        for (int i = 0; i < numOfDay; i++) {
            day[i] = scanner.next();
        }
        return day;
    }

    public static String[] addDates(Scanner scanner) { // to add dates for date array
        String[] date = new String[numOfDay];
        for (int i = 0; i < numOfDay; i++) {
            date[i] = scanner.next().replace("_", "/");
        }
        return date;
    }

    public static String[][] addStudentsName(Scanner scanner) { // to add students name for studentsName array
        int rowSize = numOfUni;
        int colSize = maxValue(intArray);
        String[][] studentsName = new String[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < intArray[i]; j++) {
                studentsName[i][j] = scanner.next().replace("_", " ");
            }
        }
        return studentsName;
    }

    public static String[] addAwardCriteria(Scanner scanner) { // to add criterias name for awardCriteria array
        String[] awardCriteria = new String[numOfCriteria];
        for (int i = 0; i < numOfCriteria; i++) {
            awardCriteria[i] = scanner.next().replace("_", " ");
        }
        return awardCriteria;
    }

    public static int[][][] addScore(Scanner scanner) { // to add scores for score array
        int d1 = numOfUni;
        int d2 = numOfCriteria;
        int d3 = maxValue(intArray);
        int[][][] score = new int[d1][d2][d3];
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < intArray[i]; j++) {
                for (int k = 0; k < d2; k++) {
                    score[i][j][k] = scanner.nextInt();
                }
            }
        }
        return score;
    }

    public static int maxValue(int[] array) { // to get max value of a array
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int maxValuePosition(int[] array) { // to get max position of a array
        int max = 0;
        int maxPosition = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxPosition = i;
            }
        }
        return maxPosition;
    }

    public static void printcontestDetails(PrintWriter printWriter) { // to print contest details

        printWriter.println("\n\n***** Welcome to FCIT, Contest App System *****");
        printWriter.println("\n------------  Contest App details are as follows ------");
        printWriter.printf("%-30s %-30s %s", "University Name", "Contest Day", "Contest Date\n");
        printWriter.println("\n------------------------------------------------------------------------------");
        for (int i = 0; i < university.length; i++) {
            printWriter.printf("%-30s %-30s %s \r\n\n", university[i], day[i], date[i]);
        }
        printWriter.close();
    }

    public static void printcontestdetailResults(PrintWriter printWriter) { // to print contest details result
        for (int i = 0; i < university.length; i++) {
            printWriter.println("\n---Contest Results of " + university[i] + " is as Follows ---");
            for (int j = 0; j < intArray[i]; j++) {
                printWriter.println("\n---Student Name   " + studentsName[i][j] + " points  are as Follows ---");
                for (int k = 0; k < awardCriteria.length; k++) {
                    printWriter.println(" " + awardCriteria[k] + " : " + score[i][j][k]);
                }
            }
        }
        printWriter.close();
    }

    public static void printwinnerAwardByEachCriteria(PrintWriter printWriter) { // to print winner name award by each
                                                                                 // criteria
        for (int i = 0; i < awardCriteria.length; i++) {
            for (int j = 0; j < university.length; j++) {
                printWriter.println("\n--- Results of  " + university[j] + " is as Follows ---");
                printWriter.println(" Contest Winner name in Category:   " + awardCriteria[i] + " :");
                printWriter.println(" " + getWinner(i, j));
            }
        }
        printWriter.println("\n	Thank you for using FCIT, Contest App System, Good Bye!\n");
        printWriter.close();
    }

    public static String getWinner(int criteriaNum, int universityNum) { // to get winner name
        int[] array = new int[intArray[universityNum]];
        int maxPosition;
        for (int j = 0; j < intArray[universityNum]; j++) {
            array[j] = score[universityNum][j][criteriaNum];
        }
        maxPosition = maxValuePosition(array);
        return studentsName[universityNum][maxPosition];
    }
}