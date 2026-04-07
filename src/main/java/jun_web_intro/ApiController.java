package jun_web_intro;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; //Springが自動でUserDaoを注入してくれる
import org.springframework.web.bind.annotation.GetMapping; //GETリクエストをメソッドに対応させるためのもの
import org.springframework.web.bind.annotation.PathVariable;  //URLの一部に含まれる値を取り出す
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; //リクエストボディのJSONをJavaオブジェクトや引数に変換して受け取る
import org.springframework.web.bind.annotation.RequestParam; //クエリパラメータを受け取るもの
import org.springframework.web.bind.annotation.RestController; //@Controller と @ResponseBody をまとめた便利アノテーション

import jakarta.validation.Valid; //オブジェクトの中に入ってる別のオブジェクトまで検査してくれる

@RestController //クラス内のメソッドの戻り値がHTTPのレスポンスボディとして返される
public class ApiController {

    private final UserDao userDao;
    @Autowired
    public ApiController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/echo/{value}") //パスメータの値をそのまま返す
    public String echo(@PathVariable String value) {
        return value;
    }

    @GetMapping("/params") //パスメータの値をJSONで返す
    public Map<String, String> params(
            @RequestParam String name,
            @RequestParam String age) {
        return Map.of( //Mapを作ってその中に入れる
                "name", name,
                "age", age
        );
    }

    @GetMapping("/user") //クエリパラメータで指定した値が一致したらDBから返す
    public User getUser(@RequestParam("user_id") int userId) {
        return userDao.findById(userId);
    }

    @GetMapping("/test") //リクエストボディに指定されたJSONの変数と値をカンマ区切りで返す
    public String test() {
        return "API起動確認OK";
    }

    @PostMapping("/body")
    public String body(@RequestBody SampleRequest request) {
        return "name=" + request.getName()
                + ", age=" + request.getAge()
                + ", job=" + request.getJob();
    }

    @PostMapping("/users") //リクエストボディで受け取ったUser情報を登録
    public String createUser(@Valid @RequestBody UserCreateRequest request) {
        userDao.insert(request);
        return "登録しました";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userDao.findAll();
    }
}