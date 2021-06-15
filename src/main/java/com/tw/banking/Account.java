package com.tw.banking;

public class Account {

    private TransactionRepository transactionRepository;
    private Printer printer;

    public Account(TransactionRepository transactionRepository, Printer printer) {
        this.transactionRepository = transactionRepository;
        this.printer = printer;
    }

    public void deposit(int amount) {
        transactionRepository.addDeposit(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.addWithdraw(amount);
    }

    public void printStatement() {
        printer.print(transactionRepository.allTransactions());
    }
}
