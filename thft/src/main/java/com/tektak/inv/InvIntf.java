package com.tektak.inv;

import java.util.List;

/**
 * Created by tektak on 7/14/15.
 */
public interface InvIntf {
    public Person addPerson(Person person);
    public Person updatePerson(Person person);
    public List<Person> getAllperson();
}
