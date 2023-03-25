SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
create schema if not exists DNS;
use DNS;

-- ----------------------------
-- Table structure for rootserver
-- ----------------------------
DROP TABLE IF EXISTS `rootserver`;
CREATE TABLE `rootserver`  (
                         `id` bigint(11) NOT NULL AUTO_INCREMENT,
                         `domainname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         PRIMARY KEY `唯一`(`domainname`) USING BTREE,
                         UNIQUE INDEX `唯一`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for topserver
-- ----------------------------
DROP TABLE IF EXISTS `topserver`;
CREATE TABLE `topserver`  (
                               `id` bigint(11) NOT NULL AUTO_INCREMENT,
                               `domainname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                               `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                               PRIMARY KEY `唯一`(`domainname`) USING BTREE,
                               UNIQUE INDEX `唯一`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for abcperserver
-- ----------------------------
DROP TABLE IF EXISTS `abcperserver`;
CREATE TABLE `abcperserver`  (
                              `id` bigint(11) NOT NULL AUTO_INCREMENT,
                              `domainname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              PRIMARY KEY `唯一`(`domainname`) USING BTREE,
                              UNIQUE INDEX `唯一`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for defperserver
-- ----------------------------
DROP TABLE IF EXISTS `defperserver`;
CREATE TABLE `defperserver`  (
                              `id` bigint(11) NOT NULL AUTO_INCREMENT,
                              `domainname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              PRIMARY KEY `唯一`(`domainname`) USING BTREE,
                              UNIQUE INDEX `唯一`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


SET FOREIGN_KEY_CHECKS = 1;