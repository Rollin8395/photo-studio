import { useEffect, useState } from "react";
import API from "../api/api";

function OrdersPage() {

  const [orders, setOrders] = useState([]);

  useEffect(() => {

    API.get("/orders")
      .then(res => setOrders(res.data));

  }, []);

  return (

    <div>

      <h2>Orders</h2>

      <ul>

        {orders.map(order => (
          <li key={order.id}>
            {order.orderCode} — {order.status}
          </li>
        ))}

      </ul>

    </div>

  );
}

export default OrdersPage;