package io.mirko.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @SequenceGenerator(
            name="users_id_seq",
            sequenceName="users_id_seq",
            allocationSize=1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="users_id_seq")
    private Long id;

    @Basic(optional = false)
    @Column(name="first_name")
    private String firstName;

    @Basic(optional = false)
    @Column(name="last_name")
    private String lastName;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded
    // This is shit. It does not seem to be possible to have all-or-nothing.
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(nullable=true)),
            @AttributeOverride(name="city", column=@Column(nullable=true)),
            @AttributeOverride(name="country", column=@Column(nullable=true))
    })
    private Address address;


    public User(@NotNull @FirstCapString String firstName, @NotNull @FirstCapString String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}

    @JsonbTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @FirstCapString(message="{users.first_name.first_cap}")
    @Size(max=64)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @FirstCapString(message="{users.last_name.first_cap}")
    @Size(max=64)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        return getAddress() != null ? getAddress().equals(user.getAddress()) : user.getAddress() == null;
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
                ", address='" + address + '\'' +
                '}';
    }
}
