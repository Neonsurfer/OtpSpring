package otp.dao;

import org.springframework.stereotype.Repository;
import otp.entity.Persons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBConnection {
    Connection connection;

    private static final String DATABASE_URL =  "jdbc:sqlserver://localhost\\SQLEXPRESS;Database=otp;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";

    @PersistenceContext(unitName = "Otp")
    private EntityManager entityManager;

    public DBConnection() {
        Query query = entityManager.createQuery("SELECT * FROM persons \n");
        List<Persons> persons = query.getResultList();

        for(Persons p : persons) {
            System.out.println(p.getId());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
