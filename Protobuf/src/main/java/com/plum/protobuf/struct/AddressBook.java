package com.plum.protobuf.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class AddressBook {
    List<Person> persons;

    public AddressBook() {
        this.persons = new ArrayList<>();
    }

    public void addPersons(Person person) {
         persons.add(person);
    }

    public List<Person> getPersons( ) {
        return persons;
    }
}
