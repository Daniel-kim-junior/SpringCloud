package demo.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CustomerService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<Customer> findAll() {
        RowMapper<Customer> rowMapper =
                (rs, i) -> new Customer(rs.getLong("ID"),
                        rs.getString("EMAIL"));
        return this.jdbcTemplate.query("select * from CUSTOMERS ", rowMapper);
    }
}
