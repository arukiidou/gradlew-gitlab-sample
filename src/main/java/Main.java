import oracle.ucp.UniversalConnectionPoolException;
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

    private static final String URL = "jdbc:oracle:thin:@192.168.1.4:1521:XE";
    private static final String USER = "system";
    private static final String PASS = "oracle";
    private static final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";

    private static PoolDataSource getPoolDataSource() {
        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

        try {
            pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
            pds.setURL(URL);
            pds.setUser(USER);
            pds.setPassword(PASS);
            pds.setConnectionPoolName("JDBC_UCP_POOL");
            pds.setInitialPoolSize(5);
            pds.setMinPoolSize(5);
            pds.setMaxPoolSize(20);
            pds.setTimeoutCheckInterval(5);
            pds.setInactiveConnectionTimeout(10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pds;
    }

    public static List<Integer> getList(){
        System.out.println("----------------start--------------");
        PoolDataSource pds = getPoolDataSource();

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
            System.out.println("----------------finished--------------");
        }
        return list;
    }


    public static void main(String[] args) throws SQLException {
        System.out.println(getList().stream().collect(Collectors.summarizingInt(e->e)));
    }
}
