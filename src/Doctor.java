public class Doctor extends Employee implements IMenu {
    private Office office;

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    Doctor(){
        super();
        office = new Office("Undefined.","Undefined.");
    }

    Doctor(String ID,String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String officeLocation, String officeHours)
    {
        super(ID,name,workHour,bornDate,title,height,weight,salary,age);
        office = new Office(officeLocation,officeHours);
    }

    @Override
    public void Menu() {
        System.out.println("Welcome.");
        System.out.println();
        System.out.println("1. Announcements.");
        System.out.println("2. Messages.");
        System.out.println("3. Show other users information.");
        System.out.println("4. Write sick report.");
        System.out.println("5. Make a medical announcement.");
        System.out.println("6. Logout.");
    }

    @Override
    public void printInfoForWorker() {
        super.printInfoForWorker();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

    @Override
    public void printInfoForAdmin() {
        super.printInfoForAdmin();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

    @Override
    public void printInfoForDoctor() {
        super.printInfoForDoctor();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

    @Override
    public void printInfoForManager() {
        super.printInfoForManager();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }
}