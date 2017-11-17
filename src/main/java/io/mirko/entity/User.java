package io.mirko.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;

    public User(@NotNull @FirstCapString String firstName, @NotNull @FirstCapString String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}

    @NotNull
    @FirstCapString(message="{users.first_name.first_cap}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @FirstCapString(message="{users.last_name.first_cap}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
