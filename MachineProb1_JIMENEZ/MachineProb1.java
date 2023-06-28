import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/*Machine Problem #1

CS 1233, Inc. is a corporation located in Davao City with 10 employees.

Write a Java program that will compute for the net semi-monthly pay of the employees.

Use the following formula to compute for the net pay.

Net Pay = Gross basic pay – Deductions. (semi-monthly/kinsena)

Deductions = Withholding tax + SSS + PhilHealth + Pag-ibig

Withholding tax  and SSS are computed based on their respective tables (attached).

Philhealth is 2% of the gross basic semi-monthly pay.

Pag-ibig is 50 pesos semi-monthly. */


public class MachineProb1{

    static Scanner in = new Scanner(System.in);
    
    static double getSemiMonthlyGrossPay(){   
        double semiMonthlyGrossPay;
        while(true){
            System.out.print("Enter Semi-monthly/Kinsena Gross Basic Pay (PHP): ");
            if(in.hasNextDouble()){
                semiMonthlyGrossPay = in.nextDouble();
                in.nextLine();
                if(semiMonthlyGrossPay > 0){
                    break;
                }else{
                    System.out.println("Please enter a positive number.");
                }
            }else{
                in.nextLine();
                System.out.println("Enter valid input");
            }

        }
        return semiMonthlyGrossPay;
    }

    static double getSSS(double semiMonthlyGrossPay) {  
        double monthlyGrossPay = semiMonthlyGrossPay * 2;
        double semiMonthlySSSContribution = 0;

        //arrays
        double[] COMPENSATION_RANGES_MIN = new double[52];
        double[] COMPENSATION_RANGES_MAX = new double[52];
        double[] EE_RANGE = new double[32];
        
        //controlling variables
        final double compensationRangesCommonDifference = 500.0;
        final double compensationRangeMin = 0.00;
        final double compensationRangeMaxBase = 4249.99;
        final double compensationRangeMinBase = 4250.00;
        final double EERangeCommonDifference = 22.50;
        final double EERangeMin = 180.00;
        final double EERangeMax = 900.00;

        //setting initial values
        EE_RANGE[0] = EERangeMin;
        COMPENSATION_RANGES_MIN[0] = compensationRangeMin;
        COMPENSATION_RANGES_MIN[1] = compensationRangeMinBase;
        COMPENSATION_RANGES_MAX[0] = compensationRangeMaxBase;

        for(int i = 1; i < EE_RANGE.length; i++){
            EE_RANGE[i] = EE_RANGE[i-1] + EERangeCommonDifference;
        }

        for(int i = 2; i < COMPENSATION_RANGES_MIN.length; i++){
            COMPENSATION_RANGES_MIN[i] = COMPENSATION_RANGES_MIN[i-1] + compensationRangesCommonDifference;
        }

        for(int i = 1; i < COMPENSATION_RANGES_MAX.length; i++){
            COMPENSATION_RANGES_MAX[i] = Math.round((COMPENSATION_RANGES_MAX[i-1]+compensationRangesCommonDifference)*100.0)/100.0;
        }
    
        //solving for the SSS value
        for(int i = 0; i < EE_RANGE.length; i++){
            if(monthlyGrossPay >= COMPENSATION_RANGES_MIN[i] && monthlyGrossPay <= COMPENSATION_RANGES_MAX[i]){
                semiMonthlySSSContribution = EE_RANGE[i]/2;
                break;
            }else{
                semiMonthlySSSContribution = (EERangeMax)/2;    
            }
        }

        return semiMonthlySSSContribution;
    }

    static double getWitholdingTax(double semiMonthlyGrossPay) {
        double withholdingTax = 0;

        //compensation ranges:
        final int COMPENSATION_RANGE_MIN = 10417;
        final int COMPENSATION_RANGE_2_MAX = 16666;
        final int COMPENSATION_RANGE_3_MIN = 16667;
        final int COMPENSATION_RANGE_3_MAX = 33332;
        final int COMPENSATION_RANGE_4_MIN = 33333;
        final int COMPENSATION_RANGE_4_MAX = 83332;
        final int COMPENSATION_RANGE_5_MIN = 83333;
        final int COMPENSATION_RANGE_5_MAX = 333332;
        final int COMPENSATION_RANGE_MAX = 333333;
        
        //base taxes:
        final double BASE_TAX_3 = 937.50;
        final double BASE_TAX_4 = 4270.70;
        final double BASE_TAX_5 = 16770.70;
        final double BASE_TAX_6 = 91770.70;

        //percentage over
        final double RANGE_PERCENT_OVER_2 = 0.15;
        final double RANGE_PERCENT_OVER_3 = 0.20;
        final double RANGE_PERCENT_OVER_4 = 0.25;
        final double RANGE_PERCENT_OVER_5 = 0.30;
        final double RANGE_PERCENT_OVER_6 = 0.35;

        //compute withholding tax
        if(semiMonthlyGrossPay <= COMPENSATION_RANGE_MIN){
            withholdingTax = 0;
        }else if(semiMonthlyGrossPay > COMPENSATION_RANGE_MIN && semiMonthlyGrossPay <= COMPENSATION_RANGE_2_MAX){

            withholdingTax = 0 + ((semiMonthlyGrossPay-COMPENSATION_RANGE_MIN )*RANGE_PERCENT_OVER_2);

        }else if(semiMonthlyGrossPay >= COMPENSATION_RANGE_3_MIN && semiMonthlyGrossPay <= COMPENSATION_RANGE_3_MAX){

            withholdingTax = BASE_TAX_3 + ((semiMonthlyGrossPay-COMPENSATION_RANGE_3_MIN)*RANGE_PERCENT_OVER_3);

        }else if(semiMonthlyGrossPay >= COMPENSATION_RANGE_4_MIN && semiMonthlyGrossPay <= COMPENSATION_RANGE_4_MAX){

            withholdingTax = BASE_TAX_4 + ((semiMonthlyGrossPay-COMPENSATION_RANGE_4_MIN)*RANGE_PERCENT_OVER_4);

        }else if(semiMonthlyGrossPay >= COMPENSATION_RANGE_5_MIN && semiMonthlyGrossPay <= COMPENSATION_RANGE_5_MAX){

            withholdingTax = BASE_TAX_5  + ((semiMonthlyGrossPay-COMPENSATION_RANGE_5_MIN)*RANGE_PERCENT_OVER_5);

        }else if(semiMonthlyGrossPay >= COMPENSATION_RANGE_MAX){

            withholdingTax = BASE_TAX_6 + ((semiMonthlyGrossPay-COMPENSATION_RANGE_MAX)*RANGE_PERCENT_OVER_6);

        }else{
            withholdingTax = 0;
        }
        
        return withholdingTax;
        
    }

    static void readFile(ArrayList<Employee> employeeList) {
        //use the File object to read the file EMPLOYEE_DETAILS.txt


        try{
            FileReader fr = new FileReader("EMPLOYEE_DETAILS.txt");
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


    static void writeFile(ArrayList<Employee> employeeList) {
        try{
            FileWriter fw = new FileWriter("EMPLOYEE_DETAILS.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            int employeeNum = 0;
            for(Employee employee: employeeList){
                employeeNum++;
                bw.write("EMPLOYEE " + employeeNum + " \n" + employee.toString());
                bw.newLine();
            }
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //necessary variables
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        String firstName;
        String lastName;
        int numberOfEmployees = 10;
        int employeeCount = 0;
        
        System.out.println("----------------------------CS 1233, Inc. Employee Semi-Monthly Pay Calculator----------------------------");
        for(int i = 0; i < numberOfEmployees; i++) {
            employeeCount++;
            System.out.println("EMPLOYEE " + employeeCount + ":");
            System.out.print("Enter First Name: ");
            firstName = in.nextLine();

            System.out.print("Enter Last Name: ");
            lastName = in.nextLine();

            double semiMonthlyGrossPay = getSemiMonthlyGrossPay();

            //deductions:
            double withholdingTax = getWitholdingTax(semiMonthlyGrossPay);
            double semiMonthlySSSContribution = getSSS(semiMonthlyGrossPay); 
            double PhilHealth = semiMonthlyGrossPay*0.02;
            int PagIbig = 50;
    
            double deductions = withholdingTax + semiMonthlySSSContribution + PhilHealth + PagIbig;
            double netSemiMonthlyPay = semiMonthlyGrossPay - deductions;
    
            employeeList.add(new Employee(firstName, lastName, netSemiMonthlyPay, semiMonthlySSSContribution, PhilHealth, PagIbig, withholdingTax));  
        }

        writeFile(employeeList);
        System.out.println("\n----------------------------EMPLOYEE DETAILS----------------------------");
        readFile(employeeList);
        System.out.println();
        
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
