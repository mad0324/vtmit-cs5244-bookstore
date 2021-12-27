package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;
    private OrderDao orderDao;
    private CustomerDao customerDao;
    private LineItemDao lineItemDao;
    private final Random random = new Random();

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.getCustomerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }

    private Date getDate(String monthString, String yearString) {
        return new Date(Integer.parseInt(yearString) - 1900, Integer.parseInt(monthString), 0);
    }

    private int generateConfirmationNumber() {
        return random.nextInt(999999999);
    }

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection, customerOrderId,
                        item.getBookId(), item.getQuantity());
            }
            connection.commit();
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

        validateCustomer(customerForm);
        validateCart(cart);

        try (Connection connection = JdbcUtils.getConnection()) {
            Date date = getDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    date, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        }
    }


    private void validateCustomer(CustomerForm customerForm) {

        String name = customerForm.getName();

        if (name == null || name.equals("") || name.length() < 4 || name.length() > 45) {
            throw new ApiException.InvalidParameter("Invalid name field");
        }

        String address = customerForm.getAddress();

        if (address == null || address.equals("") || address.length() < 4 || address.length() > 45) {
            throw new ApiException.InvalidParameter("Invalid address field");
        }

        String phone = customerForm.getPhone();

        if (phone == null || phone.equals("") || phone.replaceAll("[\\s)(-]", "").length() != 10) {
            throw new ApiException.InvalidParameter("Invalid phone field");
        }

        String email = customerForm.getEmail();

        if (email == null || email.equals("") || email.contains(" ") || !email.contains("@") || email.endsWith(".") || doesNotLookLikeAnEmail(email)) {
            throw new ApiException.InvalidParameter("Invalid email field");
        }

        String ccNumber = customerForm.getCcNumber();

        if (ccNumber == null || ccNumber.equals("") || ccNumber.replaceAll("[\\s-]", "").length() < 14 || ccNumber.replaceAll("[\\s-]", "").length() > 16) {
            throw new ApiException.InvalidParameter("Invalid ccNumber field");
        }

        String ccExpiryMonth = customerForm.getCcExpiryMonth();
        String ccExpiryYear = customerForm.getCcExpiryYear();

        if (ccExpiryMonth == null || ccExpiryMonth.equals("") || Integer.parseInt(ccExpiryMonth) < 1 || Integer.parseInt(ccExpiryMonth) > 12 || ccExpiryYear == null || ccExpiryYear.equals("") || expiryDateIsInvalid(ccExpiryMonth, ccExpiryYear)) {
            throw new ApiException.InvalidParameter("Invalid expiry date");

        }
    }

    private static Pattern SIMPLE_EMAIL_REGEX = Pattern.compile("^\\S+@\\S+$");

    private boolean doesNotLookLikeAnEmail(String email) {
        return !SIMPLE_EMAIL_REGEX.matcher(email).matches();
    }

    private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {

        return YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth)).isBefore(YearMonth.now());

    }

    private void validateCart(ShoppingCart cart) {

        if (cart.getItems().size() <= 0) {
            throw new ApiException.InvalidParameter("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            if (item.getQuantity() < 1 || item.getQuantity() > 99) {
                throw new ApiException.InvalidParameter("Invalid quantity");
            }
            Book databaseBook = bookDao.findByBookId(item.getBookId());
            if (databaseBook.getPrice() != item.getBookForm().getPrice()) {
                throw new ApiException.InvalidParameter("Invalid book price");
            }
            if (databaseBook.getCategoryId() != item.getBookForm().getCategoryId()) {
                throw new ApiException.InvalidParameter("Invalid book category");
            }
        });
    }
}
