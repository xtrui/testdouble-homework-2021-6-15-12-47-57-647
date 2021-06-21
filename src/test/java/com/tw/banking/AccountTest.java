package com.tw.banking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.mockito.Mockito.*;

class AccountTest {

    @Test
    void should_call_transactionRepository_addDeposit_with_123_when_deposit_with_123() {
        //given
        int amount = 123;
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Account account = new Account(transactionRepository, new Printer(new Console()));

        //when
        account.deposit(amount);

        // then
        verify(transactionRepository, times(1)).addDeposit(amount);


    }

    @Test
    void should_call_transactionRepository_addWithdraw_with_123_when_withdraw_with_123() {
        //given
        int amount = 123;
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Account account = new Account(transactionRepository, new Printer(new Console()));

        //when
        account.withdraw(amount);

        // then
        verify(transactionRepository, times(1)).addWithdraw(amount);
    }

    @Test
    void printStatement() {
        //given
        Printer printer = mock(Printer.class);
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Account account = new Account(transactionRepository, printer);
        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.allTransactions()).thenReturn(transactions);
        //when
        account.printStatement();

        // then
        verify(printer, times(1)).print(transactions);
    }
}