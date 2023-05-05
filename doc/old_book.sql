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

 Date: 05/05/2023 13:23:15
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
INSERT INTO `tb_banner` VALUES (1650861125954174978, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6987d84c-5230-4471-97f9-8f6a13b7259c.png', '轮播图1', 1, 0, '2023-04-25 21:55:39');
INSERT INTO `tb_banner` VALUES (1650861408688013314, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6987d84c-5230-4471-97f9-8f6a13b7259c.png', '轮播图1', 1, 1, '2023-04-25 21:56:46');
INSERT INTO `tb_banner` VALUES (1650863723423727617, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6987d84c-5230-4471-97f9-8f6a13b7259c.png', '轮播图12', 0, 0, '2023-04-25 22:05:58');
INSERT INTO `tb_banner` VALUES (1654035670253027330, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/4f2352a8-9740-4c1b-b351-107b62395698.png', '无', 1, 1, '2023-05-04 16:10:09');
INSERT INTO `tb_banner` VALUES (1654035780370284546, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f6919170-f433-4a15-ae5c-0f360d995091.png', '无', 1, 1, '2023-05-04 16:10:35');
INSERT INTO `tb_banner` VALUES (1654035901287874561, 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/afd533b7-7e37-4c79-a2eb-a386d354b3e3.png', '无', 1, 1, '2023-05-04 16:11:04');

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book`  (
  `book_id` bigint NOT NULL COMMENT '书籍id',
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
  `book_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名',
  `book_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍描述',
  `book_cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍封面',
  `book_isbn` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'isbn号',
  `book_stock` int NOT NULL COMMENT '库存',
  `book_price` decimal(6, 2) NOT NULL COMMENT '价格',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '0：下架，1上架',
  `book_add_date` datetime NOT NULL COMMENT '上架时间',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES (1650778101220638721, '软件工程', '王qzz', '非常牛皮', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b59ab7c7-3626-4949-9c36-ca48f5f62e2f.jpg', '12345678', 21, 25.50, 1, '2023-04-25 16:25:42');
INSERT INTO `tb_book` VALUES (1652215533690540034, '谁都不正常', '[美] 罗伊·理查德·格林克', '文化创造了“正常”这个概念，于是一部分人被排斥到社会之外，成为“不正常”的代表。精神疾病生动地诠释了这种“不正常”的社会现象，当我们谈到精神疾病时，我们知道它是一种疾病，同时我们的社会又将精神疾病塑造成一种人格和道德上的缺陷，这意味着将一个人的精神健康状态等同于社会身份来进行道德评判。如果我们意识到，我们绝大多数人实际上都有些神经质，情绪困扰本就是一种普遍的心理现象，那么我们就会发现偏见的存在，就会明白“谁都不正常，谁都不应该感到羞耻”。精神疾病就像感冒一样，不过是一种疾病罢了。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/73dcf49b-e42d-42c2-8cb4-5cc431adf851.jpg', '9787521749687', 5, 90.00, 1, '2023-04-29 15:37:34');
INSERT INTO `tb_book` VALUES (1652215535649280002, '鱼鳞帽艳史', '[塞尔维亚] 米洛拉德·帕维奇', '“文学帝王”帕维奇的国际巴洛克风神作', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/71fbc321-42fb-4f31-8e3e-51bceb887aa7.jpg', '9787532789313', 10, 78.00, 1, '2023-04-29 15:37:35');
INSERT INTO `tb_book` VALUES (1652215537092120577, '西方人文主义传统', '[英] 阿伦·布洛克', '★牛津大学人文学者的通识课，收录上百幅精美画作、照片及影印手稿', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6bb17fb6-b3bd-448e-ac36-1878146a4b62.jpg', '9787522512792', 5, 82.00, 1, '2023-04-29 15:37:35');
INSERT INTO `tb_book` VALUES (1652215538585292802, '南荒有沛竹', '陆源', '★ 陆源代表作小说集，朱岳、淡豹推荐。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8b3a3366-73cb-4738-8fa6-be4f9df32bd1.jpg', '9787208179806', 3, 59.00, 1, '2023-04-29 15:37:35');
INSERT INTO `tb_book` VALUES (1652215539877138434, '女性主义40年', '[日]上野千鹤子', '“我就是我！我就是女人！”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/17add1eb-1477-472a-bf29-6050f0aa7ed0.jpg', '9787572610868', 8, 59.00, 1, '2023-04-29 15:37:36');
INSERT INTO `tb_book` VALUES (1652215541240287234, '自我决定的孤独', '[德]伊丽莎白·冯·塔登', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e1697800-8559-4160-838d-024c35b79331.jpg', '9787218160177', 3, 68.00, 1, '2023-04-29 15:37:36');
INSERT INTO `tb_book` VALUES (1652215542599241729, '日常生活的现象学', '黄旺', '-编辑推荐-', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/4c83a600-ab62-4d18-aa55-0535336beacb.jpg', '9787552040128', 10, 78.00, 1, '2023-04-29 15:37:36');
INSERT INTO `tb_book` VALUES (1652215543899475970, '炽焰燃烧', '[美]罗恩·拉什', '🔥舍伍德·安德森奖、阿巴拉契亚年度图书奖、欧·亨利短篇小说奖得主，当代美国南方文学代表作家罗恩·拉什短篇小说扛鼎之作；', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/251030a0-edac-4078-97b5-043c0d7a286b.jpg', '9787020177400', 16, 65.00, 1, '2023-04-29 15:37:37');
INSERT INTO `tb_book` VALUES (1652215544931274753, '消费文化与后现代主义', '[英]迈克·费瑟斯通', '“没有哪位当代社会理论家比迈克·费瑟斯通对批判理论、消费文化和后现代领域的研究更出色。”这部当代经典著作将与后现代主义相关的消费文化带入了社会科学理论图景，第一次系统性和开创性地讨论了消费文化和流行文化、后现代生活方式、全球化多样性等主题，不仅说明了后现代是什么，还展示了它是怎样运作的。作者对消费文化与后现代主义的历史和现状的阐述全面、清晰，任何想更多地了解所处世界的普通读者，都能在本书中找到对自己日常生活的丰富精确的描述。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f42ee1a0-152f-4a8f-a5da-8ee7e4633860.jpg', '9787100211598', 3, 69.00, 1, '2023-04-29 15:37:37');
INSERT INTO `tb_book` VALUES (1652215546164400129, '学术“咸鱼”自救指南', '钱婧', '985博导、北师大教授、SSCI期刊副主编、B站知识区新晋人气UP主“云导师”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8bc76578-48c8-4de8-be2b-0f6dc70227d1.jpg', '9787221175533', 14, 68.00, 1, '2023-04-29 15:37:37');
INSERT INTO `tb_book` VALUES (1652215548265746433, '幸福地理学', '[美]埃里克·韦纳', '幸福是在别处还是一张中奖彩票？从卡塔尔到不丹，跟随作者走访十国，探寻幸福的“科学”答案', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/79a19350-95eb-41df-b84b-59620b1285fe.jpg', '9787100216869', 3, 68.00, 1, '2023-04-29 15:37:38');
INSERT INTO `tb_book` VALUES (1652215549641478145, '无尽的玩笑', '[美] 大卫·福斯特·华莱士', '∞  入选《时代》周刊评选的1923年以来世界百部最佳英语长篇小说', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6ef11911-2534-4022-9f7f-30e2929166ee.jpg', '9787208161757', 10, 168.00, 1, '2023-04-29 15:37:38');
INSERT INTO `tb_book` VALUES (1652215551273062402, '经营地方', '孙竞昊', '一部明清区域社会史研究著作，聚焦北方中等行政城市，讨论地方士绅如何在与国家权力的复杂互动中，主导并塑造了运河商阜山东济宁在经济、文化、社会诸方面的性格与生态。大学问出品', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/88ea49f0-5a6b-476e-af23-c42e1d4e1355.jpg', '9787559854704', 2, 89.00, 1, '2023-04-29 15:37:38');
INSERT INTO `tb_book` VALUES (1652215552371970049, '暮色与跳舞熊', '[中]张莉 主编', '本书为2019-2021《中国女性文学年选》的延续，是由著名评论家、女性文学研究专家、北师大教授张莉主持编选的一部中短篇小说选，产品包装上，本书定位更加市场化，更切近女性读者。图书内容上，本书延续以爱、秘密、远方为辑，收录2022年度二十位不同代际的中国女性至作者的作品，既代表了对当下文学现场的一种凝视，也代表了对女性文学未来的长远期待。20位左右不同代际的中国女性作家的作品，在一定程度上勾勒出中国老中青三代女性作家的创作谱系，为读者提供一个在一段时间内了解该年度女性文学中短小说的创作棚况的质量极高的文本。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b1c16583-940e-4d7c-8a45-dbeb66e795e0.jpg', '9787559473141', 7, 59.80, 1, '2023-04-29 15:37:39');
INSERT INTO `tb_book` VALUES (1652215553542180866, '白鸟与蝙蝠', '[日本] 东野圭吾', '★“这一次，我天空里的太阳，将由我亲手熄灭。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/181be9df-4e5a-48e3-b577-811a55937a79.jpg', '9787573503053', 1, 69.00, 1, '2023-04-29 15:37:39');
INSERT INTO `tb_book` VALUES (1652215554850803714, '马背上的朝廷', '[美]张勉治', '乾隆十六年至四十九年（1751—1784），乾隆皇帝六次南巡，往返于北京与江南。满洲皇帝及扈从，深入帝国最富庶、最重要的地区，上演了一场场长达数月之久的政治剧。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e741a6f2-4505-48f5-8cfb-3c1f91ce10af.jpg', '9787214280510', 11, 128.00, 1, '2023-04-29 15:37:39');
INSERT INTO `tb_book` VALUES (1652215556159426561, '恶徒的救赎', '[日] 高野和明', '【内容简介】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/fa60d2d7-4ee7-4598-a510-f459d34ff3fc.jpg', '9787547744345', 17, 49.90, 1, '2023-04-29 15:37:40');
INSERT INTO `tb_book` VALUES (1652215557476438017, '故事造型师', '[美]伦妮·布朗', '·王家卫喜爱的推理小说家劳伦斯·布洛克强烈推荐：两位行家给了出最具体、实用、又娱乐性十足的写作忠告！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/83e166da-9c70-4d3f-99d9-b7ffab2337df.jpg', '9787572609206', 18, 58.00, 1, '2023-04-29 15:37:40');
INSERT INTO `tb_book` VALUES (1652215558915084289, '隐喻学范式', '[德]汉斯·布鲁门贝格', '隐喻在哲学语言中扮演什么角色？它们是否阻碍了清晰的思考和清晰的表达？修辞有助于使哲学更容易被普通听众理解，但为了术语的精确性，在理想的情况下是否应该被消除？或者说，哲学家所使用的形象能否比他们精心设计的思想体系更能告诉我们规范一个时代的希望和关怀、态度和冷漠？在《隐喻学范式》中，汉斯•布鲁门贝格通过研究隐喻和概念之问的关系来处理这些问题，作者认为存在着不能翻译回概念语言的“绝对隐喻”。这些隐喻回答了 所谓本源的、理论上无法回答的问题，它们的相关性在于它们不能被揭示开来，因为我们并没有对此提出问题，而是发现它们已经在我们存在的基础上被先行提出了。因此作者认为，隐喻跃进了概念所无法填补的空白。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c24b740c-af0b-4ccf-9af4-db26f1f47489.jpg', '9787547321584', 6, 59.80, 1, '2023-04-29 15:37:40');
INSERT INTO `tb_book` VALUES (1652215560093683713, '吃的哲学', '[荷]安玛丽·摩尔', '荷兰著名人类学家、当代理论先锋安玛丽·摩尔', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/5caa507a-0198-441b-a909-802d079954ab.jpg', '9787208180130', 8, 78.00, 1, '2023-04-29 15:37:41');
INSERT INTO `tb_book` VALUES (1652215561662353410, '日复一日', '[匈]特雷齐娅·莫拉', '本书是毕希纳文学奖获奖作家特雷齐娅·莫拉的第一部长篇小说。年轻男子亚伯·内马精通十门语言，却失去了与人交流的能力，被锁在他自己的特殊沉默中。他流亡异乡，在社会边缘的难民堆中生活，人们或被他吸引，主动为其提供帮助，或对他有着无由来的仇恨，企图将其摧毁。他在人们激烈的爱恨中穿行，却仍与周围的一切保持着难以捉摸的疏离。在如今复杂的文化环境中，亚伯代表了那些背井离乡、被迫在一个新语言环境中重新塑造自我，甚至重新塑造人性的人。本书获得2005年莱比锡图书奖。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b20b2bf5-91e9-4405-8f01-7aef7a3f9257.jpg', '9787559856555', 2, 99.00, 1, '2023-04-29 15:37:41');
INSERT INTO `tb_book` VALUES (1652215563096805378, '费里尼在罗马', '[西] 蒂托·阿尔瓦 编绘', '🌠弥留之际侵入脑海的梦，让费里尼重游精神故乡罗马', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/13602713-b897-465e-a3e9-b215f0d45bc0.jpg', '9787535699817', 3, 45.00, 1, '2023-04-29 15:37:41');
INSERT INTO `tb_book` VALUES (1652215564338319362, '梁永安的电影课', '梁永安', '这是梁永安的第一本《电影课》，为我们解读了10部电影：《苔丝》《傲慢与偏见》《小妇人》《细雪》《走出非洲》《卡萨布兰卡》《罗生门》《少年派的奇幻漂流》《千与千寻》《法国中尉的女人》。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/0f36e365-d557-46ca-aa55-588d41c782d1.jpg', '9787547320273', 15, 59.00, 1, '2023-04-29 15:37:42');
INSERT INTO `tb_book` VALUES (1652215565957320706, '两个世界', '[埃]里法阿·拉费阿·塔赫塔维', '19世纪以来，欧洲工业革命所带来的成果已经盖过了东方世界曾经的辉煌。作为向西方开放贸易的东方国家之一，埃及也迫切地渴望参与其中，并分享果实。于是从19世纪20年代末开始，埃及的统治者穆罕默德·阿里派出留学生，前往欧洲学习最新的知识。本书的作者，里法阿·塔赫塔维便是埃及派出的第一批留学生之一，留学生们来到了当时欧洲的中心——法国，展开了为期5年的访问和学习。在此期间，塔赫塔维以一个阿拉伯知识分子的视角，记录下了他对法国乃至整个欧洲的观察，更是将现代性思想融入其中。当传统的阿拉伯社会，与标榜“理性”和“科学”的欧洲社会，通过一群留学生相遇时，塔赫塔维仿佛身处于两个截然不同的世界。这让他的观察陷入矛盾的漩涡：如何调和传统信仰与现代性思想之间的冲突？如何看待欧洲反绝对主义思潮与埃及政治体制之间的矛盾？……这些都是塔赫塔维不断遇到，又亟待解决的问题。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/452ed698-d32b-4056-9e92-35f01c8bb93e.jpg', '9787213109065', 6, 118.00, 1, '2023-04-29 15:37:42');
INSERT INTO `tb_book` VALUES (1652215567374995458, '溶于雾中', '[日] 笹泽左保', '1场选美大赛   5名美女入围决赛', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b7f16750-cbea-4b20-a6fa-81a9d1174a9d.jpg', '9787513351171', 14, 49.00, 1, '2023-04-29 15:37:42');
INSERT INTO `tb_book` VALUES (1652215568666841090, '洛阿娜女王的神秘火焰', '[意] 翁贝托·埃科', '* 你只有回忆起过去，才能预见未来', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/9a1eca76-03e7-48be-a0c0-a5c12a155fac.jpg', '9787532791828', 8, 158.00, 1, '2023-04-29 15:37:43');
INSERT INTO `tb_book` VALUES (1652215569841246209, '魔术师', '[爱尔兰] 科尔姆·托宾', '＊《大师》《布鲁克林》作者科尔姆·托宾最新长篇力作', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/dcb3787d-5e46-46f7-8f49-ecc7637bc1a5.jpg', '9787532791569', 16, 89.00, 1, '2023-04-29 15:37:43');
INSERT INTO `tb_book` VALUES (1652215571154063362, '基因彩票', '(美) 凯瑟琳·佩奇·哈登', '只有承认天生的不平等，才能实现真正的社会平等。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b27341a6-3d88-4d16-af5f-927241821f9f.jpg', '9787205106744', 3, 98.00, 1, '2023-04-29 15:37:43');
INSERT INTO `tb_book` VALUES (1652215572387188737, '给青年的十二封信', '朱光潜', '《给青年的十二封信》是朱光潜先生于 20 世纪 20 年代发表于《一般》杂志的文章合集，每一篇都是以“ 朋友 ”开头的书信体，每一句都能读出朱光潜先生的风趣幽默和真诚恳切。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7c1b31d7-b5c3-454d-ad40-3acfd29511b1.jpg', '9787573008732', 4, 39.90, 1, '2023-04-29 15:37:44');
INSERT INTO `tb_book` VALUES (1652215573624508418, '我在北京送快递', '胡安焉', '进入社会工作至今的十年间，胡安焉走南闯北，辗转于广东、广西、云南、上海、北京等地，做过快递员、夜班拣货工人、便利店店员、保安、自行车店销售、服装店销售、加油站加油工……他将日常的点滴和工作的甘苦化作真诚的自述，记录了一个平凡人在生活中的辛劳、私心、温情、正气。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3e327116-f076-4a2a-9caf-264d9f9d9aaa.jpg', '9787572609268', 13, 56.00, 1, '2023-04-29 15:37:44');
INSERT INTO `tb_book` VALUES (1652215574928936961, '身为女性的选择', '[日]上野千鹤子', '上野千鹤子2023全新作品！写给所有女性的清醒之书。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/fdcfcce5-56b0-4f99-8076-49264ec70673.jpg', '9787512514959', 18, 56.00, 1, '2023-04-29 15:37:44');
INSERT INTO `tb_book` VALUES (1652215576040427521, '献给爱与子宫的花束', '[日本] 铃木凉美', '“恶女斗士”、传奇作家铃木凉美的自传性随笔，', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ce8beaa4-06b5-4fc3-9e97-e7c3a1a9c568.jpg', '9787559858535', 1, 59.00, 1, '2023-04-29 15:37:44');
INSERT INTO `tb_book` VALUES (1652215577139335170, '多元视域中的明清理学', '吕妙芬', '本书以明末清初理学文本与话语为主要研究对象，试图以更丰富而新颖的史料、新的研究议题与视角，探讨明清之际儒学的发展与转型。从儒者的生死观、工夫论、舜的圣人形象、孝行与家礼、夫妇之伦、人性论等主题，分析明清之际儒学如何在历史发展及反思中自我修正与转化，建构一个能兼顾个人道德修养与家庭社群人伦，能对治晚明学风之弊，并在日用人伦中重建社会秩序的思想体系。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/39581478-7b45-4b6e-afee-a67c32b9498f.jpg', '9787220127717', 8, 98.00, 1, '2023-04-29 15:37:45');
INSERT INTO `tb_book` VALUES (1652215578510872577, '慈雨', '柚月裕子', '日本年度十佳作品！一经上市就在日本销售了30万册！柚月裕子作品首次引入大陆！曾获“这本推理小说真厉害”大奖、大薮春彦文学奖、日本推理作家协会奖！日本书店图书大奖的日本推理名家！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/457f60f8-bea5-416f-bc28-6710006300ff.jpg', '9787559665966', 17, 69.80, 1, '2023-04-29 15:37:45');
INSERT INTO `tb_book` VALUES (1652215579748192258, '多谈谈问题', '吴琦', '第33辑《单读》由9篇长访谈组成，针对历时性的问题，邀约许知远、郭玉洁、柏琳、杨潇、晓宇、叶三等作者，向不同领域的学者和从业者提问：鍾叔河、戴锦华、景凯旋、罗新、项飙、迈克尔·桑德尔、吕植、劳东燕、崔庆龙、张乔木（思想史万有引力）。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3758483e-2b97-408d-ad26-903ca0a6724c.jpg', '9787532186754', 8, 65.00, 1, '2023-04-29 15:37:45');
INSERT INTO `tb_book` VALUES (1652215581237170177, '无限阅读', '骆以军', '马尔克斯、波拉尼奥、黄锦树、邱妙津的小说有多吸引人？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/019b2668-5d14-4b0e-b2e1-43ec12e8043c.jpg', '9787532185481', 1, 70.00, 1, '2023-04-29 15:37:46');
INSERT INTO `tb_book` VALUES (1652215582474489858, '背对世界', '[德]埃尔克·海登莱希', '★ 与喧嚣的世界背道而驰，听见自己的声音。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/434833fc-9c9c-4bff-b30e-2556b8d65ae6.jpg', '9787572610400', 12, 52.80, 1, '2023-04-29 15:37:46');
INSERT INTO `tb_book` VALUES (1652215583841832962, '反资本世界简史', '[英] 大卫·哈维', '当代西方马克思主义研究权威大卫·哈维关于资本主义危机的重磅新作', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/98df912f-478d-49bb-8410-c1b45577eda6.jpg', '9787218161211', 15, 78.00, 1, '2023-04-29 15:37:46');
INSERT INTO `tb_book` VALUES (1652215585335005186, '负零', '[日]广濑正', '\\惊心动魄的时空冒险 丝丝入扣的绝妙设计/', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/bd597411-088c-43dc-9324-77e08b04559e.jpg', '9787559666932', 3, 58.00, 1, '2023-04-29 15:37:47');
INSERT INTO `tb_book` VALUES (1652215586576519170, '布鲁克林有棵树', '[美]贝蒂·史密斯', '🌲生活千疮百孔，但被爱治愈的孩子，终会向阳而生。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8fa41825-92cb-4362-b6b5-a7f9e9976ade.jpg', '9787519470746', 6, 69.80, 1, '2023-04-29 15:37:47');
INSERT INTO `tb_book` VALUES (1652215587616706561, '等待鲸唱起下一首歌', '[英]海伦·肯纳利', '当中风专家不幸罹患中风，当心理师需要照护自己与家人，他们怎样面对人生中的意外与危机？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e5c8c541-544f-448b-8195-ee6c65cfd397.jpg', '9787513350969', 12, 38.00, 1, '2023-04-29 15:37:47');
INSERT INTO `tb_book` VALUES (1652215588652699650, '女性主义全球史', '[英]露西·德拉普', '※一部纵横两百五十年、跨越世界各大洲的女性主义全球发展史※', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/077e90d1-f4fd-4195-b01c-18ff8514e22c.jpg', '9787305261244', 4, 86.00, 1, '2023-04-29 15:37:47');
INSERT INTO `tb_book` VALUES (1652215590003265538, '忠贞不贰？', '[英]史怀梅', '本书改变了以往以民族主义概念观察历史问题的视角，将10世纪燕云地区部分汉人官员和将领依附契丹政权的史实作为切入点，重新探讨了从唐灭亡至宋辽“澶渊之盟”间这一复杂时期内人们忠君观念和边界意识的变化，以及族群间文化冲突与认同等一系列重要问题。该书主要包括两大部分。第一部分专注于界定一系列概念并探讨相关观念的变化，包括边界、族性、忠诚的意识及它们之间的关系。第二部分通过对不同时期越境入辽的代表人物李澣、韩延徽、张砺、赵延寿、王继忠的个案分析，揭示了特殊历史阶段人们的不同选择。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/51c0da95-59ab-419b-8e5f-a2ee3cc1231d.jpg', '9787214269959', 7, 98.00, 1, '2023-04-29 15:37:48');
INSERT INTO `tb_book` VALUES (1652215591647432706, '白雪公主', '[日] 森川智喜', '◎日本新锐推理作家 森川智喜', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3ec6c48c-ff9e-4b8f-8412-86533f792156.jpg', '9787513351805', 11, 49.00, 1, '2023-04-29 15:37:48');
INSERT INTO `tb_book` VALUES (1652215592888946689, '一只鸟接着一只鸟', '[美]安·拉莫特', '你认识的每一个作家， 初稿写得都很糟糕，', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d5903eeb-6fdd-4841-a9a8-30eb8c2105bc.jpg', '9787521749748', 18, 56.00, 1, '2023-04-29 15:37:48');
INSERT INTO `tb_book` VALUES (1652215595044818946, '女性如何书写历史', '[英]弗朗西斯卡·韦德', '请让那些声音发出声响', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7d3a67a3-af15-418f-802c-14d9f5fd3a62.jpg', '9787513940641', 13, 118.00, 1, '2023-04-29 15:37:49');
INSERT INTO `tb_book` VALUES (1652215596353441794, '9号的工作', '[韩国] 金惠珍', '👾编辑推荐', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/feb72780-e60d-4a64-9b3a-752fdde9d022.jpg', '9787205106324', 7, 45.00, 1, '2023-04-29 15:37:49');
INSERT INTO `tb_book` VALUES (1652215597460738049, '晚清民初细菌学说与卫生防疫', '姬凌辉', '【内容简介】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/70d6755f-3240-4f1c-86bc-dac0d35c5025.jpg', '9787220128936', 6, 78.00, 1, '2023-04-29 15:37:50');
INSERT INTO `tb_book` VALUES (1652215598958104578, '生而为女', '[英]海莉·鲁本霍德', '此书终结了喂养开膛手神话的厌女症。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1425d91e-2524-4f2c-bf1c-ca4fce608c3f.jpg', '9787572610011', 4, 55.00, 1, '2023-04-29 15:37:50');
INSERT INTO `tb_book` VALUES (1652215600325447681, '山音', '[日] 川端康成', '⛰️★川端康成文学的最高峰，日本战后文学最高杰作之一！假托年老之境，谱写旧日本的挽歌。“对于死亡逼近的预感，描绘得比死亡本身更让人恐怖。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3968fb2c-c1b2-44b5-8fad-d1dda704a4d0.jpg', '9787545822038', 2, 45.00, 1, '2023-04-29 15:37:50');
INSERT INTO `tb_book` VALUES (1652215602032529409, '第三极', '[美]马克·辛诺特', '珠峰登顶百年“疑云”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ae4086df-72fb-41e8-b2e6-63c7a57d45cf.jpg', '9787020177592', 4, 69.00, 1, '2023-04-29 15:37:51');
INSERT INTO `tb_book` VALUES (1652215603135631361, '21世纪的21位思想家', '[澳]麦肯齐·沃克', '《21世纪的21位思想家》是一部介绍当代新思想的导论性书籍，作者用一本书的篇幅，评述了21位能够影响未来的思想家，为我们描绘了一幅当今世界思想导图。那些曾经启发我们、挑战我们认识局限的知识分子去哪儿了？谁是当今网络时代的萨特和波伏娃？本书作者认为，我们不会再有这样的人物 了，但是我们还有一般知识分子，他们的思考能够回应我们这个时代的疑虑和困惑。本书涵盖的话题包括文化、政治、工作、技术和人类世，评述的思想家有柄谷行人、齐泽克、朱迪·巴特勒、蒂姆·莫顿、东浩纪、全喜卿等。麦肯齐·沃克别具一格的解读既表达了欣赏赞同，同时也提出批判，她期待一般知识分子共同编织一张协同的思想之网，以使人们理解，乃至改变这个世界。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/cfc1364a-99d4-4bc2-9fee-351fdca86e12.jpg', '9787532182978', 10, 78.00, 1, '2023-04-29 15:37:51');
INSERT INTO `tb_book` VALUES (1652215604301647874, '空芯手帐', '八木咏美', '★ “今天，我怀孕了，因为不想上班，因为同事太烦！”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7f68f10f-83f7-4d5d-a43e-ef6e40801b64.jpg', '9787573502612', 17, 49.00, 1, '2023-04-29 15:37:51');
INSERT INTO `tb_book` VALUES (1652215605413138434, '边缘图像', '[英]迈克尔·卡米尔', '这是首部从历史和批评的角度探讨中世纪边缘艺术的专著。对中世纪的人们而言，边缘是双关、倒错和转化之地，是社会对立和心理冲突上演的场所。无论是常常出现在祈祷书页边的放肆无礼的丑角，还是教堂外墙上颇具嘲弄意味的滴水兽，都不在约定俗成的中心之内，而是身居边缘地带，孕育着反抗中世纪社会束缚的力量。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/dfccecd3-e447-454c-bee3-71ba3fffbf21.jpg', '9787301334263', 16, 88.00, 1, '2023-04-29 15:37:51');
INSERT INTO `tb_book` VALUES (1652215606516240386, '流动不居', '[德]鲍里斯·格罗伊斯', '-编辑推荐-', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/5caf84d3-f13e-4648-a98a-38b923ff042d.jpg', '9787568936392', 7, 48.00, 1, '2023-04-29 15:37:52');
INSERT INTO `tb_book` VALUES (1652215607757754370, '十字军东征', '[美]克里斯托弗·蒂尔曼', '在中世纪，曾有成千上万的欧洲人离开自己的家园，踏上追寻信仰之路，而在各种因素的影响下，最终带来的是一场绵延数百年的宗教战争——十字军东征。这一重要的历史事件一直是学者们关注的话题，也由此诞生了诸多经典之作。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/25eb5229-1fed-4f1f-a1a3-05b09d0b5c11.jpg', '9787552039245', 16, 88.00, 1, '2023-04-29 15:37:52');
INSERT INTO `tb_book` VALUES (1652215609053794305, '荒原狼', '[德] 赫尔曼·黑塞', '“我们所知的世界，包含其中的不公、腐朽和恶意，如何与我们信任的理想和至高愿景相协调？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8f9b8bf9-b6b7-4c79-b72b-bdfa3c6770a2.jpg', '9787533971014', 4, 59.80, 1, '2023-04-29 15:37:52');
INSERT INTO `tb_book` VALUES (1652215610358222849, '彩虹几度', '[日]川端康成', '诺贝尔文学奖得主、日本新感觉派文学大师川端康成——精选十卷集！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/39a534de-ebd9-4704-842e-09fdd8e432a2.jpg', '9787515523842', 19, 48.00, 1, '2023-04-29 15:37:53');
INSERT INTO `tb_book` VALUES (1652215611725565954, '表演与阐释', '[美] 柯马丁', '我试图将早期中国的诗学文化置入宗教、政治、哲学思想及实践的大语境中。比起将写定的文本视为当然，认为它们是早期中国文化最为自然、不证自明之物，我更愿意去探讨这些文本如何在一个与当下完全不同的世界中运作：那不是一个由沉默的抄者和读者组成的世界，在那个世界中，无论是书面文本还是口头文本，都与由鲜活的宗教政治仪式、政治劝诫、教与学、道德修身等构成的表演传统相关。那是一个诗学文本绝非纯然以书面制品的形式而起作用的世界。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/70e53a61-4423-4bde-8f7c-afa2b89153b5.jpg', '9787108074157', 10, 88.00, 1, '2023-04-29 15:37:53');
INSERT INTO `tb_book` VALUES (1652215612971274241, '改变世界的17个方程', '[英] 伊恩•斯图尔特', '17段改变人类文明进程的数学故事', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f0269cda-48d4-4a19-ac2e-b615dd739f4c.jpg', '9787115608970', 12, 89.80, 1, '2023-04-29 15:37:53');
INSERT INTO `tb_book` VALUES (1652215614401531906, '欲望与归零', '[美]杜锺敏', '德勒兹认为，欲望是一种生产性的积极力量，渗透于个人、社会、国家等各个领域，一切生产都是现实的欲望生产，欲望机器就是社会机器。德勒兹希望我们去做的，是一个欲望机器归零的境界。一般人在每一次逆境、失败、挫折时，只有把自我或者自我原来的执着消解、归零，才能在困境中找到“和谐元素”，从而调整心态，找到解决问题的办法。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c3fb1732-f78a-4f6b-9ddf-fafad1c9177a.jpg', '9787559855961', 4, 58.00, 1, '2023-04-29 15:37:54');
INSERT INTO `tb_book` VALUES (1652215615450107906, '不再讨好', '黄玉玲', '武志红诚挚推荐。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b772fe93-6f4b-4d63-94e1-9529eac46014.jpg', '9787115594839', 9, 59.80, 1, '2023-04-29 15:37:54');
INSERT INTO `tb_book` VALUES (1652215616628707330, '黑死病下的日常', '[美]约瑟夫·P.伯恩', '1348年，死神挥动镰刀，整个欧洲笼罩在黑死病的阴影下，学校停课、教堂关门、店铺废弃、邻里远迁、建筑停工……街道上不再有熙熙攘攘的人群，摊贩们的叫卖声变成了尸体搬运工们粗声粗气的吆喝，堆满了尸体和濒死之人的马车嘎吱嘎吱地提醒着所有人死亡的迫近。人们蓦然发现，自己所生活的这个世界不但受到了疾病的威胁，而且永远被疾病改变了。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/590bc0ed-0740-44fb-be21-9a9fda590d27.jpg', '9787552040142', 7, 78.00, 1, '2023-04-29 15:37:54');
INSERT INTO `tb_book` VALUES (1652215617928941570, '古希腊思想通识课：修昔底德篇', '张新刚', '公元前431年，希腊世界内部的两大阵营爆发了长达27年的伯罗奔尼撒战争。在这场影响深远的战争里，各类城邦与各色人等轮番登场，在各种极端环境中做出自己的抉择。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c86dc2ca-3a9d-48d9-b1ad-d77838c1fe0b.jpg', '9787556130870', 19, 68.00, 1, '2023-04-29 15:37:54');
INSERT INTO `tb_book` VALUES (1652215619224981505, '春', '[英] 阿莉·史密斯', '“这任凭日常困苦扰人心神，而仍怀揣希望的奔徙，就是爱。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e771ef96-11b2-4ce5-82d7-6b2c4025320e.jpg', '9787533971113', 8, 55.00, 1, '2023-04-29 15:37:55');
INSERT INTO `tb_book` VALUES (1652215620852371457, '巴基斯坦寻根之旅', '[英]伊桑巴德·威尔金森', '英国、印度与巴基斯坦百年纠葛的缩影', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/04fe562d-2cdf-4463-bc33-bcc27849e016.jpg', '9787100215923', 11, 55.00, 1, '2023-04-29 15:37:55');
INSERT INTO `tb_book` VALUES (1652215622492344322, '春天的十七个瞬间', '[苏联] 尤利安·谢苗诺夫', '★ 谍战小说巅峰之作，影响后世无数作品', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1906f7a4-c818-4f79-bd71-a51234d1667c.jpg', '9787532790937', 19, 78.00, 1, '2023-04-29 15:37:55');
INSERT INTO `tb_book` VALUES (1652215623796772866, '两度', '[法]弗兰克·蒂利耶', '🪄让读者失去所有参照物，《未完成的手稿》嵌套式神级续写！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/90f371e7-f924-44ca-b280-a0c8debdfd26.jpg', '9787559664761', 8, 68.00, 1, '2023-04-29 15:37:56');
INSERT INTO `tb_book` VALUES (1652215624774045698, '无所乐观的希望', '[英] 特里·伊格尔顿', '伊格尔顿这本书中，关于希望的讨论始于坚决拒绝乐观在人生历程中的作用。伊格尔顿探究这个熟悉却难以捉摸的词的含义：这是一种情感吗？它与欲望有何不同？它会迷恋未来吗？最后，伊格尔顿提出了悲剧性希望的新概念，其中这种旧的美德代表着即使在遭受毁灭性损失之后仍然保持的力量。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/cd7e6d9e-05b5-45fe-bf3d-74d15f5d3a98.jpg', '9787532183586', 19, 55.00, 1, '2023-04-29 15:37:56');
INSERT INTO `tb_book` VALUES (1652215626086862850, '谜托邦02', '华斯比 主编', '怪异君、呼延云鼎力推荐！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d3a110bc-f250-4ff9-9d1b-c9b772507e61.jpg', '9787559666642', 14, 79.00, 1, '2023-04-29 15:37:56');
INSERT INTO `tb_book` VALUES (1652215627332571137, '创造欧洲人', '[英] 奥兰多·费吉斯', '1846年6月13日巴黎一布鲁塞尔铁路开通，1500名旅客接受铁路公司经营者罗斯柴尔德男爵的邀请，搭上首航火车。受邀的旅客包括法国国王国王路易·菲利普一世之子内穆尔公爵和蒙庞西耶公爵、法国和比利时的官员们、大仲马、雨果、戈蒂耶、安格尔等名人。旅途中，柏辽兹指挥乐队在火车上演奏了《葬礼及凯旋大交响曲》。这条铁路并非第一条国际铁路，但却被视为最重要的，因为它连接了法国和低地国、英国与德语区。铁路穿越国界，就此开启了欧洲文化的新时代。作家、艺术家、歌剧团、乐队和演员成为常客，搭乘火车穿梭各国，举办大量的演奏会、艺术展览、读书会、沙龙，国际市场也借由便捷的跨国运输向量产的廉价画作复制品和书籍、乐谱的印刷品开放。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d3b1273a-2de2-4b9f-ae3b-2f002a1fdb2c.jpg', '9787547742273', 16, 148.00, 1, '2023-04-29 15:37:57');
INSERT INTO `tb_book` VALUES (1652215628565696513, '剑桥第一次世界大战史（第1卷）', '【美】杰伊·温特  主编', '第1 卷《全球战争》全面叙述了这场战争的军事史。一个由杰出历史学家组成的国际团队详细记述了全球主义如何使一场大战成为可能，帝国主义扩张又如何演变成巨大灾难，战争逐年扩大的规模和破坏力远远超过了人们在1914年预计的程度。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/10700332-5e28-490e-90d3-bb5dceee3ff9.jpg', '9787308202138', 1, 238.00, 1, '2023-04-29 15:37:57');
INSERT INTO `tb_book` VALUES (1652215629870125057, '我们为什么爱喝酒', '森舸澜', '每一滴酒精都对人体有害，但几乎没有一个文明错过酒精，这个现象背后有一些值得推敲的地方。森舸澜在本书中主张，酒精是人类最早的随身携带的吐真剂，它带来迅速起效但温和稳定的理性掉线、创造性思维提升，一直是促进人类合作、文化传承与创新的关键要素。在没有酒精的地方，代替它的是更加 耗时的舞蹈、效果更难以控制的药物等。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/9b852d99-a79d-474d-8fb1-b6196e21618d.jpg', '9787213108556', 6, 68.00, 1, '2023-04-29 15:37:57');
INSERT INTO `tb_book` VALUES (1652215631296188417, '凯尔特神话全书', '[英] 彼得·贝雷斯福德·埃利斯', '🔱「世界神话全书」之凯尔特卷', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/02b243e1-a1b3-4a35-8c28-41051977f761.jpg', '9787572604188', 17, 128.00, 1, '2023-04-29 15:37:58');
INSERT INTO `tb_book` VALUES (1652215632604811265, '当医疗出错时', '[美]丹妮尔·奥弗里', '医疗过失比我们想象中普遍得多，但大部分是可以避免的。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c2e857cf-a19a-495a-9e98-15966b7b9487.jpg', '9787544794787', 4, 78.00, 1, '2023-04-29 15:37:58');
INSERT INTO `tb_book` VALUES (1652215634165092353, '最后的故事', '[波兰]奥尔加·托卡尔丘克', '◑诺奖得主托卡尔丘克长篇小说代表作', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e81e9a84-3447-433f-9f07-67fa4aa688e8.jpg', '9787533971694', 12, 52.00, 1, '2023-04-29 15:37:58');
INSERT INTO `tb_book` VALUES (1652215635532435458, '第三牧场', '辉格', '阶层是支撑复杂社会的一个重要结构元素。本书围绕等级阶梯的构造、壮大、嵌套、封闭、开放等，梳理了奉行原始平等主义的狩猎采集群体如何一步步发展成结构庞杂、层级丰厚的大型人类社会；对当今全球生育率下降导致的社会进化模式的转变，以及等级、文化、价值阶梯的断裂，进行了分析与反思。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/fc65850c-bfb7-41bf-9adf-5cb8d65c302b.jpg', '9787203127628', 11, 78.00, 1, '2023-04-29 15:37:59');
INSERT INTO `tb_book` VALUES (1652215636945915906, '古人之夜', '(美) 南希· 贡琳', '诚如利希滕贝格所言，“我们整个历史仅仅是醒着的人的历史”，甚少有人研究夜晚。然而，古代的夜晚真是漆黑一片吗？古人又是如何度过漫漫长夜的呢？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/133b1b41-95ec-419f-8c82-50d1e4f4dbd9.jpg', '9787559856463', 12, 108.00, 1, '2023-04-29 15:37:59');
INSERT INTO `tb_book` VALUES (1652215638183235585, '亚文化', '[英]迪克·赫伯迪格 著', '“说到底，亚文化是一种冒犯的姿态。它是弱者发出的噪声，并试图从地下的暗处带回光明。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8a927678-87cd-4ec1-b9e9-dc33c5edf8dc.jpg', '9787559855060', 5, 58.00, 1, '2023-04-29 15:37:59');
INSERT INTO `tb_book` VALUES (1652215639747710978, '中国古代社会', '许进雄', '起源于图绘的中国古文文字，是历史悠久的非物质文化遗产。一笔一画中，隐含了先民言语的符号。象形的文字可以反映创字时的生活环境、使用的工具、生活的方式，甚至是处理事务的方法和思想观念。以分析古文字来探求古代社会的具体状况，可以解读古文明的重要信息。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8e025344-26ed-4889-8d63-5e7577625b60.jpg', '9787208178526', 10, 108.00, 1, '2023-04-29 15:38:00');
INSERT INTO `tb_book` VALUES (1652215640997613570, '那些古怪又让人忧心的问题又来了', '[美]兰道尔·门罗', '★  比尔·盖茨盛赞，全球百万级畅销科普作家，《What If？那些古怪又让人忧心的问题》《How to 如何不切实际地解决实际问题》《万物解释者》的作者兰道尔·门罗新作来了！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2bb779aa-990a-4228-981c-b64ceef79a2a.jpg', '9787574207653', 10, 88.00, 1, '2023-04-29 15:38:00');
INSERT INTO `tb_book` VALUES (1652215642494980098, '冷亲密', '[法]伊娃·易洛思', '☆“世界上最有影响力的12位思想家之一”（德国《明镜周刊》）', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2d6c7132-87a9-4219-bdff-dafca1331e63.jpg', '9787556131556', 4, 49.00, 1, '2023-04-29 15:38:00');
INSERT INTO `tb_book` VALUES (1652215643728105473, '我的骨头会唠嗑', '廖小刀', '一部法医兄弟的真实尸检手记，一幅法医视角的人性浮世绘。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ff80362a-18ba-4548-8cf4-8168258ea11a.jpg', '9787515523897', 14, 96.00, 1, '2023-04-29 15:38:01');
INSERT INTO `tb_book` VALUES (1652215645032534017, '悠悠荡荡小天国', '[日]小川糸', '支撑一个人的生活的，好像从来都不是理想与野心，而是零碎而值得欣喜的事。 小川糸散文随笔新作，她的文字带有一如既往的浓厚疗愈色彩，已成为万千读者的生活中不可或缺的重要养分。 这个世界再也没有什么东西，比过好生活还来得重要。“将耳朵浸泡在池水里，外界的声音即刻被阻断，之前置身的世界仿佛变得异常遥远。我闭上眼睛，任池水载着身体漫无目的地漂荡，渐渐地分不清身在何处，好像整个人都孤零零地悬浮在广漠的宇宙之中。” 怡然自得的感觉太过迷人，不足为奇的平淡日常亦十分可贵。哪怕只是琐碎的小事，也能为生活增添一丝愉悦。期望很久很久以后，当回忆往昔，我们仍会觉得过往的岁月都闪闪发光、无可取代。 グリーンピース 无论现实世界如何艰辛，小川糸的文字都会给我们提供一个安逸的角落。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/92ba4261-aa24-4103-8d05-ba389bb71aad.jpg', '9787572609992', 3, 49.80, 1, '2023-04-29 15:38:01');
INSERT INTO `tb_book` VALUES (1652215646211133442, '主体理论', '[法]阿兰·巴迪欧', '《主体理论》于1982年在法国首次出版，是阿兰•巴迪欧最重要的作品之一。全书从拉康精神分析学说的角度出发，探寻主体理论，将马克思主义、结构主义与后结构主义纳入理论阐发，展现了精神分析学说与哲学间的有机联系。作者精彩的文学分析，赋予理论建设以艺术美感与现实深度。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ffe85109-c4f5-4e25-8e9b-f70cec587e85.jpg', '9787568084031', 12, 108.00, 1, '2023-04-29 15:38:01');
INSERT INTO `tb_book` VALUES (1652215647771414529, '一激到底', '[美]希拉里·弗里德曼', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8c314599-4350-48d9-aabe-36408f32a2cd.jpg', '9787218160672', 7, 88.00, 1, '2023-04-29 15:38:01');
INSERT INTO `tb_book` VALUES (1652215648878710786, '外面的世界I', '[法] 玛格丽特·杜拉斯', '★“我会为外面而写，每当外面的世界将我吞没，每当发生了一些让我疯狂，让我必须窜出去、走到大街上去的事”★', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6abe99cc-2144-40ea-8802-10c7c148ad6e.jpg', '9787521750539', 3, 69.00, 1, '2023-04-29 15:38:02');
INSERT INTO `tb_book` VALUES (1652215650132807682, '资本主义与精神分裂（卷２）', '[法] 吉尔·德勒兹（Gilles Deleuze）', '本书由一座座“高原”组成，每一座高原都将不同的主题构成错综复杂的网络，这些主题则与众多学科关联在一起。德勒兹和加塔利将艺术、数学、地质学、生物学、语言学、人类学、历史学、动物行为学、文学、音乐、政治理论、经济学等学科的资源转变、折叠，在此基础上，概念的地壳不断隆起，形成了稳定的地标——高原。散布于书中的“根茎”式的思维方式或许是人类历史上最具创造力的，因为它已经触动了建筑、文学、舞蹈、电影、新媒体等众多非哲学领域的琴弦。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/5db07e40-8e55-40d5-b470-18f6b94309a2.jpg', '9787208178977', 16, 118.00, 1, '2023-04-29 15:38:02');
INSERT INTO `tb_book` VALUES (1652215651382710273, '攒多少钱，才能安心养老', '槽叔', '社科院预测，2035年养老金或将耗尽。面对老龄化、少子化的双重压力，自己给自己养老，已经是无可回避的事实。要攒够多少钱，才能让自己安度晚年？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/96d1829d-46ad-4a5b-850d-71c84d0215e9.jpg', '9787559666697', 4, 68.00, 1, '2023-04-29 15:38:02');
INSERT INTO `tb_book` VALUES (1652215652301262850, '艺术家之死', '[美]威廉·德雷谢维奇', '艺术家如何在数字时代谋生？你会听到两种截然不同的声音。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a8fce3ea-6a55-4dfa-a380-e36c9dcb8196.jpg', '9787521749670', 10, 88.00, 1, '2023-04-29 15:38:03');
INSERT INTO `tb_book` VALUES (1652215653588914178, '翻译侦探事务所', '赖慈芸', '还原名著翻译的前世今生，考索译人的生涯往事，回望共同的翻译记忆，拼凑出大时代下的翻译史拼图。幽默风趣的文字呈现严峻荒诞的史实，搭配上下求索得来的珍贵图片、经年爬梳的详尽图表，图文并茂地佐证内容的真实无讹。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/91a716dc-641a-46f4-9ad0-ad6c7b00ad7c.jpg', '9787108073198', 17, 45.00, 1, '2023-04-29 15:38:03');
INSERT INTO `tb_book` VALUES (1652215655023366146, '化家为国', '黄丽君', '本书是一部立论扎实、问题意识鲜明的清代制度史作品。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6a78d24d-c01d-4208-82fb-c8fa56a838fe.jpg', '9787208181441', 15, 88.00, 1, '2023-04-29 15:38:03');
INSERT INTO `tb_book` VALUES (1652215656462012418, '危险的边疆', '[美]托马斯·巴菲尔德', '《危险的边疆：游牧帝国与中国》通过对两千多年历史时段之内游牧帝国与中原王朝之间历史关系的人类学考察，提出了一个解释包括蒙古帝国在内的游牧大帝国兴盛和衰亡的政治和经济组织分析模式，并对中原与这些帝国的关系作了贯通性的研究。作者提出了游牧力量在与中原接触时所交替采取的“外部边界战略”和“内部边界战略”问题，指出游牧帝国对中原北部地区统治的周期律在某种程度上表现为一种基于集权化与分权化之上的机制性更替过程。本书为中国边疆史研究贡献了一种不同于专业史学家的新思路。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2422aea4-9c39-41df-8c78-8fdb4d2b0c7c.jpg', '9787214280435', 18, 118.00, 1, '2023-04-29 15:38:04');
INSERT INTO `tb_book` VALUES (1652215657695137793, '千面宋人', '仇春霞', '一部宋人命运浮沉录', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6b5a015a-ba97-426a-bd87-8b2b1cd6cdfa.jpg', '9787559853172', 3, 108.00, 1, '2023-04-29 15:38:04');
INSERT INTO `tb_book` VALUES (1652215658982789121, '角谷的藏书架', '[美]角谷美智子', '送给所有“爱书人”的一份礼物', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/afa8233c-4fdf-44fb-a055-d40d4dc5ad82.jpg', '9787522511856', 4, 118.00, 1, '2023-04-29 15:38:04');
INSERT INTO `tb_book` VALUES (1652215660207525889, '甲午战争前后之晚清政局', '石泉', '本书脱胎自石泉教授受业于陈寅恪先生时撰写的硕士论文，完成于1948年。历经半个世纪的历史风雨尘埃冲刷掩埋后得以问世，初版于1997年。此次再版，面貌一新。旨在使这部经典之作臻于完善。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/0992b420-0415-4d10-80e7-18eb1abc98d9.jpg', '9787108073075', 4, 89.00, 1, '2023-04-29 15:38:04');
INSERT INTO `tb_book` VALUES (1652215661176410113, '外面的世界II', '[法 ]玛格丽特·杜拉斯', '★“我会为外面而写，每当外面的世界将我吞没，每当发生了一些让我疯狂，让我必须窜出去、走到大街上去的事”★', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a7a48e06-7c52-4d50-89a1-650996ac2999.jpg', '9787521750546', 14, 58.00, 1, '2023-04-29 15:38:05');
INSERT INTO `tb_book` VALUES (1652215662338232321, '中国转向内在', '【美】刘子健', '在两宋之际的战乱动荡中，政治凌驾于经济、文化之上，专制皇权膨胀为绝对独裁，一些倾向保守的知识分子转向儒学，以道德重建为救亡图存之道，这种保守主义思想终于被树立为国家正统，这就是影响后世中国近千年之久的“理学”。然而，对于思想文化的发展来说，这是一场得不偿失的胜利。11世纪曾经纵横恣肆、辉煌一时、充满外向活力的中国知识分子和他们所创造的思想，在正统的笼罩下趋向内在、保守、封闭、自省，失去创新的动力。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1c74a5dc-f07d-4573-8a1c-7d5d23b185eb.jpg', '9787214270030', 18, 68.00, 1, '2023-04-29 15:38:05');
INSERT INTO `tb_book` VALUES (1652215663969816577, '天潢贵胄', '[美]贾志扬', '1127年，金军攻陷宋都开封，掠走徽钦二帝和3000余近支宗室。徽宗之子赵构在杭州重建宋朝，他死后帝位回到了匡胤子孙手里。流落到南方的宗室继续得到政府的特权和照顾，但获得了应举、做官的自由，逐渐融入士大夫阶层，个中佼佼甚至官至宰相。1279年，宋朝灭亡，赵氏三兄弟后裔作为宗室的历史宣告结束，作为宗族的历史翻开了新的一页。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/50b3ce32-f744-4682-9dd4-91ed1251e98d.jpg', '9787214280497', 10, 108.00, 1, '2023-04-29 15:38:05');
INSERT INTO `tb_book` VALUES (1652215665471377409, '人类灭绝', '[日] 高野和明', '【内容简介】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c692f880-c2d7-4534-be62-bdbf62f83873.jpg', '9787547744048', 3, 69.90, 1, '2023-04-29 15:38:06');
INSERT INTO `tb_book` VALUES (1652215666725474306, '规则的悖论', '大卫·格雷伯', '从职称评审、抵押贷款，到申请办公设备甚至出行购票的流程，五花八门的规定可以让我们填写无数张表格，在流程的提交与退回中反复摩擦，当每天的生活从填表开始，我们不禁困惑：真的需要这么多流程吗？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d6786c0c-c738-42e4-8510-0b41765ea3d8.jpg', '9787521749816', 12, 65.00, 1, '2023-04-29 15:38:06');
INSERT INTO `tb_book` VALUES (1652215667757273089, '看不见的伤痕', '[美]蕾切尔·路易丝·斯奈德', '➢内容简介', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/45c74772-6360-4e27-b0f5-cc9d1c9e3ea8.jpg', '9787513350914', 4, 69.00, 1, '2023-04-29 15:38:06');
INSERT INTO `tb_book` VALUES (1652215669204307969, '挑战古人100天2', '云葭', '一本大宋小民生活日志！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a6b388a2-9b95-4c09-bdaf-0a2abcb8fd52.jpg', '9787559666000', 6, 72.00, 1, '2023-04-29 15:38:07');
INSERT INTO `tb_book` VALUES (1652215670315798530, '在东大和上野千鹤子学“吵架”', '[日]遥洋子', '▲想要在“吵架”中获胜？上野千鹤子教授告诉你：', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/07794e00-f124-4a10-b2b2-1749032e1cc8.jpg', '9787559665386', 9, 52.00, 1, '2023-04-29 15:38:07');
INSERT INTO `tb_book` VALUES (1652215671339208706, '唯物主义', '[英] 特里·伊格尔顿（Terry Eagleton）', '作为英国左派知识分子的领军人物，特里·伊格尔顿在本书中首次对唯物主义这一中国读者耳熟能详的概念进行了全面的观察与思考，其论述的唯物主义并不局限于日常什物或哲学史上的相关讨论，而是由“身体”——人存在于世的物质基础出发，借助马克思、尼采、维特根斯坦与阿奎那的理论，在哲学、文化、科学等各个层面上展开。由此，伊格尔顿将唯物主义从唯物与唯心的对立中解放出来，还原回人类社会生活与精神生活的方方面面中。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8dd9c266-d751-42c6-9a93-7df96365f2f8.jpg', '9787532183364', 4, 55.00, 1, '2023-04-29 15:38:07');
INSERT INTO `tb_book` VALUES (1652215672522002434, '鸭镇往事', '曹寇', '听名字，曹寇就不可能是一个正经的家伙，可我愿意正经地推荐他的小说。如果你正空洞、正无聊，恰好又赶上了一本曹寇的书，我还能对你说什么呢，我只能恭喜你。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/adb71446-667c-487f-9536-5e80ef5d5c94.jpg', '9787532183593', 12, 68.00, 1, '2023-04-29 15:38:07');
INSERT INTO `tb_book` VALUES (1652215673826430977, '动物为什么吃不胖', '[澳] 大卫·劳本海默', '英国《新科学人》杂志年度最佳科普图书，两位前沿科学家横跨30年营养实验，揭秘人类代谢的蛋白质杠杆原理，让野生动物的饮食智慧教你远离肥胖，平衡美味和健康。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/541c2483-6a2a-4fa1-a4a5-4d714fee53d4.jpg', '9787221175373', 5, 60.00, 1, '2023-04-29 15:38:08');
INSERT INTO `tb_book` VALUES (1652215675046973441, '唯有书籍', '（英）汤姆·摩尔', '读书的人会消失，但书籍将带着记忆永存。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/4ee64021-30ea-4483-a034-68d0b626619d.jpg', '9787553526980', 13, 68.00, 1, '2023-04-29 15:38:08');
INSERT INTO `tb_book` VALUES (1652215676712112130, '中古探微', '[美]伊沛霞', '推荐语：伊沛霞教授五十年中国史研究成果精选。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/27697409-37f9-45ac-8aee-a4d4e33f42fd.jpg', '9787573205889', 5, 158.00, 1, '2023-04-29 15:38:08');
INSERT INTO `tb_book` VALUES (1652215678926704641, '看不见的孩子', '[美]安德里亚·埃利奥特', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/08b2ae7b-a948-41a5-95ba-c99568ce42eb.jpg', '9787521753783', 7, 88.00, 1, '2023-04-29 15:38:09');
INSERT INTO `tb_book` VALUES (1652215680398905345, '每一句话语都坐着别的眼睛', '[德] 赫塔·米勒', '2009年诺贝尔文学奖得主赫塔·米勒的准自传', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e11a3183-9c62-48e8-9efb-10df5f000e42.jpg', '9787221173836', 18, 52.00, 1, '2023-04-29 15:38:09');
INSERT INTO `tb_book` VALUES (1652215681497812993, '全能学习法', '[英] 彼得·霍林斯', '达•芬奇、埃隆•马斯克等“文艺复兴人”学习技能大揭秘', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6756cdfa-1079-4e55-8fd4-62987bbaa76c.jpg', '9787522509716', 4, 49.80, 1, '2023-04-29 15:38:10');
INSERT INTO `tb_book` VALUES (1652215682726744066, '杀手的记忆法', '[韩] 金英夏', '·患上阿尔茨海默症对连环杀手而言，简直是人生送来的烦人玩笑。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ac068f6f-27f8-4fd9-aa3c-d98513166591.jpg', '9787512514621', 9, 55.00, 1, '2023-04-29 15:38:10');
INSERT INTO `tb_book` VALUES (1652215684219916290, '精神分析的新发展', '[美]乔治·哈格曼', '这是一本心理治疗师的临床指南，更是心理咨询者的可靠良方。本书对主体间自体心理学（ISP）的理论及其临床应用进行了全面的概述，读者将深入了解过去半个世纪以来该学科发展最具生活指导意义的内容。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e8a90630-c4b6-46e3-85df-8a7555201bbb.jpg', '9787559855794', 10, 88.00, 1, '2023-04-29 15:38:10');
INSERT INTO `tb_book` VALUES (1652215685469818881, '男孩女孩呵护手册', '[日] 福地麻美', '为了坚定地守护孩子，', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b7efe229-2400-4ae6-9b53-0872d8d0c388.jpg', '9787213108808', 15, 58.00, 1, '2023-04-29 15:38:11');
INSERT INTO `tb_book` VALUES (1652215686837161986, '朝不保夕的人', '[英]盖伊·斯坦丁', '每一个生存不稳定、看不见未来而只能活在当下的人，都正在滑落为朝不保夕的人。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/57b72c40-43a5-4390-b60f-2f7c1921f272.jpg', '9787213108563', 17, 68.00, 1, '2023-04-29 15:38:11');
INSERT INTO `tb_book` VALUES (1652215687948652545, '茶烟歇', '范烟桥', '《茶烟歇》为民国才子范烟桥所著掌故笔记合集，是一部才子书，亦是一部乡邦文献，更包含了“可以订正史之讹，可以补官书之阙”。全书包含文章272篇，前部收入孙东吴、严独鹤、周瘦鹃、程小青、顾明道、尤半狂、江红蕉、金震、赵汉威、张圣瑜、金祖谦、冯超人序言12篇，柳亚子、金鹤望、包天笑等20人题辞，范烟桥自填《沁园春》一阕。本次出版将《茶烟歇》单独整理成书，便于读者细赏，所据底本为《茶烟歇》民国二十三年（1934）中孚书局初版，并对标点、句读进行调整，正其舛误，改繁体为简体，以俾读者茶余饭后消闲把玩，也正合作者于茗香烟缕间下笔之意。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8504bb04-b9ae-42ef-90be-3ac916b93cc8.jpg', '9787534097775', 17, 68.00, 1, '2023-04-29 15:38:11');
INSERT INTO `tb_book` VALUES (1652215689378910210, '来者是谁', '郑伊看', '13—14世纪欧洲艺术中出现了一个鲜为人关注的现象，在意大利绘画中出现了一些东方面孔。在中世纪人眼中，东方人形象复杂而多义，其塑造植根于同时期社会政治、宗教和文化传统之中，并随着教廷与东方的交往关系发生变化。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/86d95da8-ece6-47eb-bc06-e60e466be3cc.jpg', '9787574101852', 19, 168.00, 1, '2023-04-29 15:38:11');
INSERT INTO `tb_book` VALUES (1652215690939191298, '驻马店伤心故事集', '郑在欢', '“90后”作家郑在欢成名作修订再版，讲述用生命活出来的故事，那些老弱的、穷困的，病的、疯的、死的……21篇“非正常人类”的残酷故事，十一位畸零人的十一种活法，还有那些熟悉又陌生、既爱又恨、想要远离又难割舍的往事。他以不动神色的“狠意”刺透温情脉脉背后的残酷现实，以黑色幽默稀释悲喜人生。这些故事书写了以驻马店为代表的中原大地上的人和他们的生活，一种已经被遗忘和正在失去的现实，让我们体会到一种活生生的现实逻辑与古朴的情感表达，直面人性的弱与恶。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3526c0a1-5aa0-43e2-b20d-9c993dbf4ff9.jpg', '9787532183401', 8, 52.00, 1, '2023-04-29 15:38:12');
INSERT INTO `tb_book` VALUES (1652215692189093890, '拉丁姆（全二卷）', '[法] 罗曼·吕卡佐', '🌌当代法语科幻新高峰', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/46b0495d-00c9-412c-942c-2acd197783fc.jpg', '9787533969417', 5, 55.00, 1, '2023-04-29 15:38:12');
INSERT INTO `tb_book` VALUES (1652215693489328129, '豹', '[意]朱塞佩·托马西·迪·兰佩杜萨', '兰佩杜萨的代表作，意大利斯特雷加文学奖获奖作品。全书共八个章节，讲述了西西里一个古老贵族在资产阶级革命风暴中经历的衰亡史，它记录了一个时代的结束，见证了另一个时代的开启。小说以加里波第登陆马尔萨拉为切入点，展开了萨利纳家族在这一动荡时期发生的故事。除了社会变革这一条主线 外，书中还有一条支线，那就是爱情，从侧面展现了新旧两个阶级间互相利用、互相渗透的现象。它是一曲时代的伟大哀歌，叙事悠长、大气，语言生动诙谐，完美展现了那个波澜壮阔的时代，令人耳目一新。小说《豹》被改编成电影，获得戛纳电影节金棕榈奖，成为震惊影史的杰作。兰佩杜萨的一生是短暂的，但是，他留下的长篇小说《豹》却是不朽的。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e0aaa5ba-d706-43ed-9b02-94d5c7096a54.jpg', '9787020177554', 4, 59.00, 1, '2023-04-29 15:38:12');
INSERT INTO `tb_book` VALUES (1652215694454018050, '雪国', '[日] 川端康成', '🌲★川端康成最负盛名的代表作，日本近代文学史上抒情文学的巅峰，极致展现川端文学的美丽与哀愁、徒劳与虚无。彷徨迷茫的岛村，遇见热烈的驹子、纯情的叶子，一段错爱冲击了他们的世界，也带来了关乎生存的思考。一部影响莫言、余华、苏童等知名作家的杰作，多次被改编成影视剧，长盛不衰！ “川端康成是文学里无限柔软的象征。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/42869839-c846-41f2-989d-69c247dea464.jpg', '9787545822175', 1, 55.00, 1, '2023-04-29 15:38:13');
INSERT INTO `tb_book` VALUES (1652215695624228866, '小鸟', '[日] 小川洋子', '《小鸟》是日本作家小川洋子创作的长篇小说。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/9fc45dc4-cc6b-4f6d-b347-41b8a3d319be.jpg', '9787533970116', 14, 65.00, 1, '2023-04-29 15:38:13');
INSERT INTO `tb_book` VALUES (1652215697058680833, '需要我时打给我', '[美] 雷蒙德·卡佛', '★雷蒙德·卡佛杂文集，全面呈现文学大师的思悟与洞见。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b18ff9dc-e42b-4d89-bff8-821fcf91fbb0.jpg', '9787573503237', 3, 68.00, 1, '2023-04-29 15:38:13');
INSERT INTO `tb_book` VALUES (1652215698421829634, '西部往事：莱昂内谈电影', '[意]塞尔吉奥·莱昂内', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2145227d-6a3d-4a50-b2ff-6167bf35d49c.jpg', '9787521748925', 19, 78.00, 1, '2023-04-29 15:38:14');
INSERT INTO `tb_book` VALUES (1652215700183437314, '摇滚：一部历史，1920~1963', '[美]埃德·沃德（Ed Ward）', '骨灰级乐评人、摇滚历史学家埃德·沃德晚年力作', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/cc719fa3-4dc0-470c-a8c0-36364933782a.jpg', '9787520191562', 5, 98.00, 1, '2023-04-29 15:38:14');
INSERT INTO `tb_book` VALUES (1652215701286539266, '我们内心的冲突', '[美]卡伦·霍妮', '内容简介', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/883b002c-3b1f-4aa8-a8f3-da981b236e6d.jpg', '9787559469977', 3, 55.00, 1, '2023-04-29 15:38:14');
INSERT INTO `tb_book` VALUES (1652215702645493761, '诠释与过度诠释', '[意] 翁贝托·埃科', '* “不错，所有诠释者都是平等的，但有一些要比另一些更平等。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3caf7cce-55ca-483f-ba50-1ee79c2254ba.jpg', '9787532791279', 9, 69.00, 1, '2023-04-29 15:38:15');
INSERT INTO `tb_book` VALUES (1652215703882813441, '那个苹果也很好', '栾颖新', '东北女作家、90后历史学博士生栾颖新在巴黎六年的生活随感。因为旅行时看到被列为“瑕疵品”的苹果，作者展开了关于人生和自由的思考，由此写下这本书。本书由一些可爱、短小的篇章组成。栾颖新用轻松的语调和温暖的眼光，描绘着她所居住的巴黎，历数着在这座自由城市的收获。在此书中，她讨论的不止是一座城市，还有“我为何在巴黎”、“巴黎留学生涯”之外，更有“我在巴黎回望往昔”、“我从巴黎去往何处”。巴黎是文学的，也是生活的，每个人心中的巴黎都不同。在学术之外，颖新更愿意温柔地观察、真诚地记录生活。于她而言，巴黎是温柔之海，承托着她，让她自由地漂浮，做一只随心所欲的“苹果”。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/73af587f-372f-4b97-9eff-7902a3f49672.jpg', '9787572610035', 5, 59.80, 1, '2023-04-29 15:38:15');
INSERT INTO `tb_book` VALUES (1652215704784588801, '有效的安慰', '[法]克里斯托夫·安德烈', '安慰是同频的陪伴。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/593a468c-f854-4466-bb8f-f98e84dc403a.jpg', '9787115602695', 14, 59.80, 1, '2023-04-29 15:38:15');
INSERT INTO `tb_book` VALUES (1652215705963188226, '几何学的力量', '[美] 乔丹·艾伦伯格', '在科学、经济学、信息技术、金融学、公共卫生等领域的现实问题中，', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2455b05e-b66c-4e1c-a56b-d80f3d08a01d.jpg', '9787521752847', 17, 79.00, 1, '2023-04-29 15:38:15');
INSERT INTO `tb_book` VALUES (1652215707133399041, '旧金山海湾景象', '[波]切斯瓦夫·米沃什', '☆文学纪念碑53 诺贝尔文学奖得主米沃什反思人类文明之作，兼具回忆录与玄思性质的散文集，关照个体在历史中的限度与无助', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3f033843-1dfb-444f-b848-8f155a75e86b.jpg', '9787559857729', 14, 68.00, 1, '2023-04-29 15:38:16');
INSERT INTO `tb_book` VALUES (1652215708374913026, '生命之道', '[美]冯珠娣', '在西方，中医向来被认为带有神秘主义和迷信色彩，其效验有赖于人们的盲信。冯珠娣教授以她对于今日中国社会、知识界和医学圈的扎实深入的了解，在此做出了一番关于中医简明而精确的论述，回答了此前长久萦绕的一些本体论、认识论和伦理上的问题，试图超越生物医学和西方中心主义的局限，在科学与人文的交汇点上重新认识传统中医的价值。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d49abc57-cafa-4ad9-947a-8c2aedcd2902.jpg', '9787214278814', 3, 40.00, 1, '2023-04-29 15:38:16');
INSERT INTO `tb_book` VALUES (1652215710262349826, '电影分镜艺术典藏', '[英] 费奥纽拉·哈利根 编著', '全世界影迷的终极收藏品', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/eb35eb51-0d55-4344-aebd-49f5f023b1b5.jpg', '9787574002593', 3, 248.00, 1, '2023-04-29 15:38:16');
INSERT INTO `tb_book` VALUES (1652215711306731522, '无钱幸福', '[德]海德玛丽·施韦尔默', '“我想改变的不是人，而是这个围绕金钱打转的世界。”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c9aa158e-2700-4de7-ac52-45fca142cc14.jpg', '9787532791491', 8, 50.00, 1, '2023-04-29 15:38:17');
INSERT INTO `tb_book` VALUES (1652215712682463233, '打碎哈姆雷特的镜子', '[美]马文·卡尔森', '哈姆雷特有一句著名的话：“戏剧表演就像举起镜子观照自然。”但是与镜子里的映像不同的是，戏剧中的形象是由真实的物体构成的，其中最瞩目的就是人的身体，其在映像的世界之外是独立的存在。在整个西方戏剧史中，有一些时候隐藏在幻觉之后的现实被展示出来。近年来，欧洲和北美戏剧开始唤起人们对其作品中真实元素的关注，即表演者的直接呈现——他们并不创造角色，他们甚至不是演员，而是作为自己出现在舞台上；文本不是由剧作家创作的，而是来自现实生活：有时候演员和表演者（跟观众）共处于真实的环境，其中包含有他们可以直接接触到的真实元素。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/cc974ffd-f5e5-488f-ba89-5b655691b919.jpg', '9787305261657', 5, 30.00, 1, '2023-04-29 15:38:17');
INSERT INTO `tb_book` VALUES (1652215714116915201, '东京人（全两册）', '[日]川端康成', '诺贝尔文学奖得主、日本新感觉派文学大师川端康成——精选十卷集！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/0128ef67-7005-41bf-bc61-ea57bb2035a4.jpg', '9787515523859', 9, 136.00, 1, '2023-04-29 15:38:17');
INSERT INTO `tb_book` VALUES (1652215715433926657, '清代传统法秩序', '[日]寺田浩明', '日本著名法制史学者寺田浩明破解传统中国法之谜的扛鼎之作，以清代法为切入点，探索传统中国法秩序全貌的思想结晶。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1d3b2376-ad13-4c1c-930e-360fa00bf617.jpg', '9787559858092', 9, 98.00, 1, '2023-04-29 15:38:18');
INSERT INTO `tb_book` VALUES (1652215716985819138, '植物妻子', '[韩] 韩江', '◆诺贝尔文学奖热门候选，亚洲首位布克国际文学奖得主韩江短篇小说集。荣膺意大利马拉帕蒂文学奖、西班牙圣克莱门特文学奖、入选国际都柏林文学奖短名单、挪威未来图书馆项目年度作家。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a92ae95a-6e6a-4464-bb2f-339503de3305.jpg', '9787541165320', 10, 58.00, 1, '2023-04-29 15:38:18');
INSERT INTO `tb_book` VALUES (1652215718424465409, '孤岛的来访者', '[日] 方丈贵惠', '◎第29届鲇川哲也奖获奖作《时空旅行者的沙漏》系列第二弹', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f9f269d0-ea49-4443-9939-ba6a9c2ae7d0.jpg', '9787513351850', 19, 49.00, 1, '2023-04-29 15:38:18');
INSERT INTO `tb_book` VALUES (1652215719615647745, '大地中国', '韩茂莉', '◎ 编辑推荐', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/32690545-6ba0-45ec-bc2a-0d1fd9547114.jpg', '9787549639359', 5, 69.00, 1, '2023-04-29 15:38:19');
INSERT INTO `tb_book` VALUES (1652215721167540225, '鸟山明〇作剧场', '[日] 鸟山明', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/54b00a3a-38e1-4d39-adc6-e86f41a87eb3.jpg', '9787574001053', 1, 135.00, 1, '2023-04-29 15:38:19');
INSERT INTO `tb_book` VALUES (1652215722648129538, '屠海', '法利·莫厄特', '继《寂静的春天》之后重要的自然文学作品，记录16-20世纪超过50种北大西洋沿岸物种减少甚至灭绝的过程，掀起了世界范围内海洋生态保护的热潮。自由大地丛书', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/99ce1022-2240-4e8c-be5b-6e87a9f540e5.jpg', '9787559856746', 9, 98.00, 1, '2023-04-29 15:38:19');
INSERT INTO `tb_book` VALUES (1652215723822534657, '寂静连绵的山脉', '彭剑斌', '彭剑斌最新小说集。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/23eca804-c590-4ebe-a40a-923fa2cd065a.jpg', '9787532183425', 10, 59.00, 1, '2023-04-29 15:38:20');
INSERT INTO `tb_book` VALUES (1652215725454118914, '致命地图', '[英]桑德拉·亨佩尔', '【越是在我们以为安全的时候，病毒越可能卷土重来。】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7235a6ca-90c0-4984-992b-95108d92cbed.jpg', '9787559665225', 16, 55.00, 1, '2023-04-29 15:38:20');
INSERT INTO `tb_book` VALUES (1652215726573998082, '藕香零拾', '张旭东', '本书为“六合丛书”最新一种。本集以人物为纲，收录关于近现代知识分子的随笔三十余篇，包括陈寅恪、陈垣、傅斯年、吕思勉、顾颉刚、杨绛等。所涉主题如陈寅恪的“恪”字究竟怎么读、陈寅恪与傅斯年的交往、顾颉刚在五六十年代的经历，大多有趣味而有意义。大部分文章此前发表于《读书》《中华读书报》等报刊，亦有几篇为首次刊布。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2fe18139-7467-4235-ab01-ee5d901d0de9.jpg', '9787532185856', 2, 58.00, 1, '2023-04-29 15:38:20');
INSERT INTO `tb_book` VALUES (1652215728067170306, '知其所止', '潘星辉', '本书是作者在上海交大历史系开设课程“中国文化史”的基础上重新结构撰写而成。全书取用思想史的视角，采用“典籍选讲”的方式，按照先秦儒家、道家、道教、佛教、理学、明清之际思想及清代三教混融的次第，选钞《论语》《孟子》《庄子》《抱朴子内篇》《佛遗教经》《佛说阿弥陀经》《坛经》《朱子语类》《语录》《传习录》《明夷待访录》《阅微草堂笔记》的部分内容，以学术笔记的形式，分设若干条目进行讨论：一方面贴近文本，在具体的语义、范畴辨析中，呈现经典的主旨与原貌；另一方面，又把对作者、著述、相关思想的阐绎，放置在时代思想的宏观视域中加以考察，试图在“总体史”的关照下，打通“文”“史”界划，探索古代思想的内在紧张及外在局限，并由此窥见中西文明的根源性差异。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f1a4d553-fa70-4d42-9a39-dd30b405e0c3.jpg', '9787108075161', 1, 89.00, 1, '2023-04-29 15:38:21');
INSERT INTO `tb_book` VALUES (1652215729375793154, '家族血缘', '[法] 米歇尔·普西', '这是米歇尔·普西一本精彩的悬疑小说，以莫尔塞岛上两个犯人逃跑开篇，小说讲述了发生在岛上的一个关于宝藏的惊心动魄的故事：两个犯人逃跑后，警方追查，另一方面知晓宝藏秘密的让·雷米的儿子科林重返莫尔塞岛，试图解开父亲去世的谜团。案件交织，线索纠缠到一起，歹徒跨越十年的阴谋浮出水面——在科林继承土地之前得到宝藏。科林被歹徒欺骗并挟持去寻找宝藏，机智的科林一路上给同伴留下线索，在危急时分得救。真相最终揭开，所谓宝藏不过是一款优质葡萄酒和一片土地，但对科林来说，他找到了父亲留下来的满满的爱。小说情节跌宕起伏，双线叙事层层递进，引人入胜，跨越时间和生死的父子亲情极为感动人心，故事对于人性的揭示也让人深思。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1650e97c-f8fe-4a71-9f8d-200ab8a90839.jpg', '9787572602566', 4, 49.80, 1, '2023-04-29 15:38:21');
INSERT INTO `tb_book` VALUES (1652215731074486273, '剑桥第一次世界大战史', '【美】杰伊·温特 主编', '第3卷《民间社会》集中于描绘第一次世界大战期间，各个战场及后方的社会生活，并从整体上介绍战争期间各国的文化以及战争纪念活动。各章的作者从全球性的宏观视角出发，审视了战争期间的人口流动、家庭关系变迁、不同家庭成员的境遇、社会生产分工的变化、难民的产生和变动、病患及医疗技术的进展等等社会生活方面，此外，还从不同角度概述了各国的文化生活，包括战争期间的军人作家的活动，各国的宣传行为，电影、雕塑、悼念等活动。最后，本卷为三卷本做了总结，简要估算了第一次世界大战在各国造成的直接损失。当然，这场大战造成的间接损失，已经体现在各章具体的描绘之中。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6a8c4970-56c7-4bce-b826-ee8a5c0d147e.jpg', '9787308233781', 2, 248.00, 1, '2023-04-29 15:38:21');
INSERT INTO `tb_book` VALUES (1652215732248891394, '约翰生传', '[英] 詹姆斯·鲍斯威尔', '★ 英国文学史上传记头把交椅，华语世界首个全译本', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c2799df7-7892-4c82-a495-cd844fd56bee.jpg', '9787532783168', 17, 698.00, 1, '2023-04-29 15:38:22');
INSERT INTO `tb_book` VALUES (1652215733624623105, '玛丽·居里和她的女儿们', '[英] 伊莫金·格林伯格 著', '还原“居里夫人”独立的女性身份，见证她如何突破女性困境。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/366fe856-56ca-482c-98fc-c32f87254318.jpg', '9787556710409', 4, 68.00, 1, '2023-04-29 15:38:22');
INSERT INTO `tb_book` VALUES (1652215735113601026, '镜影', '[美] 布鲁斯·斯特林 编', '作为科幻文学的重要流派，赛博朋克的出现可谓是给日渐消沉的新浪潮科幻一记重击，革新了科幻文学史。赛博朋克回归到硬科幻风格，以“赛博朋克掌门人”威廉•吉布森、帕特•卡迪根、布鲁斯•斯特林、鲁迪•拉克、约翰•雪利、刘易斯•夏纳为杰出代表，形成了一场势不可挡的文学运动。它汇集八十年代的文化浪潮，成为一种融合了技术与音乐、迷幻剂、时尚等等的独特亚文化。之后赛博朋克文化延伸到整个社会，是此前各种科幻流派都没有出现过的现象。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/83ab54dd-11bb-4d7d-8152-da9510bd3750.jpg', '9787569947038', 6, 59.00, 1, '2023-04-29 15:38:22');
INSERT INTO `tb_book` VALUES (1652215736615161857, '驶向现代性', '张珺', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/cd0fb09e-fefe-4f9a-bc3e-63f711134412.jpg', '9787218162621', 14, 78.00, 1, '2023-04-29 15:38:23');
INSERT INTO `tb_book` VALUES (1652215738062196737, '摇滚：一部历史', '[美]埃德·沃德（Ed Ward）', '毒舌乐评人、摇滚历史学家埃德·沃德经典续作！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/2db50232-db35-42cc-8d0e-501d075a7225.jpg', '9787522808833', 17, 55.00, 1, '2023-04-29 15:38:23');
INSERT INTO `tb_book` VALUES (1652215739286933505, '伊莎贝拉', '[英] 约翰·济慈', '后浪插图经典系列，名家名译名画', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a6f546dc-dfa5-4307-be3e-aabbf63c6674.jpg', '9787559465191', 19, 98.00, 1, '2023-04-29 15:38:23');
INSERT INTO `tb_book` VALUES (1652215740658470914, '愤怒与宽恕', '[美]玛莎·C.努斯鲍姆', '许多人认为，如果面对不公不感到愤怒，就没有足够的动力去追求公正。没有愤怒，个人就不可能维护自身尊严，也不可能在受到伤害后找到真正的平静。 本书考察了在亲密关系、日常休闲、工作场所、刑事司法系统和社会变革运动中的愤怒，指出愤怒的核心观念既幼稚又有害。本书认为，从宽恕而来的“慷慨”精神，结合公正的社会福利制度，才是应对伤害的最佳途径。本书对“愤怒”和“宽恕”的深刻洞察和博学观点为解决个人问题和政治事件都提供了全新的视角。本书源自她2014年在牛津大学开设的哲学讲座。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/eda45ac7-1aeb-4b28-81a7-e1ab965ffe0f.jpg', '9787300312248', 14, 128.00, 1, '2023-04-29 15:38:24');
INSERT INTO `tb_book` VALUES (1652215742017425409, '依然如旧的月色', '茅海建', '本书初版于2014年。由几类文章汇编而成，长短不拘，比较轻松可读。增订本的部分篇目做了调整，有增有删。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c2b29c26-30c4-4212-a594-67353d9a7a3e.jpg', '9787108074607', 2, 79.00, 1, '2023-04-29 15:38:24');
INSERT INTO `tb_book` VALUES (1652215743267328002, '长空飞渡', '[英] 马克·凡霍纳克', '◎ 编辑推荐', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/5b1f1461-16b2-4fdc-810c-3e8e642a0f34.jpg', '9787522513362', 12, 68.00, 1, '2023-04-29 15:38:24');
INSERT INTO `tb_book` VALUES (1652215744378818562, '巡礼之年 （全三册）', '周志文', '【名人推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ca0a8962-ddf8-4bce-8591-54261987f020.jpg', '9787552040432', 6, 108.00, 1, '2023-04-29 15:38:25');
INSERT INTO `tb_book` VALUES (1652215745557417985, '寻找自己名字的猫', '[日] 竹下文子  著', '日本绘本4冠王，MOE绘本书店大奖、讲谈社绘本奖、LIBRO绘本大奖、未来屋绘本大奖。日本亚马逊绘本畅销榜第1名（2021.05）。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/f2b2837c-f058-4e3a-8bb8-074fa10ac81b.jpg', '9787511060976', 8, 49.80, 1, '2023-04-29 15:38:25');
INSERT INTO `tb_book` VALUES (1652215746668908546, '床，沙发，我的人生', '[法] 罗曼·莫内里', '※如果无所事事让你感到快乐，那就不算是虚度人生。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6ff52de2-e6e9-4c1b-96c7-fbd89b0fcd15.jpg', '9787521752113', 12, 49.80, 1, '2023-04-29 15:38:25');
INSERT INTO `tb_book` VALUES (1652215747763621889, '生而为人', '毕啸南', '《生而为人》收录九篇人性小说，在横跨百年的胶东大地上，游走着众多故事角色：消失的女儿、疯癫的木匠、绝恋的理发师……他们深陷在时代的逆境里，被生活的风浪所裹挟，饱含着生命的沧桑与情感，去接受命运的审判。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c4a0cbbf-5939-4e6e-803f-988c4ee3a6ae.jpg', '9787572610332', 4, 49.80, 1, '2023-04-29 15:38:25');
INSERT INTO `tb_book` VALUES (1652215749307125762, '天体嗜好症', '[日] 稻垣足穗', '现今最先端的文学其实写于半世纪前？作者是来自未来的外星人？你有过“影像以超越文字的速度撞进眼睑”的阅读体验吗？被日本人被视为与谷崎润一郎、川端康成、三岛由纪夫同等才华的作家稻垣足穗“宇宙乡愁”代表作大结集！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/9d81badd-034d-4b7a-b869-f953405ca454.jpg', '9787572609657', 12, 88.00, 1, '2023-04-29 15:38:26');
INSERT INTO `tb_book` VALUES (1652215750225678337, '妙不可言', '【美】邦妮·加莫斯', '孤绝冷静的伊丽莎白·佐特一直在反击“世界的偏见”。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1e2135dd-4c96-4175-a3ae-36db6c2ca596.jpg', '9787559665713', 12, 68.00, 1, '2023-04-29 15:38:26');
INSERT INTO `tb_book` VALUES (1652215751332974594, '波士顿的长电邮', '刘小枫', '本书是哲学学者刘小枫最新的著作，以文学的笔触展开，探讨中美两个国家之间文化政治差异的诸多问题。作者对中美两国不同的政治体制作出阐释，从历史和哲学的深层角度分析了美国的立国政策和国家政制。美利坚立国三百年，缘何在国内、国际政治中矛盾重重、四处埋雷，美国式的理念的本质为什么是通过帝国的扩张来实现“普世性”的诉求，刘小枫这本书的写作目的即是通过回顾美国建国以来的外交政策和美国政体的底色，从学理的意义上深层揭示美国政制的精神品质和文明底色。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/68ed6cd7-79fb-43cf-9325-3f46ea881c1f.jpg', '9787208180444', 7, 68.00, 1, '2023-04-29 15:38:26');
INSERT INTO `tb_book` VALUES (1652215752624820226, '久保田博二：摄影家', '[日] 久保田博二', '🎬玛格南大师、环球旅行者久保田博二的摄影生涯总结巨著，数百张照片，跨越亚欧大陆，长达万字的访谈，为我们提供看世界的多样可能性角度。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a0681f5f-3cda-40fa-b1ea-b49cf2b72511.jpg', '9787559664853', 15, 498.00, 1, '2023-04-29 15:38:27');
INSERT INTO `tb_book` VALUES (1652215753870528514, '日本文化核心', '[日]松冈正刚', '“日本文化”是松冈正刚常年以来都在深耕的课题，作为当代日本文化研究的领军人物、编辑泰斗，松冈先生常年参与多种文化领域的观察、实践经验，也让他能够以多视角、多层次，去拆解日本文化的演变路径。本书正是“松冈日本论”的集大成之作。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/99cba441-603d-4a2b-a3aa-ea0a623e1588.jpg', '9787553817002', 4, 68.00, 1, '2023-04-29 15:38:27');
INSERT INTO `tb_book` VALUES (1652215755065905154, '万物有文', '[法]莫里斯·皮亚尔-韦纳伊', '在时间洪流中沉淀百年的植物与工艺之书', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d9487ff3-7847-4dcf-9639-7cfd6f8ecf6f.jpg', '9787559472533', 1, 178.00, 1, '2023-04-29 15:38:27');
INSERT INTO `tb_book` VALUES (1652215756504551425, '生命的时间轴', '[英] 尼古拉斯·莫尼(Nicholas P. Money)', '⭐诗意，简洁，宏大，富有洞见⭐', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/ae090106-f872-45e0-bf6e-e6ddf933534d.jpg', '9787521751963', 13, 59.00, 1, '2023-04-29 15:38:27');
INSERT INTO `tb_book` VALUES (1652215757477629954, '现代数学的概念', '[英] 伊恩·斯图尔特', '在这本引人入胜的科普经典中，著名英国数学家斯图尔特用清晰流畅、幽默风趣的语言阐明了群、集合、子集、拓扑、布尔代数等“新数学”的基本概念，他认为理解这些概念是把握数学真正本质的最佳途径。此外，作者还对函数、对称、公理学、计数、拓扑学、超空间、线性代数、实分析、概率论、计算机、现代数学的应用等主题作了发人深省的讨论。读者无需任何高等数学背景，只需对代数、几何和三角学略知一二，便可读懂本书的大部分内容。读罢此书，你会更清楚地理解现代数学家对图形、函数和公式的看法，以及“新数学”的基本思想如何有助于领会数学的本质。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/95411cc1-fe26-4832-a9b2-3cd5f62c04ab.jpg', '9787100200219', 3, 88.00, 1, '2023-04-29 15:38:28');
INSERT INTO `tb_book` VALUES (1652215758522011650, '妈！这是我的人生', '王食欲', '什么是好的教育？这是个很难给出标准答案的问题。 但肯定不是“狂热”地激励孩子，本书以母女二人的亲身经历为蓝本，以双视角的创作方式，探讨教育的本质。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/63e616dc-3c9d-43df-bd79-3ef9e438bd1a.jpg', '9787115601063', 15, 69.80, 1, '2023-04-29 15:38:28');
INSERT INTO `tb_book` VALUES (1652215760489140225, '松弛感', '[日] 加藤谛三', '是时候卸下心灵的盔甲了！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6da82d37-640d-4d57-9f31-d2313fb7534d.jpg', '9787115610805', 4, 59.80, 1, '2023-04-29 15:38:28');
INSERT INTO `tb_book` VALUES (1652215761911009282, '雨中喷泉', '[日]三岛由纪夫', '为什么要火烧《金阁寺》？三岛美与暴烈之外的其他面目', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6038c9d7-f57d-4452-ad44-1e107cf47b04.jpg', '9787521752137', 13, 49.00, 1, '2023-04-29 15:38:29');
INSERT INTO `tb_book` VALUES (1652215762888282114, '把悲伤留给电影', '陈德森 口述', '成龙作序，深情推荐', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/993f8e89-cabd-489f-8035-ccb6a240051f.jpg', '9787542679222', 18, 68.00, 1, '2023-04-29 15:38:29');
INSERT INTO `tb_book` VALUES (1652215763857166337, '博士学位论文：我们这样写', '李修建', '《博士学位论文：我们这样写》精选23位于近年内攻读完博士学位的学者的博士论文写作经验，这些学者来自北京大学、中国人民大学、北京师范大学、南京大学、中国艺术研究院等高校和科研单位，结合自身专业如文学、哲学、舞蹈学等和经验，谈论了博士论文写作的方法、遇到的困境与难题、个人感受和经验等。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/dedb52c9-c560-403e-8e99-af782b1e6b73.jpg', '9787503973901', 0, 78.00, 1, '2023-04-29 15:38:29');
INSERT INTO `tb_book` VALUES (1652215764901548034, '厌女', '[日]上野千鹤子', '全新增订！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/15b87249-d3bb-43ec-bead-8ae5721b279b.jpg', '9787545219753', 4, 66.00, 1, '2023-04-29 15:38:29');
INSERT INTO `tb_book` VALUES (1652215766134673410, '成瘾', '[美]安娜·伦布克', '有些东西，你为什么总是戒不掉？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/69900824-155a-43db-a3c3-5f9f4e1474b6.jpg', '9787513350068', 8, 59.00, 1, '2023-04-29 15:38:30');
INSERT INTO `tb_book` VALUES (1652215767241969665, '亨丽埃塔与那场将人类学送上审判席的谋杀案', '[美]吉尔·施梅勒', '2017年度西南图书 （A Southwest Book of the Year 2017）', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8eeebe80-ca9a-458c-b47f-2d7a1be02421.jpg', '9787576027785', 2, 79.80, 1, '2023-04-29 15:38:30');
INSERT INTO `tb_book` VALUES (1652215768483483650, '犬儒主义', '[英]安斯加·艾伦', '继《虚无主义》后，“交界译丛”又一力作！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d80088a3-cc92-4b4c-aaad-7ea76389eafd.jpg', '9787100215220', 18, 75.00, 1, '2023-04-29 15:38:30');
INSERT INTO `tb_book` VALUES (1652215770098290690, '谁是约翰·列侬', '[英]莱斯莉-安·琼斯', '如今是否还需要另一本关于约翰·列侬的书？是的，如果它是由莱斯莉-安·琼斯写的。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/797985ab-35b5-4acb-9f46-b138a27f1919.jpg', '9787559665492', 19, 128.00, 1, '2023-04-29 15:38:31');
INSERT INTO `tb_book` VALUES (1652215771197198337, '巫师与先知', '[美]查尔斯·C·曼恩', '当2050年到来时，地球预计将会有超过一百亿人口。这意味著将有一百亿张嘴需要吃饱，一百亿所可以居住的地方，两百亿双脚需要鞋子穿。但地球真的装得下那么多东西吗？就算装得下，我们究竟要花掉多少资源来满足这一百亿人？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e2ea2506-f96a-41da-bcbe-98d7c558daa3.jpg', '9787521750300', 14, 118.00, 1, '2023-04-29 15:38:31');
INSERT INTO `tb_book` VALUES (1652215772304494593, '爱尔兰神话全书', '[爱尔兰] 玛丽·希尼', '🔱「世界神话全书」之爱尔兰卷', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/97768c09-6de9-45f1-b5a3-51cedc4b1006.jpg', '9787572604195', 4, 68.00, 1, '2023-04-29 15:38:31');
INSERT INTO `tb_book` VALUES (1652215773277573122, '战时笔记和其他', '[法]玛格丽特·杜拉斯', '★我希望在广阔的原野上向前行走，并能自由地思想。★', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b9b6f4e9-396e-41cf-b216-f85ef261c966.jpg', '9787521748697', 2, 69.00, 1, '2023-04-29 15:38:31');
INSERT INTO `tb_book` VALUES (1652215774712025090, '缔造的“中兴”', '王晨', '“圣君贤相”缔造的“盛世”', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d40b3ddb-b04b-4264-9df3-488fe9fd6d01.jpg', '9787552040876', 7, 78.00, 1, '2023-04-29 15:38:32');
INSERT INTO `tb_book` VALUES (1652215775957733378, '玻璃塔谜案', '[日] 知念实希人', '一座玻璃塔在雪地深处的森林中闪闪发光。地上十一层，地下一层，它是一个美丽而巨大的尖顶塔。在热爱推理小说的馆主的邀请下，刑警、医生、小说家、编辑、名侦探等各具特色的嘉宾聚集到玻璃塔中。当晚，馆主预定发表重大消息，但在那之前却在密室里被杀了。第二天，玻璃塔因附近山上的雪崩而关闭了交通和通信。被困在塔里的人们，等待警察的帮助，却接二连三地发生了密室杀人事件。此外，还有人用血写下了十三年前的一件疑案。名侦探碧月夜和医生一条游马追查着谜团……', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8b59dd09-0570-41c0-a2b9-67263c8306d8.jpg', '9787541165368', 14, 68.00, 1, '2023-04-29 15:38:32');
INSERT INTO `tb_book` VALUES (1652215776876285953, '孝尽', '[韩国] 郑世朗', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/e10a6fec-d7d6-44cb-a942-a878314e54a3.jpg', '9787521753431', 10, 59.80, 1, '2023-04-29 15:38:32');
INSERT INTO `tb_book` VALUES (1652215778692419585, '儒学的近代转型', '干春松', '儒学研究名家、北京大学教授干春松精思之作。梳理西学东渐下儒学的困境和转型，探究新时代儒学的发展和未来。大学问出品', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/33e51f1b-1965-4f3a-9eb2-93fab645e40f.jpg', '9787559854421', 10, 88.00, 1, '2023-04-29 15:38:33');
INSERT INTO `tb_book` VALUES (1652215779657109505, '女性的力量', '[刚果] 德尼·穆奎格', '鼓舞我一直做下去的，是女性展现的力量。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/3bcd937a-6805-473d-8511-f3e0f230ef58.jpg', '9787556130979', 6, 55.00, 1, '2023-04-29 15:38:33');
INSERT INTO `tb_book` VALUES (1652215781162864642, '天赐之日', '[荷兰] 高罗佩', '当亨德里克斯将热水倒进茶壶，富士山顶的冰雪融化了。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/018e4b76-449b-4533-b08f-56f18de9e157.jpg', '9787532791422', 18, 45.00, 1, '2023-04-29 15:38:33');
INSERT INTO `tb_book` VALUES (1652215782203052034, '英雄的习性', '[英] 伯纳德·M.W.诺克斯', '古希腊悲剧诗人索福克勒斯善于通过描写身处困境的英雄不同寻常的言行表现人的神性与神人关系，其笔下的英雄终生与人类的局限抗争，他们的举止由非凡的习性驱使，其命运也因此习性注定，尽管这有时使他们经历苦难、走向辉煌，但更多的时候则沦落深渊。然而，英雄为自己决断，为决断负责，即使失败，也是别样的成功；就连神也会由此明白人的伟大，并愿与之并肩同行。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/c80eb916-c9f8-4a58-a207-297a033942f0.jpg', '9787108074577', 13, 68.00, 1, '2023-04-29 15:38:34');
INSERT INTO `tb_book` VALUES (1652215783440371714, '克苏鲁宇宙诺顿注释本', '[美] H.P.洛夫克拉夫特', '“20世纪经典恐怖小说最杰出的先行者”，他的作品终于有了注释本。我还以为要等到天荒地老呢！', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7633821a-3336-4475-bc94-2bd2f339f0aa.jpg', '9787572609817', 13, 219.00, 1, '2023-04-29 15:38:34');
INSERT INTO `tb_book` VALUES (1652215784610582529, '中国的历史之路', '[英]伊懋可', '伊懋可在《中国的历史之路》（或译《中国历史的模式》，The Pattern of the Chinese Past，1973）一书中主要围绕三个问题展开论述：中华帝国为何能长期维持统一而没有像罗马帝国那样最终分裂？中国如何执中世纪各国经济之牛耳，并成为当时人口最多的国家？古代中国如何在科学和技术方面从落后于欧洲到处于领先地位，但是在1600—1700 年又远远落后于欧洲大多数地方？作者提出了“高水平平衡陷阱”理论。他认为传统后期的中国经济发展已达到没有工业-科学投入情况下的极限，随着人口持续增长，农业剩余产品递减，人均收入和人均需求下降；劳动力越来越便宜，而资源和资本越来越昂贵；耕作与运输技术已高度完善，难以再做简单的改进。当新技术出现的时候，已经没有足够多的剩余来购买新技术，而且这种所谓高水平的发展也抑制了对新技术发明的需求。伊懋可认为“高水平平衡...', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/0ac5879a-a3d2-4135-8e7a-8250685ef239.jpg', '9787308233125', 4, 98.00, 1, '2023-04-29 15:38:34');
INSERT INTO `tb_book` VALUES (1652215785965342722, '北宋中期儒学复兴运动', '刘复生', '唐宋之际，中国社会发生了巨大变化，反映在思想文化领域则是肇端于中唐而兴盛于北宋中期的儒学复兴运动。新儒学者鄙弃传统经生拘守注疏的习气，将眼光心思转向对儒经义理的探索，以用之于社会实践。新儒以复兴周孔之道、致君尧舜之业为旗帜，在各个领域掀起了复兴儒学的热潮，波属云委，终于走向兴盛的坦途。本书力图从不同侧面揭示这一跌宕起伏的动态过程，展现变革时代中的壮阔画卷。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/5162783d-f319-47be-be32-672d2b426308.jpg', '9787108075406', 11, 66.00, 1, '2023-04-29 15:38:34');
INSERT INTO `tb_book` VALUES (1652215787831808001, '珍珠在蒙古帝国', '[美]托马斯·爱尔森', '1221年，在今天的土库曼斯坦，一个被蒙古士兵俘获的女人，声称为保护自己的珍珠而将它们吞下。她立即被处决，为了找到几颗珍珠，成吉思汗命令士兵们对死在战场上的人开膛破肚。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/d27baef7-48f7-4168-968e-5c5f6d0ef2a6.jpg', '9787208179004', 9, 69.80, 1, '2023-04-29 15:38:35');
INSERT INTO `tb_book` VALUES (1652215789077516289, '殷商六百年', '柯胜雨', '商族与玄鸟有怎样的不解之缘？成汤不敢废夏社反映了什么样的文化传承？挂甲出征的妇好有怎样显赫的功绩？贞人群体在殷商时代发挥了什么作用？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6febcc5e-560d-4931-b034-349a77532885.jpg', '9787545572698', 10, 55.00, 1, '2023-04-29 15:38:35');
INSERT INTO `tb_book` VALUES (1652215790247727106, '寻纸', '汪帆', '【编辑推荐】', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/9d68e96f-a476-417d-b9df-380ac4b381b1.jpg', '9787534096044', 12, 128.00, 1, '2023-04-29 15:38:35');
INSERT INTO `tb_book` VALUES (1652215791896088578, '枢纽', '施展', '本书核心是要回答一个问题，究竟“何谓中国”。施展在这本书中以问题为导向，超越具体学科的限制，调用地理、历史、哲学、思想、经济、财政、货币、军事、人口、社会、法律、国际政治等各个学科领域的知识，对中国几千年的复杂历史，以及百年来的成败兴衰给出了一个统一的解释框架。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/7136cf45-9961-4f26-8f3d-30b6feb3d63d.jpg', '9787572610059', 6, 128.00, 1, '2023-04-29 15:38:36');
INSERT INTO `tb_book` VALUES (1652215793066299394, '四十而惑', '[美] 艾达·卡尔霍恩', '当艾达·卡尔霍恩发现自己处于中年危机中时，她认为自己没有权利抱怨。她已经结婚成家，也有不错的事业。为什么仍会感到难过？为什么其他X世代（指出生于1965—1980年间的人）的女性似乎也有同样的痛苦？', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/b594ad7d-8454-45eb-a6ac-0eaccc0a7657.jpg', '9787553526829', 14, 68.00, 1, '2023-04-29 15:38:36');
INSERT INTO `tb_book` VALUES (1652215794685300738, '大清宪法案', '[日]北鬼三郎', '为什么一位日本学者下定决心要给衰朽的中华帝国写一部宪法？本书从法律文本出发，呈现一位日本学者对晚清中国宪制的全面思考，从法制层面剖析晚清中国动荡不安的历史剧变。大学问出品', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/6b2b7ef7-e8ca-4de4-bf5a-f68f041cd733.jpg', '9787559856470', 4, 88.00, 1, '2023-04-29 15:38:36');
INSERT INTO `tb_book` VALUES (1652215795989729282, '古代中世纪哲学十五讲', '吴天岳', '本书以吴天岳教授在北大讲授多年的西方哲学课程为基础，分十五讲展示了从前苏格拉底到中世纪盛期2000年间西方哲学发展的整体面貌。上册八讲，梳理了从古希腊哲学开端至普罗提诺的哲学史发展线索；下册七讲，梳理了从奥古斯丁至近代哲学之前的中世纪哲学传统。', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/70148857-47aa-4a2e-b52f-97fe024fb8e9.jpg', '9787301337356', 15, 268.00, 1, '2023-04-29 15:38:37');

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
INSERT INTO `tb_book_type` VALUES (1650778127548284930, 1650778101220638721, 1650536470122876929);
INSERT INTO `tb_book_type` VALUES (1652216025187454978, 1652215537092120577, 1650536508257488898);
INSERT INTO `tb_book_type` VALUES (1654035379453542402, 1652215533690540034, 1654035214831304706);
INSERT INTO `tb_book_type` VALUES (1654035424269680642, 1652215542599241729, 1654035214831304706);
INSERT INTO `tb_book_type` VALUES (1654035450970619905, 1652215543899475970, 1654035280895787009);
INSERT INTO `tb_book_type` VALUES (1654035502938046466, 1652215548265746433, 1654035214831304706);
INSERT INTO `tb_book_type` VALUES (1654035538891620353, 1652215546164400129, 1654035214831304706);
INSERT INTO `tb_book_type` VALUES (1654035568784424962, 1652215557476438017, 1654035280895787009);

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
INSERT INTO `tb_car` VALUES (1652584994259984386, 1650431847022227457, 1652215538585292802, 3);
INSERT INTO `tb_car` VALUES (1652585017815195650, 1650431847022227457, 1652215539877138434, 4);
INSERT INTO `tb_car` VALUES (1652587198920384514, 1650431847022227457, 1652215542599241729, 3);
INSERT INTO `tb_car` VALUES (1652589955941261313, 1650431847022227457, 1652215537092120577, 2);
INSERT INTO `tb_car` VALUES (1652596819806420994, 1650431847022227457, 1652215533690540034, 1);
INSERT INTO `tb_car` VALUES (1654339156677013506, 1650534268394385410, 1652215533690540034, 2);
INSERT INTO `tb_car` VALUES (1654339179590496258, 1650534268394385410, 1652215539877138434, 4);

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
INSERT INTO `tb_comment` VALUES (1, 0, 1650431847022227457, 1652215535649280002, '这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊', '2023-04-30 18:36:21', 1);
INSERT INTO `tb_comment` VALUES (2, 1, 1650431847022227457, 1652215535649280002, '条评论啊这是一条评论啊这是一条评论', '2023-05-03 12:49:26', 1);
INSERT INTO `tb_comment` VALUES (3, 1, 1650431847022227457, 1652215535649280002, '1	0	1650431847022227457	1652215535649280002	这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊这是一条评论啊', '2023-05-03 12:49:52', 1);
INSERT INTO `tb_comment` VALUES (1653670377949347842, 0, 1650431847022227457, 1652215795989729282, '你好啊', '2023-05-03 15:58:32', 1);
INSERT INTO `tb_comment` VALUES (1653670484560166914, 0, 1650431847022227457, 1652215795989729282, '你哈啊啊', '2023-05-03 15:59:02', 1);
INSERT INTO `tb_comment` VALUES (1653682566739378178, 0, 1650431847022227457, 1652215535649280002, '你好啊', '2023-05-03 16:47:03', 1);
INSERT INTO `tb_comment` VALUES (1653682642144575490, 1653682566739378178, 1650431847022227457, 1652215535649280002, '回复给你的', '2023-05-03 16:47:20', 1);
INSERT INTO `tb_comment` VALUES (1653682719491735554, 0, 1650431847022227457, 1652215535649280002, '普通评论', '2023-05-03 16:47:39', 1);
INSERT INTO `tb_comment` VALUES (1653682781538074625, 0, 1650431847022227457, 1652215535649280002, '普通评论', '2023-05-03 16:47:54', 1);
INSERT INTO `tb_comment` VALUES (1653682815117672450, 0, 1650431847022227457, 1652215535649280002, '我是评论', '2023-05-03 16:48:02', 1);
INSERT INTO `tb_comment` VALUES (1653684814760493058, 1653682815117672450, 1650431847022227457, 1652215535649280002, '你好啊', '2023-05-03 16:55:58', 1);
INSERT INTO `tb_comment` VALUES (1653687305300459521, 0, 1650431847022227457, 1652215543899475970, '评论', '2023-05-03 17:05:52', 1);
INSERT INTO `tb_comment` VALUES (1653687373873135618, 0, 1650431847022227457, 1652215535649280002, '我是评论', '2023-05-03 17:06:09', 1);
INSERT INTO `tb_comment` VALUES (1653687390440636417, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:13', 1);
INSERT INTO `tb_comment` VALUES (1653687399382892546, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:15', 1);
INSERT INTO `tb_comment` VALUES (1653687405120700418, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:16', 1);
INSERT INTO `tb_comment` VALUES (1653687410007064578, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:17', 1);
INSERT INTO `tb_comment` VALUES (1653687415325442049, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:19', 1);
INSERT INTO `tb_comment` VALUES (1653687420677373954, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:20', 0);
INSERT INTO `tb_comment` VALUES (1653687425840562177, 0, 1650431847022227457, 1652215535649280002, 'zs 我是评论', '2023-05-03 17:06:21', 1);
INSERT INTO `tb_comment` VALUES (1653690670604468225, 0, 1650431847022227457, 1652215537092120577, '你好', '2023-05-03 17:19:15', 0);
INSERT INTO `tb_comment` VALUES (1653707400592576513, 0, 1650431847022227457, 1652215541240287234, '你好啊', '2023-05-03 18:25:43', 0);
INSERT INTO `tb_comment` VALUES (1653708009295138818, 0, 1650431847022227457, 1652215533690540034, '我是评论', '2023-05-03 18:28:08', 0);
INSERT INTO `tb_comment` VALUES (1653708036860104706, 1653708009295138818, 1650431847022227457, 1652215533690540034, '你好啊', '2023-05-03 18:28:15', 1);
INSERT INTO `tb_comment` VALUES (1654129759585591297, 0, 1650431847022227457, 1652215794685300738, '牛皮', '2023-05-04 22:24:02', 1);
INSERT INTO `tb_comment` VALUES (1654339490220650498, 0, 1650534268394385410, 1652215539877138434, '这是我李四的评论', '2023-05-05 12:17:25', 1);

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
  `buy_date` datetime NULL DEFAULT NULL COMMENT '购买时间',
  `status` tinyint NOT NULL COMMENT '0：未付款，1：已付款',
  `order_num` int NOT NULL COMMENT '购买数量',
  `alipay_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付宝交易编号',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (1653696123560357889, 59.00, 1652215539877138434, 1650431847022227457, '2023-05-03 17:40:55', '2023-05-03 17:41:22', 1, 1, '2023050322001444230505823715');
INSERT INTO `tb_order` VALUES (1653707413846577154, 204.00, 1652215541240287234, 1650431847022227457, '2023-05-03 18:25:47', NULL, 0, 3, NULL);
INSERT INTO `tb_order` VALUES (1653707815174361089, 204.00, 1652215546164400129, 1650431847022227457, '2023-05-03 18:27:22', '2023-05-03 18:27:49', 1, 3, '2023050322001444230505823413');
INSERT INTO `tb_order` VALUES (1653973533438541826, 90.00, 1652215533690540034, 1650431847022227457, '2023-05-04 12:03:14', NULL, 0, 1, NULL);
INSERT INTO `tb_order` VALUES (1654339182539091969, 236.00, 1652215539877138434, 1650534268394385410, '2023-05-05 12:16:12', '2023-05-05 12:16:50', 1, 4, '2023050522001444230505824250');

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
INSERT INTO `tb_type` VALUES (1650536470122876929, '科普');
INSERT INTO `tb_type` VALUES (1650536508257488898, '童话');
INSERT INTO `tb_type` VALUES (1654035214831304706, '文学');
INSERT INTO `tb_type` VALUES (1654035234045411330, '历史');
INSERT INTO `tb_type` VALUES (1654035280895787009, '人物事迹');

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
  `user_type` tinyint NOT NULL COMMENT '0：普通用户，1：管理员',
  `add_date` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'PjWiyHKr/DZbjoqA3zaaIA==', '13288888889', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/00336409-27d9-4477-9293-2d1b2e87f4ad.png', 1, 1, '2023-04-23 17:40:52');
INSERT INTO `tb_user` VALUES (1650431847022227457, 'zs', 'qWhRg8NHflZ2fZVW4RrjEw==', '18296642346', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/513fc417-2a54-4d03-a0b5-843d979763e0.png', 1, 0, '2023-04-24 17:29:51');
INSERT INTO `tb_user` VALUES (1650534268394385410, '李四', 'PjWiyHKr/DZbjoqA3zaaIA==', '13207961243', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/1a622f6a-52f8-40a0-aa9f-8ca2b67589fb.png', 1, 0, '2023-04-25 00:16:50');
INSERT INTO `tb_user` VALUES (1650757232125595650, '王五', 'PjWiyHKr/DZbjoqA3zaaIA==', '13434567890', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/a84587c2-d04d-491d-8d59-aee2780ce39f.png', 1, 0, '2023-04-25 15:02:48');
INSERT INTO `tb_user` VALUES (1654354345975717890, 'fei', 'PjWiyHKr/DZbjoqA3zaaIA==', '18288888888', 'http://car-system-2022.oss-cn-hangzhou.aliyuncs.com/wang-book/images/8887c69b-63c1-447f-8bc3-24006f72ca01.png', 1, 0, '2023-05-05 13:16:27');

SET FOREIGN_KEY_CHECKS = 1;
