public class Employee{
    private String name, workHour, bornDate, title, ID;
    private double height, weight, salary;
    private int age;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }
    public String getWorkHour() {
        return workHour;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }
    public String getBornDate() {
        return bornDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public double getHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }

    public void setID(String ID) { this.ID = ID; }
    public String getID() {return ID;}

    Employee(){
        ID = "Undefined.";
        name = "Undefined.";
        workHour = "Undefined.";
        bornDate = "Undefined.";
        title = "Undefined.";
        height = 0.0;
        weight = 0.0;
        salary = 0.0;
        age = 0;
    }

    Employee(String ID,String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age) {
        this.ID = ID;
        this.name = name;
        this.workHour = workHour;
        this.bornDate = bornDate;
        this.title = title;
        this.height = height;
        this.weight = weight;
        this.salary = salary;
        this.age = age;
    }

    public void printInfoForWorker()
    {
        System.out.println("ID: "+getID());
        System.out.println("Name: "+getName());
        System.out.println("Title: "+getTitle());
    }

    public void printInfoForAdmin(){
        System.out.println("ID: "+getID());
        System.out.println("Name: "+getName());
        System.out.println("Title: "+getTitle());
        System.out.println("Work Hours: "+getWorkHour());
        System.out.println("Age: "+getAge());
        System.out.println("Height: "+getHeight());
        System.out.println("Weight: "+getWeight());
        System.out.println("Born Date: "+getBornDate());
        System.out.println("Salary: "+getSalary());

    }
    public void printInfoForDoctor()
    {
        System.out.println("ID: "+getID());
        System.out.println("Name: "+getName());
        System.out.println("Title: "+getTitle());
        System.out.println("Work Hours: "+getWorkHour());
        System.out.println("Age: "+getAge());
        System.out.println("Height: "+getHeight());
        System.out.println("Weight: "+getWeight());
        System.out.println("Born Date: "+getBornDate());
    }

    public void printInfoForManager()
    {
        System.out.println("ID: "+getID());
        System.out.println("Name: "+getName());
        System.out.println("Title: "+getTitle());
        System.out.println("Work Hours: "+getWorkHour());
    }
}