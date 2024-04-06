import pytest
import pyodbc


def connect_to_mssql():
    conn = pyodbc.connect(
       'DRIVER={SQL Server};'
       "SERVER=EPUAVINW00D5\\SQLEXPRESS;"
       'DATABASE=AdventureWorks2012;'
       'UID=Tester_RF;'
       'PWD=2024Tester;'
    )
    return conn

def execute_query(conn, query):
    cursor = conn.cursor()
    cursor.execute(query)
    rows = cursor.fetchall()
    cursor.close()
    return rows


def test_table_production_document_isfolder():
    conn = connect_to_mssql()
    print("typo")
    query = "select * from Production.Document where FolderFlag=1 and Document is not NULL"
    rows = execute_query(conn, query)
    conn.close()
    assert len(rows) == 0

def test_table_production_document_max_docLevel_2():
    conn = connect_to_mssql()
    query = "select Max(documentLevel) from Production.Document"
    rows = execute_query(conn, query)
    conn.close()
    assert rows[0][0] == 2

def test_table_person_address_unique_rowguid():
    conn = connect_to_mssql()
    query = "select rowguid, count(*) from Person.Address group by rowguid having count(*)>1"
    rows = execute_query(conn, query)
    conn.close()
    assert len(rows) == 0

def test_table_person_address_modifieddate_not_in_future():
    conn = connect_to_mssql()
    query = "select addressID from Person.Address where ModifiedDate > CURRENT_TIMESTAMP"
    rows = execute_query(conn, query)
    conn.close()
    assert len(rows) == 0

def test_table_production_unitMeasure_regexp_name():
    conn = connect_to_mssql()
    query = "select name fROM Production.UnitMeasure where NOT PATINDEX ('%[^a-zA-Z, ]%', name) = 0"
    rows = execute_query(conn, query)
    conn.close()
    assert len(rows) == 0

def test_table_production_unitmeasure_unique_UnitMeasureCode():
    conn = connect_to_mssql()
    query = "select Name, count(distinct UnitMeasureCode) from Production.UnitMeasure group by Name having count(distinct UnitMeasureCode)>1"
    rows = execute_query(conn, query)
    conn.close()
    assert len(rows) == 0


if __name__ == "__main__":
    pytest.main()
