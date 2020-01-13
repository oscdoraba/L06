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

    
	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" />

	<jstl:if test="${command !='create'}">
	<acme:form-moment code="worker.application.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	<jstl:if test="${command !='create'}">
	<acme:form-textbox code="worker.application.form.label.status" path="status" />
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textbox code="worker.application.form.label.someSkills" path="someSkills" />
	<acme:form-textbox code="worker.application.form.label.someQualifications" path="someQualifications" />
	
	<jstl:if test="${command =='show'}">
    <acme:form-textbox code="worker.application.form.label.job.reference" path="job.reference"/>
    </jstl:if>
  
	 
	   <jstl:if test="${id==0}">
    <acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="create?id=${job.id}"/>
    </jstl:if>
    <jstl:if test="${id!=0}">
    <acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="create?id=${id}"/>
    </jstl:if>
    
   
	 
	<acme:form-return code="worker.application.form.button.return" />
	
</acme:form>
