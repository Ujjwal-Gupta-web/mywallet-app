package com.mywallet.backend.app.dto;

public class ContactDTO {
        private String contact;
    public ContactDTO() {
        // Default constructor
    }
        public ContactDTO(String contact) {
            this.contact = contact;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "contact='" + contact + '\'' +
                '}';
    }
}
