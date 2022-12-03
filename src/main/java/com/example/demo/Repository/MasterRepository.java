package com.example.demo.Repository;


import com.example.demo.model.Master;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface MasterRepository extends CrudRepository<Master, String> {

}
