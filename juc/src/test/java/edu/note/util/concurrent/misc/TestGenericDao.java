package edu.note.util.concurrent.misc;

import edu.note.util.concurrent.model.Employee;
import edu.note.util.concurrent.model.GenericDao;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestGenericDao {
    public static void main(String[] args) {
        GenericDao dao = new GenericDaoCached();
        System.out.println("============> 查询");
        String sql = "select * from employee where empno = ?";
        int empno = 7369;
        Employee employee = dao.queryOne(Employee.class, sql, empno);
        System.out.println(employee);
        employee = dao.queryOne(Employee.class, sql, empno);
        System.out.println(employee);
        employee = dao.queryOne(Employee.class, sql, empno);
        System.out.println(employee);

        System.out.println("============> 更新");
        dao.update("update employee set sal = ? where empno = ?", 800, empno);
        employee = dao.queryOne(Employee.class, sql, empno);
        System.out.println(employee);
    }
}

class GenericDaoCached extends GenericDao {
    private final GenericDao dao = new GenericDao();
    private final Map<SqlPair, Object> map = new HashMap<>();
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    @Override
    public <T> List<T> queryList(Class<T> beanClass, String sql, Object... args) {
        return dao.queryList(beanClass, sql, args);
    }

    @Override
    public <T> T queryOne(Class<T> beanClass, String sql, Object... args) {
        // 先从缓存中找，找到直接返回
        SqlPair key = new SqlPair(sql, args);;
        rw.readLock().lock();
        try {
            T value = (T) map.get(key);
            if(value != null) {
                return value;
            }
        } finally {
            rw.readLock().unlock();
        }
        rw.writeLock().lock();
        try {
            // 多个线程
            T value = (T) map.get(key);
            if(value == null) {
                // 缓存中没有，查询数据库
                value = dao.queryOne(beanClass, sql, args);
                map.put(key, value);
            }
            return value;
        } finally {
            rw.writeLock().unlock();
        }
    }

    @Override
    public int update(String sql, Object... args) {
        rw.writeLock().lock();
        try {
            // 先更新库
            int update = dao.update(sql, args);
            // 清空缓存
            map.clear();
            return update;
        } finally {
            rw.writeLock().unlock();
        }
    }

    static class SqlPair {
        private final String sql;
        private final Object[] args;

        public SqlPair(String sql, Object[] args) {
            this.sql = sql;
            this.args = args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SqlPair sqlPair = (SqlPair) o;
            return Objects.equals(sql, sqlPair.sql) &&
                    Arrays.equals(args, sqlPair.args);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(sql);
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }
    }

}
