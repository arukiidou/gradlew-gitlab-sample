import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getList() throws SQLException {
        Assertions.assertEquals(Main.getList().stream().count(),107);
        System.out.println(Main.getList().stream().collect(Collectors.summarizingInt(e->e)));
    }
}