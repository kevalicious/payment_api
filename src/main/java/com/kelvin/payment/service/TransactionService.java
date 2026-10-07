package com.kelvin.payment.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kelvin.payment.dto.TransactionRequest;
import com.kelvin.payment.dto.TransactionResponse;
import com.kelvin.payment.entity.Customer;
import com.kelvin.payment.entity.Transaction;
import com.kelvin.payment.entity.TransactionStatus;
import com.kelvin.payment.exception.ResourceNotFoundException;
import com.kelvin.payment.repository.CustomerRepository;
import com.kelvin.payment.repository.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id " + request.customerId()));

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAmount(request.amount());
        transaction.setCurrency(request.currency().toUpperCase());
        transaction.setReference("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaction.setStatus(TransactionStatus.PENDING);
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream().map(TransactionResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        return TransactionResponse.from(transaction);
    }

}
