WIDTH = 720;
HEIGHT = 440;
SCALE = 10;

URL = "/api/v/{0}/mappings";

function getContext() {
    var canvas = document.getElementById('map_canvas');
    return canvas.getContext('2d');
}

$(function() {
    drawFrame();
    refreshLocations();
});

function drawFrame() {
    $("#map_canvas").attr("width", WIDTH).attr("height", HEIGHT);
    var context = getContext();

    context.lineWidth = 5;
    context.strokeStyle = 'gray';

    context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(WIDTH, 0);
    context.lineTo(WIDTH, HEIGHT);
    context.lineTo(0, HEIGHT);
    context.lineTo(0, 0);
    context.stroke();
    
    eraseFrame();
}

function eraseFrame() {
    var context = getContext();
    var radius = 1;

    context.lineWidth = 1;
    context.strokeStyle = '#dadada';

    for (var hpoint = 10; hpoint < WIDTH; hpoint = hpoint + 10) {
        for (var vpoint = 10; vpoint < HEIGHT; vpoint = vpoint + 10) {
            context.beginPath();
            context.arc(hpoint, vpoint, radius, 0, 2 * Math.PI, true);
            context.stroke();
        }
    }
}

function drawLocations(data) {
    var context = getContext();
    var radius = 1;
    
    for (sessionId in data['userMappings']) {
        var userSession = data['userMappings'][sessionId];

        for (locationsIndex in userSession) {
            var value = userSession[locationsIndex];

            context.beginPath();
            context.lineWidth = 5;
            context.strokeStyle = 'gray';
            context.arc(value.x * SCALE, value.y * SCALE, radius, 0, 2 * Math.PI, true);
            context.stroke();
        }
    }
}

function getRoomLocations(callback) {
    var roomId = $("#room_id").val();
    var roomUrl = URL.format(roomId);
    
    $.getJSON(roomUrl, function(data) { callback(data); });
}

function refreshLocations() {
    var callback = function(data) {
        var maxX = 0;
        var maxY = 0;
        var pattern = "<li id='{0}'>Id: {0} - Mobile id: {1} - Coordinates: ({2}, {3})</li>"
        
        console.log("Received data: " + data);

        $("#accordion").empty();
          
        for (sessionId in data['userMappings']) {
            var userSession = data['userMappings'][sessionId];
            var userTag = $(dumpHTML().format(sessionId));

            console.log("Received user session: " + sessionId);

            for (locationsIndex in userSession) {
                var value = userSession[locationsIndex];
                var tagItem = pattern.format(value.roomId, value.mobileId, value.x, value.y);
                
                maxX = (value.x > maxX) ? value.x : maxX;
                maxY = (value.y > maxY) ? value.y : maxY;

                console.log("Received value: " + value);

                $('.data', userTag).append(tagItem);
            }
            $("#accordion").append(userTag);
        }

        eraseFrame();
        normalizeData(data, maxX, maxY);
        drawLocations(data);
    };

    getRoomLocations(callback);
}

function normalizeData(data, maxX, maxY) {
    var xFactor = (maxX * SCALE > WIDTH) ? WIDTH / maxX / SCALE : 1;
    var yFactor = (maxY * SCALE > HEIGHT) ? HEIGHT / maxY / SCALE : 1;

    console.log("X scaling factor value: " + xFactor + " for max " + maxX);
    console.log("Y scaling factor value: " + yFactor + " for max " + maxY);

    var toFunction = function(value) {
        value.x = value.x * xFactor;
        value.y = value.y * yFactor;
    };

    for (sessionId in data['userMappings']) {
        var userSession = data['userMappings'][sessionId];

        userSession.map(toFunction);
    }
}

function dumpHTML() {
    return '<div class="panel panel-default">'
            + '    <div class="panel-heading">'
            + '        <h4 class="panel-title">'
            + '            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse{0}">Mobile Id: {0}</a>'
            + '        </h4>'
            + '    </div>'
            + '    <div id="collapse{0}" class="panel-collapse collapse">'
            + '        <div class="panel-body">'
            + '            <ul class="data">'
            + '            </ul>'
            + '        </div>'
            + '    </div>'
            + '</div>';
}

if (!String.prototype.format) {
    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) { 
          return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}

