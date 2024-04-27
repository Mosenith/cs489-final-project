package food.service.dto;

import food.domain.User;

public class UserAdapter {
    public static UserDto getUserDtoFromUser(User user){
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());
        userDTO.setStreet(user.getStreet());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setZip(user.getZip());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        userDTO.setToken(user.getToken());

        return userDTO;
    }
}
