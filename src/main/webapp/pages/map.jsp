<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>iNMapper | Real-Time Map</title>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet" media="screen">
<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/map.js"></script>
</head>
<body>

    <div class="container">
        <h1>Real-Time Indoor Mapping</h1>
        <hr>
        <div class="row">
            <div class="col-md-8">
                <h3>Room identification: ${room_id}</h3>
                <input id="room_id" type="hidden" value="${room_id}">
                <canvas id="map_canvas"></canvas>
                <br>
                <button type="button" class="btn btn-primary" onclick="filterLocations()">Refresh</button>
            </div>
            <div class="col-md-4">
                <h3>Received data</h3>
                <div class="panel-group" id="accordion"></div>
                <br>
            </div>
        </div>
        <hr>
        <footer>
            <p class="text-center">
                <small>CSC2228 - Topics in Mobile and Pervasive Computing - University of Toronto</small>
            </p>
        </footer>
    </div>

</body>
</html>