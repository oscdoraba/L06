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
	<acme:form-textbox code="authenticated.challenges.form.label.title" path="title" />
	<acme:form-moment code="authenticated.challenges.form.label.deadline" path="deadline" />
	<acme:form-textbox code="authenticated.challenges.form.label.description" path="description" />
	<acme:form-textbox code="authenticated.challenges.form.label.goal1" path="goal1" />
	<acme:form-textbox code="authenticated.challenges.form.label.reward1" path="reward1" />
	<acme:form-textbox code="authenticated.challenges.form.label.goal2" path="goal2" />
	<acme:form-textbox code="authenticated.challenges.form.label.reward2" path="reward2" />
	<acme:form-textbox code="authenticated.challenges.form.label.goal3" path="goal3" />
	<acme:form-textbox code="authenticated.challenges.form.label.reward3" path="reward3" />
	<acme:form-return code="authenticated.challenges.form.button.return" />
</acme:form>
