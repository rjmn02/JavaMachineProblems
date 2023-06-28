public class Subject{
    private String subjectName;
    private String letterGrade;
    private int unit;
    private double gradePercent;
    private double QPE;
    
    Subject() {
        this.letterGrade = "";
        this.subjectName = "";
        this.unit = 0;
        this.QPE = 0;
        this.gradePercent = 0;
    }

    Subject(String name, int unit, String letterGrade, double gradePercent, double QPE) {
        this.letterGrade = letterGrade;
        this.subjectName = name;
        this.unit = unit;
        this.QPE = QPE;
        this.gradePercent = gradePercent;
 
    }
    public String toString(){
		return "SUBJECT: "+ this.subjectName + "\t" +
                "UNITS: " + this.unit + "\t" +
                "GRADE(%): " + this.gradePercent + "\t" +
                "LETTER GRADE: " + this.letterGrade + "\t" +
                "QPE: " + this.QPE;
	}
}