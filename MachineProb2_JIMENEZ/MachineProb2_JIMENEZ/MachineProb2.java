import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/*
 Machine Problem #2

Write a Java program that will compute for the 
equivalent letter grades and QPI of an ADDU student.  
Use the formula in the ADDU Student Handbook (attached) 
to determine the letter grades and compute the QPI.

*/

public class MachineProb2 {
    static Scanner in = new Scanner(System.in);

    static int getNumberOfSubjects() {
        int numberOfSubjects;
        while(true){
            System.out.println("Enter number of Subjects: ");
            if(in.hasNextInt()){            
                numberOfSubjects = in.nextInt();
                in.nextLine();
                if(numberOfSubjects > 0){
                    break;
                }else{
                    System.out.println("Enter positive number.");
                }
            }else{
                in.nextLine();
                System.out.println("Enter valid input.");
            }
        }
        return numberOfSubjects;
    }

    static void readFile(ArrayList<Subject> subjectList) {
        try{
            FileReader fr = new FileReader("SUBJECT_LIST.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!= null){
                System.out.println(line);
            }

            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    static void writeFile(ArrayList<Subject> subjectList, int numUnits) {
        try{
            FileWriter fw = new FileWriter("SUBJECT_LIST.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Subject sub: subjectList){
                bw.write(sub.toString());
                bw.newLine();
            }
            bw.write("TOTAL UNITS: " + numUnits);
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void getTotalQPI(double sum, int numUnits) {
        double QPI;
        QPI = sum/numUnits;   
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("TOTAL CALCULATED QPI: %.2f\n", QPI);
        System.out.println("--------------------------------------------------------------------------------");
    }

    static double getQPE(String letterGrade) {
        double QPE; 
        switch(letterGrade){
            case "A":
                QPE = 4.0;
                break;
            case "B+":
                QPE = 3.5;
                break;
            case "B":
                QPE = 3.0;
                break;
            case "C+":
                QPE = 2.5;
                break;
            case "C":
                QPE = 2.0;
                break;
            case "D":
                QPE = 1.0;
                break;
            default:
                QPE = 0.0;
                break;
        }
        return QPE;
    }

    static String getLetterGrade(double gradePercent) {
        String letterGrade;
        final int MINIMUM_PASSING_GRADE = 72;
        final int D_GRADE_MAX = 75;
        final int C_GRADE_MIN = 76;
        final int C_GRADE_MAX= 79;
        final int CPLUS_GRADE_MIN = 80;
        final int CPLUS_GRADE_MAX = 83;
        final int B_GRADE_MIN = 84;
        final int B_GRADE_MAX = 87;
        final int BPLUS_GRADE_MIN = 88;
        final int BPLUS_GRADE_MAX = 91;
        final int A_GRADE_MIN = 92;
        final int MAXIMUM_PASSING_GRADE = 100;

        if(gradePercent < MINIMUM_PASSING_GRADE){
            letterGrade = "F";
        }else if(gradePercent >= MINIMUM_PASSING_GRADE && gradePercent <= D_GRADE_MAX){
            letterGrade = "D";
        }else if(gradePercent >= C_GRADE_MIN && gradePercent <= C_GRADE_MAX){
            letterGrade = "C";
        }else if(gradePercent >= CPLUS_GRADE_MIN && gradePercent <= CPLUS_GRADE_MAX){
            letterGrade = "C+";
        }else if(gradePercent >= B_GRADE_MIN && gradePercent <= B_GRADE_MAX){
            letterGrade = "B";
        }else if(gradePercent >= BPLUS_GRADE_MIN && gradePercent <= BPLUS_GRADE_MAX){
            letterGrade = "B+";
        }else if(gradePercent >= A_GRADE_MIN && gradePercent <= MAXIMUM_PASSING_GRADE){
            letterGrade = "A";
        }else{
            letterGrade = "FD";
        }
        return letterGrade;
    }

    public static void main(String[] args) {
        System.out.println("--------------------------------ADDU GRADE CALCULATOR--------------------------------");
        int numberOfSubjects = getNumberOfSubjects();

        //controlling variables
        int numUnits = 0;
        int subjUnit;
        String subjectName;
        double gradePercent;
        double sum = 0;

        //arrayList of subjects
        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        
        for(int i = 0; i < numberOfSubjects; i++){
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("UNIT COUNT: " + numUnits);

            System.out.print("Enter Subject: ");
            subjectName = in.nextLine();
            
            while(true){
                System.out.print("Enter Grade % (0-100): ");
                if(in.hasNextDouble()){
                    gradePercent = in.nextDouble();
                    in.nextLine();
                    if(gradePercent >= 0.0 && gradePercent <= 100){
                        break;
                    }else{
                        System.out.print("Enter valid input.\n");
                    }
                }else{
                    in.nextLine();
                    System.out.print("Enter valid input.\n");
                }
            }

            while(true){
                System.out.print("Enter Unit: ");
                if(in.hasNextInt()){
                    subjUnit = in.nextInt();
                    in.nextLine();
                    if(subjUnit > 0){
                        break;
                    }else{
                        System.out.print("Enter valid input.\n");
                    }
                }else{
                    in.nextLine();
                    System.out.println("Enter valid input.\n");
                }
            }

            numUnits += subjUnit; //unit counter
            
            String letterGrade = getLetterGrade(gradePercent);
            double QPE = getQPE(letterGrade);
              
            subjectList.add(new Subject(subjectName, subjUnit, letterGrade, gradePercent, QPE));

            sum += (QPE*subjUnit);
        }
        
        //write contents of arraylist to a text file
        writeFile(subjectList, numUnits);

        //print out contents of the text file   
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUBJECT LIST:");
        readFile(subjectList);
        
        getTotalQPI(sum, numUnits);
        
        while(true){
            System.out.println("Do you want to use the program again (y/n)?.");
            if(in.hasNext("y") || in.hasNext("Y")){
                in.nextLine();
                main(args);
                break;
            }else if(in.hasNext("n") || in.hasNext("N")){
                in.nextLine();
                System.out.println("Thanks for using the program!.");
                System.out.println("Made by: CHRISTIAN RHYSS R. JIMENEZ.");
                break;
            }else{
                in.nextLine();
                System.out.println("Enter y or n.");
            }
        }
    }
}

