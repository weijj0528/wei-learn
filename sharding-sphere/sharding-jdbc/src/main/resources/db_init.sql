# 数据库初始化
#   test0
#   |-t_config
#   |-t_order0(user_id,order_id,***)
#   |-t_order1(user_id,order_id,***)
#   |-t_order_item0(user_id,order_id,***)
#   |-t_order_item1(user_id,order_id,***)
#   test1
#   |-t_config
#   |-t_order0(user_id,order_id,***)
#   |-t_order1(user_id,order_id,***)
#   |-t_order_item0(user_id,order_id,***)
#   |-t_order_item1(user_id,order_id,***)
#

create database test0;
create database test1;

CREATE TABLE `t_config`
(
    `id` bigint(20) unsigned DEFAULT NULL,
    UNIQUE KEY `uk_id` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `t_order0`
(
    `order_id` bigint(20) unsigned DEFAULT NULL,
    `user_id`  bigint(20) unsigned DEFAULT NULL,
    UNIQUE KEY `uk_order_id` (`order_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `t_order1`
(
    `order_id` bigint(20) unsigned DEFAULT NULL,
    `user_id`  bigint(20) unsigned DEFAULT NULL,
    UNIQUE KEY `uk_order_id` (`order_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `t_order_item0`
(
    `order_id`      bigint(20) unsigned DEFAULT NULL,
    `user_id`       bigint(20) unsigned DEFAULT NULL,
    `order_item_id` bigint(20) unsigned DEFAULT NULL,
    UNIQUE KEY `uk_order_item_id` (`order_item_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_order_id` (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `t_order_item1`
(
    `order_id`      bigint(20) unsigned DEFAULT NULL,
    `user_id`       bigint(20) unsigned DEFAULT NULL,
    `order_item_id` bigint(20) unsigned DEFAULT NULL,
    UNIQUE KEY `uk_order_item_id` (`order_item_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_order_id` (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
