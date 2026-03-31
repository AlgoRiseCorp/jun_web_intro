package jun_web_intro;

public class UserCreateRequest {

    private String name;
    private String email;

    public UserCreateRequest() { //JSONをJavaに変換するときに使う空っぽの箱
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}