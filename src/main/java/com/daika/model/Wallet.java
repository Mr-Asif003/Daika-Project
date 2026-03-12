package com.daika.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "wallets")
public class Wallet {
        @Id
        private String id;
        private String userId;
        private double balance;

}
