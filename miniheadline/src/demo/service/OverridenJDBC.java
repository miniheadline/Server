package demo.service;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OverridenJDBC extends JdbcTemplate{
	
	public OverridenJDBC(DataSource ds) {
		super(ds);
	}
	
    /**
     * ��дJdbcTemplate�����queryForObject����Դ����õ�requiredSingleResult������ѯ���Ľ��Ϊ��ʱ����null(ԭ�����׳��쳣)
     */
    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType) throws DataAccessException {
        return queryForObject(sql, getSingleColumnRowMapper(requiredType));
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, rowMapper);
        return requiredSingleResult(results);
    }

    public static <T> T requiredSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        int size = (results != null ? results.size() : 0); 
        if (size == 0) {
            return null; 
        } 
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, size); 
        } 
        return results.iterator().next(); 
    }
}