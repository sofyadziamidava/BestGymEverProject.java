import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    private String idnumber;
    private String name;
    private LocalDate paymentDate;

    public Member(String idnumber, String name, LocalDate paymentDate) {
        this.idnumber = idnumber;
        this.name = name;
        this.paymentDate = paymentDate;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }


    public static ArrayList<String> sortFile() {
        Path p = Paths.get("C:/Users/Sonya/Downloads/customers.txt");
        ArrayList<String> members = new ArrayList<>();
        String tempLine;

        try (BufferedReader reader = Files.newBufferedReader(p);) {
            while ((tempLine = reader.readLine()) != null) {
                tempLine = tempLine + ", " + reader.readLine();
                members.add(tempLine);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return members;
    }

    public static ArrayList<Member> initilizingMembersInArraylist(ArrayList<String> oldArrylist){
        ArrayList<Member> gymMembers = new ArrayList<>();
        for (String member : oldArrylist) {
            String[] separated = member.split(", ", 3);
            gymMembers.add(new Member(separated[0], separated[1], LocalDate.parse(separated[2])));
        }
        return gymMembers;
    }

    public static Member findMemeber(String input, ArrayList<Member> list) {
        for (Member member : list) {
            if (input.equals(member.getIdnumber()) || input.equalsIgnoreCase(member.getName())) {
                return member;
            }
        }
        return new Member("123", "not a member", LocalDate.now().minusYears(2));
    }

    public String checkMembershipStatus(Member member) {
        LocalDate date = LocalDate.now();
        if (member.getName().equals("not a member") && member.getIdnumber().equals("123")){
            return "Personen är inte och har inte varit kund på ”Best Gym Ever”";
        } else if (member.getPaymentDate().isAfter(date.minusYears(1))) {
            return member.getName() + " är en nuvarande kund på ”Best Gym Ever”";
        } else if ( member.getPaymentDate().isBefore(date.minusYears(1))){
            return member.getName() + " är förredetta kund\nÅrsavgiften betalades senast " + member.getPaymentDate();
        }
        return "";
    }

    public void createWorkoutInfo(Member member) {
        String workoutInfo;
        LocalDate ld = LocalDate.now();
        if (member.checkMembershipStatus(member).equals(member.getName() + " är en nuvarande kund på ”Best Gym Ever”")) {
            workoutInfo = member.getName() + " " + member.getIdnumber() + " " + ld + "\n";
            registerWorkoutForPayingMember(workoutInfo);
        }
    }
    public void registerWorkoutForPayingMember(String workoutInfo) {
        File file = new File( "C:/Users/Sonya/Downloads/workoutLoggForPayingMembers.txt");
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(workoutInfo);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
