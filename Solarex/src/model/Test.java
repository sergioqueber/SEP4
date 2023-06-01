package model;

import DAOTest.ManufacturerDAO;

import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    ManufacturerDAO manufacturerDAO = ManufacturerDAO.getInstance();
    manufacturerDAO.readByName("H");
    System.out.println();
  }
}
