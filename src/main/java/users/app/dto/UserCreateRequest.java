package users.app.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequest {

    private String name;
    private String email;
    private String password;

}
