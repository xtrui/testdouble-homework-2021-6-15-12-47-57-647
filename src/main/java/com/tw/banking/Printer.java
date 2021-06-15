package com.tw.banking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Printer {

    public static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    public static final String SEPARATOR = " | ";
    private Console console;

    public Printer(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(STATEMENT_HEADER);
        printStatementLines(transactions);
    }

    private void printStatementLines(List<Transaction> transactions) {
        List<Transaction> copyOfTransactions = new ArrayList(transactions);
        printInReverseOrder(generateOrderedStatementLines(copyOfTransactions));
    }

    private void printInReverseOrder(List<String> orderedStatementLines) {
        Collections.reverse(orderedStatementLines);
        for (String line : orderedStatementLines) {
            console.printLine(line);
        }
    }

    private List<String> generateOrderedStatementLines(List<Transaction> copyOfTransactions) {
        List<String> lines = new ArrayList<>();
        Collections.sort(copyOfTransactions);
        int runningBalance = 0;
        for (Transaction transaction: copyOfTransactions) {
            runningBalance+= transaction.amount();
            lines.add(statementLine(transaction, runningBalance));
        }
        return lines;
    }

    protected String statementLine(Transaction transaction, int runningBalance) {
        return transaction.date()
                + SEPARATOR
                + transaction.amount()
                + SEPARATOR
                 + runningBalance;
    }
}
