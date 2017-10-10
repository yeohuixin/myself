package core.dao;

public class DataSourceTypeManager {

    public static int test1 = 0;

    public static final String MASTER = "dataSourceMaster";

    public static final String SLAVE = "dataSourceSlave0";

    private static final ThreadLocal<String> dataSourceTypes = new ThreadLocal();

    public static String get() {
        String dataSource = null;
//        if((test1++)%2 == 0){
//            dataSource = MASTER;
//        }else {
//            dataSource = SLAVE;
//        }
        dataSource = dataSourceTypes.get();
        if(dataSource == null || dataSource.equals("")){
            dataSource = MASTER;
        }
        return dataSource;
    }

    public static void set(String dataSourceType) {
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset() {
        dataSourceTypes.remove();
    }

    public static boolean isChoiceWrite() {
        return dataSourceTypes.get() != null && dataSourceTypes.get().contains(MASTER);
    }
}
