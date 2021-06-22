import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> getList() throws SQLException {
        final String URL = "jdbc:oracle:thin:@192.168.1.4:1521:XE";
        final String USER = "system";
        final String PASS = "oracle";
        final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";

        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

        pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
        pds.setURL(URL);
        pds.setUser(USER);
        pds.setPassword(PASS);
        pds.setConnectionPoolName("JDBC_UCP_POOL");

        // Default is 0. Set the initial number of connections to be created
        // when UCP is started.
        pds.setInitialPoolSize(5);
        pds.setMinPoolSize(5);
        pds.setMaxPoolSize(20);
        pds.setTimeoutCheckInterval(5);
        pds.setInactiveConnectionTimeout(10);

        List<Integer> list = new ArrayList<>();
        final String SQL = "select * from HR.EMPLOYEES";
        try (Connection conn = pds.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt("EMPLOYEE_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finished");
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getList().stream().collect(Collectors.summarizingInt(e->e)));
    }
}
