// Web Service URL
URL = "/api/v/{0}/mappings";

// Canvas dimensions
WIDTH = 720;
HEIGHT = 440;
SCALE = 10;

// Instance variables
mappings = {}

function getContext() {
    var canvas = document.getElementById('map_canvas');
    return canvas.getContext('2d');
}

$(function() {
    refreshLocations();
});

function refreshLocations() {
    var callback = function(data) {
        normalizeData(data);
        assembleMobileData(data);
        mappings = data;

        filterLocations();        
    };
    getRoomLocations(callback);
}

function filterLocations() {
    var filter = getUncheckedSessions();

    eraseFrame();
    drawLocations(mappings, filter);
}

function getUncheckedSessions() {
    var sessionsToBeFiltered = [];

    $(".session-check").each(function() {
        var checkbox = $(this);

        if (!checkbox.prop("checked")) {
            sessionsToBeFiltered.push(checkbox.val());
        }
    });
    return sessionsToBeFiltered;
}

function getRoomLocations(callback) {
    var roomId = $("#room_id").val();
    var roomUrl = URL.format(roomId);
    
    $.getJSON(roomUrl, function(data) { callback(data); });
}

function eraseFrame() {
    var canvas = document.getElementById('map_canvas');
    var context = getContext();
    // Store the current transformation matrix
    context.save();

    // Use the identity matrix while clearing the canvas
    context.setTransform(1, 0, 0, 1, 0, 0);
    context.clearRect(0, 0, canvas.width, canvas.height);

    // Restore the transform
    context.restore();

    drawFrame();
}

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
    
    drawSnaps();
}

function drawSnaps() {
    var context = getContext();
    var radius = 2;

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

function drawLocations(data, sessionIdFilters) {
    var sessionIdFilters = sessionIdFilters || []
    var context = getContext();
    var radius = 1;

    loopOver(data, function(metadata) {
        var value = metadata["location"];
        var shouldBePresented = $.inArray(metadata["sessionId"], sessionIdFilters) == -1;

        if (shouldBePresented) {
            context.beginPath();
            context.lineWidth = 5;
            context.strokeStyle = 'gray';
            context.arc(value.x * SCALE, value.y * SCALE, radius, 0, 2 * Math.PI, true);
            context.stroke();
        }
    });
}

function loopOver(data, callback) {
    for (sessionId in data['userMappings']) {
        var session = data['userMappings'][sessionId];

        for (locationIndex in session) {
            var metadata = { "sessionId" : sessionId, "session" : session, "location" : session[locationIndex] };

            callback(metadata);
        }
    }
}

function assembleMobileData(data) {
    var pattern = "<li id='{0}'>Id: {0} <br> Mobile id: {1} <br> Coordinates: ({2}, {3})</li>"
    
    console.log("Received data: " + data);

    $("#accordion").empty();
      
    for (sessionId in data['userMappings']) {
        var userSession = data['userMappings'][sessionId];
        var userTag = $(dumpHTML().format(sessionId));

        console.log("Received user session: " + sessionId);

        for (locationsIndex in userSession) {
            var value = userSession[locationsIndex];
            var tagItem = pattern.format(value.roomId, value.mobileId, value.x, value.y);
            
            console.log("Received value: x=" + value.x + " y=" + value.y);

            $('.data', userTag).append(tagItem);
        }
        $("#accordion").append(userTag);
    }
}

function normalizeData(data) {
    var maxX = 0;
    var maxY = 0;
    var minX = 9999999999;
    var minY = 9999999999;

    loopOver(data, function(metadata) {
        var value = metadata["location"];

        maxX = (value.x > maxX) ? value.x : maxX;
        maxY = (value.y > maxY) ? value.y : maxY;
        minX = (value.x < minX) ? value.x : minX;
        minY = (value.y < minY) ? value.y : minY;
        
        console.log("Normalizing value: x=" + value.x + " y=" + value.y);
    });

    var xWidth = maxX - minX;
    var yWidth = maxY - minY;
    var xFactor = (xWidth * SCALE > WIDTH) ? (WIDTH - 10) / xWidth / SCALE : 1;
    var yFactor = (yWidth * SCALE > HEIGHT) ? (HEIGHT - 10) / yWidth / SCALE : 1;
    var factor = (xFactor < yFactor) ? xFactor : yFactor;

    console.log("X scaling factor value: " + factor + " for max " + maxX);
    console.log("Y scaling factor value: " + factor + " for max " + maxY);
    
    console.log("X min value: " + minX);
    console.log("Y min value: " + minY);

    loopOver(data, function(metadata) {
        var value = metadata["location"];

        value.x = (value.x - minX) * factor;
        value.y = (value.y - minY) * factor;
    });
}

function dumpHTML() {
    return '<div class="panel panel-default">'
            + '    <div class="panel-heading">'
            + '        <h4 class="panel-title">'
            + '            <div class="checkbox">'
            + '            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse{0}">Mobile Id: {0}</a>'
            + '                    <input class="session-check" type="checkbox" value="{0}" checked>'
            + '            </div>'
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

