package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@RestController
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class })
public class DemoController {

    //setup the Log4j logger
    private static final Logger logger = LogManager.getLogger(DemoController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value= "/add")
    //@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@RequestParam Map<String, String> reqParam) {
        Customer customer = new Customer();
        customer.setFirstName(reqParam.get("firstName"));
        customer.setLastName(reqParam.get("lastName"));
        customerRepository.save(customer);
        logger.info("firstname::" + reqParam.get("firstName"));
        return "added customer " + reqParam.get("firstName");
    }

    @GetMapping(value="/list")
    public Iterable<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping(value="/find/{id}")
    public Customer findCustomerbyId(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }

}
