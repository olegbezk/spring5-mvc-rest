package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CustomerMapperTest {

    private static final String FIRST_NAME = "Joe";

    private static final String LAST_NAME = "Doe";

    private static final long ID = 1L;

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDto() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);
        customer.setId(ID);

        //when
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);

        //then
        assertEquals(FIRST_NAME, customerDto.getFirstname());
        assertEquals(LAST_NAME, customerDto.getLastname());
    }

}