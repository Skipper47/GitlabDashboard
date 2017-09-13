<%@include file="/init.jsp" %>
 

<%
	HttpClient client = new HttpClient();

	String urlProjects = "http://gitlab.open.gr/api/v4/projects?private_token=usJo8krA1-pRTEoFL4u8&per_page=100";
	GetMethod methodProjects = new GetMethod(urlProjects);

	methodProjects.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
			new DefaultHttpMethodRetryHandler(3, false));
	try {
		int statusCode = client.executeMethod(methodProjects);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + methodProjects.getStatusLine());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	List<String> projects = new ArrayList();

	try {
		String responseBody = methodProjects.getResponseBodyAsString();

		JSONArray allProjects = JSONFactoryUtil.createJSONArray(responseBody);
		for (int i = 0; i < allProjects.length(); i++) {
			JSONObject project = allProjects.getJSONObject(i);
			String name = project.getString("name_with_namespace");
			projects.add(name);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	request.setAttribute("projects", projects);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form id="2" action="<%=configurationActionURL %>" method="Post">
 <aui:input name="<%= Constants.CMD %>" type="hidden"  value="<%= Constants.UPDATE %>" />
<%-- 	<aui:select name="users" multiple="true">
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
	</aui:select> --%>
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
	<input name="Get Users and Projects" type="Submit" value="Get Users and Projects">
</aui:form>
