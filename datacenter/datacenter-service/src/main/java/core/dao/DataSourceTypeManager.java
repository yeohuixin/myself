package core.dao;

/**
 * 选择数据源
 */
public class DataSourceTypeManager {

    public static int test1 = 0;

    /**
     * 与配置文件的bean名称一致
     */
    public static final String MASTER = "dataSourceMaster";

    public static final String SLAVE = "dataSourceSlave0";

    private static final ThreadLocal<String> DATA_SOURCE_TYPES = new ThreadLocal();

    public static String get() {
        String dataSource = null;
        // 数据源切换测试
//        if((test1++)%2 == 0){
//            dataSource = MASTER;
//        }else {
//            dataSource = SLAVE;
//        }
        dataSource = DATA_SOURCE_TYPES.get();
        if(dataSource == null || "".equals(dataSource)){
            dataSource = MASTER;
        }
        return dataSource;
    }

    public static void set(String dataSourceType) {
        DATA_SOURCE_TYPES.set(dataSourceType);
    }

    public static void reset() {
        DATA_SOURCE_TYPES.remove();
    }

    public static boolean isChoiceWrite() {
        return DATA_SOURCE_TYPES.get() != null && DATA_SOURCE_TYPES.get().contains(MASTER);
    }
}
