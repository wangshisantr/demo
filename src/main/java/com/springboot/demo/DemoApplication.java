package com.springboot.demo;

import com.springboot.demo.config.BootSettingConfig;
import com.springboot.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class DemoApplication {

//    @Value("${boot.author}")
//    private  String bootAuthor;
//    @Value("${boot.name}")
//    private String bootName;
    @Autowired
BootSettingConfig bootSettingConfig;
    Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
//        logger.info("this is debug info");
        return "bootAuthor:" + bootSettingConfig.getAuthor()
                + " bootName:" + bootSettingConfig.getName();
    }

    @RequestMapping("index")
    public String index(Model model) {
        Person person = new Person("shisan", 18);
        model.addAttribute("person", person);
        return "index";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
