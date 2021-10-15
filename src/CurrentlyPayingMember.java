import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class CurrentlyPayingMember extends Member{
    ArrayList<Member> allCurrentlyPayingMembers = new ArrayList<>();

    public CurrentlyPayingMember(String idnumber, String name, LocalDate paymentDate) {
        super(idnumber, name, paymentDate);
    }

    public void createListWithAllPayingMembers(ArrayList<Member> members){
        LocalDate date = LocalDate.now();
        for (Member member: members) {
            if (!member.getName().equals("not a member") && !member.getIdnumber().equals("123") && member.getPaymentDate().isAfter(date.minusYears(1))) {
            allCurrentlyPayingMembers.add(member);
        }
    }
    }

    public void createWorkoutInfo(ArrayList<Member> payingMembers){
        LocalDate ld = LocalDate.now();
        String workoutInfo = "";
        for (Member member: payingMembers) {
            workoutInfo = workoutInfo + member.getName() + " " + member.getIdnumber() + " " + ld + "\n";
        }
        registerWorkoutForPayingMember(workoutInfo);
    }
    public void registerWorkoutForPayingMember(String workoutInfo) {
        File log = new File( "C:/Users/Sonya/Downloads/workoutLoggForPayingMembers.txt");
        LocalDate ld = LocalDate.now();
        try (FileWriter fileWriter = new FileWriter(log, true);) {
            if(!log.exists()){
                log.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(workoutInfo);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
