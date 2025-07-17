CREATE TABLE music_users (
    email       VARCHAR2(100)   NOT NULL,
    password    VARCHAR2(100)   NOT NULL,
    is_admin    NUMBER(1)       DEFAULT 0 NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (email)
);

CREATE TABLE music (
    music_id      NUMBER          NOT NULL,
    title         VARCHAR2(200)   NOT NULL,
    artist        VARCHAR2(100)   NOT NULL,
    album         VARCHAR2(100),
    youtube_link  VARCHAR2(300),
    spotify_link  VARCHAR2(300),
    CONSTRAINT music_pk PRIMARY KEY (music_id)
);

-- music_id 자동 증가 시퀀스
CREATE SEQUENCE music_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- 자동 증가 트리거
CREATE OR REPLACE TRIGGER music_id_trigger
BEFORE INSERT ON music
FOR EACH ROW
WHEN (NEW.music_id IS NULL)
BEGIN
    SELECT music_seq.NEXTVAL INTO :NEW.music_id FROM dual;
END;
/


CREATE TABLE music_favorite (
    email      VARCHAR2(100) NOT NULL,
    music_id   NUMBER        NOT NULL,
    CONSTRAINT pk_favorite PRIMARY KEY (email, music_id),
    CONSTRAINT fk_favorite_user FOREIGN KEY (email)
        REFERENCES music_users (email) ON DELETE CASCADE,
    CONSTRAINT fk_favorite_music FOREIGN KEY (music_id)
        REFERENCES music (music_id) ON DELETE CASCADE
);

CREATE TABLE music_recent_keyword (
    email         VARCHAR2(100) NOT NULL,
    keyword       VARCHAR2(100) NOT NULL,
    search_time   DATE          NOT NULL,
    CONSTRAINT pk_recent_keyword PRIMARY KEY (email, keyword, search_time),
    CONSTRAINT fk_recent_keyword_user FOREIGN KEY (email)
        REFERENCES music_users (email) ON DELETE CASCADE
);

-- 곡명 검색용
CREATE INDEX idx_music_title ON music(title);

-- 아티스트명 검색용
CREATE INDEX idx_music_artist ON music(artist);

-- 최근검색어 빠른 탐색
CREATE INDEX idx_recent_keyword_user_keyword ON music_recent_keyword(email, keyword);
