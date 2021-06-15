package com.tw.banking;

import org.joda.time.LocalDate;
import org.joda.time.format.*;

import java.lang.Comparable;

import static com.tw.banking.Clock.DATE_FORMAT;


public class Transaction implements Comparable<Transaction>{
    private String date;
    private int amount;

    public Transaction(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public String date() {
        return date;
    }

    public int amount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction transaction) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
        final LocalDate date1 = dateTimeFormatter.parseLocalDate(this.date);
        final LocalDate date2 = dateTimeFormatter.parseLocalDate(transaction.date);
        if (date1.isBefore(date2)) {
            return -1;
        }
        return 1;
    }

}
