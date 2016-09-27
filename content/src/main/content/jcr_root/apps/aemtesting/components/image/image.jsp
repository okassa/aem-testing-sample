<%@page language="java" pageEncoding="UTF-8" session="false" %>
<%@include file="/libs/quatico/base/global.jsp" %>
<comp:controller var="ctrl" cls="com.quatico.samples.components.ImageController">

	<figure class="figure" role="group">
		<img class="h-flex" src="${ctrl.source}" alt="${ctrl.alt}"/>
		<figcaption class="figcaption js-figcaption">
			<c:if test="${ctrl.hasCaption}">
				${ctrl.caption}
			</c:if>
		</figcaption>
	</figure>
</comp:controller>
