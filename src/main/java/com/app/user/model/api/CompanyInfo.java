package com.app.user.model.api;


    public class CompanyInfo {
        private String department;
        private String name;
        private String title;
        private AddressInfo address;

        // Constructors, getters, and setters

        public CompanyInfo() {
            // Default constructor
        }

        public CompanyInfo(String department, String name, String title, AddressInfo address) {
            this.department = department;
            this.name = name;
            this.title = title;
            this.address = address;
        }

        // Getter and setter methods for each field

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public AddressInfo getAddress() {
            return address;
        }

        public void setAddress(AddressInfo address) {
            this.address = address;
        }
    }

