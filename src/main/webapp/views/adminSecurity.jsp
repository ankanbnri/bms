<%
		response.setHeader("Cache-Control", "no-cache, no-store, no-revalidate");	// HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies
%>
	
<c:if test="${sessionScope.isValidAdmin != true}">
	<%response.sendRedirect(request.getContextPath()+"/admin/login");%>
</c:if>