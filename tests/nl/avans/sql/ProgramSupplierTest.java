package nl.avans.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ProgramSupplierTest {
    @Test
    void getProgramByIdWithValidIdReturnsCorrectProgram() {
        //Arrange
        SQLConnection connection = new SQLConnection() ;
        ProgramSupplier supplier = null;
        if (connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;")){
            supplier = new ProgramSupplier(connection);
        }
        try {
            assert supplier != null;
            supplier.makeProgramsAndSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Program program = supplier.getPrograms().get(0);

        //Act
        Program program1 = supplier.getProgramById(program.getId());

        //Assert
        Assertions.assertTrue(program.equals(program1));

    }

    void getProgramByIdWithInvalidIdReturnsNull() {
        //Arrange
        SQLConnection connection = new SQLConnection() ;
        ProgramSupplier supplier = null;
        if (connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;")){
            supplier = new ProgramSupplier(connection);
        }
        try {
            assert supplier != null;
            supplier.makeProgramsAndSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Act
        Program program = supplier.getProgramById(-20394);

        //Assert
        Assertions.assertNull(program);

    }

    @Test
    void makeProgramsWithValidConnectionProvidesNotEmptyList() {
        //Arrange
        SQLConnection connection = new SQLConnection() ;
        ProgramSupplier supplier = null;
        if (connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;")){
            supplier = new ProgramSupplier(connection);
        }

        //Act
        try {
            assert supplier != null;
            supplier.makeProgramsAndSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Assert
        Assertions.assertFalse(supplier.getPrograms().isEmpty());

    }

}