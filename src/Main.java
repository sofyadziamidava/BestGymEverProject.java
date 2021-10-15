import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            String input = JOptionPane.showInputDialog("Skriv in medlemmens namn eller personummer");
            if (input == null) {
                break;
            }
            ArrayList<String> members = Member.sortFile();

            ArrayList<Member> gymMembers = Member.initilizingMembersInArraylist(members);

            Member member = Member.findMemeber(input, gymMembers);
            member.createWorkoutInfo(member);
            String infoForReception = member.checkMembershipStatus(member);
            JOptionPane.showMessageDialog(null, infoForReception);

        }

    }

}