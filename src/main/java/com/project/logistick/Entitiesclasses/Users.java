package com.project.logistick.Entitiesclasses;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String mobile;
    private String location;
    private String name;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    public Users() {}

    public Users(String email, String password, String mobile,
                 String location, String name, UsageType usageType) {
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.location = location;
        this.name = name;
        this.usageType = usageType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UsageType getUsageType() { return usageType; }
    public void setUsageType(UsageType usageType) { this.usageType = usageType; }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", usageType=" + usageType +
                '}';
    }
}
