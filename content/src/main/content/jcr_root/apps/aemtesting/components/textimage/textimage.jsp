<%@page language="java" pageEncoding="UTF-8" session="false" %>
<%@include file="/libs/quatico/base/global.jsp" %>
<comp:controller var="ctrl" cls="com.quatico.samples.components.TextImageController">
	<c:set var="title">
		<c:if test="${comp:exists(ctrl.content.text.title)}">
			<h2>
				${ctrl.content.text.title}
			</h2>
		</c:if>
	</c:set>
	<c:set var="showTitleBelow" value="${ctrl.showTitleBelow}"/>
	<article class="h-cf">
		<c:if test="${not showTitleBelow}">${title}</c:if>
		<c:if test="${ctrl.content.image.hasImageData}">
			<figure <c:if test="${ctrl.content.image.imageSize eq 'small'}"> class="float-${ctrl.content.image.imagePosition} span2"</c:if>>
				${ctrl.content.image.html}
				<c:if test="${comp:exists(ctrl.imageLegend)}">
					<figcaption>${ctrl.imageLegend}</figcaption>
				</c:if>
			</figure>
		</c:if>
		<c:if test="${showTitleBelow}">${title}</c:if>
			${ctrl.content.text.text}
	</article>
</comp:controller>
