package com.daika.repository;

import com.daika.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet,String> {

}
