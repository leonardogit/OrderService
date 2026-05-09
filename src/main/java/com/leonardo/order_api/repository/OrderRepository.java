package com.leonardo.order_api.repository;

import com.leonardo.order_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/*
This is VERY powerful.

By extending: JpaRepository
Spring automatically creates:

save()
findAll()
findById()
delete()
update()

WITHOUT writing SQL.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
