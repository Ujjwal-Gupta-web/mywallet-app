package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.ContactDao;
import com.mywallet.backend.app.models.Contact;
import com.mywallet.backend.app.dto.ResponseDTO;
import com.mywallet.backend.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ContactService {

    @Autowired
    UserService userService;

    @Autowired
    ContactDao contactDao;
    public ResponseDTO addContact(String username, String contact){
        User contact1=userService.getUserByUserName(contact);
        if(contact1==null){
            return new ResponseDTO(false,"USER NOT AVAILABLE",null);
        }
        Contact contactObj=contactDao.getContactsByUsername(username);
        if(contactObj==null){
            contactObj=new Contact(username);
        }
        HashSet<String> contactList=contactObj.getContactList();
        if(contactList.contains(contact)){
            return new ResponseDTO(false,"USER ALREADY EXISTS",null);

        }
        contactList.add(contact);
        contactObj.setContactList(contactList);
        contactDao.addContact(contactObj);
        return new ResponseDTO(true,"USER ADDED",null);
    }

    public Contact getContactsByUsername(String username){
        return contactDao.getContactsByUsername(username);
    }
}
