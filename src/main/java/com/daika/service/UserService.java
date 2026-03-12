package com.daika.service;

import com.daika.model.User;
import com.daika.model.Wallet;
import com.daika.repository.UserRepository;
import com.daika.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository repo;
    private final WalletRepository wrepo;

    public UserService(UserRepository repo, WalletRepository wrepo) {
        this.repo = repo;
        this.wrepo = wrepo;
    }
 @Transactional
    public void createUserWithWallet(User user) {

        // 1️⃣ Save user first (ID gets generated)
        User savedUser = repo.save(user);

        // 2️⃣ Create wallet
        Wallet wallet = new Wallet();
        wallet.setUserId(savedUser.getId());
        wallet.setBalance(500);

        // 3️⃣ Save wallet
        wrepo.save(wallet);
    }
}