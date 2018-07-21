USE master;
GO
IF DB_ID (N'koubei') IS NOT NULL
DROP DATABASE mytest;
GO
CREATE DATABASE mytest;
GO

USE koubei;

-- Property: not sure this table is required
CREATE TABLE ScoreProperty
(
  Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
  Name VARCHAR(30) NOT NULL
)

-- EntityScoreProperty
CREATE TABLE EntityScoreProperty
(
  Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
  UserRoleId INT NOT NULL,
  EntityTypeId INT NOT NULL,
  Properties NVARCHAR(max) NOT NULL,
  CreatedDate DATETIME2 NOT NULL default CURRENT_TIMESTAMP,
  CreatedBy INT NOT NULL,
  LastUpdatedDate DATETIME2 NOT NULL default CURRENT_TIMESTAMP,
  LastUpdatedBy INT NOT NULL
);

GO

-- EntityScore
CREATE TABLE EntityScore
(
  Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
  UserId INT NOT NULL,
  EntityId INT NOT NULL,
  EntityScore NVARCHAR(max) NOT NULL,
  CreatedDate DATETIME2 NOT NULL default CURRENT_TIMESTAMP
)
GO