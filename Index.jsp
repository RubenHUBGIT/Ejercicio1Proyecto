<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <title>Pagina de Inicio</title>
    <title>Agenda de Urgencias</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/test/includes/css/estilo-ui-extra.css" />
    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/2.2.4/jquery-2.2.4.min.js"
        type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/css/bootstrap-4.5/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/theming.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/svg-inject.min.js"
        type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/ui/1.10.2/jquery-ui-1.10.4.js"
        type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/plugins/ui.multiselect.js"
        type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/grid.locale-es.js"
        type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/jqgrid/jquery.jqgrid.js"
        type="text/javascript"></script>
    <script
        src="<%=request.getContextPath()%>/test/includes/javascript/jquery/plugins/json-2.3/js/jquery.json-2.3.min.js"
        type="text/javascript"></script>
</head>

<body>
	<header class="title fixed-top">Dedalus</header>
    
    <div class="container-fluid px-5">
        <div class="row mt-5 pt-0 justify-content-evenly">
            <div class="card m-1 p-2 justify-content-center" style="width: 17rem;">
                <img class="card-img-top img thumbnail w-100 h-100" src="https://laribera.san.gva.es/wp-content/uploads/2018/03/urgencias.png">
                <div class="card-body">                    
                    <a href="agendaUrgencias.jsp" type="button" class="btn colorhcis d-flex justify-content-center ">Agenda Urgencias</a>
                </div>                
            </div>
            <div class="card m-1 p-2 justify-content-center" style="width: 17rem;">
                <img class="card-img-top img thumbnail w-100 h-100" src="https://centrossanitarios.sanidadmadrid.org/img/icoBuscadorMiCS.svg">
                <div class="card-body">                    
                    <a href="Centros.jsp" type="button" class="btn colorhcis d-flex justify-content-center">Centros</a>
                </div>                
            </div> 
            <div class="card m-1 p-2 justify-content-center" style="width: 17rem;">
                <img class="card-img-top img thumbnail w-100 h-100" src="https://cdn-icons-png.flaticon.com/512/3209/3209018.png">
                <div class="card-body">                    
                    <a href="Ubicaciones.jsp" type="button" class="btn colorhcis d-flex justify-content-center">Ubicaciones</a>
                </div>                
            </div>               
        </div>
        
         
<!-- Carousel -->       
        <div class="row m-5 pt-0 justify-content-center">
            <div id="carousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="https://www.dedalus.com/spain/wp-content/uploads/sites/27/2021/03/andorradesktop.jpg" class="d-block w-100 img-thumbnail" data-bs-interval="500">
                    </div>
                    <div class="carousel-item">
                        <img src="https://www.dedalus.com/spain/wp-content/uploads/sites/27/2021/10/carrousel-web-dobco.jpg" class="d-block w-100 img-thumbnail" data-bs-interval="500">
                    </div>
                    <div class="carousel-item">
                        <img src="https://www.dedalus.com/spain/wp-content/uploads/sites/27/2021/03/cover_desk-1.jpg" class="d-block w-100 img-thumbnail" data-bs-interval="500">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carousel"
                    data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carousel"
                    data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
    integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
    integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
    crossorigin="anonymous"></script>

</html>