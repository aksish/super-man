namespace java com.tektak.thft

typedef i32 int
struct Person{
1: int userId;
2: string fName;
3: string lName;
4: string phone;
5: string address;
}

exception PersonNotFound{
    1: string message;
}



typedef Person person

service Inventory{
    int addPerson(1: Person person),
    person getPerson(2: int userId),
    list<Person> getAll()
}