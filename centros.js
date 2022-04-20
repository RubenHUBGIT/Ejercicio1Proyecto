function buscar(){
	 			var centro = {codCentro :'',centro:''};
	 			centro['codCentro']=document.getElementById("input_codigo_filtro").value;
	 			centro['centro']=document.getElementById("centro_filtro").value;
	 			var hayFiltro = false;
	 			console.log(centro)
	 			if(centro['codCentro'] != '') {
	        		hayFiltro=true;
	        	}
	        	if(centro['centro'] != '' && !hayFiltro) {
	        		hayFiltro=true;
	        	}        	
	        	if(hayFiltro) {
	        		buscarCentrosFiltro(centro);	        		        		
	        	}else{
	        		viewPanelResultadosPojo();	
	        	}
	 		}
	 		
//BUSQUEDA DE CENTRO CON FILTRO
	 		function buscarCentrosFiltro(centro){
	 			$.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/getCentrosFiltrado.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: centro,
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
	 		
	 		function limpiar(){
	 			document.getElementById("input_codigo_filtro").value="";	
	 			document.getElementById("centro_filtro").value="";	 			
	 		}
	 		function limpiarFormAdd(){
	 			document.getElementById("nombreCentro").value="";			
	 		}
	 		function limpiarFormEdit(){
	 			document.getElementById("editCodCentro").value="";
	 			document.getElementById("editNombreCentro").value="";			
	 		}
//AÑADIR CENTRO
	 		function addCentro(){
	 			var centro = {centro:''};	 			
	 			centro['centro']=document.getElementById("nombreCentro").value;
	 			console.log(centro);
	 			
	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/addCentro.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: centro,
	 		        success: function(data){
	 		        	alert("Centro añadido correctamente!");
	 		        	buscar();
	 		        }
 		    	});
	 		   limpiarFormAdd();
	 		}
//EDITAR CENTRO
	 		function editCentro(){
	 			var centro = {cod_centro:'',centro:''};	
	 			centro['cod_centro']=document.getElementById("editCodCentro").value;
	 			centro['centro']=document.getElementById("editNombreCentro").value;
	 			console.log(centro); 			

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/editCentro.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: centro,
	 		        success: function(data){
	 		        	alert("Centro editado correctamente!");
	 		        	buscar();
	 		        }
	 		    });
		 		limpiarFormEdit();
	 		}
	 		
	 		function viewPanelResultadosPojo() {
	 		    var pars = "";

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/getCentros.test',
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
//ELIMINAR CENTRO
	 		function eliminarCentro(codCentro){
	 			
	 			var centro = {codCentro:codCentro};		

	 		    $.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/deleteCentro.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: centro,
	 		        success: function(data){
	 		        	alert("Centro eliminado correctamente!");
	 		        	buscar();
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
	 		    	data: obj.centroList,
	 		        datatype: "local",
	 		        colNames: ['Codigo','Nombre de Centro','Eliminar'],
	 				colModel:[
	 					{name:'codCentro',index:'codCentro', width:200},
	 					{name:'centro',index:'centro', width:200},
	 					{name: 'eliminar', index: 'eliminar', width: 80, align: 'center',
		                     formatter: function (value, grid, rows, state) { return "<button class=\"btn btn-danger\" onclick=\"eliminarCentro(" + rows.codCentro + ")\"><i class=\"fa-regular fa-trash-can\"></i></button>" }	                        
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
	 		        rowNum: obj.centroList.length,
	 		        hoverrows: false,
	 		    });
	 		    panel.jqGrid('hideCol','cb');
	 		    $('#pager option:last').text('Todos');
	 		}
//AJUSTAR ALTURA
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
		    }
//CARGAR CENTROS EN BOTON DROPDOWN
		    function cargarCentros(){
		    	var selectCentros = document.getElementById("centro_filtro");
		    	var pars = "";
		    	
		    	$.ajax({
	 		        url: '/spring-mvc-hibernate-example/centro/getCentros.test',
	 		        type: "post",
	 		        dataType: 'json',
	 		        asynchronous: false,
	 		        cache: false,
	 		        data: pars,
	 		        success: function (data, textStatus) {
	 		        	console.log(data);
	 		        	selectCentros.options[0] = new Option(' ','')
	 		            for(var i=0 ; i < data.centroList.length;i++){
	 		            	selectCentros.options[i+1]= new Option(data.centroList[i].centro, data.centroList[i].centro);
	 		            }
	 		        }
	 		    });
		    }