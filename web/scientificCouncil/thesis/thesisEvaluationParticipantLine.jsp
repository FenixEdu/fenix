<%@page import="net.sourceforge.fenixedu.domain.thesis.ThesisParticipationType"%>
<%@page import="net.sourceforge.fenixedu.domain.thesis.ThesisEvaluationParticipant"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr" %>

<html:xhtml/>

<%
	final ThesisEvaluationParticipant participant = (ThesisEvaluationParticipant) request.getAttribute("thesisEvaluationParticipant");
	final ThesisParticipationType type = participant.getType();
%>
<tr>
	<td>
		<html:link action="<%= "/manageSecondCycleThesis.do?method=editThesisEvaluationParticipant&amp;thesisEvaluationParticipantOid=" + participant.getExternalId() %>">
			<bean:message bundle="ENUMERATION_RESOURCES" key="<%= "ThesisParticipationType." + participant.getType().getName() %>"/>
		</html:link>
	</td>
	<td>
		<bean:define id="url" type="java.lang.String">/publico/retrievePersonalPhoto.do?method=retrieveByUUID&amp;contentContextPath_PATH=/homepage&amp;uuid=<bean:write name="thesisEvaluationParticipant" property="person.username"/></bean:define>
		<img src="<%= request.getContextPath() + url %>"/>
	</td>	
	<td>
		<%= participant.getPersonNameWithLogin() %>
	</td>
	<td>
		<%= participant.getCategory() %>
	</td>
	<td>
		<%= participant.getAffiliation() %>
	</td>
	<td>
		<%
			if (type == ThesisParticipationType.ORIENTATOR) {
		%>
				<%= participant.getThesis().getOrientatorCreditsDistribution() %> %
		<%
			} else if (type == ThesisParticipationType.COORIENTATOR) {
		%>
				<%= participant.getThesis().getCoorientatorCreditsDistribution() %> %
		<%
			}
		%>
	</td>
	<td>
		<bean:define id="confirm" type="java.lang.String">return confirm('<bean:message key="message.confirm.remove" bundle="SCIENTIFIC_COUNCIL_RESOURCES"/>')</bean:define>	
		<html:link page="<%= "/manageSecondCycleThesis.do?method=removeThesisEvaluationParticipant&amp;thesisEvaluationParticipantOid=" + participant.getExternalId() %>"
				style="border-bottom: none;"
				onclick="<%= confirm %>">
			<img src="<%= request.getContextPath() + "/images/transitional/error01.gif" %>"/>
		</html:link>
	</td>
</tr>
