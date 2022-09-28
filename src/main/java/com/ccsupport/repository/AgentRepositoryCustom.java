package com.ccsupport.repository;

import java.util.List;

import com.ccsupport.entity.Agent;

public interface AgentRepositoryCustom {
  //  public void agentList(String firstName);
	 public List<Agent> agentListByName(String firstName);
		

}
