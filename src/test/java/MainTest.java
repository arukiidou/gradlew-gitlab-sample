import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getList() throws SQLException {
        Assertions.assertEquals(Main.getList().stream().count(),107);
    }
}