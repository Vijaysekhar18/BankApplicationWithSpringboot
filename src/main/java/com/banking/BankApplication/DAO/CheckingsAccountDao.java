package com.banking.BankApplication.DAO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("checkingAccountDao")
public class CheckingsAccountDao extends AccountsDao {

}
