package com.example.demo.Services;


import com.example.demo.Repository.MasterRepository;
import com.example.demo.model.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class MasterService {



    @Autowired
    private MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {

        this.masterRepository = masterRepository;
    }

    public Iterable<Master> list() {
        return masterRepository.findAll();
    }


    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    public void saveOrUpdate(Master master){
        masterRepository.save(master);
    }

    public void delete(Master master) {
        masterRepository.delete(master);
    }

    public void deleteAllPassingEntities(List<Master> masters) {
        masterRepository.deleteAll(masters);
    }

    @Transactional
    public void deleteAll() {
        masterRepository.deleteAll();
    }


    public Iterable<Master> save(List<Master> masters) {
        return masterRepository.saveAll(masters);
    }



}

