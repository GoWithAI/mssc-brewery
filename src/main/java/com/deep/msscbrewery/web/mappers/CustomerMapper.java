package com.deep.msscbrewery.web.mappers;

import com.deep.msscbrewery.domain.Customer;
import com.deep.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

}
