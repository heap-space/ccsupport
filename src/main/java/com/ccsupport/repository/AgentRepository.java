package com.ccsupport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccsupport.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>, AgentRepositoryCustom{
	

}
