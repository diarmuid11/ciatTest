<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>CIAT Test</title>
	<s:head />
</head>
<body>
	<s:form action="partner">
		<s:actionerror/>
		<s:actionmessage/>
		<s:checkbox label="Is this institution a branch?" name="branch" onchange="document.getElementById('headQuarter').style.display = this.checked?'block':'none'"></s:checkbox>
		<s:select label="Institution HeadQuarter:" list="headQuarters" emptyOption="true" name="headQuarter" id="headQuarter" style="display:none"></s:select>
		<s:textfield label="Acronym:" name="acronym"/>
		<s:textfield label="Name:" name="name"/>
		<s:select label="Type:" list="types" emptyOption="true" name="type"></s:select>
		<s:select label="Country:" list="countries" emptyOption="true" name="country"></s:select>
		<s:textfield label="City:" name="city"/>
		<s:textfield label="URL:" name="url"/>
		<s:submit />
	</s:form>
</body>
</html>
	