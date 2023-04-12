/*
 Navicat Premium Data Transfer

 Source Server         : wsl_mysql
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : 192.168.2.129:3306
 Source Schema         : old_book

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 12/04/2023 15:57:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner`  (
  `bn_id` bigint NOT NULL COMMENT '轮播图id',
  `bn_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图url',
  `bn_content` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图内容',
  `bn_is_show` tinyint NOT NULL COMMENT '0：不展示，1：展示',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '0：删除，1：正常',
  `bn_date` datetime NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`bn_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_banner
-- ----------------------------

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book`  (
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
  `book_author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名',
  `book_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍描述',
  `book_cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍封面',
  `book_isbn` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'isbn号',
  `book_stock` int NOT NULL COMMENT '库存',
  `book_price` decimal(6, 2) NOT NULL COMMENT '价格',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '0：下架，1上架',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book
-- ----------------------------

-- ----------------------------
-- Table structure for tb_book_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_book_type`;
CREATE TABLE `tb_book_type`  (
  `id` bigint NOT NULL COMMENT '主键',
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `type_id` bigint NOT NULL COMMENT '书籍类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car`  (
  `id` bigint NOT NULL COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `order_num` int NOT NULL COMMENT '书籍书',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_car
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `cm_id` bigint NOT NULL COMMENT '评论id',
  `cm_parent_id` bigint NOT NULL COMMENT '父评论id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `cm_content` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `cm_date` datetime NOT NULL COMMENT '评论时间',
  `status` tinyint NOT NULL COMMENT '0：删除，1：正常',
  PRIMARY KEY (`cm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` bigint NOT NULL COMMENT '订单id',
  `order_price` decimal(6, 2) NOT NULL COMMENT '订单价格',
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `order_date` datetime NOT NULL COMMENT '下单时间',
  `buy_date` datetime NOT NULL COMMENT '购买时间',
  `status` tinyint NOT NULL COMMENT '0：未付款，1：已付款',
  `order_num` int NOT NULL COMMENT '购买数量',
  `alipay_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '支付宝交易编号',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type`  (
  `type_id` bigint NOT NULL COMMENT '书籍类型id',
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '0：冻结，1：正常',
  `user_type` tinyint NOT NULL COMMENT '0：超管，1：普管，2：普通用户',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
