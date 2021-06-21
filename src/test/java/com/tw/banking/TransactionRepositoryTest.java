package com.tw.banking;

import org.junit.jupiter.api.Test;

import org.joda.time.LocalDate;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.tw.banking.Clock.DATE_FORMAT;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRepositoryTest {

    @Test
    void should_call_transaction_add_with_right_transaction_when_addDeposit() {
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        TransactionRepository spyTransactionRepository = spy(transactionRepository);
        int amount = 123;
        Transaction transaction = new Transaction(clock.todayAsString(), amount);
        when(clock.todayAsString()).thenReturn( new LocalDate().toString(DATE_FORMAT));

        // when
        spyTransactionRepository.addDeposit(amount);

        // then
        verify(spyTransactionRepository,times(1)).transactions.add(transaction);

    }

    @Test
    void should_call_transaction_add_with_right_transaction_when_addWithdraw() {
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        TransactionRepository spyTransactionRepository = spy(transactionRepository);
        int amount = 123;
        Transaction transaction = new Transaction(clock.todayAsString(), -amount);
        when(clock.todayAsString()).thenReturn( new LocalDate().toString(DATE_FORMAT));
        // when
        spyTransactionRepository.addWithdraw(amount);

        // then
        verify(spyTransactionRepository,times(1)).transactions.add(transaction);

    }

    @Test
    void should_return_right_list_when_allTransactions() {
        TransactionRepository transactionRepository = new TransactionRepository(new Clock());

        //when
        List<Transaction> transactions = transactionRepository.allTransactions();

        //then
        assertEquals(transactions,unmodifiableList(transactionRepository.transactions));

    }
}