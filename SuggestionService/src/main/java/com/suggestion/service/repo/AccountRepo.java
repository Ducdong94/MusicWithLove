package com.suggestion.service.repo;

import com.suggestion.service.model.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<AccountEntity,Long> {
    AccountEntity findByEmail(String email);
}
