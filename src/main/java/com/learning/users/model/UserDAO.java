package com.learning.users.model;

import com.learning.users.repository.Connector;
import com.learning.users.repository.UserRepository;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class UserDAO implements UserRepository {

    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;
    private User user;

    javax.validation.Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(Validation.buildDefaultValidatorFactory().getMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    @Override
    public void create(User user) throws Exception {

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        if(read(user.getEmail()) == null){
            throw new NullPointerException();
        }

        cn = new Connector().connectDatabase();
        String SQL_INSERT = "INSERT INTO user (name, lastName, email, " +
                "dateOfBirth, phone, gitHubProfile) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        ps = cn.prepareStatement(SQL_INSERT);
        ps.setString(1, user.getName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
        ps.setString(5, user.getPhone());
        ps.setString(6, user.getGitHubProfile());
        ps.executeUpdate();
        System.out.println("User registered successfully.");
        ps.close();
        cn.close();
    }

    @Override
    public void update(User user) throws Exception {

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        else if(read(user.getId()) == null){
            throw new UserNotFoundException(user.getId());
        }

        cn = new Connector().connectDatabase();
        String SQL_UPDATE = "UPDATE user SET name = ?, lastName = ?, email = ?, dateOfBirth = ?, " +
                "phone = ?, gitHubProfile = ?, updatedAt = ? WHERE id = ? AND deletedAt IS NULL";
        ps = cn.prepareStatement(SQL_UPDATE);
        ps.setString(1, user.getName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
        ps.setString(5, user.getPhone());
        ps.setString(6, user.getGitHubProfile());
        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        ps.setInt(8, user.getId());
        ps.executeUpdate();
        System.out.println("User data updated successfully.");
        ps.close();
        cn.close();
    }

    @Override
    public void delete(User user) throws Exception {

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        if(user.getDeletedAt() != null){
            throw new UserNotFoundException(user.getEmail());
        }

        cn  = new Connector().connectDatabase();
        String SQL_DELETE = "UPDATE user SET deletedAt = ? WHERE id = ?";
        ps = cn.prepareStatement(SQL_DELETE);
        ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        ps.setInt(2, user.getId());
        ps.executeUpdate();
        System.out.println("User " + user.getEmail() + " was deleted successfully.");
        ps.close();
        cn.close();

    }

    @Override
    public User read(int id) throws Exception {

        cn = new Connector().connectDatabase();
        String SQL_SELECT = "SELECT * FROM user WHERE id = ?";
        ps = cn.prepareStatement(SQL_SELECT);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if(!rs.next()){
            throw new UserNotFoundException(id);
        }

        user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString( 2));
        user.setLastName(rs.getString( 3));
        user.setEmail(rs.getString( 4));
        user.setDateOfBirth(rs.getDate( 5).toLocalDate());
        user.setPhone(rs.getString( 6));
        user.setGitHubProfile(rs.getString( 7));
        user.setUpdatedAt(rs.getTimestamp(8) != null ? rs.getTimestamp(8).toLocalDateTime() : null);
        user.setDeletedAt(rs.getTimestamp(9) != null ? rs.getTimestamp(9).toLocalDateTime() : null);
        rs.close();
        ps.close();
        cn.close();

        return user;

    }

    @Override
    public User read(String email) throws Exception {

        String mySql = "SELECT * FROM user WHERE email = ?";

        cn = new Connector().connectDatabase();
        ps = cn.prepareStatement(mySql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if(!rs.next()){
            throw new UserNotFoundException(email);
        }
        user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString( 2));
        user.setLastName(rs.getString( 3));
        user.setEmail(rs.getString( 4));
        user.setDateOfBirth(rs.getDate( 5).toLocalDate());
        user.setPhone(rs.getString( 6));
        user.setGitHubProfile(rs.getString( 7));
        user.setUpdatedAt(rs.getTimestamp(8) != null ? rs.getTimestamp(8).toLocalDateTime() : null);
        user.setDeletedAt(rs.getTimestamp(9) != null ? rs.getTimestamp(9).toLocalDateTime() : null);
        rs.close();
        ps.close();
        cn.close();

        return user;

    }

    @Override
    public int count() throws Exception {

        int i;
        ps = cn.prepareStatement("SELECT count(id) FROM USER");
        rs = ps.executeQuery();
        i = rs.getInt(0);
        rs.close();
        ps.close();
        return i;
    }

    /*
    The method allows the user to find and to list users name.
    String nameForSearching: parameter Name can be partially or fully informed.
    int QuantityToList: parameter refers to the number of elements to be returned
    int maxQuantity: is the maximum number of user allowed to be shown
    defined.
     */
    @Override
    public List<User> list(boolean active, int limitToList, String name) throws Exception {

        String mySql = "SELECT * FROM user WHERE 1 = 1";

        if(limitToList > 50){
            throw new IllegalArgumentException("The maximum users requests must be 50");
        }

        List<User> listOfUser = null;

        if(active) {
            mySql = mySql.concat(" AND deletedAt IS NULL");
        }

        if(name.length() > 0) {
            mySql = mySql.concat(" AND name LIKE '%?%'");
            ps.setString(1, name);
        }

        ps = cn.prepareStatement(mySql);
        rs = ps.executeQuery();
        while (rs.next()) {
            listOfUser.add (read(rs.getInt(0)));
        }
        rs.close();
        ps.close();
        cn.close();

        if(listOfUser.size() > limitToList){
            throw new IllegalArgumentException();
        }

        return listOfUser;

    }

    @Override
    public boolean findUser(int idUser) throws Exception {

        final String mySql = "SELECT id FROM user WHERE id = ?";

        cn = new Connector().connectDatabase();
        ps = cn.prepareStatement(mySql);
        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        if(!rs.next()){
            throw new UserNotFoundException(idUser);
        }
        rs.close();
        ps.close();
        cn.close();

        return true;
    }
}
