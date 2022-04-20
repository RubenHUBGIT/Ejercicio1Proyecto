<!DOCTYPE html>
<%-- Modificar la siguiente línea para soporte del correspondiente juego de caracteres --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
	<head>
	    <title>Centros</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/estilo-ui-hcis.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/ui.jqgrid-hcis.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/jquery-ui-hcis.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/estilo-ui-extra.css" />
		<script src="https://kit.fontawesome.com/cd4dd06bb1.js" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/2.2.4/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/test/includes/css/bootstrap-4.5/js_5.1.3/bootstrap.bundle.min.js"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/theming.js" type="text/javascript"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/svg-inject.min.js"  type="text/javascript"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/ui/1.10.2/jquery-ui-1.10.4.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/grid.locale-es.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/jquery.jqgrid.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/json-2.3/js/jquery.json-2.3.min.js" type="text/javascript"></script>	   
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/centros.js" type="text/javascript"></script>
	</head>
	<body class="overflow-hidden full-height-layout" data-module="Centros" onload="onLoad();">
		<header class="title">Centros</header>
		<!-- Formulario de filtros -->
	    <section class="criterios">
			<header>
				<button type="button" class="btn collapser" data-toggle="collapse" data-target="#capaCriteriosCentros" aria-expanded="true" aria-controls="capaCriteriosCentros">
		            <img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-box-plus.svg" class="icon">
				</button>
				<a data-toggle="collapse" href="#capaCriteriosCentros" aria-expanded="true" aria-controls="capaCriteriosCentros">Criterios de búsqueda</a>
			</header>
			
			<section id="capaCriteriosCentros" class="collapse show">
				<form class="form-search m-0" id="formCentros">
					<div class="form-group" data-display="justified icons-2">		    
				    	<label for="input_codigo_filtro">Código</label>  
					    <input id="input_codigo_filtro" type="text" id="codigoFiltro" name="codigoFiltro" value="">		    
					</div>
					<div class="divGridCentros">						
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="centro_filtro">Centro</label>  
						    <select id="centro_filtro" name="centro_filtro" title="Centro">
						    	
					        </select>			    
						</div>						
					</div>
				</form>
				<section class="form-search buttons border-top">
						<button class="button" type="button" onclick="javascript:buscar();">Buscar</button>
						<button class="button clear" type="button" onclick="javascript:limpiar();">Limpiar</button>
				</section>
			</section>
		</section>
		<!-- Tabla -->
		<div class="hcis-toolbar primary" role="toolbar">
			<div class="btn-group">
				<button type="button" data-bs-toggle="modal" data-bs-target="#addCentroModal" >
					<img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-add-new-row-white.svg" title="Crear">
				</button>
				<button type="button" data-bs-toggle="modal" data-bs-target="#editCentroModal">
					<img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-edit.svg" title="Modificar"/>
				</button>
			</div>
		</div>
		<div id="divResultadosPojo" class="h-100">
		    <table id="panelResultadosPojo">
		    	
		    </table>
		</div>
		<div id="pager"></div>
		<div class="fixed-bot m-2 justify-content-left">
			<a type="button" class="btn colorhcis" href="Index.jsp"><i class="fas fa-home"></i></a>
		</div>


<!-- Modal Añadir Centro -->
		<div class="modal fade" id="addCentroModal" tabindex="-1" aria-labelledby="addCentroModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="addCentroModalLabel">Añadir Centro</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form>
	
	                <label  class="form-label">Centro<span class="fw-light">(*)</span></label>
	                	<input class="form-control" id="nombreCentro" required>
	                <label class="fw-light fs-6 m-2">(*) Campos Requeridos </label>
	
              	</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="javascript:addCentro();">Guardar Centro</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- Modal Editar Centro -->		
		<div class="modal fade" id="editCentroModal" tabindex="-1" aria-labelledby="editCentroModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="editarCentroModalLabel">Editar Centro</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form>
	
					<label  class="form-label">Código<span class="fw-light">(*)</span></label>
	                	<input class="form-control" id="editCodCentro" required>
	                <label  class="form-label">Centro</label>
	                	<input class="form-control" id="editNombreCentro">
	                <label class="fw-light fs-6 m-2">(*) Campos Requeridos </label>
	
              	</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="javascript:editCentro();">Editar Centro</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
	
</html>
