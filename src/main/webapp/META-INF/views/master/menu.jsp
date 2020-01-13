	<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.companyRecords" action="/anonymous/companyRecord/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.investor" action="/anonymous/investor/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.announcement" action="/anonymous/announcement/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.customisationParameters"
				action="/administrator/customisationParameters/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.companyRecords" action="/administrator/companyRecord/list" />
			<acme:menu-separator />
      <acme:menu-suboption code="master.menu.administrator.companyRecords.create" action="/administrator/companyRecord/create" />
      <acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.challenges" action="/administrator/challenges/list" />
      <acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.challenges.create" action="/administrator/challenges/create" />
		  <acme:menu-separator />
      <acme:menu-suboption code="master.menu.administrator.investor" action="/administrator/investor/list" />
      <acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.investor.create" action="/administrator/investor/create" />
			<
			
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.requests.create" action="/authenticated/requests/create" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')"> 
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/authenticated/offer/create" />
		</acme:menu-option>
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')"> 
			<acme:menu-suboption code="master.menu.worker.applications.list" action="/worker/application/list-mine" />
			<acme:menu-suboption code="master.menu.worker.job.list" action="/worker/job/list" />
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')"> 
			<acme:menu-suboption code="master.menu.employer.job.listMine" action="/employer/job/list-mine" />
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create" />
  		<acme:menu-suboption code="master.menu.employer.application.listMine" action="/employer/application/list-mine" />

		</acme:menu-option>

		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
				<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />
				<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update"
				access="hasRole('Worker')" />

			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
	
			<acme:menu-suboption code="master.menu.consumer.announcement" action="/authenticated/announcement/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.consumer.companyRecords" action="/authenticated/companyRecord/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.consumer.investor" action="/authenticated/investor/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.consumer.requests" action="/authenticated/requests/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.consumer.offer" action="/authenticated/offer/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.consumer.challenges" action="/authenticated/challenges/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.jobs" action="/authenticated/job/list" />
			
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

