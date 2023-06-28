public class Employee {
    private String firstName;
    private String lastName;
    private double netSemiMonthlyPay;
    private double semiMonthlySSSContribution;
    private double PhilHealth;
    private double PagIbig;
    private double withholdingTax;

    //empty constructor
    Employee() {
        this.firstName = "";
        this.lastName = "";
        this.netSemiMonthlyPay = 0;
        this.semiMonthlySSSContribution = 0;
        this.PhilHealth = 0;
        this.PagIbig = 0;
        this.withholdingTax = 0;
    }

    Employee(String firstName, String lastName, double netSemiMonthlyPay, double semiMonthlySSSContribution, 
            double PhilHealth, double PagIbig, double withholdingTax) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.netSemiMonthlyPay = netSemiMonthlyPay;
        this.semiMonthlySSSContribution = semiMonthlySSSContribution;
        this.PhilHealth = PhilHealth;
        this.PagIbig = PagIbig;
        this.withholdingTax = withholdingTax;
    }

    public String toString(){
        return String.format("FIRST NAME: %s \nLAST NAME: %s \nNET PAY(SEMI-MONTHLY/KINSENAS): PHP %.2f" +  
                            "\nSSS CONTRIBUTION: PHP %.2f \nPHILHEALTH: PHP %.2f \nPAG-IBIG: PHP %.2f \nWITHOLDING TAX: PHP %.2f \n", 
                            this.firstName, this.lastName, this.netSemiMonthlyPay, this.semiMonthlySSSContribution,
                            this.PhilHealth, this.PagIbig, this.withholdingTax);
    }
}
