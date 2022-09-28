package com.ccsupport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsupport.common.exceptions.InvalidFieldsException;
import com.ccsupport.common.exceptions.NotFoundException;
import com.ccsupport.common.validator.CommonValidator;
import com.ccsupport.entity.Agent;
import com.ccsupport.repository.AgentRepository;

/** 
 * <h1>Service for agent related handling</h1> 
 * Service that handles the specific needs of the Agent Related activities
 * "/rb" is the root mapping for this class
 * @author  heap-space
 * @version 1.0 
 * @since   2020-03-20 
 */
@Service
public class AgentServiceImpl implements AgentService {
	
	
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private CommonValidator commonValidator;
	
	private Agent agent;
	
	/** 
	  * This method is to validate essential agent properties 
	  * It accept the Agent Objects
	  * It won't return anything. Throws exception if there is anything wrong
	  */
	@Override
	public boolean validateCreateAgent(Agent agent) {
		System.out.println("Validate Agents");
		ArrayList<String> invalidFields = new ArrayList<String>();
		boolean validationStatus = false;
		
		   if(agent == null) {
			   throw new NullPointerException();
		   }
		  try {
			  commonValidator.notEmpty(agent.getFirstName(), "First Name");
	        } catch (IllegalArgumentException | NullPointerException e) {
	        	invalidFields.add("First Name");
	        }
		  
		  try {
			  commonValidator.isValidEmail(agent.getEmail(), "Email");
			  
	        } catch (IllegalArgumentException | NullPointerException e) {
	        	invalidFields.add("Email");
	        }
		  
			if (!invalidFields.isEmpty()) {
				throw new InvalidFieldsException(invalidFields);
			} else{
				validationStatus = true;
			}
			return validationStatus;
	}
	
	/** 
	  * This method is to validate essential agent properties 
	  * It accept the Agent Objects
	  * It won't return anything. Throws exception if there is anything wrong
	  */
	@Override
	public boolean validateUpdateAgent(Agent agent) {
		System.out.println("Validate Update Agents");
		ArrayList<String> invalidFields = new ArrayList<String>();
		boolean validationStatus = false;
		validateCreateAgent(agent);
	  try {
		  commonValidator.isValidID(agent.getId(), "ID");
		  
        } catch (IllegalArgumentException | NullPointerException e) {
        	invalidFields.add("Id");
        }
	  
		if (!invalidFields.isEmpty()) {
			throw new InvalidFieldsException(invalidFields);
		} else{
			validationStatus = true;
		}
		return validationStatus;
	}
	
	@Override
	public Agent createAgent(Agent agent) {
		try {
			agent = agentRepository.save(agent);
		} catch (Exception e) {
			System.out.println("Erro in creating agent" +e.fillInStackTrace());
			throw e;
		}
		return agent;

	}
	
	public Agent updateAgent(Agent agent) {
		try {
			agent = agentRepository.save(agent);
			
		} catch (Exception e) {
			throw e;
		}
		return agent;
	}
	
	public void deleteAgent(long deleteId) {
		try {
			agentRepository.delete(deleteId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String agentName(String testName) {
		return "RST";
	}

	@Override
	public List<Agent> agentListByName(String firstName) {
		return agentRepository.agentListByName(firstName);
	}

	@Override
	public List<Agent> agentList() {
		return (List<Agent>) agentRepository.findAll();
	}

	@Override
	public Agent fetchAgent(long agentId) {
		try {
			commonValidator.isValidID(agentId, "Agent ID");
			agent = agentRepository.findOne(agentId);
			if(null == agent) {
				throw new NotFoundException("Can't find the requested agent");
	
			}
		} catch(Exception e) {
			throw e;
		}
    	return agent;
	}
	
	/**
     * This setter method should be used only by unit tests.
     * @param agentRepository
     */
    public void setAgentRepository(AgentRepository agentRepository) { 
        this.agentRepository = agentRepository;
    }

	/**
	 *This is method is to implement the pagination using spring data JPA
	 *Yet to complete this with proper error and exception handling
	 */
	@Override
	public Page<Agent> agentList(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = new PageRequest(pageNo, pageSize, new Sort(sortBy));
        Page<Agent> pagedResult = agentRepository.findAll(paging);
		return pagedResult;
	}

}
