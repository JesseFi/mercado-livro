package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRespository: CustomerRepository
) {
    val customers = mutableListOf<CustomerModel>();

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRespository.findByNameContaining(name)
        }
        return customerRespository.findAll().toList();
    }

    fun create(customer: CustomerModel) {
        customerRespository.save(customer);
    }

    fun getCustomer(id: Int): CustomerModel {
        return customerRespository.findById(id).get();
    }

    fun update(customer: CustomerModel) {
        if (!customerRespository.existsById(customer.id!!)) {
            throw Exception();
        }
        customerRespository.save(customer);
    }

    fun delete(id: Int) {
        if (!customerRespository.existsById(id)) {
            throw Exception();
        }
        customerRespository.deleteById(id);
    }
}