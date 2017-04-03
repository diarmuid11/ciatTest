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

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.UrlValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

public class PartnerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(PartnerAction.class);

	private Boolean branch;
	private String headQuarter;
	private String acronym;
	private String name;
	private String type;
	private String country;
	private String city;
	private String url;

	/*
	 * @TypeConversion(converter = "org.ciat.test.DateConverter")
	 * 
	 * @RequiredFieldValidator(message = "Please enter the date") public void
	 * setDateNow(Date now) { this.now = now; } public Date getDateNow() {
	 * return now; }
	 * 
	 * @RequiredStringValidator(message = "Please enter a name", trim = true)
	 * public void setName(String name) { this.name = name; } public String
	 * getName() { return this.name; }
	 */

	public Boolean getBranch() {
		return branch;
	}

	public void setBranch(Boolean branch) {
		this.branch = branch;
	}

	public String getHeadQuarter() {
		return headQuarter;
	}

	public void setHeadQuarter(String headQuarter) {
		this.headQuarter = headQuarter;
	}

	public String getAcronym() {
		return acronym;
	}

	@RequiredStringValidator(message = "Please enter an acronym", trim = true)
	@StringLengthFieldValidator(message = "The acronym must have less than 10 characters",maxLength="9",trim=true)
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	@RequiredStringValidator(message = "Please enter a name", trim = true)
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	@RequiredStringValidator(message = "Please enter a type", trim = true)
	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	@RequiredStringValidator(message = "Please enter a country", trim = true)
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	@RequiredStringValidator(message = "Please enter a city", trim = true)
	public void setCity(String city) {
		this.city = city;
	}

	public String getUrl() {
		return url;
	}

	@RequiredStringValidator(message = "Please enter a url", trim = true)
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Map<String,String> getHeadQuarters() { 
    	return Persistence.getInstance().getHeadQuarters(); 
    }
    
    public Map<String, String> getTypes() {
    	return Persistence.getInstance().getTypes(); 
	}

	public Map<String, String> getCountries() {
    	return Persistence.getInstance().getCountries(); 
	}
	
	@Override
	public void validate() {
		if (name.split("\\s+").length>=10) {
			addFieldError("name", "Name must have less than ten words");
		}
		
		if (branch && (headQuarter==null || "".equals(headQuarter.trim()))){
			addFieldError("headQuarter", "A headquarter is needed if this is a branch");
		}
		
		if (!url.startsWith("http") && !url.startsWith("https")){
			addFieldError("url", "An URL must start with http o https");
		}
	}


	public String execute(){
		try {
			logger.info("WTF");
			if (!this.branch) {
				
				Persistence.getInstance().addPartner(acronym, name, type, country, city, url);
			}else {
				Persistence.getInstance().addBranch(headQuarter,acronym, name, type, country, city, url);
			}
			addActionMessage("All OK!");
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}
}
