package jun_web_intro;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping; //@GetMappingを使うため URLへのゲットアクセスを受けるための部品
import org.springframework.web.bind.annotation.RestController; //@RestControllerを使うため HTTPリクエストを受け取るクラス

@RestController //SpringにWeb用のクラスとして認識させている
public class HelloController {

    @GetMapping("/hello") //URLが使われたら呼ばれる
    public String hello() {
        return "hello";
    }
    @GetMapping("/jun")
    public String jun(){
        return "jun";
    }
    @GetMapping("/html")
    public String html(){
        return "<html><body><h1>hello world</h1><h2>yeah</h2></body></html>";
    }
    @GetMapping("/json")
    public Map<String, String> json(){
        return Map.of("msg", "こんにちは。");
    }
    @GetMapping("/echo")
    public String echo(){
        return "echo";
    }
}