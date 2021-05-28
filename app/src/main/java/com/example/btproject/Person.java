package com.example.btproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String userName, password;

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<Person> getPersonsList(){
        ArrayList persons = new ArrayList();
        persons.add(new Person("Celal","123"));
        persons.add(new Person("Afishionado","idneMaN"));
        persons.add(new Person("Ambrosaur","eReGeOc"));
        persons.add(new Person("Slamboozle","rOCATos"));
        persons.add(new Person("BlabberCrab","RaStUCl"));
        persons.add(new Person("Catnoodle","ablEiVe"));
        persons.add(new Person("Caterwhy","IshOMeT"));
        persons.add(new Person("CuckooBlue","ecIliTO"));
        persons.add(new Person("RibbitRiot","vIDeNsi"));
        persons.add(new Person("GobbeldyGator","iNGEADO"));
        persons.add(new Person("Hedgehound","iCiDERe"));
        persons.add(new Person("Kittenkerfuffle","nChewAy"));

        return persons;
    };

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
