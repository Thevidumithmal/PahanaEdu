package com.pahanaedu.model;

public class Customer {
    private int accountNumber;
    private String name;
    private String address;
    private String phoneNumber;
    private int unitsConsumed;

    // ✅ Private constructor to enforce the use of Builder
    private Customer(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.name = builder.name;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.unitsConsumed = builder.unitsConsumed;
    }

    // ✅ Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    // ✅ Static nested Builder class
    public static class Builder {
        private int accountNumber;
        private String name;
        private String address;
        private String phoneNumber;
        private int unitsConsumed;

        public Builder setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setUnitsConsumed(int unitsConsumed) {
            this.unitsConsumed = unitsConsumed;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
