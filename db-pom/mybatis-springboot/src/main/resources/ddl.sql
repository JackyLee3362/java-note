-- 检查当前数据库的时区
select
    curtime ();

-- test_db.`user` definition
CREATE TABLE
    `user` (
        `id` bigint NOT NULL AUTO_INCREMENT,
        `name` varchar(100) DEFAULT NULL,
        `age` bigint DEFAULT NULL,
        `create_time` datetime DEFAULT NULL,
        `is_delete` int DEFAULT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- test_db.`datetime` definition
CREATE TABLE
    `datetime` (
        `id` bigint NOT NULL AUTO_INCREMENT,
        `timestamp` varchar(100) DEFAULT NULL,
        `local_date_time` datetime DEFAULT NULL,
        `instant` timestamp NULL DEFAULT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;