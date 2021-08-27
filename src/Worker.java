public class Worker extends Employee implements IMenu{
    private String field;

    public void setField(String field) {
        this.field = field;
    }
    public String getField() {
        return field;
    }

    Worker()
    {
        super();
        field = "Undefined.";
    }

    Worker(String ID,String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String field)
    {
        super(ID,name,workHour,bornDate,title,height,weight,salary,age);
        this.field = field;
    }

    @Override
    public void Menu() {
        System.out.println("Welcome.");
        System.out.println();
        System.out.println("1. Announcements.");
        System.out.println("2. Messages.");
        System.out.println("3. Show other users information.");
        System.out.println("4. My sick reports.");
        System.out.println("5. Logout.");
    }

    @Override
    public void printInfoForWorker() {
        super.printInfoForWorker();
        System.out.println("Field: " + getField());
        System.out.println();
    }

    @Override
    public void printInfoForAdmin() {
        super.printInfoForAdmin();
        System.out.println("Field: " + getField());
    }

    @Override
    public void printInfoForDoctor() {
        super.printInfoForDoctor();
        System.out.println("Field: " + getField());
    }

    @Override
    public void printInfoForManager() {
        super.printInfoForManager();
        System.out.println("Field: " + getField());
    }
}