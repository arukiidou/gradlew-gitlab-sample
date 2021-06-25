import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getList() throws SQLException {
        IntSummaryStatistics summary = Main.getList().stream().collect(Collectors.summarizingInt(e -> e));
        Assertions.assertEquals(summary.getCount(), 107);
        System.out.println(summary);
    }
}