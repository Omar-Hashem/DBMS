

import junit.framework.TestCase;

import org.junit.Test;

public class lab03 extends TestCase {

	DBMSystem d = new DBMSystem(); // need to be initialized by the implemented class

	@Test
	public void test_1() throws Exception {
		assertEquals(
				dbms.PARSING_ERROR,
				d.input("CREATE TABLE Persons PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255);"));
		assertEquals(dbms.PARSING_ERROR, d.input("CRETE DATABASE LAB3;"));

		assertEquals(
				dbms.PARSING_ERROR,
				d.input("INSERT Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));

		assertEquals(dbms.DB_NOT_FOUND, d.input("DELETE FROM Persons;"));

		assertEquals(dbms.PARSING_ERROR, d.input("SELECT *  Customers;"));

		assertEquals(
				dbms.PARSING_ERROR,
				d.input("UPDATE Customers  ContactName='Alfred Schmidt', City='Hamburg' WHERE CustomerName='Alfreds Futterkiste'; "));

	}

	@Test
	public void test_2() throws Exception {
		assertEquals(
				dbms.DB_NOT_FOUND,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
		assertEquals(dbms.Con_DB, d.input("CREATE DATABASE LAB3;"));
		assertEquals(
				dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

		assertEquals(
				dbms.Con_Table,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
		assertEquals(
				dbms.TABLE_ALREADY_EXISTS,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));

		assertEquals(
				dbms.COLUMN_NOT_FOUND,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));

		assertEquals(
				dbms.COLUMN_TYPE_MISMATCH,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

		assertEquals(
				dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

	}

	@Test
	public void test_3() throws Exception {
		assertEquals(dbms.Con_Delete, d.input("Delete * from persons;"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (1,'Mohamed','Ali','11 street','Tanta');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (2,'Mohamed','Tamer','11 street','Alexandria');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (3,'Ahmad','Mohsen','12 street','Cairo');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (4,'Bassem','Yasser','43 street','Banha');"));

		assertEquals(
				"1 Mohamed Ali 11 street Tanta\n2 Mohamed Tamer 11 street Alexandria\n3 Ahmad Mohsen 12 street Cairo\n4 Bassem Yasser 43 street Banha",
				d.input("select * from persons"));

		assertEquals(
				"2 Mohamed Tamer 11 street Alexandria\n3 Ahmad Mohsen 12 street Cairo\n4 Bassem Yasser 43 street Banha",
				d.input("select * from persons where PersonID > 1"));

		assertEquals(
				"1 Mohamed Ali 11 street Tanta\n2 Mohamed Tamer 11 street Alexandria",
				d.input("select * from persons where LastName='Mohamed'"));

		assertEquals(
				"Mohsen",
				d.input("select FirstName from persons where address='12 street'"));

		assertEquals(
				dbms.Con_Update,
				d.input("UPDATE persons SET Lastname='Salem', City='Hamburg' WHERE personid= 1;"));

		assertEquals("Ali",
				d.input("select FirstName from persons where City='Hamburg'"));

		assertEquals("1 Salem Ali 11 street Hamburg",
				d.input("select * from persons where City='Hamburg'"));

		assertEquals(dbms.NOT_MATCH_CRITERIA,
				d.input("DELETE FROM persons WHERE lastname='salem';"));

		assertEquals(dbms.Con_Delete,
				d.input("DELETE FROM persons WHERE lastname='Salem';"));

		assertEquals(dbms.NOT_MATCH_CRITERIA,
				d.input("select * from persons where City='Hamburg'"));

	}
///////////////////////////////// REQUIRED JUNIT TEST////////////////////////////////////////////////////////
	
	@Test
	public void test_4() throws Exception {
		assertEquals(
				dbms.TABLE_ALREADY_EXISTS,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));

		assertEquals(dbms.Con_Delete, d.input("DELETE FROM Persons;"));

		assertEquals(dbms.PARSING_ERROR, d.input("SELECT *  Customers;"));

		assertEquals(
				dbms.PARSING_ERROR,
				d.input("UPDATE Persons  ContactName='Alfred Schmidt', City='Hamburg' WHERE CustomerName='Alfreds Futterkiste'; "));

	}

	@Test
	public void test_5() throws Exception {


		assertEquals(
				dbms.TABLE_ALREADY_EXISTS,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
		assertEquals(
				dbms.Con_Table,
				d.input("CREATE TABLE Customers(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));

		assertEquals(
				dbms.COLUMN_NOT_FOUND,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));

		assertEquals(
				dbms.Con_insert,
				d.input("insert into persons (PersonID,LastName,FirstName)VALUES (1,'mohamed','shahd');"));


		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (6,'xyz','Ali','11 street','Tanta');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (7,'aya','Tamer','11 street','Alexandria');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (9,'nada','Mohsen','12 street','Cairo');"));
		assertEquals(
				dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (10,'fay','Yasser','43 street','Banha');"));
		
		assertEquals(
				"9 nada Mohsen 12 street Cairo\n10 fay Yasser 43 street Banha",
				d.input("select * from persons where PersonID > 7"));


		assertEquals(
				"1 mohamed shahd null null\n6 xyz Ali 11 street Tanta\n7 aya Tamer 11 street Alexandria\n9 nada Mohsen 12 street Cairo\n10 fay Yasser 43 street Banha",
				d.input("select * from persons order by PersonID;"));
		
	}
	
	
}
