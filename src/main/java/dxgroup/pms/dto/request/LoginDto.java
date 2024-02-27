//package dxgroup.pms.dto.request;
//
//import dxgroup.pms.domain.Person;
//import dxgroup.pms.domain.Role;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Getter
//public class LoginDto {
//    private final String name;
//    private final String email;
//    private final String userId;
//    private final String password;
//    private final Role role = Role.USER;
//
//    public static LoginDto from(Person person) {
//        return new LoginDto(person.getName(), person.getEmail(), person.getUserId(), person.getPassword());
//    }
//}
//todo 삭제 ㄱㄱ