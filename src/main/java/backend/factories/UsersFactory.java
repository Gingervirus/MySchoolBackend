package backend.factories;

import backend.domain.Users;

/**
 * Created by amwentzel on 2016/08/29.
 */
public class UsersFactory {
    public static Users getStudentAccount(String studentEmail, String studentPassword, String role){

        return new Users.Builder()

                .password(studentPassword)
                .role(role)
                .build();
    }

    public static Users getStudentAccount(long id, String studentEmail, String studentPassword, String role) {

        return new Users.Builder()
                .user_id(id)
                .password(studentPassword)
                .role(role)
                .build();
    }
}
