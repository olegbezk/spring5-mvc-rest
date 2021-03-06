package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private static final Long ID = 2L;

    private static final String FIRST_NAME = "Jimmy";

    private static final String LAST_NAME = "Root";

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
    public void getCustomerById() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when
        CustomerDto customerById = customerService.getCustomerById(ID);

        //then
        assertEquals(FIRST_NAME, customerById.getFirstname());
        assertEquals(LAST_NAME, customerById.getLastname());
    }

    @Test
    public void testCreateNewCustomer() {
        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDto.getFirstname());
        savedCustomer.setLastname(customerDto.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDto savedDto = customerService.createNewCustomer(customerDto);

        //then
        assertEquals(customerDto.getFirstname(), savedDto.getFirstname());
        assertEquals(getCustomerUrl(1L), savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDto() throws Exception {

        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDto.getFirstname());
        savedCustomer.setLastname(customerDto.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDto savedDto = customerService.saveCustomerByDto(1L, customerDto);

        //then
        assertEquals(customerDto.getFirstname(), savedDto.getFirstname());
        assertEquals(getCustomerUrl(1L), savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomer() {
        customerService.deleteCustomerById(1L);

        verify(customerRepository).deleteById(eq(1L));
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }
}