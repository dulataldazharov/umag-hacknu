package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.Test;
import kz.umag.hacknu.umaghacknu.repo.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    public TestRepository testRepository;

    public List<Test> getAll() {
        return testRepository.findAll();
    }
}
