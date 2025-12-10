CREATE DATABASE  IF NOT EXISTS `textflow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `textflow`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: textflow
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment_likes`
--

DROP TABLE IF EXISTS `comment_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_likes` (
  `like_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `comment_id` bigint unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `ux_user_comment` (`user_id`,`comment_id`),
  KEY `idx_comment_like_comment` (`comment_id`),
  KEY `idx_comment_likes_user` (`user_id`),
  CONSTRAINT `fk_comment_likes_comment` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_likes_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_likes`
--

LOCK TABLES `comment_likes` WRITE;
/*!40000 ALTER TABLE `comment_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned DEFAULT NULL,
  `episode_id` bigint unsigned NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `parent_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `idx_comment_episode` (`episode_id`),
  KEY `fk_comments_parent` (`parent_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`episode_id`) REFERENCES `episodes` (`episode_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comments_parent` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode_daily_metrics`
--

DROP TABLE IF EXISTS `episode_daily_metrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `episode_daily_metrics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `episode_id` bigint unsigned NOT NULL,
  `metric_date` date NOT NULL,
  `view_count` bigint NOT NULL DEFAULT '0',
  `like_count` bigint NOT NULL DEFAULT '0',
  `comment_count` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `episode_id` (`episode_id`,`metric_date`),
  CONSTRAINT `fk_episode_daily_metrics_episode` FOREIGN KEY (`episode_id`) REFERENCES `episodes` (`episode_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_daily_metrics`
--

LOCK TABLES `episode_daily_metrics` WRITE;
/*!40000 ALTER TABLE `episode_daily_metrics` DISABLE KEYS */;
/*!40000 ALTER TABLE `episode_daily_metrics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode_likes`
--

DROP TABLE IF EXISTS `episode_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `episode_likes` (
  `like_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `episode_id` bigint unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `ux_like_user_episode` (`user_id`,`episode_id`),
  KEY `idx_like_episode` (`episode_id`),
  KEY `idx_episode_likes_user` (`user_id`),
  CONSTRAINT `episode_likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `episode_likes_ibfk_2` FOREIGN KEY (`episode_id`) REFERENCES `episodes` (`episode_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode_likes`
--

LOCK TABLES `episode_likes` WRITE;
/*!40000 ALTER TABLE `episode_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `episode_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episodes`
--

DROP TABLE IF EXISTS `episodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `episodes` (
  `episode_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `work_id` bigint unsigned NOT NULL,
  `episode_no` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `content_meta` json DEFAULT NULL,
  `content_type` enum('IMAGE','MIXED','TEXT') NOT NULL,
  `episode_length` int unsigned DEFAULT NULL COMMENT '웹소설 글자수 또는 웹툰 컷수',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `is_adult` tinyint(1) NOT NULL DEFAULT '0',
  `view_count` bigint unsigned NOT NULL DEFAULT '0',
  `like_count` bigint unsigned NOT NULL DEFAULT '0',
  `comment_count` bigint unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`episode_id`),
  UNIQUE KEY `ux_work_episode_no` (`work_id`,`episode_no`),
  KEY `idx_episode_work` (`work_id`),
  CONSTRAINT `episodes_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `works` (`work_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episodes`
--

LOCK TABLES `episodes` WRITE;
/*!40000 ALTER TABLE `episodes` DISABLE KEYS */;
INSERT INTO `episodes` VALUES (1,1,1,'EP.01 - 서막','<p>마법의 세계 1화 내용(샘플) — 새로운 여정의 시작.</p>','{\"images\": [], \"summary\": \"서막\"}','TEXT',3500,0.00,'APPROVED',0,1200,45,12,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(2,1,2,'EP.02 - 시험의 날','<p>주인공이 첫 시험을 치르는 에피소드.</p>','{\"images\": [], \"summary\": \"시험\"}','TEXT',4200,0.00,'APPROVED',0,980,30,8,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(3,1,3,'EP.03 - 동료','<p>동료를 만나 팀이 구성된다.</p>','{\"images\": [], \"summary\": \"동료\"}','TEXT',3900,0.00,'APPROVED',0,860,22,3,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(4,1,4,'EP.04 - 결투','<p>첫 결투 장면, 긴장감이 고조된다.</p>','{\"images\": [], \"summary\": \"결투\"}','TEXT',4100,0.00,'APPROVED',0,740,18,2,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(5,1,5,'EP.05 - 진실의 조각','<p>과거의 단서가 드러난다.</p>','{\"images\": [], \"summary\": \"진실\"}','TEXT',4500,0.00,'APPROVED',0,650,15,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(6,2,1,'EP.01 - 첫 만남','<p>사랑의 온도 1화: 첫 만남의 떨림.</p>','{\"images\": [], \"summary\": \"첫 만남\"}','TEXT',2700,0.00,'APPROVED',0,430,12,2,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(7,2,2,'EP.02 - 오해','<p>작은 오해가 갈등을 만든다.</p>','{\"images\": [], \"summary\": \"오해\"}','TEXT',2900,0.00,'APPROVED',0,410,9,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(8,2,3,'EP.03 - 진심','<p>서로의 진심이 비춰진다.</p>','{\"images\": [], \"summary\": \"진심\"}','TEXT',3000,0.00,'APPROVED',0,390,10,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(9,2,4,'EP.04 - 갈등의 끝','<p>갈등이 해소되는 전환점.</p>','{\"images\": [], \"summary\": \"화해\"}','TEXT',2800,0.00,'APPROVED',0,370,7,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(10,2,5,'EP.05 - 새로운 약속','<p>앞으로의 시간을 약속한다.</p>','{\"images\": [], \"summary\": \"약속\"}','TEXT',2600,0.00,'APPROVED',0,350,5,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(11,3,1,'EP.01 - 각성','<p>최강의 검사 1화: 각성의 순간.</p>','{\"images\": []}','IMAGE',8,0.00,'APPROVED',0,9800,420,90,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(12,3,2,'EP.02 - 훈련','<p>강렬한 훈련 장면.</p>','{\"images\": []}','IMAGE',10,0.00,'APPROVED',0,8700,380,72,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(13,3,3,'EP.03 - 표적','<p>첫 표적과의 대면.</p>','{\"images\": []}','IMAGE',9,0.00,'APPROVED',0,7600,330,60,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(14,3,4,'EP.04 - 배신','<p>충격적인 배신이 드러난다.</p>','{\"images\": []}','IMAGE',11,0.00,'APPROVED',0,6500,290,40,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(15,3,5,'EP.05 - 반격','<p>반격의 서막이 열린다.</p>','{\"images\": []}','IMAGE',12,0.00,'APPROVED',0,5400,240,30,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(16,4,1,'EP.01 - 의뢰','<p>미스터리 탐정 1화: 한 건의 의뢰로 시작된다.</p>','{\"images\": [], \"summary\": \"의뢰\"}','TEXT',2200,0.00,'APPROVED',0,320,11,2,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(17,4,2,'EP.02 - 단서','<p>작은 단서들이 모인다.</p>','{\"images\": [], \"summary\": \"단서\"}','TEXT',2500,0.00,'APPROVED',0,300,9,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(18,4,3,'EP.03 - 추격','<p>추격전이 벌어진다.</p>','{\"images\": [], \"summary\": \"추격\"}','TEXT',2600,0.00,'APPROVED',0,280,8,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(19,4,4,'EP.04 - 반전','<p>예상치 못한 반전이 등장한다.</p>','{\"images\": [], \"summary\": \"반전\"}','TEXT',2800,0.00,'APPROVED',0,260,6,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(20,4,5,'EP.05 - 결말','<p>사건의 결말이 드러난다.</p>','{\"images\": [], \"summary\": \"결말\"}','TEXT',3000,0.00,'APPROVED',0,240,5,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(21,5,1,'EP.01 - 부활','<p>환생한 용사 1화: 부활의 서막.</p>','{\"images\": [], \"summary\": \"부활\"}','TEXT',3800,1.99,'APPROVED',0,2200,110,25,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(22,5,2,'EP.02 - 정체','<p>주인공의 정체가 드러난다.</p>','{\"images\": [], \"summary\": \"정체\"}','TEXT',4000,1.99,'APPROVED',0,2100,95,20,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(23,5,3,'EP.03 - 동맹','<p>새로운 동맹이 생긴다.</p>','{\"images\": [], \"summary\": \"동맹\"}','TEXT',3600,1.99,'APPROVED',0,2000,88,18,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(24,5,4,'EP.04 - 시험','<p>강력한 시험을 통과해야 한다.</p>','{\"images\": [], \"summary\": \"시험\"}','TEXT',4200,1.99,'APPROVED',0,1900,72,12,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(25,5,5,'EP.05 - 결의','<p>결의를 다지는 순간.</p>','{\"images\": [], \"summary\": \"결의\"}','TEXT',4400,1.99,'APPROVED',0,1800,60,8,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(26,6,1,'EP.01 - 만남','<p>운명의 만남 1화: 운명이 시작된다.</p>','{\"images\": [], \"summary\": \"만남\"}','TEXT',2400,0.00,'APPROVED',0,310,14,2,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(27,6,2,'EP.02 - 오해와 화해','<p>짧은 오해가 생긴다.</p>','{\"images\": [], \"summary\": \"오해\"}','TEXT',2300,0.00,'APPROVED',0,290,10,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(28,6,3,'EP.03 - 데이트','<p>달콤한 데이트 장면.</p>','{\"images\": [], \"summary\": \"데이트\"}','TEXT',2200,0.00,'APPROVED',0,270,9,1,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(29,6,4,'EP.04 - 시험','<p>감정의 시험이 온다.</p>','{\"images\": [], \"summary\": \"시험\"}','TEXT',2500,0.00,'APPROVED',0,250,7,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(30,6,5,'EP.05 - 약속','<p>앞으로의 약속을 한다.</p>','{\"images\": [], \"summary\": \"약속\"}','TEXT',2100,0.00,'APPROVED',0,230,5,0,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(31,7,1,'EP.01 - 귀환','<p>회귀한 천재 마법사 1화: 귀환의 시작.</p>','{\"images\": [], \"summary\": \"귀환\"}','TEXT',4800,0.00,'APPROVED',0,212300,8200,1200,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(32,7,2,'EP.02 - 기억','<p>이전 생의 기억들이 되살아난다.</p>','{\"images\": [], \"summary\": \"기억\"}','TEXT',5000,0.00,'APPROVED',0,211000,7900,1100,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(33,7,3,'EP.03 - 성장','<p>압도적 성장을 보인다.</p>','{\"images\": [], \"summary\": \"성장\"}','TEXT',5200,0.00,'APPROVED',0,210000,7600,1050,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(34,7,4,'EP.04 - 대결','<p>라이벌과의 대결이 펼쳐진다.</p>','{\"images\": [], \"summary\": \"대결\"}','TEXT',5400,0.00,'APPROVED',0,209000,7300,980,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(35,7,5,'EP.05 - 전장','<p>전장의 서막이 열린다.</p>','{\"images\": [], \"summary\": \"전장\"}','TEXT',5600,0.00,'APPROVED',0,208000,7000,900,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(36,8,1,'EP.01 - 각성','<p>레벨업의 신 1화: 각성의 순간.</p>','{\"images\": []}','TEXT',4200,0.00,'APPROVED',0,151000,6000,800,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(37,8,2,'EP.02 - 전투','<p>전투에서 성장하는 모습.</p>','{\"images\": []}','TEXT',4300,0.00,'APPROVED',0,150000,5600,720,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(38,8,3,'EP.03 - 던전','<p>던전 탐험이 시작된다.</p>','{\"images\": []}','TEXT',4100,0.00,'APPROVED',0,149000,5200,650,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(39,8,4,'EP.04 - 동료','<p>새로운 동료와 팀을 이룬다.</p>','{\"images\": []}','TEXT',4000,0.00,'APPROVED',0,148000,4800,600,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(40,8,5,'EP.05 - 보스전','<p>보스와의 치열한 전투.</p>','{\"images\": []}','TEXT',4500,0.00,'APPROVED',0,147000,4300,520,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(41,9,1,'EP.01 - 실타래','<p>운명의 붉은 실 1화: 만남의 시작.</p>','{\"images\": [], \"summary\": \"실타래\"}','TEXT',3100,0.00,'APPROVED',0,980000,4200,600,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(42,9,2,'EP.02 - 갈등','<p>작은 갈등들이 쌓인다.</p>','{\"images\": [], \"summary\": \"갈등\"}','TEXT',3000,0.00,'APPROVED',0,970000,4000,520,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(43,9,3,'EP.03 - 이해','<p>서로를 이해해가는 과정.</p>','{\"images\": [], \"summary\": \"이해\"}','TEXT',2900,0.00,'APPROVED',0,960000,3800,480,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(44,9,4,'EP.04 - 위기','<p>위기가 찾아오고 감정이 흔들린다.</p>','{\"images\": [], \"summary\": \"위기\"}','TEXT',3200,0.00,'APPROVED',0,950000,3600,420,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(45,9,5,'EP.05 - 화해','<p>화해와 새로운 시작.</p>','{\"images\": [], \"summary\": \"화해\"}','TEXT',3300,0.00,'APPROVED',0,940000,3400,380,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(46,10,1,'EP.01 - 전장 입성','<p>시간을 달리는 사냥꾼 1화: 전장으로의 첫 발.</p>','{\"images\": []}','IMAGE',9,0.00,'APPROVED',0,52000,1900,210,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(47,10,2,'EP.02 - 추적','<p>시간 추적 기술로 표적을 쫓는다.</p>','{\"images\": []}','IMAGE',8,0.00,'APPROVED',0,50000,1700,180,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(48,10,3,'EP.03 - 함정','<p>함정에 빠진 주인공, 탈출을 시도한다.</p>','{\"images\": []}','IMAGE',7,0.00,'APPROVED',0,48000,1500,140,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(49,10,4,'EP.04 - 동맹','<p>뜻밖의 동맹이 등장한다.</p>','{\"images\": []}','IMAGE',10,0.00,'APPROVED',0,46000,1300,110,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(50,10,5,'EP.05 - 반격','<p>치열한 반격전이 벌어진다.</p>','{\"images\": []}','IMAGE',11,0.00,'APPROVED',0,44000,1100,90,'2025-12-09 19:24:27','2025-12-09 19:24:27'),(51,11,1,'EP.01 - 새 출발','<p>11번 작품 1화 내용(샘플)</p>','{\"images\": [], \"summary\": \"시작\"}','TEXT',2900,0.00,'APPROVED',0,1200,42,9,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(52,11,2,'EP.02 - 첫 갈등','<p>갈등이 생기는 장면</p>','{\"images\": [], \"summary\": \"갈등\"}','TEXT',3000,0.00,'APPROVED',0,1100,38,6,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(53,11,3,'EP.03 - 희망','<p>희망의 단서 등장</p>','{\"images\": [], \"summary\": \"희망\"}','TEXT',2800,0.00,'APPROVED',0,1000,30,4,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(54,11,4,'EP.04 - 시험','<p>시험을 받는 에피소드</p>','{\"images\": [], \"summary\": \"시험\"}','TEXT',3200,0.00,'APPROVED',0,900,25,2,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(55,11,5,'EP.05 - 결의','<p>다짐의 순간</p>','{\"images\": [], \"summary\": \"결의\"}','TEXT',3100,0.00,'APPROVED',0,800,20,1,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(56,12,1,'EP.01 - 시작','<p>12번 작품 1화</p>','{\"images\": [], \"summary\": \"시작\"}','TEXT',2600,0.00,'APPROVED',0,700,18,1,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(57,12,2,'EP.02 - 도전','<p>새로운 도전</p>','{\"images\": [], \"summary\": \"도전\"}','TEXT',2700,0.00,'APPROVED',0,650,16,1,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(58,12,3,'EP.03 - 연대','<p>연대와 신뢰</p>','{\"images\": [], \"summary\": \"연대\"}','TEXT',2800,0.00,'APPROVED',0,600,14,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(59,12,4,'EP.04 - 분투','<p>분투의 시간</p>','{\"images\": [], \"summary\": \"분투\"}','TEXT',2900,0.00,'APPROVED',0,550,12,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(60,12,5,'EP.05 - 전진','<p>전진의 결심</p>','{\"images\": [], \"summary\": \"전진\"}','TEXT',3000,0.00,'APPROVED',0,500,10,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(61,13,1,'EP.01 - 초점','<p>13번 작품 1화</p>','{\"images\": [], \"summary\": \"초점\"}','TEXT',2500,0.00,'APPROVED',0,480,9,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(62,13,2,'EP.02 - 만남','<p>인연의 시작</p>','{\"images\": [], \"summary\": \"만남\"}','TEXT',2600,0.00,'APPROVED',0,460,8,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(63,13,3,'EP.03 - 시험','<p>시련과 시험</p>','{\"images\": [], \"summary\": \"시련\"}','TEXT',2700,0.00,'APPROVED',0,440,7,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(64,13,4,'EP.04 - 결전','<p>결전의 순간</p>','{\"images\": [], \"summary\": \"결전\"}','TEXT',2800,0.00,'APPROVED',0,420,6,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(65,13,5,'EP.05 - 여운','<p>여운이 남는 마무리</p>','{\"images\": [], \"summary\": \"여운\"}','TEXT',2400,0.00,'APPROVED',0,400,5,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(66,14,1,'EP.01 - 서막','<p>14번 작품 1화</p>','{\"images\": [], \"summary\": \"서막\"}','TEXT',3000,0.00,'APPROVED',0,390,5,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(67,14,2,'EP.02 - 향방','<p>향방이 알려진다</p>','{\"images\": [], \"summary\": \"향방\"}','TEXT',3200,0.00,'APPROVED',0,370,4,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(68,14,3,'EP.03 - 대치','<p>대치 상황</p>','{\"images\": [], \"summary\": \"대치\"}','TEXT',3100,0.00,'APPROVED',0,350,3,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(69,14,4,'EP.04 - 분열','<p>내부 분열이 발생</p>','{\"images\": [], \"summary\": \"분열\"}','TEXT',3300,0.00,'APPROVED',0,330,2,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(70,14,5,'EP.05 - 화해','<p>화해와 해소</p>','{\"images\": [], \"summary\": \"화해\"}','TEXT',3400,0.00,'APPROVED',0,310,1,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(71,15,1,'EP.01 - 도입','<p>15번 작품 1화</p>','{\"images\": [], \"summary\": \"도입\"}','TEXT',2800,0.00,'APPROVED',0,300,6,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(72,15,2,'EP.02 - 펼쳐짐','<p>이야기가 펼쳐진다</p>','{\"images\": [], \"summary\": \"전개\"}','TEXT',2900,0.00,'APPROVED',0,280,5,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(73,15,3,'EP.03 - 고비','<p>고비가 온다</p>','{\"images\": [], \"summary\": \"고비\"}','TEXT',3000,0.00,'APPROVED',0,260,4,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(74,15,4,'EP.04 - 반전','<p>반전의 순간</p>','{\"images\": [], \"summary\": \"반전\"}','TEXT',3100,0.00,'APPROVED',0,240,3,0,'2025-12-09 19:24:31','2025-12-09 19:24:31'),(75,15,5,'EP.05 - 결심','<p>새로운 결심</p>','{\"images\": [], \"summary\": \"결심\"}','TEXT',3200,0.00,'APPROVED',0,220,2,0,'2025-12-09 19:24:31','2025-12-09 19:24:31');
/*!40000 ALTER TABLE `episodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL,
  `work_id` bigint NOT NULL,
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `episode_id` bigint unsigned DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `currency` varchar(10) NOT NULL DEFAULT 'KRW',
  `provider` varchar(50) NOT NULL,
  `provider_payment_id` varchar(255) DEFAULT NULL,
  `status` enum('CANCELLED','FAILED','PENDING','SUCCESS') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `method` varchar(50) NOT NULL DEFAULT 'POINT',
  PRIMARY KEY (`payment_id`),
  KEY `episode_id` (`episode_id`),
  KEY `idx_pay_user` (`user_id`),
  KEY `idx_pay_provider_payment_id` (`provider_payment_id`(100)),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`episode_id`) REFERENCES `episodes` (`episode_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `points_ledger`
--

DROP TABLE IF EXISTS `points_ledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `points_ledger` (
  `ledger_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `delta` bigint NOT NULL,
  `balance_after` bigint NOT NULL,
  `reason` varchar(100) NOT NULL,
  `reference_payment_id` bigint unsigned DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ledger_id`),
  KEY `idx_points_user` (`user_id`),
  KEY `idx_points_ref_payment` (`reference_payment_id`),
  CONSTRAINT `points_ledger_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT,
  CONSTRAINT `points_ledger_ibfk_2` FOREIGN KEY (`reference_payment_id`) REFERENCES `payments` (`payment_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points_ledger`
--

LOCK TABLES `points_ledger` WRITE;
/*!40000 ALTER TABLE `points_ledger` DISABLE KEYS */;
/*!40000 ALTER TABLE `points_ledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `reporter_id` bigint unsigned DEFAULT NULL,
  `reason` varchar(500) NOT NULL,
  `status` enum('OPEN','REJECTED','RESOLVED') NOT NULL,
  `admin_note` text,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `target_type` enum('COMMENT','EPISODE','USER','WORK') DEFAULT NULL,
  `target_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `reporter_id` (`reporter_id`),
  KEY `idx_reports_status` (`status`),
  KEY `idx_reports_target` (`target_type`,`target_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`reporter_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_balances`
--

DROP TABLE IF EXISTS `user_balances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_balances` (
  `user_id` bigint unsigned NOT NULL,
  `balance` bigint NOT NULL DEFAULT '0',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_balances_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_balances`
--

LOCK TABLES `user_balances` WRITE;
/*!40000 ALTER TABLE `user_balances` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_balances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_episode_access`
--

DROP TABLE IF EXISTS `user_episode_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_episode_access` (
  `access_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `episode_id` bigint unsigned NOT NULL,
  `type` enum('OWN','RENT') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `payment_id` bigint unsigned DEFAULT NULL,
  `ledger_id` bigint unsigned DEFAULT NULL,
  `purchased_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`access_id`),
  UNIQUE KEY `ux_user_episode_type` (`user_id`,`episode_id`,`type`),
  KEY `payment_id` (`payment_id`),
  KEY `idx_access_user` (`user_id`),
  KEY `idx_access_episode` (`episode_id`),
  KEY `idx_access_expires` (`expires_at`),
  KEY `idx_access_ledger` (`ledger_id`),
  CONSTRAINT `fk_access_ledger` FOREIGN KEY (`ledger_id`) REFERENCES `points_ledger` (`ledger_id`) ON DELETE SET NULL,
  CONSTRAINT `user_episode_access_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user_episode_access_ibfk_2` FOREIGN KEY (`episode_id`) REFERENCES `episodes` (`episode_id`) ON DELETE CASCADE,
  CONSTRAINT `user_episode_access_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_episode_access`
--

LOCK TABLES `user_episode_access` WRITE;
/*!40000 ALTER TABLE `user_episode_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_episode_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` enum('ADMIN','AUTHOR','USER') COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `display_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bio` text COLLATE utf8mb4_unicode_ci,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'author1','testhash','AUTHOR','author1@example.com','김작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(2,'author2','testhash','AUTHOR','author2@example.com','이작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(3,'author3','testhash','AUTHOR','author3@example.com','박작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(4,'author4','testhash','AUTHOR','author4@example.com','최작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(5,'author5','testhash','AUTHOR','author5@example.com','정작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(6,'author6','testhash','AUTHOR','author6@example.com','한작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(7,'author7','testhash','AUTHOR','author7@example.com','문작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(8,'author8','testhash','AUTHOR','author8@example.com','강작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(9,'author9','testhash','AUTHOR','author9@example.com','윤작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47',''),(10,'author10','testhash','AUTHOR','author10@example.com','송작가',NULL,'2025-12-09 19:23:47','2025-12-09 19:23:47','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `works` (
  `work_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `author_id` bigint unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` longtext,
  `category` enum('FANTASY','ROMANCE','ACTION','THRILLER','DAILY') NOT NULL,
  `thumbnail_url` varchar(500) DEFAULT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `view_count` bigint unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_adult` tinyint(1) NOT NULL DEFAULT '0',
  `publication_status` varchar(20) NOT NULL DEFAULT 'ONGOING',
  PRIMARY KEY (`work_id`),
  UNIQUE KEY `ux_works_slug` (`slug`),
  KEY `idx_works_title` (`title`),
  KEY `idx_works_author` (`author_id`),
  KEY `idx_works_category` (`category`),
  CONSTRAINT `works_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (1,1,'마법의 세계','mabeob-ui-segye','마법과 모험이 공존하는 대형 판타지 소설. 운명을 건 전투가 시작된다.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',123456,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(2,2,'사랑의 온도','sarang-ui-ondo','달콤하고 애틋한 로맨스. 서로 다른 두 사람의 온도 차이가 맞춰진다.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',98432,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(3,3,'최강의 검사','choegang-ui geomsa','절정의 액션 연출과 박진감 넘치는 전투가 일품인 웹툰.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',758901,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(4,4,'미스터리 탐정','misteri tamjeong','미궁 같은 사건을 해결하는 천재 탐정의 이야기.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',45231,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(5,5,'환생한 용사','hwansaenghan yongsa','전생의 기억을 가진 용사가 세상을 구하는 여정.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',612300,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(6,6,'운명의 만남','unmyeong-ui mannam','우연한 만남이 운명을 바꾼다. 순수 로맨스 소설.','ROMANCE','https://images.unsplash.com/photo-1759863738666-7584248cdf7e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',23012,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(7,7,'회귀한 천재 마법사','hoegwihan cheonjae mabeobsa','회귀를 통해 전생의 지식으로 성장하는 천재의 이야기.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',1502300,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(8,8,'레벨업의 신','rebeleop-ui sin','끊임없이 성장하는 주인공의 전투 판타지.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',980123,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(9,9,'운명의 붉은 실','unmyeong-ui bulgeun sil','운명적으로 얽힌 두 사람의 사랑과 시련.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',987654,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(10,10,'시간을 달리는 사냥꾼','siganeul dallineun sanyangkkun','시간을 이동하며 사건을 해결하는 하드보일드 액션.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','PENDING',43210,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(11,1,'달빛 아래서','dalbit araseo','달빛 아래에서 시작된 애틋한 이야기. 독점 연재 로맨스.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',450000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(12,2,'검은 심연','geomeun simyeon','심연 속에 숨겨진 비밀과 전투의 서사시.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',120000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(13,3,'도시의 그림자','doshi-ui geurimja','도시 속 어둠을 파헤치는 스릴러.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',89000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(14,4,'왕의 귀환','wang-ui gwihwan','멸망한 왕국의 재건을 그린 대서사시 판타지.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',320000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(15,5,'비밀의 서약','bimil-ui seoyak','비밀스러운 맹세가 불러온 운명의 소용돌이.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','REJECTED',5400,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(16,6,'철혈기사단','cheolhyeol gisadan','강렬한 전투와 신념의 이야기를 담은 액션 판타지.','ACTION','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',210000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(17,7,'폐허의 아이','paehue-ui ai','폐허에서 살아남은 소년의 성장 서사.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',43000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(18,8,'복수의 날개','boksu-ui nalgae','복수를 위해 날개를 단 자의 이야기.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',76000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(19,9,'비 오는 날의 약속','bi oneun nal-ui yaksok','비 내리는 날 시작된 약속과 사랑.','ROMANCE','https://images.unsplash.com/photo-1759863738666-7584248cdf7e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',66000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(20,10,'암흑의 군주','amhuk-ui gunju','암흑 속에서 권력을 쥔 자의 이야기.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','PENDING',12000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(21,1,'그녀의 초상','geunyeo-ui chusang','한 이미지로 시작된 미스터리 로맨스.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',540000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(22,2,'은빛 검','eunbit geom','전설의 검을 둘러싼 전투 판타지.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',270000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(23,3,'밤의 순찰자','bam-ui sunchalja','도시를 지키는 그림자 히어로의 이야기.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',130000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(24,4,'잃어버린 기억','ilheo-beorin gieok','기억을 잃은 주인공이 자신을 찾는 여정.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',87000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(25,5,'붉은 달의 기사','bulgeun dal-ui gisa','붉은 달 아래에서 각성하는 기사단의 전쟁.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',200000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(26,6,'소문난 연애학','somunnan yeonaehak','웃음과 감동이 있는 로맨틱 코미디 연재.','ROMANCE','https://images.unsplash.com/photo-1759863738666-7584248cdf7e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',15000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(27,7,'도적왕의 귀환','dojeokwang-ui gwihwan','한 도적이 세계를 뒤흔드는 이야기.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',98000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(28,8,'은밀한 거래','eunmilhan georae','비밀 거래가 부른 파국을 그린 스릴러.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',43000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(29,9,'꽃길을 걸어','kkotgireul georeo','따뜻한 일상 로맨스, 힐링 스토리.','DAILY','https://images.unsplash.com/photo-1759863738666-7584248cdf7e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',21000,'2025-12-09 19:23:52','2025-12-10 14:59:04',0,'ONGOING'),(30,10,'심연의 사서','simyeon-ui saseo','고대 지식과 마법을 수호하는 자의 이야기.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','PENDING',5400,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(31,1,'비밀 요원 007','bimil yowon 007','스파이 액션의 정수, 긴박한 전개.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',77000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(32,2,'달의 연인들','dal-ui yeonindeul','달빛이 엮어내는 로맨스 앤 드라마.','ROMANCE','https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',122000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(33,3,'전장으로 가는 길','jeonjang-euro ganeun gil','전쟁 속 영웅들의 운명과 형제애.','ACTION','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',91000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'COMPLETED'),(34,4,'잊혀진 도시','ijhyeojin doshi','고대 문명이 남긴 비밀을 추적하는 모험.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',65000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(35,5,'그림자 연대기','geurimja yeondaegi','음모와 배신으로 얽힌 정치 스릴러.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',34000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(36,6,'낙원의 기사','nagwon-ui gisa','천상의 힘을 가진 기사들의 전투.','FANTASY','https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',177000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(37,7,'소문의 연인','somun-ui yeonin','두 사람 사이에 퍼진 소문이 불러온 갈등과 화해.','ROMANCE','https://images.unsplash.com/photo-1759863738666-7584248cdf7e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',41000,'2025-12-09 19:23:52','2025-12-10 14:59:01',0,'ONGOING'),(38,8,'밤을 삼킨 도시','bam-eul samkin doshi','범죄와 부패가 뒤얽힌 도시의 이야기.','THRILLER','https://images.unsplash.com/photo-1698956483970-a47edef29331?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',22000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING'),(39,9,'평범한 영웅','pyeongbeomhan yeongung','보통 사람이 영웅이 되는 평범한 판타지.','FANTASY','https://images.unsplash.com/photo-1653723367970-ae966c1b803e?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',99000,'2025-12-09 19:23:52','2025-12-10 14:59:00',0,'ONGOING'),(40,10,'시간의 조각들','siganeui jogakdeul','시간을 조작하는 초능력자들의 드라마.','ACTION','https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080','APPROVED',134000,'2025-12-09 19:23:52','2025-12-10 14:59:02',0,'ONGOING');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-10 16:40:40
