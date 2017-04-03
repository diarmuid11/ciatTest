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
	<table style="border:1px">
		<tr>
			<th>Acronym</th>
			<th>Name</th>
			<th>Type</th>
			<th>Country</th>
			<th>City</th>
			<th>URL</th>
			<th>Branch?</th>
			<th>HeadQuarter</th>
		</tr>
		<s:iterator var="partner" value="partners">
			<tr>
				<td><s:property value="acronym"/></td>
				<td><s:property value="name"/></td>
				<td><s:property value="type"/></td>
				<td><s:property value="country"/></td>
				<td><s:property value="city"/></td>
				<td><s:property value="url"/></td>
				<td><s:property value="branch"/></td>
				<td><s:property value="headQuarter"/></td>
			</tr>	
		</s:iterator>
		
	</table>
		<s:url action="index" var="urlTag"/>
		<a href="<s:property value="#urlTag" />" >Enter new Partner</a>

</body>
</html>
	