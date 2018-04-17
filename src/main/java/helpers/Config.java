package helpers;

public class Config {
//    protected String dbHost = "mysql6002.site4now.net";
//    protected String dbPort = "3306";
//    protected String dbUser = "a38c16_popov39";
//    protected String dbPass = "CbcntvfB334TX";
//    protected String dbName = "db_a38c16_popov39";

    protected static String dbHost = "192.168.0.50";
    protected static String dbPort = "3306";
    protected static String dbUser = "root";
    protected static String dbPass = "psw1234";
    protected static String dbName = "icetronbase";

    protected static String dbUrl = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?autoReconnect=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";


}
