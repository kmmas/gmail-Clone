package com.example.demo.Models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database {
    private File directory = new File("data");
    private File file;
    private Map<String, Account> AccountList = new HashMap<>();
    private static final Database Instance = new Database();

    private Database() {
        this.file = new File(directory, "Accounts.json");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ObjectMapper map = new ObjectMapper();
        try {
            AccountList = map.readValue(file, new TypeReference<HashMap<String, Account>>() {
            });
        } catch (StreamReadException e) {
            System.out.println("Stream");
            e.printStackTrace();
        } catch (DatabindException e) {
            System.out.println("Databind");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
    }
     
    public File getDirectory() {
		return directory;
	}

	public static Database getInstance() {
        return Instance;
    }

    public Account GetAccountByEmail(String email) {
        return AccountList.get(email);
    }

    public int Signin(String email, String password) {
        if (AccountList.containsKey(email)) {
            if (AccountList.get(email).getPassword().equals(password)) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    public String AddAccount(String username,String email, String password){
        if(AccountList.containsKey(email)){
            return "email already exists";
        }
        Account newuser = new AccountBuilder(username, email, password).SetDefaultFolders().Build();
        AccountList.put(email, newuser);
        UpdateDatabase();
        return "successful";
    }

    public void UpdateDatabase(){
        ObjectMapper map = new ObjectMapper();
        try {
            map.writeValue(file, AccountList);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
