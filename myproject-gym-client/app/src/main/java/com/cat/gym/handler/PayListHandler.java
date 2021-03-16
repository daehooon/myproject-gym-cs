package com.cat.gym.handler;

import java.util.Iterator;
import java.util.List;
import com.cat.gym.domain.Pay;

public class PayListHandler extends AbstractPayHandler {

  public PayListHandler(List<Pay> payList) {
    super(payList);
  }

  @Override
  public void service() {
    System.out.println("[결제/예약 목록]");
    System.out.println();

    Iterator<Pay> iterator = payList.iterator();

    while (iterator.hasNext()) {
      Pay p = iterator.next();
      System.out.printf("%s %s %s\n",
          p.getId(), getSelectLabel(p.getSelect()), p.getStartDate());
      System.out.println();
    }
  }

}
