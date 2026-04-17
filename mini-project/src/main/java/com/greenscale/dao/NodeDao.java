package com.greenscale.dao;

import com.greenscale.db.DatabaseConnection;
import com.greenscale.model.NodeBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodeDao {

    public List<NodeBean> getAllNodes() throws SQLException {
        List<NodeBean> nodes = new ArrayList<>();
        String sql = "SELECT node_id, hostname, specs, status FROM nodes ORDER BY node_id";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NodeBean n = new NodeBean(
                        rs.getInt("node_id"),
                        rs.getString("hostname"),
                        rs.getString("specs"),
                        rs.getString("status")
                );
                nodes.add(n);
            }
        }
        return nodes;
    }

    public boolean allocateNode(int nodeId) throws SQLException {
        String sql = "UPDATE nodes SET status = ? WHERE node_id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "In-Use");
            ps.setInt(2, nodeId);
            int updated = ps.executeUpdate();
            return updated > 0;
        }
    }
}
