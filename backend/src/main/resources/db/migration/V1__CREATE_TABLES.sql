CREATE TABLE form
(
    form_id     SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(100)
);

CREATE TABLE question
(
    question_id       SERIAL NOT NULL PRIMARY KEY,
    form_id           INT    NOT NULL REFERENCES form (form_id) ON DELETE CASCADE,
    description       VARCHAR(100),
    number_of_choices INT    NOT NULL DEFAULT 1
);

CREATE TABLE choice
(
    choice_id   SERIAL NOT NULL PRIMARY KEY,
    question_id INT    NOT NULL REFERENCES question (question_id) ON DELETE CASCADE,
    description VARCHAR(100)
);

CREATE TABLE interviewed
(
    interviewed_id   SERIAL NOT NULL PRIMARY KEY,
    interviewed_ip   CIDR   NOT NULL,
    form_id          INT    NOT NULL REFERENCES form (form_id) ON DELETE CASCADE,
    interviewed_date TIMESTAMP
);

CREATE TABLE interviewed_choice
(
    interviewed_id INT NOT NULL REFERENCES interviewed (interviewed_id) ON DELETE CASCADE,
    choice_id      INT NOT NULL REFERENCES choice (choice_id) ON DELETE CASCADE,
    PRIMARY KEY (interviewed_id, choice_id)
);