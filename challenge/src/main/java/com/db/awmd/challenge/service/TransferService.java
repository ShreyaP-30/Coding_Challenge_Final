package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Transfer;
import com.db.awmd.challenge.repository.TransferRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

  @Getter
  private final TransferRepository transferRepository;

  @Autowired
  public TransferService(TransferRepository transferRepository) {
    this.transferRepository = transferRepository;
  }

  public Transfer transferMoney(Transfer transfer) {
   return this.transferRepository.transferMoney(transfer);
  }

}
