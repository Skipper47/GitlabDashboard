<%@ include file="/init.jsp"%>
<liferay-ui:error key="no-users-selected" message="users.required" />


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



