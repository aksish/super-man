package com.tektak.inv;

import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tektak on 7/14/15.
 */
public class PeopleInventory implements Inventory.Iface {

    List<Person> personList;
    @Override
    public int addPerson(Person person) {
        System.out.println("Person added: " +  person.getFName() + " " + person.getLName());
        return person.getUserId();
    }

    @Override
    public Person getPerson(int userId) throws TException {
        return null;
    }

    @Override
    public List<Person> getAll() throws TException {
        return null;
    }

    public void loadPersons(){
        personList = new ArrayList<>();
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        p1.setUserId(001);
        p1.setFName("Jack");
        p1.setLName("black");

        p2.setUserId(002);
        p2.setFName("Bruce");
        p2.setLName("Willis");

        p3.setUserId(003);
        p3.setFName("Adam");
        p3.setLName("Neon");

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
    }
}
