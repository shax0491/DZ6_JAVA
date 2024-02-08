package com.stream.tasks;

import java.util.*;
import java.util.stream.Collectors;

class PhoneRow {
    private String _unit_name;
    private ArrayList<Integer> _phone_number = new ArrayList<>();

    PhoneRow(String name, int num) {        
        _unit_name = name;
        _phone_number.add(num);
    }

    public int getPhoneNumberCounter() {
        return this.getPhones().size();
    }

    public String toString() {
        return String.format("{%s:%s}", this._unit_name, this._phone_number.toString());
    }

    public String getName() {
        return this._unit_name;
    }

    public ArrayList<Integer> getPhones() {
        return this._phone_number;
    }
}

class PhoneBook {
    private int _ai = 0;
    private static HashMap<Integer, PhoneRow> phoneBook = new HashMap<>();
    public ArrayList<Integer> sortedKeys = new ArrayList<>();

    public void add(String unitName, Integer phoneNum) {
        Boolean[] isExists = { false };
        phoneBook.forEach((k,v) -> {
            String name = v.getName();
            ArrayList<Integer> phones = v.getPhones();
            if (name.equals(unitName)) {
                phones.add(phoneNum);
                isExists[0] = true;
            }
        });
        if (!isExists[0]) {
            phoneBook.put(++this._ai, new PhoneRow(unitName, phoneNum));
        }
        this.wasAdded(this._ai);
    }

    public static HashMap<Integer, PhoneRow> getPhoneBook() {
        return phoneBook;
    }

    public void wasAdded(int idx) {
        String str = new String(
            String.format("**** User %s with id(%d) has %d numbers", 
                phoneBook.get(idx).getName(), 
                idx, 
                phoneBook.get(idx).getPhoneNumberCounter()
            )
        );
        System.out.println(str);
    }
}

class Printer {
    public static void main(String[] args) {
        String name1;
        String name2;
        int phone1;
        int phone2;

        if (args.length == 0) {
            name1 = "Ivanov";
            name2 = "Petrov";
            phone1 = 123456;
            phone2 = 654321;
        } else {
            name1 = args[0];
            name2 = args[1];
            phone1 = Integer.parseInt(args[2]);
            phone2 = Integer.parseInt(args[3]);
        }
        
        PhoneBook myPhoneBook = new PhoneBook();
        
        myPhoneBook.add(name1, phone1);
        myPhoneBook.add(name1, phone2);
        myPhoneBook.add(name2, phone2);
        myPhoneBook.add(name2, phone1);
        myPhoneBook.add(name2, phone2);
        
