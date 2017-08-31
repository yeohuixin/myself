CREATE TABLE `goods` (
`id`  varchar(40) NOT NULL ,
`goodsunitprice`  float(20,2) NULL ,
`goodscount`  integer NULL ,
`goodsname`  varchar(40) NULL ,
`createtime`  datetime NOT NULL ,
`updatetime`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
)
;
