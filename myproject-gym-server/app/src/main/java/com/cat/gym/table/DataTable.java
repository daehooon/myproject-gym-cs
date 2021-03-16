package com.cat.gym.table;

import com.cat.util.Request;
import com.cat.util.Response;

public interface DataTable {
  void service(Request request, Response response) throws Exception;
}
