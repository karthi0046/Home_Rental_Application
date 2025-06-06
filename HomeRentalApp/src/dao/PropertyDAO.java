package dao;

import model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PropertyDAO {

    String url = "jdbc:mysql://localhost:3306/homerentalapp";
    String user = "root";
    String pass = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    //addProperties
    public boolean addProperty(Property property) throws SQLException {
        String query = "insert into properties(user_id, title, description, address, rent, rooms, is_available) values (?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, property.getUserId());
        ps.setString(2, property.getTitle());
        ps.setString(3, property.getDescription());
        ps.setString(4, property.getAddress());
        ps.setDouble(5, property.getRent());
        ps.setInt(6, property.getRooms());
        ps.setBoolean(7, property.isAvailable());

        int row = ps.executeUpdate();
        if (row > 0) {
            return true;
        } else return false;
    }

    //getPropertyByPropertyId(int id)
    public Property getPropertyByPropertyId(int property_id) throws SQLException {

        Property property = null;

        String query = "select * from properties where  property_id = ? ";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, property_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            property = new Property(rs.getInt("property_id"), rs.getInt("user_id"),
                    rs.getString("title"), rs.getString("description"), rs.getString("address"),
                    rs.getDouble("rent"), rs.getInt("rooms"), rs.getBoolean("is_available"));
        }
        return property;
    }

    //getPropertiesByOwnerId(int ownerId)
    public List<Property> getPropertiesByOwnerId(int userId) throws SQLException {

        List<Property> propertyList = new ArrayList<>();

        String query = "select * from properties where  user_id = ? ";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Property property = new Property(rs.getInt("property_id"), rs.getInt("user_id"),
                    rs.getString("title"), rs.getString("description"), rs.getString("address"),
                    rs.getDouble("rent"), rs.getInt("rooms"), rs.getBoolean("is_available"));
            propertyList.add(property);
        }
        return propertyList;
    }

    //getAllAvailableProperties()
    public List<Property> getAllAvailableProperties() throws SQLException {
        String query = "select * from properties";
        List<Property> propertyList = new ArrayList<>();
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Property property = new Property(rs.getInt("property_id"), rs.getInt("user_id"),
                    rs.getString("title"), rs.getString("description"), rs.getString("address"),
                    rs.getDouble("rent"), rs.getInt("rooms"), rs.getBoolean("is_available"));
            propertyList.add(property);
        }
        return propertyList;
    }

    //updateProperty(Property property)
    public void updateProperty(Property property) throws SQLException {

        String query = "update properties SET title =  ?, description = ?, address = ?, rent = ?, rooms = ?, is_available = ?  WHERE property_id = ?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, property.getTitle());
        ps.setString(2, property.getDescription());
        ps.setString(3, property.getAddress());
        ps.setDouble(4, property.getRent());
        ps.setInt(5, property.getRooms());
        ps.setBoolean(6, property.isAvailable());
        ps.setInt(7, property.getPropertyId());
        int row = ps.executeUpdate();

        if (row > 0) {
            System.out.println("Update Successfully!");
        } else {
            System.out.println("Update Failed");
        }
    }

    //deleteProperty(int propertyId)

    public void deleteProperty(int propertyId) throws SQLException {

        String query = "delete from properties where  property_id = ?";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, propertyId);
        int row = ps.executeUpdate();
        if (row > 0) {
            System.out.println("Deleted Successfully!");
        } else {
            System.out.println("Delete Failed");
        }
    }

    public boolean markPropertyAsUnavailable(int propertyId) throws SQLException {
        String query = "update properties set is_available = false  where property_id = ?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, propertyId);
        return ps.executeUpdate() > 0;
    }
}
