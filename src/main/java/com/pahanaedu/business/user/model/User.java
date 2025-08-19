package com.pahanaedu.business.user.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String phone;
    private String address;
    private String name;
    private String nicNo;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
        this.phone = builder.phone;
        this.address = builder.address;
        this.name = builder.name;
        this.nicNo = builder.nicNo;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getName() { return name; }          // new
    public String getNicNo() { return nicNo; }        // new

    public static class Builder {
        private int id;
        private String username;
        private String password;
        private String role;
        private String phone;
        private String address;
        private String name;         // new
        private String nicNo;        // new

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setUsername(String username) { this.username = username; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setRole(String role) { this.role = role; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
        public Builder setAddress(String address) { this.address = address; return this; }
        public Builder setName(String name) { this.name = name; return this; }        // new
        public Builder setNicNo(String nicNo) { this.nicNo = nicNo; return this; }    // new

        public User build() { return new User(this); }
    }
}
