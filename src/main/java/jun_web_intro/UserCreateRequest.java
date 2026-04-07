package jun_web_intro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserCreateRequest {

    @NotBlank(message = "nameは必須です")
    @Pattern(regexp = "^(?!名無し$).*$", message = "その名前は使えません")
    private String name;

    @NotBlank(message = "e-mailは必須です")
    private String email;

    public UserCreateRequest() {
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