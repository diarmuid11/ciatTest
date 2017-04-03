/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ciat.test;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;

/**
 * 
 */
@Conversion()
public class IndexAction extends ActionSupport {
    
	private static final long serialVersionUID = 1L;
	
	public Map<String,String> getHeadQuarters() { 
    	return Persistence.getInstance().getHeadQuarters(); 
    }
    
    public Map<String, String> getTypes() {
    	return Persistence.getInstance().getTypes(); 
	}

	public Map<String, String> getCountries() {
    	return Persistence.getInstance().getCountries(); 
	}

	public String execute() throws Exception {
        return SUCCESS;
    }
}
