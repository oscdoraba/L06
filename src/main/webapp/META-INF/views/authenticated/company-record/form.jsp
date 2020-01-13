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

<acme:form readonly="true">

	<acme:form-textbox code="anonymous.companyRecord.form.label.name" path="nameExtra" />
	<acme:form-textbox code="authenticated.companyRecord.form.label.sector" path="sector" />
	<acme:form-textbox code="authenticated.companyRecord.form.label.ceoName" path="ceoName" />
	<acme:form-textbox code="authenticated.companyRecord.form.label.activities" path="activities" />
	<acme:form-url code="authenticated.companyRecord.form.label.web" path="web" />
	<acme:form-textbox code="authenticated.companyRecord.form.label.phone" path="phone" />
	<acme:form-textbox code="authenticated.companyRecord.form.label.email" path="email" />

	<jstl:if test="${not empty stars}">
		<acme:form-textbox code="authenticated.companyRecord.form.label.stars" path="stars" />
	</jstl:if>

	<acme:form-return code="authenticated.announcement.form.button.return" />
</acme:form>
