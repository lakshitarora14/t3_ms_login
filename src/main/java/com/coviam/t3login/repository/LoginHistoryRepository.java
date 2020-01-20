package com.coviam.t3login.repository;

import com.coviam.t3login.entity.LoginHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends CrudRepository<LoginHistory,String> {
}
