<%--
Document : menu
Created on : 7/jan/2015, 19:19:01
Author : Acer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>GRH.com</title>
<link href="css/rh.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="navMenu">
<ul>
<li><a href="#">Inicio</a>
<ul>
<li><a href="#">Log aut</a></li>
<li><a href="#">Modo Administrador</a></li>
</ul><!--fim junçao UL-->
</li><!--fim LI principal-->
</ul><!--fim UL principal-->
<ul>
<li><a href="#">Editar</a>
<ul>
<li><a href="#">Editar Novo</a></li>
<li><a href="#">Editar Existente</a></li>
</ul><!--fim junçao UL-->
</li><!--fim LI principal-->
</ul><!--fim UL principal-->
<ul>
<li><a href="#">Pesquisar</a>
<ul>
<li><a href="#">Nome</a></li>
<li><a href="#">Função</a></li>
<li><a href="#">Idade</a></li>
</ul><!--fim junçao UL-->
</li><!--fim LI principal-->
</ul><!--fim UL principal-->
<ul>
<li><a href="#">Salvar</a>
<ul>
<li><a href="#">Salvar</a></li>
<li><a href="#">Salvar Como</a></li>
</ul><!--fim junçao UL-->
</li><!--fim LI principal-->
</ul><!--fim UL principal-->
<ul>
<li><a href="#">Ajuda</a>
<ul>
<li><a href="#">Sobre</a></li>
</ul><!--fim junçao UL-->
</li><!--fim LI principal-->
</ul><!--fim UL principal-->
<br class="clearFloat"/>
</div><!--fim navMenu-->
<div class="Luser">
<%
out.print(session.getAttribute("user"));
%>
</div>
<div class="identificacao">
</p>
<form>
</br>
<table width="500" align="left" cellpadding="3">
<tr><td width="100">Nome:</td><td><input type="text" name="nome"></td></tr>
<tr><td width="100">Idade:</td><td><input type="text" name="idade"></td></tr>
<tr><td width="100">Morada:</td><td><input type="text" name="morada"></td></tr>
<tr><td width="100">Função:</td><td><input type="text" name="função"></td></tr>
</table>
</form>
</div>
<div class="foto">
<img src="fotoEu.jpg" alt="foto" >
</div>
<div id="textarea">
<br/>
<form align="lefth" >
<div>
<table style="width:100%">
<td style="width:30%; text-align:center">
Competencia
</td>
<td style="width:40%"></td>
<td style="width:30%; text-align:center">
Experiencia
</td>
</table>
</div>
<div>
<textarea id="competencia"></textarea>
<textarea id="experiencia"></textarea>
</div>
</form>
</div>
<div class="cv_cr">
</br>
<div>
<table style="width:50%" align="center">
<td style="width:50%; text-align:center";>
CV
</td>
<td style="width:50%; text-align:center">
CR
</td>
</table>
</div>
<form style="align-content:">
<div id="upload">
<input type="file" name="upload">
<input type="file" name="upload">
</div>
</form>
</div>
</body>
</html>
