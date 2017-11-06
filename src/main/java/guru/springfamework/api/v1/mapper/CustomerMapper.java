package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);
}
