package org.opens.tanaguru.util;

import java.util.HashSet;
import java.util.Set;

import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;

/**
 * Parameters initialization utilities.
 * 
 * This class defines sets of parameters used in different webservice's operations.
 * 
 * @author shamdi
 *
 */
public class ParameterUtils {
	
//	public static Map<String, Parameter> parametersMap = null;
	
	/**@
	 * Common parameters definition with default values. 
	 * @return set of parameters needed in audit request.
	 */
	private static Set<Parameter> commonDefaultParameters() {
		
		Set<Parameter> parameters = new HashSet<Parameter>();
		
		parameters.add(createParameter(6l, "DATA_TABLE_MARKER", ""));
		parameters.add(createParameter(8l, "PROXY_HOST", ""));
		parameters.add(createParameter(41l, "CONSIDER_COOKIES", "true"));
		parameters.add(createParameter(40l, "ALTERNATIVE_CONTRAST_MECHANISM", "false"));
		parameters.add(createParameter(5l, "LEVEL", "AW22;Ar"));
		parameters.add(createParameter(39l, "INFORMATIVE_IMAGE_MARKER", ""));
		parameters.add(createParameter(9l, "PROXY_PORT", ""));
		parameters.add(createParameter(7l,"PRESENTATION_TABLE_MARKER", ""));
		parameters.add(createParameter(38l, "DECORATIVE_IMAGE_MARKER", ""));
		
		return parameters;
	}
	
	/**
	 * Create parameter based on minimal information.
	 * @param code : parameter element code
	 * @param value : parameter value
	 * @return created parameter
	 */
	//TODO : knwon bug to be fixed : every time parameters are created in db.
    public static Parameter createParameter(Long id, String code, String value) {
        
    	ParameterElement parameterElement = new ParameterElementImpl();
        parameterElement.setParameterElementCode(code);
        parameterElement.setId(id);
        
        
        Parameter parameter = new ParameterImpl();
        parameter.setDefaultParameterValue(false);
        parameter.setParameterElement(parameterElement);
        parameter.setValue(value);
        
        return parameter;
    }
    
	/**
	 * Page audit parameters definition with default values. 
	 * @return set of parameters needed in page audit request.
	 */
	public static Set<Parameter> getDefaultParametersForPA(){
		
		Set<Parameter> parameters = commonDefaultParameters();
		
		//specific parameters to page audit
		parameters.add(createParameter(1l, "MAX_DOCUMENTS", "1"));
		parameters.add(createParameter(4l, "MAX_DURATION", "86400"));
		parameters.add(createParameter(2l, "EXCLUSION_REGEXP", ""));
		parameters.add(createParameter(3l, "DEPTH", "0"));
		
		return parameters;
	}
	
	/**
	 * Scenario audit parameters definition with default values. 
	 * @return set of parameters needed in scenario audit request.
	 */
	public static Set<Parameter> getDefaultParametersForScenario() {
		return commonDefaultParameters();
	}
}
