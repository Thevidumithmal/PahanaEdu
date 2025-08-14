package com.pahanaedu.business.user.dto;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String role;
    private String phone;
    private String address;
    private String name;       // new
    private String nicNo;      // new

    public UserDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNicNo() { return nicNo; }
    public void setNicNo(String nicNo) { this.nicNo = nicNo; }
}
