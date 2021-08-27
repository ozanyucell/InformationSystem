public class Administrator extends Employee implements IMenu{
    private Office office;

    Administrator(){
        super();
        office = new Office("Undefined.","Undefined.");
    }
    Administrator(String ID,String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String officeLocation, String officeHours)
    {
        super(ID,name,workHour,bornDate,title,height,weight,salary,age);
        office = new Office(officeLocation,officeHours);
    }



    public void setOffice(Office office) {
        this.office = office;
    }
    public Office getOffice() {
        return office;
    }

    @Override
    public void Menu() {
        System.out.println("Welcome.");
        System.out.println();
        System.out.println("1. Announcements.");
        System.out.println("2. Messages.");
        System.out.println("3. Show other users information.");
        System.out.println("4. Add users.");
        System.out.println("5. Make an announcement.");
        System.out.println("6. Sick reports.");
        System.out.println("7. Logout.");
    }

    @Override
    public void printInfoForAdmin() {
        super.printInfoForAdmin();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

    @Override
    public void printInfoForManager() {
        super.printInfoForManager();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

    @Override
    public void printInfoForDoctor() {
        super.printInfoForDoctor();
        System.out.println("Office Location: " + getOffice().getLocation());
        System.out.println("Office Hours: " + getOffice().getOpenHours());
    }

}