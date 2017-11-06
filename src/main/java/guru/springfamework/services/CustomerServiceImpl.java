package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerMapper customerMapper, final CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    final CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(final Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDto createNewCustomer(final CustomerDto customerDto) {
        return saveAndReturnDto(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public CustomerDto saveCustomerByDto(final Long id, final CustomerDto customerDto) {
        final Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);

        return saveAndReturnDto(customer);
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(customer -> {

            if (customerDto.getFirstname() != null) {
                customer.setFirstname(customerDto.getFirstname());
            }

            if (customerDto.getLastname() != null) {
                customer.setLastname(customerDto.getLastname());
            }

            CustomerDto returnDto = customerMapper.customerToCustomerDto(customerRepository.save(customer));

            returnDto.setCustomerUrl("/api/v1/customer/" + id);

            return returnDto;

        }).orElseThrow(RuntimeException::new); //todo implement better exception handling;
    }

    private CustomerDto saveAndReturnDto(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDto returnDto = customerMapper.customerToCustomerDto(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnDto;
    }
}
