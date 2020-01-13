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
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
	
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="employer.job.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
<%-- 	<acme:form-option code="employer.job.form.label.true" value="true"/> --%>
<%-- 	<acme:form-option code="employer.job.form.label.false" value="false"/> --%>
<%-- 	</acme:form-select> --%>
	
	<acme:form-textbox code="employer.job.form.label.status" path="status" placeholder="DRAFT, PUBLISHED"/>
<%-- 	<acme:form-option code="employer.job.form.label.status.draft" value="DRAFT"/> --%>
<%-- 	<acme:form-option code="employer.job.form.label.status.published" value="PUBLISHED"/> --%>
<%-- 	</acme:form-select> --%>
	
	<acme:form-textbox code="employer.job.form.label.active" path="active" placeholder="true, false"/>
<%-- 	<acme:form-option code="employer.job.form.label.true" value="true"/> --%>
<%-- 	<acme:form-option code="employer.job.form.label.false" value="false"/> --%>
<%-- 	</acme:form-select> --%>
	
	<acme:form-textarea code="employer.job.form.label.description" path="descriptor.description" />
	
<%-- 	<jstl:if test="${command == 'create' }"> --%>
<%-- 	<acme:form-checkbox code="authenticated.offer.label.accept" path="accept"/> --%>
<%-- 	</jstl:if> --%>

	
	
	 <acme:form-submit test="${command == 'show' }"
	 code="employer.job.form.button.update" 
	 action="/employer/job/update"/>
	 
	 <acme:form-submit test="${command == 'show' }"
	 code="employer.job.form.button.delete" 
	 action="/employer/job/delete"/>
	
	 <acme:form-submit test="${command == 'create' }"
	 code="employer.job.form.button.create" 
	 action="/employer/job/create"/>
	 
	 <acme:form-submit test="${command == 'update' }"
	 code="employer.job.form.button.update" 
	 action="/employer/job/update"/>
	 
	 <acme:form-submit test="${command == 'delete' }"
	 code="employer.job.form.button.delete" 
	 action="/employer/job/delete"/>
	

	<jstl:if test="${command == 'show' }">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/duty/list-mine?id=${descriptor.id}')"
            class="btn btn-default">
            <acme:message code="employer.job.form.button.duties"/>
           
    </button>
    </jstl:if>	
    
    <jstl:if test="${command == 'show' }">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/duty/create?id=${descriptor.id}')"
            class="btn btn-default">
            <acme:message code="employer.job.form.button.crearduties"/>
           
    </button>
    </jstl:if>
    

	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
