# cassandra-poc

docker compose to cassandra cluster

##endpoints 

###location 

create data
curl localhost:8080/location/generate    

get
curl localhost:8080/location/speciality/ES-Vacinas 

put (update location avaibility)
curl -X PUT localhost:8080/location/ES-Vacinas/DIB/false

###schedule slot

create data
curl localhost:8080/schedule/generate 

get
curl localhost:8080/schedule/ES-Peniscopia/DSS/2021-01-27  

put (remove slot from columnfamily)
curl -X PUT localhost:8080/schedule/ES-Peniscopia/DSS/2021-01-27/30
