package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private static final Long ID = 2L;

    private static final String NAME = "Jimmy";

    @Autowired
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDto> customerDto = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDto.size());
    }

    @Test
    public void getCustomerByName() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);

        when(customerRepository.findByName(anyString())).thenReturn(customer);

        //when
        CustomerDto customerByName = customerService.getCustomerByName(NAME);

        //then
        assertEquals(ID, customerByName.getId());
        assertEquals(NAME, customerByName.getName());
    }

}