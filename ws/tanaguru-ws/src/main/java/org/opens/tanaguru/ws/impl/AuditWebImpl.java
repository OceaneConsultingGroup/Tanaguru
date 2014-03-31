package org.opens.tanaguru.ws.impl;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.util.ParameterUtils;
import org.opens.tanaguru.ws.AuditWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Webservice implementation class.
 * 
 * This class defines all exposed webservice's operations.
 *  
 * @author shamdi
 *
 */
@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuditWebImpl implements AuditWeb {

	@Autowired
    private AuditService auditService;
	
//	@Autowired
//	ParameterDataService parameterDataService;

	
    public AuditWebImpl() {
        super();
    }
    

	@GET
	@Path("/auditPage")
    public Response auditPage(@QueryParam("url")String pageURL, @QueryParam("level")String level) {
		//Get default set of parameters
		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
		
		//define level if necessary : supposing it's not mondatory
		if(level != null && !level.isEmpty()){
			parameters.add(ParameterUtils.createParameter(5l, "LEVEL", level));
		}
		
		//launch ws
    	Audit audit = auditService.auditPage(pageURL, parameters);
    	
    	//return response
    	return Response.status(200).entity("Page audit was launched").build();
    }
	
	
	@POST
	@Path("/auditScenario")
	public Response auditScenario(@FormParam("scenarioName") String scenarioName, @FormParam("scenarioText") String scenarioText , @FormParam("level")String level){
		
//		
//		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForScenario();
//		
//		//define level if necessary : supposing it's not mondatory
//		if(level != null && !level.isEmpty()){
//			parameters.add(ParameterUtils.createParameter(5l, "LEVEL", level));
//		}
//		//TODO : adding security a
//		
//		Audit audit = auditService.auditScenario(scenarioName, scenarioText, parameters);
//    	
//		//return response
    	return Response.status(200).entity("Scenario audit was launched").build();
	}
	

	public Response auditSite(@FormParam("url") String siteURL, @FormParam("level")String level) {
		// TODO Auto-generated method stub
		return null;
	}

    

}
