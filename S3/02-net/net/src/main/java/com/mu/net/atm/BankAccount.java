package com.mu.net.atm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 12:18
 **/

@Data
@AllArgsConstructor
public class BankAccount {
    private int id;
    private double balance;
}
