package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        return new ResponseEntity<>(new CustomerListDto(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable final Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody final CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.createNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.saveCustomerByDto(id, customerDto), HttpStatus.OK);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDto), HttpStatus.OK);
    }
}
