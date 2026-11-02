package com.example.complaint;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintRepository {

    private final JdbcTemplate jdbcTemplate;

    public ComplaintRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Read all complaints from MySQL.
    public List<Complaint> findAll() {
        String sql = "SELECT id, title, description, status, priority "
                   + "FROM complaints ORDER BY id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Complaint(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getString("priority")
            )
        );
    }

    // Insert a new complaint into MySQL.
    public void save(Complaint complaint) {
        String sql = "INSERT INTO complaints "
                   + "(title, description, status, priority) "
                   + "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                complaint.getTitle(),
                complaint.getDescription(),
                "Pending",
                complaint.getPriority());
    }

    // Update complaint status.
    public void updateStatus(int id, String status) {
        String sql = "UPDATE complaints SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    // Update complaint priority.
    public void updatePriority(int id, String priority) {
        String sql = "UPDATE complaints SET priority = ? WHERE id = ?";
        jdbcTemplate.update(sql, priority, id);
    }

    // Return only pending complaints.
    public List<Complaint> findPending() {
        String sql = "SELECT id, title, description, status, priority "
                   + "FROM complaints WHERE status = ? ORDER BY id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Complaint(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getString("priority")
            ), "Pending"
        );
    }
}
