package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.TickTable;

public interface TickTableRepository extends JpaRepository<TickTable, Integer>{

}
