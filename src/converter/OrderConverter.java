package converter;

import model.Order;
import dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    BookConverter converter = new BookConverter();

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.newBuilder()
                .id(order.getId())
                .user(order.getUser())
                .price(order.getPrice())
                .status(order.getStatus())
                .books(converter.converterBookList(order.getBooks()))
                .build();
    }

    public List<OrderDTO> converterListOrderToOrderDTO(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(orderToOrderDTO(order));
        }
        return orderDTOList;
    }
}