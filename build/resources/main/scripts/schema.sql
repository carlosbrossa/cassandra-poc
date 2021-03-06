CREATE KEYSPACE schedule WITH replication = {'class': 'SimpleStrategy', 'replication_factor':3};

CREATE TABLE schedule.location_by_specialty_brand(specialty text, brand text, locations map<text,boolean>, PRIMARY KEY(specialty, brand)) WITH CLUSTERING ORDER BY (brand DESC);

CREATE TABLE schedule.schedule_by_specialty_location_day(specialty text, brand text, location text, day date, slots map<int,text>, PRIMARY KEY(specialty, brand, location, day)) WITH CLUSTERING ORDER BY (brand DESC);
