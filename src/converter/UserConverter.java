package converter;

import model.User;
import dto.UserDTO;

public class UserConverter {
    public UserDTO converteUserToUserDTO(User user) {
        return UserDTO.newBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}