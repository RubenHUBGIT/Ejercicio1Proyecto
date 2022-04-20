function buscar(){
	 			var paciente = {nombre :'',apellidos:'',centro:'',ubicacion:''};
	 			paciente['nombre']=document.getElementById("input_nombre_filtro").value;
	 			paciente['apellidos']=document.getElementById("input_apellidos_filtro").value;
	 			paciente['centro']=document.getElementById("centro_filtro").value;
	 			paciente['ubicacion']=document.getElementById("ubicacion_filtro").value;
	 			var hayFiltro = false;
	 			
	 			if(paciente['nombre'] != '') {	
	 				console.log('Entra en nombre');
	        		hayFiltro=true;
	        	}
	        	if(paciente['apellidos'] != '' && !hayFiltro) {
	        		console.log('Entra en apellidos');
	        		hayFiltro=true;
	        	}
	        	if(paciente['centro'] != '' && !hayFiltro) {
	        		console.log('Entra en centro');
	        		hayFiltro=true;
	        	}
	        	if(paciente['ubicacion'] != '' && !hayFiltro) {
	        		console.log('Entra en Ubicación');
	        		hayFiltro=true;
	        	}	        	
	        	if(hayFiltro) {
	        		buscarPacientesFiltro(paciente);	        		        		
	        	}else{
	        		viewPanelResultadosPojo();	
	        	}
	 		}

//BUSCAR PACIENTES CON FILTRO	
	 		function buscarPacientesFiltro(paciente){
	 			$.ajax({
	 		        url: '/spring-mvc-hibernate-example/paciente/getPacientesFiltrado.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: paciente,
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
		 		           console.log(data);
			 		       console.log(textStatus);	
		 		        }		 		    
	 		    });
	 		}

//LIMPIAR FORMULARIOS
	 		function limpiarFiltro(){
	 			document.getElementById("centro_filtro").value="";
	 			document.getElementById("input_nombre_filtro").value="";
	 			document.getElementById("input_apellidos_filtro").value="";
	 			document.getElementById("ubicacion_filtro").value="";	 			
	 		}
	 		function limpiarFormAdd(){
	 			document.getElementById("nombrePaciente").value="";
	 			document.getElementById("apellidosPaciente").value="";
	 			document.getElementById("centroPaciente").value="";
	 			document.getElementById("ubicacionPaciente").value="";	 			
	 		}
	 		function limpiarFormEdit(){
	 			document.getElementById("nhcPaciente").value="";
	 			document.getElementById("editNombrePaciente").value="";
	 			document.getElementById("editApellidosPaciente").value="";
	 			document.getElementById("editCentroPaciente").value="";
	 			document.getElementById("editUbicacionPaciente").value="";	 			
	 		}
	 		
//AÑADIR PACIENTE
	 		function addPaciente(){
	 			var paciente = {nombre :'',apellidos:'',centro:'',ubicacion:''};	 			
	 			paciente['nombre']=document.getElementById("nombrePaciente").value;
	 			paciente['apellidos']=document.getElementById("apellidosPaciente").value;
	 			paciente['centro']=document.getElementById("centroPaciente").value;
	 			paciente['ubicacion']=document.getElementById("ubicacionPaciente").value;
	 			console.log(paciente);
	 			
	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/paciente/addPaciente.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: paciente,
	 		        success: function(data){
	 		        	alert("Paciente Añadido correctamente!")
	 		        	buscar();
	 		        }
	 		    });
		 		limpiarFormAdd();
	 		}
	 		
//EDITAR PACIENTE
	 		function editPaciente(){
	 			var paciente = {nhc:'',nombre :'',apellidos:'',centro:'',ubicacion:''};	
	 			paciente['nhc']=document.getElementById("nhcPaciente").value;
	 			paciente['nombre']=document.getElementById("editNombrePaciente").value;
	 			paciente['apellidos']=document.getElementById("editApellidosPaciente").value;
	 			paciente['centro']=document.getElementById("editCentroPaciente").value;
	 			paciente['ubicacion']=document.getElementById("editUbicacionPaciente").value;	
	 			
	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/paciente/editPaciente.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: paciente,
	 		        success: function(data){
	 		        	alert("Paciente Añadido correctamente!")
	 		        	buscar();
	 		        }
	 		    });
	 		  limpiarFormEdit();
	 		}
	 		
	 		function rellenarFormEdit(){
//	 			var tablaPanel = 
	//	 			jQuery("#")
		// 			var rowid = jQuery("#" + tablaPanel).jqGrid('getGridParam', 'selarrrow');
		 //			return jQuery("#" + tablaPanel).getRowData(rowid);
	 		}
	 		
	 		function viewPanelResultadosPojo() {
	 		    var pars = "";
	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/paciente/getPacientes.test',
	 		        type: "post",
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
	 		           console.log(data);
		 		       console.log(textStatus);	
	 		        }
	 		    });
	 		}
	 		
//ELIMINAR PACIENTE
	 		function eliminarPaciente(nhc){
	 			
	 			var paciente = {nhc:nhc};		

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/paciente/deletePaciente.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: paciente,
	 		        success: function(data){
	 		        	alert("Paciente Eliminado correctamente!")
	 		        	buscar();
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
	 		        colNames: ['N.H.C','Nombre','Apellidos','Centro','Ubicación','Eliminar'],
	 				colModel:[
	 					{name:'nhc',index:'nhc', width:200},
	 					{name:'nombre',index:'nombre', width:200},
	 					{name:'apellidos',index:'apellidos', width:200},
	 					{name:'centro',index:'centro', width:200},
	 					{name:'ubicacion',index:'ubicacion', width:200},
	 					{name: 'eliminar', index: 'eliminar', width: 80, align: 'center',
	                     formatter: function (value, grid, rows, state) { return "<button class=\"btn btn-danger\" onclick=\"eliminarPaciente(" + rows.nhc + ")\"><i class=\"fa-regular fa-trash-can\"></i></button>" }	                        
	                    },
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
	 		       	searching: {
	 		            defaultSearch: "cn"
	 		        },
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
		    function onLoad(){
		    	cargarCentros();
		    	cargarUbicaciones();
		    }
		    function cargarCentros(){
		    	var selectCentros = document.getElementById("centro_filtro");
		    	var selectAddPaciente = document.getElementById("centroPaciente");
		    	var selectEditPaciente = document.getElementById("editCentroPaciente");
		    	var pars = "";
		    	
		    	$.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/getCentros.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {	 		        	
	 		        	selectCentros.options[0] = new Option(' ','');
	 		        	selectAddPaciente.options[0] = new Option(' ','');
	 		        	selectEditPaciente.options[0] = new Option(' ','');
	 		            for(var i=1 ; i < data.centroList.length;i++){
	 		            	selectCentros.options[i]= new Option(data.centroList[i].centro, data.centroList[i].centro);
	 		            	selectAddPaciente.options[i]= new Option(data.centroList[i].centro, data.centroList[i].centro);
	 		            	selectEditPaciente.options[i]= new Option(data.centroList[i].centro, data.centroList[i].centro);
	 		            }
	 		        }
	 		    });
		    }
		    function cargarUbicaciones(){
		    	var selectUbicaciones = document.getElementById("ubicacion_filtro");
		    	var selectAddPaciente = document.getElementById("ubicacionPaciente");
		    	var selectEditPaciente = document.getElementById("editUbicacionPaciente");
		    	var pars = "";
		    	
		    	$.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/getUbicaciones.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {
	 		        	selectUbicaciones.options[0] = new Option(' ','');
	 		        	selectAddPaciente.options[0] = new Option(' ','');
	 		        	selectEditPaciente.options[0] = new Option(' ','');
	 		            for(var i=0 ; i < data.ubicacionList.length;i++){
	 		            	selectUbicaciones.options[i+1]= new Option(data.ubicacionList[i].ubicacion, data.ubicacionList[i].ubicacion);
	 		            	selectAddPaciente.options[i+1]= new Option(data.ubicacionList[i].ubicacion, data.ubicacionList[i].ubicacion);
	 		            	selectEditPaciente.options[i+1]= new Option(data.ubicacionList[i].ubicacion, data.ubicacionList[i].ubicacion);
	 		            }
	 		        }
	 		    });
		    }