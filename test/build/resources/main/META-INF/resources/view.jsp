<%@ include file="/init.jsp"%>
<liferay-ui:error key="no-users-selected" message="users.required" />


<%-- <portlet:actionURL name="getTable" var="getTable" />


<aui:form id="1" action="<%=getTable%>" method="Post">
	<aui:select name="users" multiple="true">
		<c:forEach items="${users}" var="user">
			<c:set var="found" value="false"></c:set>
			<c:forEach items="${usersSelected}" var="userSelected">
				<c:if test="${ userSelected == user }">
					<c:set var="found" value="true"></c:set>
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${ found }">
					<option selected="selected" value="${user}">${user}</option>
				</c:when>
				<c:otherwise>
					<option value="${user}">${user}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</aui:select>
	<aui:select name="projects" multiple="true">
		<c:forEach items="${projects}" var="project">
			<c:set var="found" value="false"></c:set>
			<c:forEach items="${projectsSelected}" var="projectSelected">
				<c:if test="${ projectSelected == project }">
					<c:set var="found" value="true"></c:set>
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${ found }">
					<option selected="selected" value="${project}">${project}</option>
				</c:when>
				<c:otherwise>
					<option value="${project}">${project}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</aui:select>
	<input name="Get Users and Projects" type="Submit"
		value="Get Users and Projects">
</aui:form> --%>


<table text-align="center" border="1" style="width: 100%">
	<c:forEach items="${map}" var="map">
		<tr>
			<td>${map.key}</td>
			<c:forEach items="${map.value}" var="name">
				<td>${name}</td>
			</c:forEach>
		</tr>
	</c:forEach>
	<br>
	<br>
</table>



