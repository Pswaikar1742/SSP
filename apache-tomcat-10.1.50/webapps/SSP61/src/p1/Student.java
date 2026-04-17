package p1;

public class Student {
    private String name;
    private String roll;
    private String courses;
    private double totalFee;

    public Student() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoll() { return roll; }
    public void setRoll(String roll) { this.roll = roll; }

    public String getCourses() { return courses; }
    // Accept comma-separated course list
    public void setCourses(String courses) { this.courses = courses; }

    public double getTotalFee() { return totalFee; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }
}
