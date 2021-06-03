package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;


public class OrderItemsController implements CrudController<OrderItems> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderItemsDAO orderItemsDAO;
	private Utils utils;
	
	public OrderItemsController(OrderItemsDAO orderItemsDAO, Utils utils) {
		super();
		this.orderItemsDAO = orderItemsDAO;
		this.utils = utils;
	}

	@Override
	public List<OrderItems> readAll() {
		List<OrderItems> orderItems = orderItemsDAO.readAll();
		for (OrderItems orderItems1 : orderItems) {
			LOGGER.info(orderItems1);
		}
		return null;
	}

	@Override
	public OrderItems create() {
		LOGGER.info("Please enter an id for the order items");
		Long orderItemsId = utils.getLong();
		LOGGER.info("Please enter an order id for the order items");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please enter an items id for the order items");
		Long fkItemId = utils.getLong();
		OrderItems orderItems = orderItemsDAO.create(new OrderItems(orderItemsId, fkOrderId, fkItemId));
		LOGGER.info("Order items created");
		return orderItems;
	}

	@Override
	public OrderItems update() {
		LOGGER.info("Please enter the id of the order items you would like to update");
		Long orderItemsId = utils.getLong();
		LOGGER.info("Please enter the id of the Order you would like to update");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please enter the id of the item you would like to update");
		Long fkItemId = utils.getLong();
		OrderItems orderItems = orderItemsDAO.update(new OrderItems(orderItemsId, fkOrderId, fkItemId));
		LOGGER.info("Order Updated");
		return orderItems;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order items you would like to delete");
		Long orderItemsId = utils.getLong();
		return orderItemsDAO.delete(orderItemsId);
	}
	
	

	
	

}
