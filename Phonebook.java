package org.maktab.phonebook;

public class Phonebook {
    private Contact[] contacts;
    private int emptyHomeIndex = 0;

    public Phonebook() {
        contacts = new Contact[1000];
    }

    public Phonebook(int contactsNumber) {
        contacts = new Contact[contactsNumber];
    }

    public void saveContact(String firstName, String lastName, String phoneNumber, String address) {
        // Problem: What if we exceed array length??????
        Contact newContact = new Contact(firstName, lastName, phoneNumber, address);
        contacts[emptyHomeIndex] = newContact;
        emptyHomeIndex++;
    }

    // search -> fn, ln : phonenumber
    public String search(String firstName, String lastName) {
        String phoneNumber = null;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (contacts[i] != null && contacts[i].getFirstName().equals(firstName) && contacts[i].getLastName().equals(lastName)) {
                phoneNumber = contacts[i].getPhoneNumber();
                break;
            }
        }
        return phoneNumber;
    }

    /*public String[] findByFirstName(String firstName) {
        return new String[10];
    }

    public String[] findByLastName(String lastName) {
        return new String[10];
    }*/

    // Phonenumber -> doesn't change.
    public void updateContact(String firstName, String lastName, String phoneNumber, String address) {
        // find contact that has same phoneNumber.
        int contactIndex = findByPhoneNumber(phoneNumber);
        // update f_n, l_n, address
        if (contactIndex != -1) {
            contacts[contactIndex].setFirstName(firstName);
            contacts[contactIndex].setLastName(lastName);
            contacts[contactIndex].setAddress(address);
        }
    }

    public void deleteContact(String phoneNumber) {
        // find contact with given phoneNumber.
        int contactIndex = findByPhoneNumber(phoneNumber);
        // ?????
        if (contactIndex != -1) {
            contacts[contactIndex] = null;

            // shift dadan
            /*for (int i = contactIndex; i <= emptyHomeIndex ; i++) {
                contacts[i] = contacts[i+1];
            }
            emptyHomeIndex--;*/

            // replace last contact with this one.
            // ORDER is not important!
            /*contacts[contactIndex] = contacts[emptyHomeIndex - 1];
            contacts[emptyHomeIndex -1 ] = null;
            emptyHomeIndex -= 2;*/
        }
    }

   private int findByPhoneNumber(String phoneNumber) {
        int index = -1;
       for (int i = 0; i < emptyHomeIndex; i++) {
           if (contacts[i] != null && contacts[i].getPhoneNumber().equals(phoneNumber)) {
               index = i;
               break;
           }
       }
       return index;
   }



    // search based on phonenumber -> Contact
    // search based on firstname -> Contact[]
    // search based on lastName -> Contact[]
    // search based on firstname, lastName -> Contact[]
}
