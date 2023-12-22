package io.pcsp.multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/one")
    public String testRepoOne() {
        List<String> results = testRepository.queryOne();
        return results.toString();
    }

    @RequestMapping("/two")
    public String testRepoTwo() {
        List<String> results = testRepository.queryTwo();
        return results.toString();
    }
}
