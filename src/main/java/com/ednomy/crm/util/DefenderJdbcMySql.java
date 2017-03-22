package com.ednomy.crm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdContentDataEntity;

@Component
public class DefenderJdbcMySql {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(EdContentDataEntity edContentDataEntity) {

		String sql = "INSERT INTO CUSTOMER "
				+ "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, edContentDataEntity.getId());
			ps.setString(2, edContentDataEntity.getCategory1());
			ps.setString(3, edContentDataEntity.getCategory2());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public EdContentDataEntity findByEdContentDataEntityId(Long custId) {

		String sql = "SELECT * FROM `biz_content_data` WHERE data_id = ?";

		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, custId);
			EdContentDataEntity edContentDataEntity = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				edContentDataEntity = new EdContentDataEntity();
				edContentDataEntity.setId(rs.getLong("data_id"));
				edContentDataEntity.setContent(rs.getString("data_content"));
				edContentDataEntity.setSt1(rs.getString("st1"));
			}

			rs.close();
			ps.close();

			return edContentDataEntity;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<EdContentDataEntity> selectRecordsFromDbUserTable() throws SQLException, IllegalArgumentException, IllegalAccessException{
		
		List<EdContentDataEntity> edContentDataEntities = new ArrayList<EdContentDataEntity>();
		
		String sql = "SELECT * FROM `biz_content_data`";
		Connection connection = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			
			statement = connection.createStatement();

			System.out.println(sql);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				EdContentDataEntity edContentDataEntity = new EdContentDataEntity();
				
				Class clazz = null;
				try {
					clazz = Class.forName("com.ednomy.crm.entity.EdContentDataEntity");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Field[] fields = clazz.getDeclaredFields();
				
				for (Field field : fields) {
					
					Annotation [] annotations  = field.getAnnotations();
					for (Annotation annotation : annotations) {
						if (annotation instanceof Column) {
							Column column = (Column) annotation;
							field.set(field.getName(), rs.getObject(column.name()));
						}
					}
				}
				
				
				edContentDataEntity.setId(rs.getLong("data_id"));
				edContentDataEntity.setContent(rs.getString("data_content"));
				edContentDataEntity.setSt1(rs.getString("st1"));
				edContentDataEntities.add(edContentDataEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
		
		return edContentDataEntities;

	}
}