import { useEffect, useState } from "react";

export default function OrdersPage() {

  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/orders")
      .then((res) => res.json())
      .then((data) => {
        setOrders(data);
      })
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="orders-container">
      <h1>Your Orders</h1>

      {orders.length === 0 ? (
        <p>No orders yet.</p>
      ) : (
        <div className="orders-grid">
          {orders.map((order) => (
            <div key={order.id} className="order-card">

              <img
                src={`http://localhost:8080/api/uploads/${order.imageName}`}
                alt="order"
              />

              <p><b>Name:</b> {order.customerName}</p>
              <p><b>Size:</b> {order.printSize}</p>
              <p><b>Quantity:</b> {order.quantity}</p>
              <p className="status">{order.status}</p>

            </div>
          ))}
        </div>
      )}
    </div>
  );
}