WIDTH = 720;
HEIGHT = 400;
URL = "/api/v/{0}/locations";

$(function() {
    drawFrame();
    getRoomLocations();
});

function drawFrame() {
    $("#map_canvas").attr("width", WIDTH).attr("height", HEIGHT);
    
    var canvas = document.getElementById('map_canvas');
    var context = canvas.getContext('2d');

    context.beginPath();
    context.lineWidth = 5;
    context.strokeStyle = 'gray';
    context.moveTo(0, 0);
    context.lineTo(WIDTH, 0);
    context.lineTo(WIDTH, HEIGHT);
    context.lineTo(0, HEIGHT);
    context.lineTo(0, 0);
    context.stroke();
}

function drawLocations(data) {
    var canvas = document.getElementById('map_canvas');
    var context = canvas.getContext('2d');
    var radius = 1;
    
    
    for (index in data) {
        var value = data[index];
        
        context.beginPath();
        context.lineWidth = 1;
        context.strokeStyle = 'gray';
        context.arc(value.point.x * 10, value.point.y * 10, radius, 0, 2 * Math.PI, true);
        context.stroke();
    }
    
}

function getRoomLocations() {
    var roomId = $("#room_id").val();
    var roomUrl = URL.format(roomId);
    
    $.getJSON(roomUrl, function(data) {
        var items = [];
        var pattern = "<li id='{0}'>Id: {0} - Mobile id: {1} - Coordinates: ({2}, {3}) @{4}</li>"
          
        console.log("Received value: " + data);
          
        for (index in data) {
            var value = data[index];
            var tagItem = pattern.format(value.id, value.mobileId, value.point.x, value.point.y, value.point.angle);
            
            $("#raw_data").append(tagItem);
        }
        drawLocations(data)
    });
}

if (!String.prototype.format) {
  String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined' ? args[number] : match;
    });
  };
}

