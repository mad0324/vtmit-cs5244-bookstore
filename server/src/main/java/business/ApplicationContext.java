
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.order.*;

public class ApplicationContext {

    private final BookDao bookDao;
    private final CategoryDao categoryDao;
    private final OrderService orderService;
    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private final LineItemDao lineItemDao;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        bookDao = new BookDaoJdbc();
        categoryDao = new CategoryDaoJdbc();

        orderDao = new OrderDaoJdbc();
        customerDao = new CustomerDaoJdbc();
        lineItemDao = new LineItemDaoJdbc();

        orderService = new DefaultOrderService();
        ((DefaultOrderService) orderService).setBookDao(bookDao);
        ((DefaultOrderService) orderService).setCustomerDao(customerDao);
        ((DefaultOrderService) orderService).setOrderDao(orderDao);
        ((DefaultOrderService) orderService).setLineItemDao(lineItemDao);
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }
}
