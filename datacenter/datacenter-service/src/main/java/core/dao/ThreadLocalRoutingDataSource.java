package core.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by D7070 on 2017/10/10.
 */
public class ThreadLocalRoutingDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
