import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    private ArrayList<String> passwords = new ArrayList<>();
    ArrayList<String> annTitleArrayL = new ArrayList<>();
    ArrayList<String> annBodyArrayL = new ArrayList<>();
    ArrayList<String> medAnnTitleArrayL = new ArrayList<>();
    ArrayList<String> medAnnBodyArrayL = new ArrayList<>();
    ArrayList<String> messageToIDsArrayList = new ArrayList<>();
    ArrayList<String> messagesArrayList = new ArrayList<>();
    ArrayList<String> messageTopicsArrayList = new ArrayList<>();
    ArrayList<String> messageFromIDsArrayList = new ArrayList<>();
    ArrayList<ArrayList> allArrayList = new ArrayList<>();
    ArrayList<Administrator> allAdministrators = new ArrayList<>();
    ArrayList<Doctor> allDoctors = new ArrayList<>();
    ArrayList<Manager> allManagers = new ArrayList<>();
    ArrayList<Worker> allWorkers = new ArrayList<>();
    ArrayList<String> sickReportForArray = new ArrayList<>();
    ArrayList<String> sickReportBodyArray = new ArrayList<>();
    ArrayList<String> sickReportDateArray = new ArrayList<>();
    ArrayList<String> IDs = new ArrayList<>();
    ArrayList<String> getSickReportDoctorArrayList = new ArrayList<>();

    File filePasswords = new File("/IO_files/passwords.txt");

    public void readID() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePasswords)));
        String line = reader.readLine();
        line = reader.readLine();
        while(line != null) {
            IDs.add(line);
            line = reader.readLine();
            line = reader.readLine();
        }
    }

    public boolean checkID(String ID){
        for (int i=0; i<IDs.size(); i++){
            if (IDs.get(i).equals(ID)){
                return true;
            }
        }
        return false;
    }

    public boolean matchPasswordID(String password, String ID){
        int passCnt = 0, IDCnt = 0;
        for (; passCnt<passwords.size(); passCnt++){
            if (passwords.get(passCnt).equals(password)){
                break;
            }
        }
        for (; IDCnt < IDs.size(); IDCnt++) {
            if (IDs.get(IDCnt).equals(ID)) {
                break;
            }
        }

        return passCnt == IDCnt;
    }

    public boolean passwordController(String password, String userID) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePasswords)));
        String line = reader.readLine();
        while(line != null) {
            if (line.equals(password) || line.equals(userID)) {
                return false;
            }
            line = reader.readLine();
        }
        return true;
    }



    public void savePasswords(String password, String userID){
        try {
            if (passwordController(password,userID)) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePasswords, true)));
                writer.append(password);
                writer.newLine();
                writer.append(userID);
                writer.newLine();
                writer.flush();
            }
            else if (!passwordController(password,userID)){
                System.out.println("This password or username already exist.");
            }
        }
        catch (FileNotFoundException e) { System.out.println("File not found."); }
        catch (IOException e) { System.out.println("IOException Class:io"); e.printStackTrace(); }
    }


    public void readPasswords() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePasswords)));
        String line = reader.readLine();
        while(line != null) {
            passwords.add(line);
            line = reader.readLine();
            line = reader.readLine();
        }
    }


    public boolean checkPassword(String password){
        for (int i=0; i<passwords.size(); i++){
            if (passwords.get(i).equals(password)){
                return true;
            }
        }
        return false;
    }

    public void addWorker(String ID, String password, String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String field) throws IOException {
        File fileInformation = new File("/IO_files/information.txt");

        BufferedWriter writerInformation = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInformation, true)));
        String weightString = String.valueOf(weight);
        String heightString = String.valueOf(height);
        String ageString = String.valueOf(age);
        String salaryString = String.valueOf(salary);
        String information = ID + "," + name + "," + workHour + "," + bornDate + "," + title + "," + heightString + "," + weightString + "," + salaryString + "," + ageString + "," + field;

        savePasswords(password, ID);

        writerInformation.newLine();
        writerInformation.append(information);
        writerInformation.flush();

        Worker newWorker = new Worker(ID, name, workHour, bornDate, title, height, weight, salary, age, field);
        allWorkers.add(newWorker);
    }


    public void addDoctor(String ID, String password, String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String officeLocation, String officeHours) throws IOException {
        File fileInformation = new File("/IO_files/information.txt");

        BufferedWriter writerInformation = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInformation, true)));
        String weightString = String.valueOf(weight);
        String heightString = String.valueOf(height);
        String ageString = String.valueOf(age);
        String salaryString = String.valueOf(salary);
        String information = ID + "," + name + "," + workHour + "," + bornDate + "," + title + "," + heightString + "," + weightString + "," + salaryString + "," + ageString + "," + officeLocation + "," + officeHours;

        savePasswords(password, ID);

        writerInformation.newLine();
        writerInformation.append(information);
        writerInformation.flush();

        Doctor newDoctor = new Doctor(ID, name, workHour, bornDate, title, height, weight, salary, age, officeLocation, officeHours);
        allDoctors.add(newDoctor);
    }

    public void addManager(String ID, String password, String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String officeLocation, String officeHours) throws IOException {
        File fileInformation = new File("/IO_files/information.txt");

        BufferedWriter writerInformation = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInformation, true)));
        String weightString = String.valueOf(weight);
        String heightString = String.valueOf(height);
        String ageString = String.valueOf(age);
        String salaryString = String.valueOf(salary);
        String information = ID + "," + name + "," + workHour + "," + bornDate + "," + title + "," + heightString + "," + weightString + "," + salaryString + "," + ageString + "," + officeLocation + "," + officeHours;


        savePasswords(password, ID);

        writerInformation.newLine();
        writerInformation.append(information);
        writerInformation.flush();


        Manager newManager = new Manager(ID, name, workHour, bornDate, title, height, weight, salary, age, officeLocation, officeHours);
        allManagers.add(newManager);
    }

    public void addAdministrator(String ID, String password, String name, String workHour, String bornDate, String title, double height, double weight, double salary, int age, String officeLocation, String officeHours) throws IOException {
        File fileInformation = new File("/IO_files/information.txt");

        BufferedWriter writerInformation = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileInformation, true)));
        String weightString = String.valueOf(weight);
        String heightString = String.valueOf(height);
        String ageString = String.valueOf(age);
        String salaryString = String.valueOf(salary);
        String information = ID + "," + name + "," + workHour + "," + bornDate + "," + title + "," + heightString + "," + weightString + "," + salaryString + "," + ageString + "," + officeLocation + "," + officeHours;

        savePasswords(password, ID);

        writerInformation.newLine();
        writerInformation.append(information);
        writerInformation.flush();

        Administrator newAdministrator = new Administrator(ID, name, workHour, bornDate, title, height, weight, salary, age, officeLocation, officeHours);
        allAdministrators.add(newAdministrator);
    }

    public ArrayList<ArrayList> getAllInformation() throws IOException {

        String[] AdminInformation = new String[10];
        String[] ManagerInformation = new String[10];
        String[] DoctorInformation = new String[10];
        String[] WorkerInformation = new String[9];
        File fileInformation = new File("/IO_files/information.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInformation)));
        String line = reader.readLine();
        while (line != null) {
            if (line.substring(0, 2).equals("90")) {
                AdminInformation = line.split(",");
                Administrator admin = new Administrator();
                Office office = new Office();
                admin.setID(AdminInformation[0]);
                admin.setName(AdminInformation[1]);
                admin.setWorkHour(AdminInformation[2]);
                admin.setBornDate(AdminInformation[3]);
                admin.setTitle(AdminInformation[4]);
                admin.setHeight(Double.parseDouble(AdminInformation[5]));
                admin.setWeight(Double.parseDouble(AdminInformation[6]));
                admin.setSalary(Double.parseDouble(AdminInformation[7]));
                admin.setAge(Integer.parseInt(AdminInformation[8]));
                office.setLocation(AdminInformation[9]);
                office.setOpenHours(AdminInformation[10]);
                admin.setOffice(office);
                allAdministrators.add(admin);

            } else if (line.substring(0, 2).equals("80")) {
                ManagerInformation = line.split(",");
                Manager manager = new Manager();
                Office office = new Office();
                manager.setID(ManagerInformation[0]);
                manager.setName(ManagerInformation[1]);
                manager.setWorkHour(ManagerInformation[2]);
                manager.setBornDate(ManagerInformation[3]);
                manager.setTitle(ManagerInformation[4]);
                manager.setHeight(Double.parseDouble(ManagerInformation[5]));
                manager.setWeight(Double.parseDouble(ManagerInformation[6]));
                manager.setSalary(Double.parseDouble(ManagerInformation[7]));
                manager.setAge(Integer.parseInt(ManagerInformation[8]));
                office.setLocation(ManagerInformation[9]);
                office.setOpenHours(ManagerInformation[10]);
                manager.setOffice(office);
                allManagers.add(manager);
            } else if (line.substring(0, 2).equals("70")) {
                DoctorInformation = line.split(",");
                Doctor doctor = new Doctor();
                Office office = new Office();
                doctor.setID(DoctorInformation[0]);
                doctor.setName(DoctorInformation[1]);
                doctor.setWorkHour(DoctorInformation[2]);
                doctor.setBornDate(DoctorInformation[3]);
                doctor.setTitle(DoctorInformation[4]);
                doctor.setHeight(Double.parseDouble(DoctorInformation[5]));
                doctor.setWeight(Double.parseDouble(DoctorInformation[6]));
                doctor.setSalary(Double.parseDouble(DoctorInformation[7]));
                doctor.setAge(Integer.parseInt(DoctorInformation[8]));
                office.setLocation(DoctorInformation[9]);
                office.setOpenHours(DoctorInformation[10]);
                doctor.setOffice(office);
                allDoctors.add(doctor);
            } else if (line.substring(0, 2).equals("60")) {
                WorkerInformation = line.split(",");
                Worker worker = new Worker();
                worker.setID(WorkerInformation[0]);
                worker.setName(WorkerInformation[1]);
                worker.setWorkHour(WorkerInformation[2]);
                worker.setBornDate(WorkerInformation[3]);
                worker.setTitle(WorkerInformation[4]);
                worker.setHeight(Double.parseDouble(WorkerInformation[5]));
                worker.setWeight(Double.parseDouble(WorkerInformation[6]));
                worker.setSalary(Double.parseDouble(WorkerInformation[7]));
                worker.setAge(Integer.parseInt(WorkerInformation[8]));
                worker.setField(WorkerInformation[9]);
                allWorkers.add(worker);
            }
            line = reader.readLine();
        }
        allArrayList.add(allAdministrators);
        allArrayList.add(allDoctors);
        allArrayList.add(allManagers);
        allArrayList.add(allWorkers);
        return allArrayList;
    }

    public void makeAnnouncement() throws IOException {
        File Announcement = new File("/IO_files/announcements.txt");
        Scanner input = new Scanner(System.in);
        BufferedWriter writerAnn = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Announcement, true)));
        System.out.println("Enter -1 to return menu.");
        System.out.print("Please enter your announcement's title: ");
        String AnnTitle = input.nextLine();
        if (!AnnTitle.equals("-1")) {
            System.out.println("Please enter your announcement's body: ");
            String AnnBody = input.nextLine();
            if (!AnnBody.equals("-1")){
                writerAnn.append(AnnTitle);
                writerAnn.newLine();
                writerAnn.append(AnnBody);
                writerAnn.newLine();
                writerAnn.flush();
                System.out.println("Your announcement has been set.");
                System.out.println();
            }
        }
        annTitleArrayL.clear();
        annBodyArrayL.clear();
        readAnnouncement();
    }

    public void readAnnouncement() throws IOException{
        File Announcement = new File("/IO_files/announcements.txt");
        BufferedReader readerAnn = new BufferedReader(new InputStreamReader(new FileInputStream(Announcement)));
        String line = readerAnn.readLine();
        while (line!=null) {
            annTitleArrayL.add(line);
            line = readerAnn.readLine();
            annBodyArrayL.add(line);
            line = readerAnn.readLine();
        }
    }

    public void makeMedicalAnnouncement() throws IOException {
        File medAnnouncement = new File("/IO_files/medicalAnnouncements.txt");
        Scanner input = new Scanner(System.in);
        BufferedWriter writerMedAnn = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(medAnnouncement, true)));

        System.out.println("Enter -1 to return menu.");
        System.out.print("Please enter your announcement's title: ");
        String medAnnTitle = input.nextLine();
        if (!medAnnTitle.equals("-1")) {
            System.out.println("Please enter your announcement's body.");
            String medAnnBody = input.nextLine();
            if (!medAnnBody.equals("-1")) {
                writerMedAnn.append(medAnnTitle);
                writerMedAnn.newLine();
                writerMedAnn.append(medAnnBody);
                writerMedAnn.newLine();
                writerMedAnn.flush();
                System.out.println("Your announcement has been set.");
                System.out.println();
            }
        }
        medAnnTitleArrayL.clear();
        medAnnBodyArrayL.clear();
        readMedicalAnnouncements();
    }

    public void readMedicalAnnouncements() throws IOException {
        File medAnnouncement = new File("/IO_files/medicalAnnouncements.txt");

        BufferedReader readerMedAnn = new BufferedReader(new InputStreamReader(new FileInputStream(medAnnouncement)));
        String line = readerMedAnn.readLine();
        while (line!=null) {
            medAnnTitleArrayL.add(line);
            line = readerMedAnn.readLine();
            medAnnBodyArrayL.add(line);
            line = readerMedAnn.readLine();
        }
    }

    public void readMessages() throws IOException{
        File messages = new File("/IO_files/sentMessages.txt");
        BufferedReader readerMessage = new BufferedReader(new InputStreamReader(new FileInputStream(messages)));
        String line = readerMessage.readLine();

        while (line!=null) {
            messageFromIDsArrayList.add(line);
            line = readerMessage.readLine();
            messageToIDsArrayList.add(line);
            line = readerMessage.readLine();
            messageTopicsArrayList.add(line);
            line = readerMessage.readLine();
            messagesArrayList.add(line);
            line = readerMessage.readLine();
        }
    }

    public boolean checkAnnouncement(){
        return annTitleArrayL.size() != 0;
    }

    public boolean checkMedAnnouncement(){
        return medAnnTitleArrayL.size() != 0;
    }

    public boolean checkMessages(String ID){
        if (messageToIDsArrayList.size()==0){
            return false;
        }
        else {
            for (int i=0; i<messageToIDsArrayList.size(); i++) {
                if (messageToIDsArrayList.get(i).equals(ID)){
                    return true;
                }
            }
            return false;
        }
    }


    public boolean checkSickReport(String ID){
        if (sickReportForArray.size()==0){
            return false;
        }
        else {
            for (int i=0; i<sickReportForArray.size(); i++) {
                if (sickReportForArray.get(i).equals(ID)){
                    return true;
                }
            }
            return false;
        }

    }

    public void readSickReports() throws IOException {
        File sickReports = new File("/IO_files/sickReports.txt");
        BufferedReader readerSickReport = new BufferedReader(new InputStreamReader(new FileInputStream(sickReports)));
        String line = readerSickReport.readLine();
        while (line!=null) {
            sickReportForArray.add(line);
            line = readerSickReport.readLine();
            sickReportDateArray.add(line);
            line = readerSickReport.readLine();
            sickReportBodyArray.add(line);
            line = readerSickReport.readLine();
            getSickReportDoctorArrayList.add(line);
            line = readerSickReport.readLine();
            line = readerSickReport.readLine();
        }
    }

    public void createSickReport (String name) throws IOException {
        Scanner input = new Scanner(System.in);
        File sickReports = new File("/IO_files/sickReports.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sickReports, true)));

        System.out.print("For (ID): ");
        String patientName = input.nextLine();
        writer.append(patientName);
        writer.newLine();
        System.out.print("Date range: ");
        String date = input.nextLine();
        writer.append(date);
        writer.newLine();
        System.out.println("Sick report body: ");
        String reportBody = input.nextLine();
        writer.append(reportBody);
        writer.newLine();
        writer.append(name);
        writer.newLine();
        writer.flush();
        sickReportDateArray.clear();
        sickReportForArray.clear();
        sickReportBodyArray.clear();
        readSickReports();
    }

    public void addUserMenu() throws IOException {
        IO io = new IO();
        Scanner input = new Scanner(System.in);
        System.out.println("What type of user you wish to add?");
        System.out.println("1. Administrator. (ID has to be like 90xx-Name!)");
        System.out.println("2. Manager. (ID has to be like 80xx-Name!)");
        System.out.println("3. Doctor. (ID has to be like 70xx-Name!)");
        System.out.println("4. Worker. (ID has to be like 60xx-Name!)");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1){
            for (;;) {
                System.out.println("ID: ");
                String ID = input.nextLine();
                if (ID.substring(0,2).equals("90")) {
                    System.out.println("Password: ");
                    String password = input.nextLine();
                    System.out.println("Name: ");
                    String name = input.nextLine();
                    System.out.println("Work Hours: ");
                    String workHours = input.nextLine();
                    System.out.println("Date of Born (DD/MM/YY): ");
                    String bornDate = input.nextLine();
                    System.out.println("Title: ");
                    String Title = input.nextLine();
                    System.out.println("Height: ");
                    double height = input.nextDouble();
                    System.out.println("Weight: ");
                    double weight = input.nextDouble();
                    System.out.println("Salary: ");
                    double salary = input.nextDouble();
                    System.out.println("Age: ");
                    int Age = input.nextInt();
                    System.out.println("Office Location: ");
                    input.nextLine();
                    String officeLocation = input.nextLine();
                    System.out.println("Office Hours: ");
                    String officeHours = input.nextLine();

                    io.addAdministrator(ID, password, name, workHours, bornDate, Title, height, weight, salary, Age, officeLocation, officeHours);
                    break;
                }
                else {
                    System.out.println("ID has to be like 90xx!");
                }
            }
        }
        else if (choice == 2){
            for (;;) {
                System.out.println("ID: ");
                String ID = input.nextLine();
                if (ID.substring(0,2).equals("80")) {
                    System.out.println("Password: ");
                    String password = input.nextLine();
                    System.out.println("Name: ");
                    String name = input.nextLine();
                    System.out.println("Work Hours: ");
                    String workHours = input.nextLine();
                    System.out.println("Date of Born (DD/MM/YY): ");
                    String bornDate = input.nextLine();
                    System.out.println("Title: ");
                    String Title = input.nextLine();
                    System.out.println("Height: ");
                    double height = input.nextDouble();
                    System.out.println("Weight: ");
                    double weight = input.nextDouble();
                    System.out.println("Salary: ");
                    double salary = input.nextDouble();
                    System.out.println("Age: ");
                    int Age = input.nextInt();
                    input.nextLine();
                    System.out.println("Office Location: ");
                    String officeLocation = input.nextLine();
                    System.out.println("Office Hours: ");
                    String officeHours = input.nextLine();

                    io.addManager(ID, password, name, workHours, bornDate, Title, height, weight, salary, Age, officeLocation, officeHours);
                    break;
                }
                else {
                    System.out.println("ID has to be like 80xx!");
                }
            }
        }
        else if (choice==3){
            for (;;) {
                System.out.println("ID: ");
                String ID = input.nextLine();
                if (ID.substring(0,2).equals("70")) {
                    System.out.println("Password: ");
                    String password = input.nextLine();
                    System.out.println("Name: ");
                    String name = input.nextLine();
                    System.out.println("Work Hours: ");
                    String workHours = input.nextLine();
                    System.out.println("Date of Born (DD/MM/YY): ");
                    String bornDate = input.nextLine();
                    System.out.println("Title: ");
                    String Title = input.nextLine();
                    System.out.println("Height: ");
                    double height = input.nextDouble();
                    System.out.println("Weight: ");
                    double weight = input.nextDouble();
                    System.out.println("Salary: ");
                    double salary = input.nextDouble();
                    System.out.println("Age: ");
                    int Age = input.nextInt();
                    input.nextLine();
                    System.out.println("Office Location: ");
                    String officeLocation = input.nextLine();
                    System.out.println("Office Hours: ");
                    String officeHours = input.nextLine();

                    io.addDoctor(ID, password, name, workHours, bornDate, Title, height, weight, salary, Age, officeLocation, officeHours);
                    break;
                }
                else {
                    System.out.println("ID has to be like 70xx!");
                }
            }
        }
        else if (choice ==4){
            for (;;) {
                System.out.println("ID: ");
                String ID = input.nextLine();
                if (ID.substring(0, 2).equals("60")) {
                    System.out.println("Password: ");
                    String password = input.nextLine();
                    System.out.println("Name: ");
                    String name = input.nextLine();
                    System.out.println("Work Hours: ");
                    String workHours = input.nextLine();
                    System.out.println("Date of Born (DD/MM/YY): ");
                    String bornDate = input.nextLine();
                    System.out.println("Title: ");
                    String Title = input.nextLine();
                    System.out.println("Height: ");
                    double height = input.nextDouble();
                    System.out.println("Weight: ");
                    double weight = input.nextDouble();
                    System.out.println("Salary: ");
                    double salary = input.nextDouble();
                    System.out.println("Age: ");
                    int Age = input.nextInt();
                    input.nextLine();
                    System.out.println("Field: ");
                    String field = input.nextLine();

                    io.addWorker(ID, password, name, workHours, bornDate, Title, height, weight, salary, Age, field);
                    break;
                }
                else {
                    System.out.println("ID has to be like 60xx!");
                }
            }
        }
    }
}
