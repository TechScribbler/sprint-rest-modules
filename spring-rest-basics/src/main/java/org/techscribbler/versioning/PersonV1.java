package org.techscribbler.versioning;

public class PersonV1 {

    public PersonV1(String name){
        this.name=name;
    }
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
