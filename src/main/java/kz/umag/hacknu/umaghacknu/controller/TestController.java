package kz.umag.hacknu.umaghacknu.controller;

import kz.umag.hacknu.umaghacknu.model.Test;
import kz.umag.hacknu.umaghacknu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    public TestService testService;

    @GetMapping(path="test")
    public @ResponseBody Iterable<Test> test() {
        // This returns a JSON or XML with the users
        return testService.getAll();
    }
}
