function buscar(){
 			var ubicacion = {codUbicacion :'',ubicacion:''};
 			ubicacion['codUbicacion']=document.getElementById("input_codigo_filtro").value;
 			ubicacion['ubicacion']=document.getElementById("ubicacion_filtro").value;
 			var hayFiltro = false;
 			
 			if(ubicacion['codUbicacion'] != '') {
        		hayFiltro=true;
        	}
 			if(ubicacion['ubicacion'] != '') {	
 				console.log('Entra en ubicacion');
        		hayFiltro=true;
        	}
        	
        	if(hayFiltro) {
        		buscarUbicacionesFiltro(ubicacion);	        		        		
        	}else{
        		viewPanelResultadosPojo();	
        	}
 		}
 		
 		function buscarUbicacionesFiltro(ubicacion){
 			$.ajax({
 		        url: '/spring-mvc-hibernate-example/ubicacion/getUbicacionesFiltrado.test',
 		        type: "post",
 		        dataType: 'json',
 		        asynchronous: false,
 		        cache: false,
 		        data: ubicacion,
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
 		
//LIMPIAR FORMULARIOS
	 		function limpiar(){
	 			document.getElementById("input_codigo_filtro").value="";
	 			document.getElementById("ubicacion_filtro").value="";
	 		}
	 		function limpiarFormAdd(){
	 			document.getElementById("ubicacion").value="";			
	 		}
	 		function limpiarFormEdit(){
	 			document.getElementById("editCodUbicacion").value="";
	 			document.getElementById("editUbicacion").value="";			
	 		}
//AÑADIR UBICACION
	 		function addUbicacion(){
	 			var  ubicacion = {ubicacion:''};	 			
	 			ubicacion['ubicacion']=document.getElementById("ubicacion").value;
	 			console.log();
	 			
	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/addUbicacion.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: ubicacion ,
	 		        success: function(data){
	 		        	alert("Ubicación Añadido correctamente!");
	 		        	buscar();
	 		        	limpiarFormAdd();
	 		        }
	 		    })
	 		}
//EDITAR UBICACION
	 		function editUbicacion(){
	 			var ubicacion = {cod_ubicacion:'',ubicacion:''};	
	 			ubicacion['cod_ubicacion']=document.getElementById("editCodUbicacion").value;
	 			ubicacion['ubicacion']=document.getElementById("editUbicacion").value;
	 			console.log(); 			

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/editUbicacion.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: ubicacion,
	 		        success: function(data){
	 		        	alert("Ubicación editada correctamente!");
	 		        	buscar();
	 		        }
 		    	});
	 		   limpiarFormEdit();
	 		}
//ELIMINAR UBICACION
			function eliminarUbicacion(codUbicacion){				 			
	 			var ubicacion = {codUbicacion:codUbicacion};		

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/deleteUbicacion.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: ubicacion,
	 		        success: function(data){
	 		        	alert("Ubicación eliminada correctamente!");
	 		        	buscar();
	 		        }
	 		    });
	 		}
	 		function viewPanelResultadosPojo() {
	 		    var pars = "";

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/getUbicaciones.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {
	 		            var panelResultados = $("#panelResultadosPojo");
	 		            panelResultados.jqGrid("clearGridData", true);
	 		            panelResultados.jqGrid('GridUnload');
	 		            console.log(data);
	 		            if (data == '' || data == undefined) {
	 		                alert("Error al cargar los datos");
	 		            } else {
	 		                loadGrid(data);
	 		                ajustarAltura();
	 		            }
	 		        }
	 		    });
	 		}
//CARGAR TABLA
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
	 		    	data: obj.ubicacionList,
	 		        datatype: "local",
	 		        colNames: ['Codigo','Ubicacion','Eliminar'],
	 				colModel:[
	 					{name:'codUbicacion',index:'codUbicacion', width:200},
	 					{name:'ubicacion',index:'ubicacion', width:200},
	 					{name: 'eliminar', index: 'eliminar', width: 80, align: 'center',
		                     formatter: function (value, grid, rows, state) { return "<button class=\"btn btn-danger\" onclick=\"eliminarUbicacion(" + rows.codUbicacion + ")\"><i class=\"fa-regular fa-trash-can\"></i></button>" }	                        
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
	 		        rowNum: obj.ubicacionList.length,
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
		    
		    function onLoad(){
		    	cargarUbicaciones();
		    }
		    
//CARGAR UBICACIONES
		    function cargarUbicaciones(){
		    	var selectUbicaciones = document.getElementById("ubicacion_filtro");
		    	var pars = "";
		    	
		    	$.ajax({
	 		        url: '/spring-mvc-hibernate-example/ubicacion/getUbicaciones.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {
	 		        	selectUbicaciones.options[0] = new Option(' ','')	 		        	
	 		            for(var i=0 ; i < data.ubicacionList.length;i++){
	 		            	selectUbicaciones.options[i+1]= new Option(data.ubicacionList[i].ubicacion, data.ubicacionList[i].ubicacion);
	 		            }
	 		        }
	 		    });
		    }