package org.techscribbler.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioning {
    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Devaraj");

    }
    @GetMapping("/v2/person")
    public  PersonV2 personV2(){
        return new PersonV2(new Name("Devaraj","Durairaj"));

    }

    @GetMapping(value="/person/param" ,params="version=1")
    public PersonV1 param1(){
        return new PersonV1("Devaraj");

    }
    @GetMapping(value="/person/param",params="version=2")
    public  PersonV2 param2(){
        return new PersonV2(new Name("Devaraj","Durairaj"));

    }
    @GetMapping(value="/person/header",headers="X-API-VERSION=1")
    public PersonV1 header1(){
        return new PersonV1("Devaraj");

    }
    @GetMapping(value="/person/header",headers="X-API-VERSION=2")
    public  PersonV2 header2(){
        return new PersonV2(new Name("Devaraj","Durairaj"));

    }
    @GetMapping(value="/person/produces",produces="application/vnd.techscribber.app.v1+json")
    public PersonV1 contentTypeHeader1(){
        return new PersonV1("Devaraj");

    }
    @GetMapping(value="/person/produces",produces="application/vnd.techscribber.app.v2+json")
    public  PersonV2 contentTypeHeader2(){
        return new PersonV2(new Name("Devaraj","Durairaj"));

    }
}
