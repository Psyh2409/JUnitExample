package com.gmail.psyh2409;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class UsersService {
    
    private List<Users> users;
    
    public UsersService(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }
    
    public List<Users> createNewUser(String name, LocalDate birthday) throws CustomFieldException {
        validateUser(name, birthday);
        Users user = new Users(name, birthday);
        users.add(user);
        return users;
    }

    public void removeUser(String name) {
        users = users.stream().filter(it -> !it.getName().equals(name)).collect(Collectors.toList());
//        for (Users u: users) {
//            if (u.getName().equals(name))
//                users.remove(u);
//        }
    }

    public boolean isBirthDay(Users user, LocalDate date) throws CustomFieldException {
        if (isNull(user) || isNull(user.getBirthday()))
            throw new CustomFieldException("User or its date of birth is null");
        if (isNull(date))
            throw new CustomFieldException("Compare date must not be null");
        return date.getDayOfMonth() == user.getBirthday().getDayOfMonth()
                && date.getMonth().equals(user.getBirthday().getMonth());
    }

    private void validateUser(String name, LocalDate birthday) throws CustomFieldException {
        if (isNull(name) || name.isEmpty()) {
            throw new CustomFieldException("Name could not be empty or null");
        }
        if (isNull(birthday)) {
            throw new CustomFieldException("Date of birth could not be null");
        }
    }
}
