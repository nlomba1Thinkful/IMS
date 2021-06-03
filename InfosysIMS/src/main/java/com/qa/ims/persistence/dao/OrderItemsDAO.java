package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO implements Dao<OrderItems> {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<OrderItems> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderItems");){
			List<OrderItems> orderItems = new ArrayList<>();
			while (resultSet.next()) {
				orderItems.add(modelFromResultSet(resultSet));
			}
			return orderItems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public OrderItems read(Long orderItemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderItems WHERE id = ?");){
			statement.setLong(1, orderItemsId);
			try (ResultSet resultSet = statement.executeQuery();){
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public OrderItems readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderItems ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			 LOGGER.debug(e); 
			 LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems create(OrderItems orderItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderItems(orderItemId, fkOrderId, fkItemId) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderItems.getOrderItemsId());
			statement.setLong(2, orderItems.getFkOrderId());
			statement.setLong(3, orderItems.getFkItemId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) { 
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems update(OrderItems orderItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET orderId = ?, fkCustomerId = ? id = ?");) {
			statement.setLong(1, orderItems.getOrderItemsId());
			statement.setLong(2, orderItems.getFkOrderId());
			statement.setLong(3, orderItems.getFkItemId());
			return read(orderItems.getOrderItemsId());
		} catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage()); 
		}
		return null;
	}

	@Override
	public int delete(long orderItemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderItems WHERE id = ?");) {
			statement.setLong(1, orderItemsId);
			return statement.executeUpdate();
		} catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderItemsId = resultSet.getLong("orderItemsId");
		Long fkOrderId = resultSet.getLong("fkOrderId");
		Long fkItemsId = resultSet.getLong("fkItemsId");
		return new OrderItems(orderItemsId, fkOrderId, fkItemsId);
	}
	
	

}
