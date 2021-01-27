CREATE KEYSPACE airfare WITH replication = {'class': 'SimpleStrategy', 'replication_factor':3};

CREATE TABLE airfare.location_by_specialty_brand(specialty text, brand text, locations map<text,boolean>, PRIMARY KEY(specialty, brand)) WITH CLUSTERING ORDER BY (brand DESC);

