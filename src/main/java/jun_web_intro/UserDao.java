package jun_web_intro;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate; //DB操作を省略できる
import org.springframework.stereotype.Repository; //SpringがこのクラスをDIと認識してくれる

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate; //jdbcTemplateを使います

    public UserDao(JdbcTemplate jdbcTemplate) { //UserDaoを使うときjdbcTemplateを受け取る
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(int userId) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject( //SQLを実行して一件だけ受けとる
                sql,
                (rs, rowNum) -> new User( // DBから受け取った一行をUserオブジェクトに渡している
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ),
                userId //？に入る値
        );
    }

    public void insert(UserCreateRequest request) { //nameとe-mailとageとjobを受け取ってテーブルに入れる
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        jdbcTemplate.update(sql,
                request.getName(),
                request.getEmail()
        );
    }

    public List<User> findAll(){
        String spl = "SELECT id, name, email FROM users";

        return jdbcTemplate.query(
            spl,
            (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
            )
        );

    }
}