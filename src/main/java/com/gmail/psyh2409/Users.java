package com.gmail.psyh2409;

import java.time.LocalDate;
import java.util.Objects;

public class Users {
    private String name;
    private LocalDate birthday;

    public Users(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(name, users.name) &&
                Objects.equals(birthday, users.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
