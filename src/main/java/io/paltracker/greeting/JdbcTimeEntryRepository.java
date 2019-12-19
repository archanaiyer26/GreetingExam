package io.paltracker.greeting;

import io.paltracker.greeting.model.GreetingGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements GreetingGeneratorRepository {

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;


    @Override
    public GreetingGenerator create(GreetingGenerator greetingGenerator) {

        KeyHolder generatorKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into greeting_generator(message) " +
                    "values (?)",RETURN_GENERATED_KEYS);
            statement.setString(1,greetingGenerator.getMessage());

            return  statement;
        },generatorKeyHolder);

       return find(generatorKeyHolder.getKey().longValue());
    }

    @Override
    public GreetingGenerator update(GreetingGenerator greetingGenerator, Long id) {

        jdbcTemplate.update("UPDATE greeting_generator set message = ? where id = ?",greetingGenerator.getMessage(),
                id);
        return find(id);
    }

    @Override
    public void delete(long id) {
            jdbcTemplate.update("Delete from greeting_generator where id=?",id);
    }

    @Override
    public GreetingGenerator find(long id) {
        return jdbcTemplate.query("SELECT id, message FROM greeting_generator where id=?",new Object[]{id},extractor );
    }

    @Override
    public List<GreetingGenerator> list() {
        return  jdbcTemplate.query("select id, message FROM greeting_generator",mapper);

    }

    private final RowMapper<GreetingGenerator> mapper =  (rs, rowNum) -> new GreetingGenerator(
                rs.getLong("id"),rs.getString("message"));

private final ResultSetExtractor<GreetingGenerator> extractor = (rs )-> rs.next() ? mapper.mapRow(rs,1):null;


}
