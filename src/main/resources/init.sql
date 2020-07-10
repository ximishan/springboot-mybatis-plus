SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_permission`;
CREATE TABLE `upms_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(32) NOT NULL COMMENT '编号',
  `pid` varchar(32) DEFAULT '' COMMENT '所属上级',
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) DEFAULT '' COMMENT '权限值',
  `uri` varchar(100) DEFAULT '' COMMENT '路径',
  `icon` varchar(50) DEFAULT '' COMMENT '图标',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0:禁止,1:正常)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_permission`;
CREATE TABLE `upms_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_permission_id` varchar(32) NOT NULL COMMENT '编号',
  `role_id` varchar(32) NOT NULL COMMENT '角色编号',
  `permission_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限编号',
  `create_dts` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`role_permission_id`) USING BTREE,
  KEY `FK_Reference_23` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_role`;
CREATE TABLE `upms_role` (
  `role_id` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(20) DEFAULT '' COMMENT '角色名称',
  `title` varchar(20) DEFAULT '' COMMENT '角色标题',
  `description` varchar(500) DEFAULT '' COMMENT '角色描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for upms_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_permission`;
CREATE TABLE `upms_user_permission` (
  `user_permission_id` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `permission_id` varchar(32) NOT NULL COMMENT '权限编号',
  `create_dts` datetime DEFAULT NULL,
  PRIMARY KEY (`user_permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限关联表';

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_role`;
CREATE TABLE `upms_user_role` (
  `user_role_id` varchar(45) NOT NULL COMMENT '编号',
  `user_id` varchar(45) NOT NULL COMMENT '用户编号',
  `role_id` varchar(45) NOT NULL DEFAULT '' COMMENT '角色编号',
  `create_dts` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS `upms_user`;
CREATE TABLE `upms_user` (
  `user_id` varchar(32) NOT NULL COMMENT '编号',
  `username` varchar(45) NOT NULL COMMENT '帐号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(20) DEFAULT '' COMMENT '姓名',
  `avatar` varchar(50) DEFAULT '' COMMENT '头像',
  `phone` varchar(20) DEFAULT '' COMMENT '电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `sex` int(1) DEFAULT '1' COMMENT '性别(1男/2女)',
  `locked` tinyint(1) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `create_dts` datetime DEFAULT NULL COMMENT '创建时间',
  `update_dts` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

BEGIN;
INSERT INTO `upms_user`(`user_id`, `username`, `password`, `realname`, `avatar`, `phone`, `email`, `sex`, `locked`, `create_dts`, `update_dts`) VALUES ('1', 'admin', '$2a$10$JXDq.LuL0WRCkm60jPRm1ue4ZuXiERlHaHxYG1axsA4G9hCkNMvNO', 'admin', '', '', '', 1, 0, NULL, NULL);

INSERT INTO `upms_role`(`role_id`, `name`, `title`, `description`, `ctime`, `orders`) VALUES ('63b705a7683e48ca875f28c3db021bf5', 'ROLE_ADMIN', '超级管理员', '超级管理员~', 1586850342459, 1586850342459);
INSERT INTO `upms_role`(`role_id`, `name`, `title`, `description`, `ctime`, `orders`) VALUES ('f14b0eb493834f9ba3ae268e878ebaf8', 'ROLE_USER', '用户', '普通用户', 1586857358695, 1586857358695);

INSERT INTO `upms_user_role`(`user_role_id`, `user_id`, `role_id`, `create_dts`) VALUES ('1', '1', '63b705a7683e48ca875f28c3db021bf5', '2020-04-20 13:43:56');

INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (1, '1', '63b705a7683e48ca875f28c3db021bf5', '1', '2020-04-15 10:48:11');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (2, '10', '63b705a7683e48ca875f28c3db021bf5', '10', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (3, '11', '63b705a7683e48ca875f28c3db021bf5', '11', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (4, '12', '63b705a7683e48ca875f28c3db021bf5', '12', '2020-05-06 14:15:00');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (5, '13', '63b705a7683e48ca875f28c3db021bf5', '13', '2020-05-06 11:32:52');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (6, '14', '63b705a7683e48ca875f28c3db021bf5', '14', '2020-05-06 11:33:02');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (7, '15', '63b705a7683e48ca875f28c3db021bf5', '15', '2020-05-06 11:33:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (8, '16', '63b705a7683e48ca875f28c3db021bf5', '16', '2020-05-06 11:34:02');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (9, '17', '63b705a7683e48ca875f28c3db021bf5', '17', '2020-05-06 11:34:04');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (10, '18', '63b705a7683e48ca875f28c3db021bf5', '18', '2020-05-06 11:34:07');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (11, '19', '63b705a7683e48ca875f28c3db021bf5', '19', '2020-05-06 11:34:08');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (12, '2', '63b705a7683e48ca875f28c3db021bf5', '2', '2020-04-15 10:48:32');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (13, '20', '63b705a7683e48ca875f28c3db021bf5', '20', '2020-05-06 11:34:10');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (14, '21', '63b705a7683e48ca875f28c3db021bf5', '21', '2020-05-06 11:34:35');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (15, '22', '63b705a7683e48ca875f28c3db021bf5', '22', '2020-05-06 11:34:36');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (16, '23', '63b705a7683e48ca875f28c3db021bf5', '23', '2020-05-06 11:34:37');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (17, '24', '63b705a7683e48ca875f28c3db021bf5', '24', '2020-05-06 11:34:38');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (18, '25', '63b705a7683e48ca875f28c3db021bf5', '25', '2020-05-06 14:26:16');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (19, '26', '63b705a7683e48ca875f28c3db021bf5', '26', '2020-05-15 14:27:15');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (20, '27', '63b705a7683e48ca875f28c3db021bf5', '27', '2020-05-15 14:27:24');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (21, '28', '63b705a7683e48ca875f28c3db021bf5', '28', '2020-05-15 14:27:33');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (22, '29', '63b705a7683e48ca875f28c3db021bf5', '29', '2020-05-15 14:27:44');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (23, '3', '63b705a7683e48ca875f28c3db021bf5', '4', '2020-04-20 13:45:56');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (24, '30', '63b705a7683e48ca875f28c3db021bf5', '30', '2020-05-15 14:27:55');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (25, '31', '63b705a7683e48ca875f28c3db021bf5', '31', '2020-05-15 14:28:48');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (26, '32', '63b705a7683e48ca875f28c3db021bf5', '32', '2020-05-15 14:44:13');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (27, '33', '63b705a7683e48ca875f28c3db021bf5', '33', '2020-05-15 14:44:13');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (28, '34', '63b705a7683e48ca875f28c3db021bf5', '34', '2020-05-15 14:44:14');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (29, '35', '63b705a7683e48ca875f28c3db021bf5', '35', '2020-05-15 14:44:15');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (30, '4', '63b705a7683e48ca875f28c3db021bf5', '3', '2020-04-15 10:48:39');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (31, '5', '63b705a7683e48ca875f28c3db021bf5', '5', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (32, '6', '63b705a7683e48ca875f28c3db021bf5', '6', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (33, '7', '63b705a7683e48ca875f28c3db021bf5', '7', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (34, '8', '63b705a7683e48ca875f28c3db021bf5', '8', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (35, '9', '63b705a7683e48ca875f28c3db021bf5', '9', '2020-05-06 11:32:25');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (36, '36', '63b705a7683e48ca875f28c3db021bf5', '36', '2020-05-15 14:58:56');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (37, '37', '63b705a7683e48ca875f28c3db021bf5', '37', '2020-05-15 14:58:57');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (38, '38', '63b705a7683e48ca875f28c3db021bf5', '38', '2020-05-15 14:58:58');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (39, '39', '63b705a7683e48ca875f28c3db021bf5', '39', '2020-05-15 15:02:05');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (40, '40', '63b705a7683e48ca875f28c3db021bf5', '40', '2020-05-15 15:13:38');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (41, '41', '63b705a7683e48ca875f28c3db021bf5', '41', '2020-05-15 15:13:39');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (42, '42', '63b705a7683e48ca875f28c3db021bf5', '42', '2020-05-15 15:13:40');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (43, '43', '63b705a7683e48ca875f28c3db021bf5', '43', '2020-05-15 15:26:36');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (44, '44', '63b705a7683e48ca875f28c3db021bf5', '44', '2020-05-15 15:27:32');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (45, '45', '63b705a7683e48ca875f28c3db021bf5', '45', '2020-05-15 15:31:52');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (46, '46', '63b705a7683e48ca875f28c3db021bf5', '46', '2020-05-15 15:31:54');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (47, '47', '63b705a7683e48ca875f28c3db021bf5', '47', '2020-05-15 15:39:29');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (48, '48', '63b705a7683e48ca875f28c3db021bf5', '48', '2020-05-15 15:39:30');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (49, '49', '63b705a7683e48ca875f28c3db021bf5', '49', '2020-05-15 15:39:30');
INSERT INTO `upms_role_permission`(`id`, `role_permission_id`, `role_id`, `permission_id`, `create_dts`) VALUES (50, '50', '63b705a7683e48ca875f28c3db021bf5', '50', '2020-05-15 15:39:32');
COMMIT;



SET FOREIGN_KEY_CHECKS = 1;