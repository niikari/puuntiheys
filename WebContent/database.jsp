<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Harjoitustyö</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h1>Puun tiheyslaskenta</h1>

	<form method="post" action="/database">
		<table>
			<tr>
				<td></td>
				<td>Arvo</td>
			</tr>
			<tr>
				<td>Paksuus (mm)</td>
				<td><input type="number" name="paksuus"
					value="<%out.print(request.getParameter("paksuus"));%>"></td>
			</tr>
			<tr>
				<td>Pituus (mm)</td>
				<td><input type="number" name="pituus"
					value="<%out.print(request.getParameter("pituus"));%>"></td>
			</tr>
			<tr>
				<td>Leveys (mm)</td>
				<td><input type="number" name="leveys"
					value="<%out.print(request.getParameter("leveys"));%>"></td>
			</tr>
			<tr>
				<td>Paino (g)</td>
				<td><input type="number" name="paino"
					value="<%out.print(request.getParameter("paino"));%>"></td>
			</tr>
			<tr>
				<td>Grain</td>
				<td><input type="text" name="grain"
					value="<%out.print(request.getParameter("grain"));%>"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" class="btn btn-success" name="action"
					value="Laske"></td>
			</tr>
			<tr>
				<td>Tulos (kg/m3)</td>
				<td><span style="font-weight: bold">${ tulos }</span> kg/m3</td>
			</tr>


		</table>

		<table>
			<tr>
				<td>id</td>
				<td>tiheys</td>
				<td>grain</td>
				<td>paksuus</td>
				<td>leveys</td>
				<td>pituus</td>
				<td></td>
			</tr>
			<c:forEach items="${ items }" var="balsalevy">
				<tr id="balsa-${ balsalevy.getId() }">
					<td><c:out value="${ balsalevy.getId() }"></c:out></td>
					<td><c:out value="${ balsalevy.getTiheys() }"></c:out></td>
					<td><c:out value="${ balsalevy.getGrain() }"></c:out></td>
					<td><c:out value="${ balsalevy.getPaksuus() }"></c:out></td>
					<td><c:out value="${ balsalevy.getLeveys() }"></c:out></td>
					<td><c:out value="${ balsalevy.getPituus() }"></c:out></td>
					<td><button type="button" class="btn btn-danger"
							onClick="removeProduct(${ balsalevy.getId() })">DELETE</button></td>
				</tr>
			</c:forEach>
		</table>

	</form>

	<script type="text/javascript">
	async function removeProduct(id) {
		let response = await fetch(`/database?id=${id}`, { method: "DELETE" });

		if (response.status === 200) {
			let element = document.getElementById(`balsa-${id}`);
			element.remove();
		} else {
			alert("Jotain meni pieleen...");
		}
	}
	
	
	</script>


</body>
</html>