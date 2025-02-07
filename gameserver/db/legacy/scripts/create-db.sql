-- ZONELIST
DROP TABLE IF EXISTS ZONELIST;
CREATE TABLE ZONELIST (
	ID VARCHAR(5) NOT NULL,
	ORIG_X DECIMAL(65535,32767) DEFAULT 0 NOT NULL,
	ORIG_Y DECIMAL(65535,32767) DEFAULT 0 NOT NULL,
	ORIG_Z DECIMAL(65535,32767) DEFAULT 0 NOT NULL,
	"SIZE" DECIMAL(65535,32767) DEFAULT 624.153 NOT NULL
);
CREATE INDEX ZONELIST_ID_IDX ON ZONELIST (ID);

-- ARMORS
DROP TABLE IF EXISTS ARMOR;
CREATE TABLE ARMOR (
  item_id DECIMAL(11, 0) NOT NULL,
  name VARCHAR(70),
  bodypart VARCHAR(15) NOT NULL DEFAULT '',
  crystallizable VARCHAR(5) NOT NULL DEFAULT '',
  armor_type VARCHAR(5) NOT NULL DEFAULT '',
  weight DECIMAL(5, 0) NOT NULL DEFAULT 0,
  material VARCHAR(15) NOT NULL DEFAULT '',
  crystal_type VARCHAR(4) NOT NULL DEFAULT '',
  avoid_modify DECIMAL(1, 0) NOT NULL DEFAULT 0,
  duration DECIMAL(3, 0) NOT NULL DEFAULT 0,
  p_def DECIMAL(3, 0) NOT NULL DEFAULT 0,
  m_def DECIMAL(2, 0) NOT NULL DEFAULT 0,
  mp_bonus DECIMAL(3, 0) NOT NULL DEFAULT 0,
  price DECIMAL(11, 0) NOT NULL DEFAULT 0,
  crystal_count DECIMAL(4, 0),
  sellable VARCHAR(5),
  dropable VARCHAR(5),
  destroyable VARCHAR(5),
  tradeable VARCHAR(5),
  item_skill_id decimal(11,0) NOT NULL default '0',
  item_skill_lvl decimal(11,0) NOT NULL default '0',
  PRIMARY KEY (item_id)
);

 -- CHAR TEMPLATE
DROP TABLE IF EXISTS CHAR_TEMPLATE;
CREATE TABLE CHAR_TEMPLATE (
  ClassId DECIMAL(11, 0) NOT NULL,
  ClassName VARCHAR(20) NOT NULL DEFAULT '',
  RaceId DECIMAL(1, 0) NOT NULL DEFAULT 0,
  STR DECIMAL(2, 0) NOT NULL DEFAULT 0,
  CON DECIMAL(2, 0) NOT NULL DEFAULT 0,
  DEX DECIMAL(2, 0) NOT NULL DEFAULT 0,
  _INT DECIMAL(2, 0) NOT NULL DEFAULT 0,
  WIT DECIMAL(2, 0) NOT NULL DEFAULT 0,
  MEN DECIMAL(2, 0) NOT NULL DEFAULT 0,
  P_ATK DECIMAL(3, 0) NOT NULL DEFAULT 0,
  P_DEF DECIMAL(3, 0) NOT NULL DEFAULT 0,
  M_ATK DECIMAL(3, 0) NOT NULL DEFAULT 0,
  M_DEF DECIMAL(2, 0) NOT NULL DEFAULT 0,
  P_SPD DECIMAL(3, 0) NOT NULL DEFAULT 0,
  M_SPD DECIMAL(3, 0) NOT NULL DEFAULT 0,
  ACC DECIMAL(3, 0) NOT NULL DEFAULT 0,
  CRITICAL DECIMAL(3, 0) NOT NULL DEFAULT 0,
  EVASION DECIMAL(3, 0) NOT NULL DEFAULT 0,
  MOVE_SPD DECIMAL(3, 0) NOT NULL DEFAULT 0,
  x DECIMAL(10, 2) NOT NULL DEFAULT 0,
  y DECIMAL(10, 2) NOT NULL DEFAULT 0,
  z DECIMAL(10, 2) NOT NULL DEFAULT 0,
  items1 DECIMAL(4, 0) NOT NULL DEFAULT 0,
  items2 DECIMAL(4, 0) NOT NULL DEFAULT 0,
  items3 DECIMAL(4, 0) NOT NULL DEFAULT 0,
  items4 DECIMAL(4, 0) NOT NULL DEFAULT 0,
  items5 DECIMAL(10, 0) NOT NULL DEFAULT 0,
  F_COL_H DECIMAL(10, 2) NOT NULL DEFAULT 0,
  F_COL_R DECIMAL(10, 2) NOT NULL DEFAULT 0,
  M_COL_H DECIMAL(10, 2) NOT NULL DEFAULT 0,
  M_COL_R DECIMAL(10, 2) NOT NULL DEFAULT 0,
  PRIMARY KEY (ClassId)
);

-- CHARACTER
DROP TABLE IF EXISTS PLAYER_ITEM;
DROP TABLE IF EXISTS `character`;
CREATE TABLE `character`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_name VARCHAR(64) NOT NULL,
    char_name VARCHAR(64) NOT NULL,
    title VARCHAR(64) DEFAULT '',
    race TINYINT DEFAULT 0,
    class_id TINYINT DEFAULT 0,
    access_level INT DEFAULT 0,
    online BOOLEAN DEFAULT FALSE,
    char_slot TINYINT DEFAULT 0,
    level INT DEFAULT 1,
    hp INT DEFAULT 1,
    max_hp INT DEFAULT 1,
    cp INT DEFAULT 1,
    max_cp INT DEFAULT 1,
    mp INT DEFAULT 1,
    max_mp INT DEFAULT 1,
    acc INT DEFAULT 1,
    critical INT DEFAULT 1,
    evasion INT DEFAULT 1,
    m_atk INT DEFAULT 1,
    m_def INT DEFAULT 1,
    m_spd INT DEFAULT 1,
    p_atk INT DEFAULT 1,
    p_def INT DEFAULT 1,
    p_spd INT DEFAULT 1,
    run_spd INT DEFAULT 1,
    walk_spd INT DEFAULT 1,
    str TINYINT DEFAULT 1,
    con TINYINT DEFAULT 1,
    dex TINYINT DEFAULT 1,
    _int TINYINT DEFAULT 1,
    men TINYINT DEFAULT 1,
    wit TINYINT DEFAULT 1,
    face TINYINT DEFAULT 0,
    hair_style TINYINT DEFAULT 0,
    hair_color TINYINT DEFAULT 0,
    sex TINYINT DEFAULT 0,
    heading FLOAT DEFAULT 0,
    x FLOAT DEFAULT 0,
    y FLOAT DEFAULT 0,
    z FLOAT DEFAULT 0,
    colR FLOAT DEFAULT 0,
    colH FLOAT DEFAULT 0,
    exp BIGINT DEFAULT 0,
    sp BIGINT DEFAULT 0,
    karma INT DEFAULT 0,
    pvp_kills INT DEFAULT 0,
    pk_kills INT DEFAULT 0,
    clan_id INT,
    max_weight INT DEFAULT 0,
    last_login BIGINT DEFAULT 0,
    delete_time BIGINT,
	create_date BIGINT DEFAULT 0 NOT NULL
);

-- PLAYER ITEM
DROP TABLE IF EXISTS PLAYER_ITEM;
CREATE TABLE PLAYER_ITEM (
	OBJECT_ID INT AUTO_INCREMENT PRIMARY KEY,
	OWNER_ID INTEGER NOT NULL,
	ITEM_ID INTEGER NOT NULL,
	COUNT INTEGER DEFAULT 1,
	ENCHANT_LEVEL TINYINT DEFAULT 0,
	LOC TINYINT NOT NULL,
	SLOT INTEGER DEFAULT 0,
	PRICE_SELL INTEGER DEFAULT 0,
	PRICE_BUY INTEGER DEFAULT 0,
	CONSTRAINT CONSTRAINT_2 PRIMARY KEY (OBJECT_ID)
);
CREATE INDEX FK_OWNER_ID_INDEX_2 ON PLAYER_ITEM (OWNER_ID);
CREATE UNIQUE INDEX PRIMARY_KEY_2 ON PLAYER_ITEM (OBJECT_ID);
ALTER TABLE PLAYER_ITEM ADD CONSTRAINT FK_OWNER_ID FOREIGN KEY (OWNER_ID) REFERENCES "CHARACTER"(ID) ON DELETE CASCADE ON UPDATE RESTRICT;

-- NPC 
DROP TABLE IF EXISTS npc;
CREATE TABLE npc (
  id DECIMAL(11, 0) NOT NULL,
  idTemplate DECIMAL(11, 0) NOT NULL,
  name VARCHAR(200) DEFAULT NULL,
  serverSideName DECIMAL(1, 0) DEFAULT 0,
  title VARCHAR(45) DEFAULT '',
  serverSideTitle DECIMAL(1, 0) DEFAULT 0,
  class VARCHAR(200) DEFAULT NULL,
  collision_radius DECIMAL(5, 2) DEFAULT NULL,
  collision_height DECIMAL(5, 2) DEFAULT NULL,
  level DECIMAL(2, 0) DEFAULT NULL,
  sex VARCHAR(6) DEFAULT NULL,
  type VARCHAR(20) DEFAULT NULL,
  attackrange DECIMAL(11, 0) DEFAULT NULL,
  hp DECIMAL(8, 0) DEFAULT NULL,
  mp DECIMAL(5, 0) DEFAULT NULL,
  hpreg DECIMAL(8, 2) DEFAULT NULL,
  mpreg DECIMAL(5, 2) DEFAULT NULL,
  str DECIMAL(7, 0) DEFAULT NULL,
  con DECIMAL(7, 0) DEFAULT NULL,
  dex DECIMAL(7, 0) DEFAULT NULL,
  `int` DECIMAL(7, 0) DEFAULT NULL,
  wit DECIMAL(7, 0) DEFAULT NULL,
  men DECIMAL(7, 0) DEFAULT NULL,
  exp DECIMAL(9, 0) DEFAULT NULL,
  sp DECIMAL(8, 0) DEFAULT NULL,
  patk DECIMAL(5, 0) DEFAULT NULL,
  pdef DECIMAL(5, 0) DEFAULT NULL,
  matk DECIMAL(5, 0) DEFAULT NULL,
  mdef DECIMAL(5, 0) DEFAULT NULL,
  atkspd DECIMAL(3, 0) DEFAULT NULL,
  aggro DECIMAL(6, 0) DEFAULT NULL,
  matkspd DECIMAL(4, 0) DEFAULT NULL,
  rhand DECIMAL(4, 0) DEFAULT NULL,
  lhand DECIMAL(4, 0) DEFAULT NULL,
  armor DECIMAL(1, 0) DEFAULT NULL,
  walkspd DECIMAL(3, 0) DEFAULT NULL,
  runspd DECIMAL(3, 0) DEFAULT NULL,
  faction_id VARCHAR(40) DEFAULT NULL,
  faction_range DECIMAL(4, 0) DEFAULT NULL,
  isUndead DECIMAL(11, 0) DEFAULT 0,
  absorb_level DECIMAL(2, 0) DEFAULT 0,
  absorb_type VARCHAR(20) DEFAULT 'LAST_HIT' NOT NULL,
  PRIMARY KEY (id)
);

-- SPAWN LIST
DROP TABLE IF EXISTS spawnlist;
CREATE TABLE spawnlist (
  id INT NOT NULL AUTO_INCREMENT,
  location VARCHAR(40) NOT NULL DEFAULT '',
  count INT NOT NULL DEFAULT 0,
  npc_templateid INT NOT NULL DEFAULT 0,
  locx FLOAT NOT NULL DEFAULT 0,
  locy FLOAT NOT NULL DEFAULT 0,
  locz FLOAT NOT NULL DEFAULT 0,
  randomx INT NOT NULL DEFAULT 0,
  randomy INT NOT NULL DEFAULT 0,
  heading INT NOT NULL DEFAULT 0,
  respawn_delay INT NOT NULL DEFAULT 0,
  loc_id INT NOT NULL DEFAULT 0,
  periodOfDay DECIMAL(2, 0) DEFAULT 0,
  PRIMARY KEY (id)
);
  
 -- ETC ITEM
DROP TABLE IF EXISTS etcitem;
CREATE TABLE etcitem (
  item_id DECIMAL(11, 0) NOT NULL DEFAULT 0,
  name VARCHAR(100) DEFAULT NULL,
  crystallizable VARCHAR(5) DEFAULT NULL,
  item_type VARCHAR(12) DEFAULT NULL,
  weight DECIMAL(4, 0) DEFAULT NULL,
  consume_type VARCHAR(9) DEFAULT NULL,
  material VARCHAR(11) DEFAULT NULL,
  crystal_type VARCHAR(4) DEFAULT NULL,
  duration DECIMAL(3, 0) DEFAULT NULL,
  price DECIMAL(11, 0) DEFAULT NULL,
  crystal_count INT DEFAULT NULL,
  sellable VARCHAR(5) DEFAULT NULL,
  dropable VARCHAR(5) DEFAULT NULL,
  destroyable VARCHAR(5) DEFAULT NULL,
  tradeable VARCHAR(5) DEFAULT NULL,
  id_type VARCHAR(100) NOT NULL DEFAULT '',
  id_name VARCHAR(100) NOT NULL DEFAULT '',
  drop_category ENUM('0', '1', '2') NOT NULL DEFAULT '2',
  PRIMARY KEY (item_id)
);

-- LVLUPGAIN
DROP TABLE IF EXISTS LVLUPGAIN;
CREATE TABLE LVLUPGAIN (
  classid INT NOT NULL DEFAULT 0,
  defaulthpbase DECIMAL(5, 1) NOT NULL DEFAULT 0.0,
  defaulthpadd DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  defaulthpmod DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  defaultcpbase DECIMAL(5, 1) NOT NULL DEFAULT 0.0,
  defaultcpadd DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  defaultcpmod DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  defaultmpbase DECIMAL(5, 1) NOT NULL DEFAULT 0.0,
  defaultmpadd DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  defaultmpmod DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
  class_lvl INT NOT NULL DEFAULT 0,
  PRIMARY KEY (classid)
);

-- WEAPON
DROP TABLE IF EXISTS weapon;
CREATE TABLE weapon (
  item_id DECIMAL(11, 0) NOT NULL DEFAULT 0,
  name VARCHAR(70) DEFAULT NULL,
  bodypart VARCHAR(15) DEFAULT NULL,
  crystallizable VARCHAR(5) DEFAULT NULL,
  weight DECIMAL(4, 0) DEFAULT NULL,
  soulshots DECIMAL(2, 0) DEFAULT NULL,
  spiritshots DECIMAL(1, 0) DEFAULT NULL,
  material VARCHAR(11) DEFAULT NULL,
  crystal_type VARCHAR(4) DEFAULT NULL,
  p_dam DECIMAL(5, 0) DEFAULT NULL,
  rnd_dam DECIMAL(2, 0) DEFAULT NULL,
  weaponType VARCHAR(8) DEFAULT NULL,
  critical DECIMAL(2, 0) DEFAULT NULL,
  hit_modify DECIMAL(6, 5) DEFAULT NULL,
  avoid_modify DECIMAL(2, 0) DEFAULT NULL,
  shield_def DECIMAL(3, 0) DEFAULT NULL,
  shield_def_rate DECIMAL(2, 0) DEFAULT NULL,
  atk_speed DECIMAL(3, 0) DEFAULT NULL,
  mp_consume DECIMAL(2, 0) DEFAULT NULL,
  m_dam DECIMAL(3, 0) DEFAULT NULL,
  duration DECIMAL(3, 0) DEFAULT NULL,
  price DECIMAL(11, 0) DEFAULT NULL,
  crystal_count INT DEFAULT NULL,
  sellable VARCHAR(5) DEFAULT NULL,
  dropable VARCHAR(5) DEFAULT NULL,
  destroyable VARCHAR(5) DEFAULT NULL,
  tradeable VARCHAR(5) DEFAULT NULL,
  item_skill_id decimal(11,0) NOT NULL default '0',
  item_skill_lvl decimal(11,0) NOT NULL default '0',
  PRIMARY KEY (item_id)
);

