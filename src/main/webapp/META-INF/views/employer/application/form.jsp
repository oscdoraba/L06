<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber" readonly="true"/>
	<acme:form-moment  code="employer.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.someSkills" path="someSkills" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.someQualifications" path="someQualifications" readonly="true"/>
 	<acme:form-textbox code="employer.application.form.label.status" path="status" placeholder="ACCEPTED o REJECTED" /> 


	<acme:form-textarea code="employer.application.form.label.justification" path="justification" />
	
	<acme:form-submit test="${command == 'show' }"
	 code="employer.application.form.button.update" 
	 action="/employer/application/update"/>

	 <acme:form-submit test="${command == 'update' }"
	 code="employer.application.form.button.update" 
	 action="/employer/application/update"/>
	 
	 
	
	<acme:form-return code="employer.application.form.button.return" />
</acme:form>
