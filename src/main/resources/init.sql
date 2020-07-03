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
INSERT INTO `db_ecp_hub`.`upms_user`(`user_id`, `username`, `password`, `realname`, `avatar`, `phone`, `email`, `sex`, `locked`, `create_dts`, `update_dts`) VALUES ('1', 'admin', '$2a$10$JXDq.LuL0WRCkm60jPRm1ue4ZuXiERlHaHxYG1axsA4G9hCkNMvNO', 'admin', '', '', '', 1, 0, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;