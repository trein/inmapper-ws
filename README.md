inmapper-ws
===========
Web service responsible for handling requests from iNMapper clients

Description:
------------
Web Service will expose two different services. The first one would receive the data from the mobile device, calculate the coordinates and store the same in a database server. The second will respond to the information requests of the mobile client. It will fetch the required data from the database and pass the same to the client which will be used to present a map at the client mobile device.

Web Service REST API:
-------------------------
| Resource                          | Description
| --------------------------------- | ------------- |
| `GET api/v/health`                | Retrieve the current service status. It returns http code 200 if the server is alive and ready to receive requests. Any other response can be considered as service failure.
  `GET api/v/token`                 | Returns a mapping session identifier for the mobile client. This identifier is unique and valid only during a mapping session.
  `POST api/v/positions`            | Creates a new indoor mapping using the posted sensor data for the specified room or appends the new data if it already exists. It returns the identification of the created or appended indoor mapping.
  `GET api/v/{room_id}/mappings`    | Retrieves all mapping information for the specified `{room_id}` and returns it in a JSON format. If you are willing to display the mapping information, we suggest you to use `GET web/{room_id}`directly.
  `GET web/{room_id}`               | Retrieves all mapping information for the specified `{room_id}` using resources provided by `POST api/v/{room_id}/mappings`, parses it and displays the resulting data on an interactive graphical map.
