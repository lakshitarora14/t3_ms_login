package com.coviam.t3login.repository;


import com.coviam.t3login.entity.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {



    Login findPasswordByEmail(String email);

    //List<Login> getAll();
}
