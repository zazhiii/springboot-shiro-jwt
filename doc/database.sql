-- 创建数据库
CREATE
DATABASE `shiro_jwt_rbac`;
USE
shiro_jwt_rbac;

-- 表结构
CREATE TABLE `user`
(
    `id`       int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role_permission`
(
    `role_id`       int NOT NULL,
    `permission_id` int NOT NULL,
    PRIMARY KEY (`permission_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据
INSERT INTO `user`(username, password)
VALUES ("AAA", "111");
INSERT INTO `user`(username, password)
VALUES ("BBB", "111");

INSERT INTO `role`(name, description)
VALUES ("ROLE-A", "");
INSERT INTO `role`(name, description)
VALUES ("ROLE-B", "");

INSERT INTO `user_role`(user_id, role_id)
VALUES (1, 1);
INSERT INTO `user_role`(user_id, role_id)
VALUES (2, 2);

INSERT INTO `permission`(name, description)
VALUES ("PERMISSION-A", "");
INSERT INTO `permission`(name, description)
VALUES ("PERMISSION-B", "");

INSERT INTO `role_permission`(role_id, permission_id)
VALUES (1, 1);
INSERT INTO `role_permission`(role_id, permission_id)
VALUES (2, 2);