<!DOCTYPE html>
<%-- Modificar la siguiente línea para soporte del correspondiente juego de caracteres --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
	<head>
	    <title>Agenda de Urgencias</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/estilo-ui-hcis.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/ui.jqgrid-hcis.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/jquery-ui-hcis.css" />
		<script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/2.2.4/jquery-2.2.4.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/test/includes/css/bootstrap-4.5/js/bootstrap.bundle.min.js"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/theming.js" type="text/javascript"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/svg-inject.min.js"  type="text/javascript"></script>
	 	<script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/ui/1.10.2/jquery-ui-1.10.4.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/plugins/ui.multiselect.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/grid.locale-es.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/jquery.jqgrid.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/json-2.3/js/jquery.json-2.3.min.js" type="text/javascript"></script>
	 	<script >
	 	
	 		function buscar(){
	 			viewPanelResultadosPojo();
	 		}
	 		
	 		function limpiar(){
	 			document.getElementById("centro").value="vacio";
	 			document.getElementById("selectservicio").value="vacio";
	 			document.getElementById("nivel_triaje").value="vacio";
	 			document.getElementById("input_estado").value="vacio";
	 			document.getElementById("ubicacion").value="vacio";
	 			document.getElementById("input_medicoResponsable").value="";
	 			document.getElementById("input_zona").value="";
	 			
	 		}
	 		
	 		function viewPanelResultadosPojo() {
	 		    var pars = "";

	 		    $.ajax({
	 		        url: '<%=request.getContextPath()%>/paciente/getPacientes.test',
	 		        type: "get",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {
	 		            var panelResultados = $("#panelResultadosPojo");
	 		            panelResultados.jqGrid("clearGridData", true);
	 		            panelResultados.jqGrid('GridUnload');
	 		            if (data == '' || data == undefined) {
	 		                alert("Error al cargar los datos");
	 		            } else {
	 		                loadGrid(data);
	 		                ajustarAltura();
	 		            }
	 		        }
	 		    });
	 		}
	 		
	 		function loadGrid(obj) {
	 		    var panel = $("#panelResultadosPojo");
	 		    panel.jqGrid({
	 		    	cmTemplate: {
	 		    	    autoResizable: true
	 		    	},
	 		    	autoresizeOnLoad: true,
	 		    	autoResizing: {
	 		    	    maxColWidth: 500,
	 		    	},
	 		    	data: obj.pacienteList,
	 		        datatype: "local",
	 		        colNames: ['N.H.C','Nombre','Apellidos','Centro','Ubicación'],
	 				colModel:[
	 					{name:'nhc',index:'nhc', width:200},
	 					{name:'nombre',index:'nombre', width:200},
	 					{name:'apellidos',index:'apellidos', width:200},
	 					{name:'centro',index:'centro', width:200},
	 					{name:'ubicacion',index:'ubicacion', width:200},		
	 				],
	 		        multiselect: false,
	 		        jsonReader: {
	 		            repeatitems : false
	 		        },
	 		        viewrecords: true,
	 		        shrinkToFit: false,
	 		        loadComplete : function(id){
	 		        	setTimeout(function() {
	 		        		ajustarAltura();
	 		        	}, 10);
	 		        },
	 		        onSortCol: function (index, columnIndex, sortOrder) {
	 		            sorter = index;
	 		        },
	 		        altRows: true, 
	 		        altclass: 'filapar',
	 		        pager: "#pager",
	 		        recordpos:"left",
	 		        pgbuttons: false,
	 		        pginput: false,
	 		        gridview: false,
	 		        autoencode: true,
	 		        ignoreCase: true,
	 		        rowNum: obj.pacienteList.length,
	 		        hoverrows: false,
	 		    });
	 		    panel.jqGrid('hideCol','cb');
	 		    $('#pager option:last').text('Todos');
	 		}
	 		
	 		function ajustarAltura(){
	 		    var $grid = $("#panelResultadosPojo");
	 		    var $divPanel = $('#divResultadosPojo');

	 		    $grid.setGridWidth($divPanel.width());
	 		    $grid.setGridHeight(0); // reset b4 computing
	 		    var header = $grid.prop('grid')['hDiv'];
	 		    var headerHgt = (header) ? $(header).outerHeight() : 0;
	 		    var $pager = $($grid.getGridParam("pager"))
	 		    var pagerHgt = ($pager.length) ? $pager.outerHeight() : 0;
	 		    var panelHgt = $divPanel.outerHeight();
	 		    var hgt = panelHgt - headerHgt - pagerHgt;
	 		    $grid.setGridHeight(hgt);
	 		}
	 		
	 		$(window).resize(function(){
	 			ajustarAltura();
		    });
	 		$(document).on('hide.bs.collapse show.bs.collapse', function (e) {
	 			ajustarAltura();
		    });
		    $(document).on('hidden.bs.collapse shown.bs.collapse', function (e) {
		        Theming.stopTimer();
		        ajustarAltura();
		    });
	 	</script>
	</head>
	<body class="overflow-hidden full-height-layout" data-module="agenda urgencias">
		<header class="title">Agenda de urgencias</header>
	    <section class="criterios">
			<header>
				<button type="button" class="btn collapser" data-toggle="collapse" data-target="#capaCriteriosUrg" aria-expanded="true" aria-controls="capaCriteriosUrg">
		            <img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-box-plus.svg" class="icon">
				</button>
				<a data-toggle="collapse" href="#capaCriteriosUrg" aria-expanded="true" aria-controls="capaCriteriosUrg">Criterios de búsqueda</a>
			</header>
			<section id="capaCriteriosUrg" class="collapse show">
				<form class="form-search m-0" id="formUrgencias">
					<div class="divGridUrgencias">
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="centro">Centro</label>  
						    <select id="centro" name="centro" title="Centro">
						    	<option value="vacio">&nbsp;</option>
					            <option>
					                -- Todos los Centros --
					            </option>
					            <option>General Nª Sª Remedios</option>
					            <option>Hospital del Este</option>
					            <option>Hospital del Oeste</option>
					            <option>Costa del Sur</option>
					            <option>Hospital Regional</option>
					        </select>			    
						</div>
						<div class="form-group" data-display="justified icons-2">
							<label for="selectservicio">Servicio</label>  
						    <select name="servicio" id="selectservicio" title='<hu:msg texto="Servicio" ml="TRD005441" />' tabindex="1" >
					            <option value="vacio">&nbsp;</option>
					            <option> 
					                -- Todos los Servicios --
					            </option>
					            <option>Digestivo CEX</option>
					            <option>Cadiología</option>
					            <option>Anestesia</option>
					            <option>Consultas Externas</option>
					            <option>Hospital de Día</option>
					    	</select>
						</div>
						<div class="form-group" data-display="auto label-4">
							 <label for="responsable">Médico Responsable</label>  
						     <input class="w-100 mr-0" id="input_medicoResponsable" type="text" id="responsable" name="responsable" value="">
						     <img id="busquedaMedicoResponsable" name="busquedaMedicoResponsable" src="<%=request.getContextPath()%>/test/imagenes/icons/icon-search-form-field-blue.svg" title="Buscar Médico">
					         <img id="delMedicoResponsable" name="delMedicoResponsable" src="<%=request.getContextPath()%>/test/imagenes/icons/icon-erase.svg" title="Borrar Médico">
					         <input type="hidden" id="responsableHidden" name="responsableHidden" value="">
						</div>
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="nivel_triaje">Triaje</label>  
						    <select  id="nivel_triaje" name='nivel_triaje' title="Nivel de triaje" >
				                <option value="vacio">&nbsp;</option>
				                <option>Crítico</option>
				                <option>Estandar</option>
				                <option>Habitual</option>
				                <option>Emergencia</option>
				                <option>Sin prioridad</option>
							</select>			    
						</div>
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="input_estado">Estado</label>  
						    <select  id="input_estado" name='input_estado' title="Estado" >
				                <option value="vacio">&nbsp;</option>
				                <option>Pendiente de triaje</option>
				                <option>Sin atender</option>
				                <option>Atendiendose</option>
				                <option>Ingresado sin cama</option>
				                <option>Pendiente de dar de alta</option>
							</select>		    
						</div>
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="input_zona">Zona</label>  
						    <input id="input_zona" type="text" id="zona" name="zona" value="">		    
						</div>
						<div class="form-group" data-display="justified icons-2">		    
					    	<label for="input_zona">Ubicación</label>  
						    <select  id="ubicacion" name='ubicacion' title="Ubicación" >
				                <option value="vacio">&nbsp;</option>
				                <option>Cama 1001</option>
				                <option>Cama 1002</option>
				                <option>Cama 1003</option>
				                <option>Cama 1004</option>
				                <option>Cama 1005</option>
							</select>	    
						</div>
						<div class="form-group" data-display="justified icons-2">
							<label for="emergencias" class="text-wrap my-auto">Emergencias vitales</label>
							<input type="checkbox" id="emergencias" title="Emergencias vitales">
						</div>
						<div class="form-group" data-display="justified icons-2">
							<label for="pacientes" class="text-wrap my-auto">Pacientes con datos incompletos</label>
							<input type="checkbox" id="pacientes" title="Pacientes con datos incompletos">
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
				<button type="button" onclick="javascript:;">
					<img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-add-new-row-white.svg" title="Crear">
				</button>
				<button type="button" onclick="javascript:;">
					<img src="<%=request.getContextPath()%>/test/imagenes/icons/icon-edit.svg" title="Modificar"/>
				</button>
			</div>
		</div>
		<div id="divResultadosPojo" class="h-100">
		    <table id="panelResultadosPojo">
		    	
		    </table>
		</div>
		<div id="pager"></div>
	</body>
</html>
