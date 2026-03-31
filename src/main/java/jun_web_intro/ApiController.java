package jun_web_intro;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;  //URLの一部に含まれる値を取り出す
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; //文字列とJSONをそのまま返す

@RestController
public class ApiController {

    private final UserDao userDao;

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

    @org.springframework.web.bind.annotation.PostMapping("/body")
    public String body(@RequestBody SampleRequest request) {
        return "name=" + request.getName()
                + ", age=" + request.getAge()
                + ", job=" + request.getJob();
    }

    @org.springframework.web.bind.annotation.PostMapping("/users") //リクエストボディで受け取ったUser情報を登録
    public String createUser(@RequestBody UserCreateRequest request) {
        userDao.insert(request);
        return "登録しました";
    }
}