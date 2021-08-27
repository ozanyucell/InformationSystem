import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void mainMenu(){
        System.out.println("1. Login.");
        System.out.println("2. Exit.");
    }

    public static void main(String[] args)  {
        Administrator admin = new Administrator();
        Manager manager = new Manager();
        Worker worker = new Worker();
        Doctor doctor = new Doctor();
        Scanner input = new Scanner(System.in);
        IO io = new IO();
        try {
            io.readPasswords();
            io.readAnnouncement();
            io.readMedicalAnnouncements();
            io.readMessages();
            io.readSickReports();
            io.readID();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList> allData = new ArrayList<>();
        ArrayList<Administrator> allAdministrator = new ArrayList<>();
        ArrayList<Doctor> allDoctors = new ArrayList<>();
        ArrayList<Manager> allManagers = new ArrayList<>();
        ArrayList<Worker> allWorkers = new ArrayList<>();

        try {
            allData = io.getAllInformation();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        allAdministrator = allData.get(0);
        allDoctors = allData.get(1);
        allManagers = allData.get(2);
        allWorkers = allData.get(3);


        for (;;) {
            try {
                mainMenu();
                int choice = input.nextInt();
                input.nextLine();
                System.out.println();
                if (choice == 1) {
                    for (;;) {
                        System.out.print("ID (Case Sensitive): ");
                        String ID = input.nextLine();
                        System.out.print("Password (Case Sensitive): ");
                        String password = input.nextLine();
                        System.out.println();

                        //admin
                        if (io.checkPassword(password) && ID.substring(0,2).equals("90") && io.checkID(ID) && io.matchPasswordID(password,ID)) {
                            for (;;) {
                                admin.Menu();
                                int choice2 = input.nextInt();
                                input.nextLine();
                                System.out.println();

                                if (choice2 == 1) {
                                    for(;;) {
                                        System.out.println("1. Announcements. ");
                                        System.out.println("2. Medical Announcements.");
                                        System.out.println("3. Return to menu.");
                                        int choiceAnn = input.nextInt();
                                        System.out.println();

                                        if (choiceAnn == 1) {
                                            if (io.checkAnnouncement()) {
                                                for (int i = 0; i < io.annTitleArrayL.size(); i++) {
                                                    System.out.println(i + 1 + ". " + io.annTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println();
                                                System.out.println(io.annTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.annBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else{
                                                System.out.println("You have no announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 2) {
                                            if (io.checkMedAnnouncement()) {
                                                for (int i = 0; i < io.medAnnTitleArrayL.size(); i++) {
                                                    System.out.println(i+1 + ". " + io.medAnnTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println(io.medAnnTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.medAnnBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else if(io.checkMedAnnouncement()){
                                                System.out.println("You have no medical announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 3) {
                                            break;
                                        }
                                        else{
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                        System.out.println();
                                    }

                                }

                                else if (choice2 == 2) {
                                    for(;;) {
                                        System.out.println("1. Send a message.");
                                        System.out.println("2. View your messages.");
                                        System.out.println("3. Return to menu.");
                                        int messageMenuChoice = input.nextInt();
                                        input.nextLine();
                                        System.out.println();
                                        if (messageMenuChoice == 1) {
                                            File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                            BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                            System.out.print("To (ID): ");
                                            String to = input.nextLine();
                                            System.out.print("Topic: ");
                                            String topic = input.nextLine();
                                            System.out.print("Your Message:");
                                            String message = input.nextLine();

                                            writerMessage.append(ID);
                                            writerMessage.newLine();
                                            writerMessage.append(to);
                                            writerMessage.newLine();
                                            writerMessage.append(topic);
                                            writerMessage.newLine();
                                            writerMessage.append(message);
                                            writerMessage.newLine();
                                            writerMessage.flush();
                                            System.out.println("Your message has been sent.");
                                            System.out.println();
                                        }

                                        else if (messageMenuChoice == 2) {
                                            int cnt = 1;
                                            int i = 0;
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            if (io.checkMessages(ID)) {
                                                for (; i < io.messageToIDsArrayList.size(); i++) {
                                                    if (io.messageToIDsArrayList.get(i).equals(ID)) {
                                                        System.out.println(cnt + ". " + io.messageTopicsArrayList.get(i) + ", From: " + io.messageFromIDsArrayList.get(i));
                                                        helper.add(cnt);
                                                        helper.add(i);
                                                        cnt++;
                                                    }
                                                }
                                                int messageChoice = input.nextInt();
                                                System.out.println("From: " + io.messageFromIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println("To (ID): " + io.messageToIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println("Topic: " + io.messageTopicsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println(io.messagesArrayList.get(helper.get(2*messageChoice-1)));

                                                System.out.println();
                                                System.out.println();
                                                System.out.println("1. Reply.");
                                                System.out.println("2. Exit.");
                                                System.out.println();

                                                int messageChoice2 = input.nextInt();
                                                if (messageChoice2 == 1) {
                                                    File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                                    BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                                    System.out.print("Topic: ");
                                                    String topic = input.nextLine();
                                                    System.out.print("Your Message:");
                                                    String message = input.nextLine();

                                                    writerMessage.append(ID);
                                                    writerMessage.newLine();
                                                    writerMessage.append(io.messageFromIDsArrayList.get(messageChoice - 1));
                                                    writerMessage.newLine();
                                                    writerMessage.append(topic);
                                                    writerMessage.newLine();
                                                    writerMessage.append(message);
                                                    writerMessage.newLine();
                                                    writerMessage.flush();
                                                }
                                                else if (messageChoice2 == 2) {
                                                    break;
                                                }
                                            }
                                            else{
                                                System.out.println("You have no message.");
                                                System.out.println();
                                            }
                                        }
                                        else if (messageMenuChoice == 3) {
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }

                                else if (choice2 == 3) {
                                    boolean valid = true;
                                    while (valid) {
                                        System.out.println("Please choose the user type which you are looking for: ");
                                        System.out.println("1. Administrator.");
                                        System.out.println("2. Manager.");
                                        System.out.println("3. Worker.");
                                        System.out.println("4. Doctor.");
                                        System.out.println("5. Return to menu.");
                                        int choiceInfo = input.nextInt();
                                        System.out.println();

                                        if (choiceInfo == 1) {
                                            System.out.println("Please choose the user which you are looking for:  ");
                                            int cnt = 1;
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                System.out.println(cnt + ". " + allAdministrator.get(i).getName());
                                                cnt++;
                                            }

                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allAdministrator.get(i).printInfoForAdmin();
                                                    System.out.println();
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 2) {
                                            System.out.println("Please choose the user which you are looking for:  ");
                                            int cnt = 1;
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                System.out.println(cnt + ". " + allManagers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allManagers.get(i).printInfoForAdmin();
                                                    System.out.println();
                                                    break;
                                                }
                                            }

                                        }
                                        else if (choiceInfo == 3) {
                                            System.out.println("Please choose the user which you are looking for:  ");
                                            int cnt = 1;
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                System.out.println(cnt + ". " + allWorkers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allWorkers.get(i).printInfoForAdmin();
                                                    System.out.println();
                                                    break;
                                                }
                                            }

                                        }
                                        else if (choiceInfo == 4) {
                                            System.out.println("Please choose the user which you are looking for:  ");
                                            int cnt = 1;
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                System.out.println(cnt + ". " + allDoctors.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allDoctors.get(i).printInfoForAdmin();
                                                    System.out.println();
                                                    break;
                                                }
                                            }

                                        }
                                        else if (choiceInfo == 5){
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }

                                }
                                else if (choice2 == 4) {
                                    io.addUserMenu();
                                }
                                else if (choice2 == 5) {
                                    io.makeAnnouncement();
                                }
                                else if (choice2 == 6) {
                                    System.out.println("1. My sick reports.");
                                    System.out.println("2. Other employee's sick reports.");
                                    int choiceSick = input.nextInt();
                                    System.out.println();

                                    if (choiceSick==1){
                                        if(io.checkSickReport(ID)) {
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            int cnt = 1;
                                            for (int i = 0; i < io.sickReportForArray.size(); i++) {
                                                if (io.sickReportForArray.get(i).equals(ID)) {
                                                    System.out.println(cnt +". Dr. " + io.getSickReportDoctorArrayList.get(i) + " Date Range: " + io.sickReportDateArray.get(i));
                                                    helper.add(cnt);
                                                    helper.add(i);
                                                    cnt++;
                                                }
                                            }
                                            int reportChoice = input.nextInt();
                                            System.out.println();
                                            System.out.println("For: " + io.sickReportForArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println("Date: " + io.sickReportDateArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println(io.sickReportBodyArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println("Dr. " + io.getSickReportDoctorArrayList.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println();
                                        }
                                        else
                                        {
                                            System.out.println("No sick report found.");
                                            System.out.println();
                                        }
                                    }
                                    else if (choiceSick==2) {
                                        if (io.sickReportBodyArray.size()==0) {
                                            System.out.println("No sick report found.");
                                            System.out.println();
                                        }
                                        else{
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            int cnt=1;
                                            for (int i = 0; i < io.sickReportForArray.size(); i++) {
                                                System.out.println(cnt + ". Dr. " +io.getSickReportDoctorArrayList.get(i) + " Date Range: " +io.sickReportDateArray.get(i)+" For: "+io.sickReportForArray.get(i));
                                                helper.add(cnt);
                                                helper.add(i);
                                                cnt++;
                                            }
                                            int reportChoice = input.nextInt();
                                            System.out.println();
                                            System.out.println("For: " + io.sickReportForArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println("Date: " + io.sickReportDateArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println(io.sickReportBodyArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println("Dr. " + io.getSickReportDoctorArrayList.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println();
                                        }
                                    }
                                }
                                else if (choice2 ==7){
                                    break;
                                }
                                else{
                                    System.out.println("Invalid input. Please try again.");
                                }
                            }
                        }

                        //manager
                        else if (io.checkPassword(password) && ID.substring(0,2).equals("80") && io.checkID(ID) && io.matchPasswordID(password,ID)) {
                            for (;;) {
                                manager.Menu();
                                int choice2 = input.nextInt();
                                input.nextLine();
                                System.out.println();

                                if (choice2 == 1) {
                                    for(;;) {
                                        System.out.println("1. Announcements. ");
                                        System.out.println("2. Medical Announcements.");
                                        System.out.println("3. Return to menu.");
                                        int choiceAnn = input.nextInt();
                                        System.out.println();

                                        if (choiceAnn == 1) {
                                            if (io.checkAnnouncement()) {
                                                for (int i = 0; i < io.annTitleArrayL.size(); i++) {
                                                    System.out.println(i + 1 + ". " + io.annTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println();
                                                System.out.println(io.annTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.annBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else{
                                                System.out.println("You have no announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 2) {
                                            if (io.checkMedAnnouncement()) {
                                                for (int i = 0; i < io.medAnnTitleArrayL.size(); i++) {
                                                    System.out.println(i+1 + ". " + io.medAnnTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println(io.medAnnTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.medAnnBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else if(io.checkMedAnnouncement()){
                                                System.out.println("You have no medical announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 3) {
                                            break;
                                        }
                                        else{
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                        System.out.println();
                                    }
                                }
                                else if (choice2 == 2) {
                                    for(;;) {
                                        System.out.println("1. Send a message.");
                                        System.out.println("2. View your messages.");
                                        System.out.println("3. Return to menu.");
                                        int messageMenuChoice = input.nextInt();
                                        System.out.println();
                                        if (messageMenuChoice == 1) {
                                            File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                            BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                            System.out.print("To (ID): ");
                                            String to = input.nextLine();
                                            System.out.print("Topic: ");
                                            String topic = input.nextLine();
                                            System.out.print("Your Message:");
                                            String message = input.nextLine();

                                            writerMessage.append(ID);
                                            writerMessage.newLine();
                                            writerMessage.append(to);
                                            writerMessage.newLine();
                                            writerMessage.append(topic);
                                            writerMessage.newLine();
                                            writerMessage.append(message);
                                            writerMessage.newLine();
                                            writerMessage.flush();
                                            System.out.println("Your message has been sent.");
                                            System.out.println();
                                        }
                                        else if (messageMenuChoice == 2) {
                                            int cnt = 1;
                                            int i = 0;
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            if (io.checkMessages(ID)) {
                                                for (; i < io.messageToIDsArrayList.size(); i++) {
                                                    if (io.messageToIDsArrayList.get(i).equals(ID)) {
                                                        System.out.println(cnt + ". " + io.messageTopicsArrayList.get(i) + ", From: " + io.messageFromIDsArrayList.get(i));
                                                        helper.add(cnt);
                                                        helper.add(i);
                                                        cnt++;
                                                    }
                                                }
                                                int messageChoice = input.nextInt();
                                                System.out.println("From: " + io.messageFromIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println("To (ID): " + io.messageToIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println("Topic: " + io.messageTopicsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println(io.messagesArrayList.get(helper.get(2*messageChoice-1)));

                                                System.out.println();
                                                System.out.println();
                                                System.out.println("1. Reply.");
                                                System.out.println("2. Exit.");
                                                System.out.println();

                                                int messageChoice2 = input.nextInt();
                                                if (messageChoice2 == 1) {
                                                    File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                                    BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                                    System.out.print("Topic: ");
                                                    String topic = input.nextLine();
                                                    System.out.print("Your Message:");
                                                    String message = input.nextLine();

                                                    writerMessage.append(ID);
                                                    writerMessage.newLine();
                                                    writerMessage.append(io.messageFromIDsArrayList.get(messageChoice - 1));
                                                    writerMessage.newLine();
                                                    writerMessage.append(topic);
                                                    writerMessage.newLine();
                                                    writerMessage.append(message);
                                                    writerMessage.newLine();
                                                    writerMessage.flush();
                                                }
                                                else if (messageChoice2 == 2) {
                                                    break;
                                                }
                                            }
                                            else{
                                                System.out.println("You have no message.");
                                                System.out.println();
                                            }
                                        }
                                        else if (messageMenuChoice == 3) {
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2 == 3) {
                                    boolean valid = true;
                                    while (valid) {
                                        System.out.println("Please choose the user type which you are looking for: ");
                                        System.out.println("1. Administrator.");
                                        System.out.println("2. Manager.");
                                        System.out.println("3. Worker.");
                                        System.out.println("4. Doctor.");
                                        int choiceInfo = input.nextInt();
                                        System.out.println();

                                        if (choiceInfo == 1) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                System.out.println(cnt + ". " + allAdministrator.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allAdministrator.get(i).printInfoForManager();
                                                    System.out.println();
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 2) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                System.out.println(cnt + ". " + allManagers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allManagers.get(i).printInfoForManager();
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 3) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                System.out.println(cnt + ". " + allWorkers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allWorkers.get(i).printInfoForManager();
                                                    System.out.println();
                                                    break;
                                                }
                                            }

                                        }
                                        else if (choiceInfo == 4) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                System.out.println(cnt + ". " + allDoctors.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allDoctors.get(i).printInfoForManager();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2 == 4){
                                    io.makeAnnouncement();
                                }
                                else if (choice2 == 5) {
                                    System.out.println("1. My sick reports.");
                                    System.out.println("2. Other employee's sick reports.");
                                    int choiceSick = input.nextInt();
                                    input.nextLine();
                                    System.out.println();

                                    if (choiceSick==1){
                                        if(io.checkSickReport(ID)){
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            int cnt=1;
                                            for (int i = 0; i < io.sickReportForArray.size(); i++) {
                                                if (io.sickReportForArray.get(i).equals(ID)) {
                                                    System.out.println(cnt + ". Dr. " + io.getSickReportDoctorArrayList.get(i) + " Date Range: " +io.sickReportDateArray.get(i));
                                                    helper.add(cnt);
                                                    helper.add(i);
                                                    cnt++;
                                                }
                                            }
                                            int reportChoice = input.nextInt();
                                            System.out.println();
                                            System.out.println("For: " + io.sickReportForArray.get(helper.get(2*reportChoice-1)));
                                            System.out.println("Date: " + io.sickReportDateArray.get(helper.get(2*reportChoice-1)));
                                            System.out.println();
                                            System.out.println(io.sickReportBodyArray.get(helper.get(2*reportChoice-1)));
                                            System.out.println();
                                            System.out.println("Dr. " + io.getSickReportDoctorArrayList.get(helper.get(2*reportChoice-1)));
                                            System.out.println();
                                            System.out.println();
                                        }
                                        else
                                        {
                                            System.out.println("No sick report found.");
                                            System.out.println();
                                        }
                                    }
                                    else if (choiceSick==2) {
                                        if (io.sickReportBodyArray.size() == 0) {
                                            System.out.println("No sick report found.");
                                            System.out.println();
                                        }
                                        else{
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            int cnt=1;
                                            for (int i = 0; i < io.sickReportForArray.size(); i++) {
                                                System.out.println(cnt + ". Dr. " +io.getSickReportDoctorArrayList.get(i) + " Date Range: " +io.sickReportDateArray.get(i)+" For: "+io.sickReportForArray.get(i));
                                                helper.add(cnt);
                                                helper.add(i);
                                                cnt++;
                                            }
                                            int reportChoice = input.nextInt();
                                            System.out.println();
                                            System.out.println("For: " + io.sickReportForArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println("Date: " + io.sickReportDateArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println(io.sickReportBodyArray.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println("Dr. " + io.getSickReportDoctorArrayList.get(helper.get(2 * reportChoice - 1)));
                                            System.out.println();
                                            System.out.println();
                                        }
                                    }
                                }
                                else if (choice2==6){
                                    break;
                                }

                                else{
                                    System.out.println("Invalid input. Please try again.");
                                }
                            }
                        }

                        //doctor
                        else if (io.checkPassword(password) && ID.substring(0,2).equals("70") && io.checkID(ID) && io.matchPasswordID(password,ID)){
                            for (;;) {
                                doctor.Menu();
                                int choice2 = input.nextInt();
                                input.nextLine();
                                System.out.println();

                                if (choice2 == 1) {
                                    for (; ; ) {
                                        System.out.println("1. Announcements. ");
                                        System.out.println("2. Medical Announcements.");
                                        System.out.println("3. Return to menu.");
                                        int choiceAnn = input.nextInt();
                                        input.nextLine();
                                        System.out.println();

                                        if (choiceAnn == 1) {
                                            if (io.checkAnnouncement()) {
                                                for (int i = 0; i < io.annTitleArrayL.size(); i++) {
                                                    System.out.println(i + 1 + ". " + io.annTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println();
                                                System.out.println(io.annTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.annBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else{
                                                System.out.println("You have no announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 2) {
                                            if (io.checkMedAnnouncement()) {
                                                for (int i = 0; i < io.medAnnTitleArrayL.size(); i++) {
                                                    System.out.println(i+1 + ". " + io.medAnnTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println(io.medAnnTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.medAnnBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else if(io.checkMedAnnouncement()){
                                                System.out.println("You have no medical announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 3) {
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                        System.out.println();
                                    }
                                }
                                else if (choice2 == 2) {
                                    for (;;) {
                                        System.out.println("1. Send a message.");
                                        System.out.println("2. View your messages.");
                                        System.out.println("3. Return to menu.");
                                        int messageMenuChoice = input.nextInt();
                                        input.nextLine();
                                        System.out.println();
                                        if (messageMenuChoice == 1) {
                                            File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                            BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                            System.out.print("To (ID): ");
                                            String to = input.nextLine();
                                            System.out.print("Topic: ");
                                            String topic = input.nextLine();
                                            System.out.print("Your Message: ");
                                            String message = input.nextLine();

                                            writerMessage.append(ID);
                                            writerMessage.newLine();
                                            writerMessage.append(to);
                                            writerMessage.newLine();
                                            writerMessage.append(topic);
                                            writerMessage.newLine();
                                            writerMessage.append(message);
                                            writerMessage.newLine();
                                            writerMessage.flush();
                                            System.out.println("Your message has been sent.");
                                            System.out.println();
                                        }
                                        else if (messageMenuChoice == 2) {
                                            int cnt = 1;
                                            int i = 0;
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            if (io.checkMessages(ID)) {
                                                for (; i < io.messageToIDsArrayList.size(); i++) {
                                                    if (io.messageToIDsArrayList.get(i).equals(ID)) {
                                                        System.out.println(cnt + ". " + io.messageTopicsArrayList.get(i) + ", From: " + io.messageFromIDsArrayList.get(i));
                                                        helper.add(cnt);
                                                        helper.add(i);
                                                        cnt++;
                                                    }
                                                }
                                                int messageChoice = input.nextInt();
                                                System.out.println("From: " + io.messageFromIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println("To (ID): " + io.messageToIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println("Topic: " + io.messageTopicsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println(io.messagesArrayList.get(helper.get(2*messageChoice-1)));

                                                System.out.println();
                                                System.out.println();
                                                System.out.println("1. Reply.");
                                                System.out.println("2. Exit.");
                                                System.out.println();

                                                int messageChoice2 = input.nextInt();
                                                input.nextLine();
                                                if (messageChoice2 == 1) {
                                                    File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                                    BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                                    System.out.print("Topic: ");
                                                    String topic = input.nextLine();
                                                    System.out.print("Your Message:");
                                                    String message = input.nextLine();

                                                    writerMessage.append(ID);
                                                    writerMessage.newLine();
                                                    writerMessage.append(io.messageFromIDsArrayList.get(messageChoice - 1));
                                                    writerMessage.newLine();
                                                    writerMessage.append(topic);
                                                    writerMessage.newLine();
                                                    writerMessage.append(message);
                                                    writerMessage.newLine();
                                                    writerMessage.flush();
                                                }
                                                else if (messageChoice2 == 2) {
                                                    break;
                                                }
                                            }
                                            else{
                                                System.out.println("You have no message.");
                                                System.out.println();
                                            }
                                        }
                                        else if (messageMenuChoice == 3) {
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2 == 3) {
                                    boolean valid = true;
                                    while (valid) {
                                        System.out.println("Please choose the user type which you are looking for: ");
                                        System.out.println("1. Administrator. ");
                                        System.out.println("2. Manager.");
                                        System.out.println("3. Worker.");
                                        System.out.println("4. Doctor.");
                                        int choiceInfo = input.nextInt();
                                        System.out.println();

                                        if (choiceInfo == 1) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                System.out.println(cnt + ". " + allAdministrator.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allAdministrator.get(i).printInfoForDoctor();
                                                    System.out.println();
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 2) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                System.out.println(cnt + ". " + allManagers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allManagers.get(i).printInfoForDoctor();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 3) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                System.out.println(cnt + ". " + allWorkers.get(i).getName());
                                                cnt++;
                                            }

                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allWorkers.get(i).printInfoForDoctor();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 4) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                System.out.println(cnt + ". " + allDoctors.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allDoctors.get(i).printInfoForDoctor();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        } else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2 ==4){
                                    String doctorName = "";
                                    for(int i =0; i<allDoctors.size(); i++){
                                        if (allDoctors.get(i).getID().equals(ID)){
                                            doctorName = allDoctors.get(i).getName();
                                        }
                                    }
                                    io.createSickReport(doctorName);
                                }
                                else if (choice2 ==5){
                                    io.makeMedicalAnnouncement();
                                }
                                else if (choice2 == 6) {
                                    break;
                                }
                                else {
                                    System.out.println("Invalid input. Please try again.");
                                }
                            }
                        }

                        //worker
                        else if (io.checkPassword(password) && ID.substring(0,2).equals("60") && io.checkID(ID) && io.matchPasswordID(password,ID)){
                            for(;;) {
                                worker.Menu();
                                int choice2 = input.nextInt();
                                input.nextLine();
                                System.out.println();

                                if (choice2 == 1) {
                                    for (;;) {
                                        System.out.println("1. Announcements. ");
                                        System.out.println("2. Medical Announcements.");
                                        System.out.println("3. Return to menu.");
                                        int choiceAnn = input.nextInt();
                                        System.out.println();

                                        if (choiceAnn == 1) {
                                            if (io.checkAnnouncement()) {
                                                for (int i = 0; i < io.annTitleArrayL.size(); i++) {
                                                    System.out.println(i + 1 + ". " + io.annTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println();
                                                System.out.println(io.annTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.annBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else{
                                                System.out.println("You have no announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 2) {
                                            if (io.checkMedAnnouncement()) {
                                                for (int i = 0; i < io.medAnnTitleArrayL.size(); i++) {
                                                    System.out.println(i+1 + ". " + io.medAnnTitleArrayL.get(i));
                                                }
                                                int choiceMedAnn = input.nextInt();
                                                System.out.println(io.medAnnTitleArrayL.get(choiceMedAnn - 1));
                                                System.out.println(io.medAnnBodyArrayL.get(choiceMedAnn - 1));
                                            }
                                            else if(io.checkMedAnnouncement()){
                                                System.out.println("You have no medical announcement.");
                                            }
                                        }
                                        else if (choiceAnn == 3) {
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                        System.out.println();
                                    }
                                }
                                else if (choice2 == 2) {
                                    for (;;) {
                                        System.out.println("1. Send a message.");
                                        System.out.println("2. View your messages.");
                                        System.out.println("3. Return to menu.");
                                        int messageMenuChoice = input.nextInt();
                                        System.out.println();
                                        if (messageMenuChoice == 1) {
                                            File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                            BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                            System.out.print("To (ID): ");
                                            String to = input.nextLine();
                                            System.out.print("Topic: ");
                                            String topic = input.nextLine();
                                            System.out.print("Your Message:");
                                            String message = input.nextLine();

                                            writerMessage.append(ID);
                                            writerMessage.newLine();
                                            writerMessage.append(to);
                                            writerMessage.newLine();
                                            writerMessage.append(topic);
                                            writerMessage.newLine();
                                            writerMessage.append(message);
                                            writerMessage.newLine();
                                            writerMessage.flush();
                                            System.out.println("Your message has been sent.");
                                            System.out.println();
                                        }
                                        else if (messageMenuChoice == 2) {
                                            int cnt = 1;
                                            int i = 0;
                                            ArrayList<Integer> helper = new ArrayList<>();
                                            if (io.checkMessages(ID)) {
                                                for (; i < io.messageToIDsArrayList.size(); i++) {
                                                    if (io.messageToIDsArrayList.get(i).equals(ID)) {
                                                        System.out.println(cnt + ". " + io.messageTopicsArrayList.get(i) + ", From: " + io.messageFromIDsArrayList.get(i));
                                                        helper.add(cnt);
                                                        helper.add(i);
                                                        cnt++;
                                                    }
                                                }
                                                int messageChoice = input.nextInt();
                                                System.out.println("From: " + io.messageFromIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println("To (ID): " + io.messageToIDsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println("Topic: " + io.messageTopicsArrayList.get(helper.get(2*messageChoice-1)));
                                                System.out.println();
                                                System.out.println(io.messagesArrayList.get(helper.get(2*messageChoice-1)));

                                                System.out.println();
                                                System.out.println();
                                                System.out.println("1. Reply.");
                                                System.out.println("2. Exit.");
                                                System.out.println();

                                                int messageChoice2 = input.nextInt();
                                                if (messageChoice2 == 1) {
                                                    File messages = new File("C:/Users/ozany/Desktop/texts/sentMessages.txt");
                                                    BufferedWriter writerMessage = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(messages, true)));

                                                    System.out.print("Topic: ");
                                                    String topic = input.nextLine();
                                                    System.out.print("Your Message:");
                                                    String message = input.nextLine();

                                                    writerMessage.append(ID);
                                                    writerMessage.newLine();
                                                    writerMessage.append(io.messageFromIDsArrayList.get(messageChoice-1));
                                                    writerMessage.newLine();
                                                    writerMessage.append(topic);
                                                    writerMessage.newLine();
                                                    writerMessage.append(message);
                                                    writerMessage.newLine();
                                                    writerMessage.flush();
                                                }
                                                else if (messageChoice2 == 2) {
                                                    break;
                                                }
                                            }
                                            else{
                                                System.out.println("You have no message.");
                                                System.out.println();
                                            }
                                        }
                                        else if (messageMenuChoice == 3) {
                                            break;
                                        } else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2 == 3) {
                                    boolean valid = true;
                                    while (valid) {
                                        System.out.println("Please choose the user type which you are looking for");
                                        System.out.println("1. Administrator.");
                                        System.out.println("2. Manager.");
                                        System.out.println("3. Worker.");
                                        System.out.println("4. Doctor.");
                                        int choiceInfo = input.nextInt();
                                        System.out.println();

                                        if (choiceInfo == 1) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                System.out.println(cnt + ". " + allAdministrator.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allAdministrator.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allAdministrator.get(i).printInfoForWorker();
                                                    System.out.println();
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 2) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                System.out.println(cnt + ". " + allManagers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allManagers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allManagers.get(i).printInfoForWorker();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 3) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                System.out.println(cnt + ". " + allWorkers.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allWorkers.size(); i++) {
                                                if (chosenUser - 1 == i) {
                                                    allWorkers.get(i).printInfoForWorker();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else if (choiceInfo == 4) {
                                            System.out.println("Please choose the user which you are looking for: ");
                                            int cnt = 1;
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                System.out.println(cnt + ". " + allDoctors.get(i).getName());
                                                cnt++;
                                            }
                                            int chosenUser = input.nextInt();
                                            for (int i = 0; i < allDoctors.size(); i++) {
                                                if (chosenUser-1 == i) {
                                                    allDoctors.get(i).printInfoForWorker();
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                }
                                else if (choice2==4){
                                    if(io.checkSickReport(ID)){
                                        ArrayList<Integer> helper = new ArrayList<>();
                                        int cnt=1;
                                        for (int i = 0; i < io.sickReportForArray.size(); i++) {
                                            if (io.sickReportForArray.get(i).equals(ID)) {
                                                System.out.println(cnt + ". Dr. " + io.getSickReportDoctorArrayList.get(i) + " Date Range: " +io.sickReportDateArray.get(i));
                                                helper.add(cnt);
                                                helper.add(i);
                                                cnt++;
                                            }
                                        }
                                        int reportChoice = input.nextInt();
                                        input.nextLine();
                                        System.out.println();
                                        System.out.println("For: " + io.sickReportForArray.get(helper.get(2*reportChoice-1)));
                                        System.out.println("Date: " + io.sickReportDateArray.get(helper.get(2*reportChoice-1)));
                                        System.out.println();
                                        System.out.println(io.sickReportBodyArray.get(helper.get(2*reportChoice-1)));
                                        System.out.println();
                                        System.out.println("Dr. " + io.getSickReportDoctorArrayList.get(helper.get(2*reportChoice-1)));
                                        System.out.println();
                                        System.out.println();
                                    }
                                    else
                                    {
                                        System.out.println("No sick report found.");
                                        System.out.println();
                                    }
                                }
                                else if (choice2==5){
                                    break;
                                }
                            }
                        }

                        else{
                            System.out.println("ID or Password not found.");
                            System.out.println();
                        }
                    }
                }
                else if (choice == 2) {
                    System.exit(0);
                }
            }
            catch (Exception e) {
                input.nextLine();
                System.out.println("Invalid input.");
                System.out.println();
            }
        }
    }
}