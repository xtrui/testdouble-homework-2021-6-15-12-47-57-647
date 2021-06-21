package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tw.banking.Printer.SEPARATOR;
import static com.tw.banking.Printer.STATEMENT_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PrinterTest {

    @Test
    void should_return_right_string_when_print() {
        //given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);
        Transaction transaction1 = new Transaction("15/06/2021", 200);
        Transaction transaction2 = new Transaction("15/06/2021", -100);
        List<Transaction> transactions = List.of(transaction1, transaction2);

        //when
        printer.print(transactions);

        //then
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(console, times(transactions.size() + 1)).printLine(stringArgumentCaptor.capture());
        List<String> printLines = stringArgumentCaptor.getAllValues();

        assertEquals(STATEMENT_HEADER, printLines.get(0));
        printLines.remove(0);
        AtomicInteger index = new AtomicInteger();
        AtomicInteger runningBalance = new AtomicInteger();
        Collections.reverse(printLines);
        printLines.forEach(printLine -> {
            Transaction transaction = transactions.get(index.getAndIncrement());
            runningBalance.addAndGet(transaction.amount());
            assertEquals(transaction.date()
                    + SEPARATOR
                    + transaction.amount()
                    + SEPARATOR
                    + runningBalance, printLine);
        });
    }
}