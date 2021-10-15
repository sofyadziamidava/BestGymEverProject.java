import org.junit.jupiter.api.Test;

import javax.management.remote.JMXServerErrorException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member payingMember = new Member("7805211234", "Nahema Ninsson", LocalDate.parse("2021-08-04"));

    @Test
    void checkMembershipStatusTest() {
        assertTrue(payingMember.checkMembershipStatus(payingMember).equals
                ( "Nahema Ninsson är en nuvarande kund på ”Best Gym Ever”"));
        assertFalse(payingMember.checkMembershipStatus(payingMember).equals
                ("Personen är inte och har inte varit kund på ”Best Gym Ever”"));
    }
}