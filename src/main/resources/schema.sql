CREATE TABLE IF NOT EXISTS Book (
    id      INTEGER       PRIMARY KEY AUTO_INCREMENT
                          NOT NULL,
    version INTEGER,
    name    VARCHAR (100)
);

CREATE TABLE IF NOT EXISTS Person (
    id          INTEGER      PRIMARY KEY AUTO_INCREMENT
                             NOT NULL,
    version     INTEGER,
    first_name  VARCHAR (50),
    second_name VARCHAR (50),
    surname     VARCHAR (50),
    birthday    DATE,
    phone       VARCHAR (15),
    email       VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS Book_Writer (
    book_id   INTEGER REFERENCES Book (id),
    person_id INTEGER REFERENCES Person (id)
);

CREATE TABLE IF NOT EXISTS Catalog (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER,
    book_id     INTEGER REFERENCES Book (id),
    description TEXT,
    total_count INTEGER
);

CREATE TABLE IF NOT EXISTS Subscriber (
    person_id        INTEGER NOT NULL
                             REFERENCES Person (id),
    version          INTEGER,
    subscribe_date   DATE,
    unsubscribe_date DATE
);

CREATE TABLE IF NOT EXISTS Library_Card (
    id            INTEGER PRIMARY KEY AUTO_INCREMENT
                          NOT NULL,
    version       INTEGER,
    subscriber_id INTEGER REFERENCES Subscriber (person_id),
    catalog_id    INTEGER REFERENCES Catalog (id),
    receive_date  DATE,
    return_date   DATE
);
