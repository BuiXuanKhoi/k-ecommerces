package com.ecommerce.customerservice.services;

import org.example.proto.generated.TopUpMessage;
import org.example.proto.generated.WithDrawMessage;

public interface FinanceService {

    TopUpMessage topUp(TopUpMessage message);

    WithDrawMessage withDraw(WithDrawMessage message);
}
